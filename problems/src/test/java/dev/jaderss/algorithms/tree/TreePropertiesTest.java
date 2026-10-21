package dev.jaderss.algorithms.tree;

import static dev.jaderss.algorithms.tree.Trees.randomShape;
import static dev.jaderss.algorithms.tree.Trees.searchTree;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.UniqueElements;

class TreePropertiesTest {

    @Property
    void serializingAndDeserializingKeepsTheTree(@ForAll @Size(max = 40) List<Integer> values, @ForAll Random random) {
        TreeNode tree = randomShape(values, random);
        assertThat(SerializeBinaryTree.deserialize(SerializeBinaryTree.serialize(tree)))
                .isEqualTo(tree);
    }

    @Property
    void invertingTwiceRestoresTheTreeAndKeepsItsDepth(
            @ForAll @Size(max = 40) List<Integer> values, @ForAll Random random) {
        TreeNode tree = randomShape(values, random);
        TreeNode inverted = InvertBinaryTree.invert(tree);
        assertThat(InvertBinaryTree.invert(inverted)).isEqualTo(tree);
        assertThat(MaximumDepth.of(inverted)).isEqualTo(MaximumDepth.of(tree));
    }

    @Property
    void treesBuiltByOrderedInsertionAreValidAndInvertingBreaksThem(
            @ForAll @Size(min = 2, max = 40) @UniqueElements List<Integer> values) {
        TreeNode tree = searchTree(values);
        assertThat(ValidateBst.isValid(tree)).isTrue();
        assertThat(ValidateBst.isValid(InvertBinaryTree.invert(tree))).isFalse();
    }

    @Property
    void lowestCommonAncestorIsTheLastSharedNodeOnBothRootPaths(
            @ForAll @Size(min = 1, max = 30) @UniqueElements List<@IntRange(max = 100) Integer> values,
            @ForAll @IntRange(max = 100) int first,
            @ForAll @IntRange(max = 100) int second,
            @ForAll Random random) {
        TreeNode tree = randomShape(values, random);
        List<TreeNode> firstPath = pathTo(tree, first);
        List<TreeNode> secondPath = pathTo(tree, second);
        if (firstPath.isEmpty() || secondPath.isEmpty()) {
            assertThat(LowestCommonAncestor.of(tree, first, second)).isEmpty();
        } else {
            int shared = 0;
            while (shared < Math.min(firstPath.size(), secondPath.size())
                    && firstPath.get(shared) == secondPath.get(shared)) {
                shared++;
            }
            assertThat(LowestCommonAncestor.of(tree, first, second)).containsSame(firstPath.get(shared - 1));
        }
    }

    private static List<TreeNode> pathTo(TreeNode root, int value) {
        List<TreeNode> path = new ArrayList<>();
        return collectPath(root, value, path) ? path : List.of();
    }

    private static boolean collectPath(TreeNode node, int value, List<TreeNode> path) {
        if (node == null) {
            return false;
        }
        path.add(node);
        if (node.value() == value || collectPath(node.left(), value, path) || collectPath(node.right(), value, path)) {
            return true;
        }
        path.removeLast();
        return false;
    }
}
