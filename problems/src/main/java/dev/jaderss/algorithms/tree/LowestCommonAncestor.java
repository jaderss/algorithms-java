package dev.jaderss.algorithms.tree;

import java.util.Optional;

public final class LowestCommonAncestor {

    private record Search(int found, TreeNode ancestor) {}

    private LowestCommonAncestor() {}

    public static Optional<TreeNode> of(TreeNode root, int first, int second) {
        return Optional.ofNullable(search(root, first, second).ancestor());
    }

    private static Search search(TreeNode node, int first, int second) {
        if (node == null) {
            return new Search(0, null);
        }
        Search left = search(node.left(), first, second);
        if (left.ancestor() != null) {
            return left;
        }
        Search right = search(node.right(), first, second);
        if (right.ancestor() != null) {
            return right;
        }
        int found = left.found() + right.found() + matches(node, first) + matches(node, second);
        return new Search(found, found == 2 ? node : null);
    }

    private static int matches(TreeNode node, int value) {
        return node.value() == value ? 1 : 0;
    }
}
