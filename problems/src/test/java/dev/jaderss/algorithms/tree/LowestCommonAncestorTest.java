package dev.jaderss.algorithms.tree;

import static dev.jaderss.algorithms.tree.Trees.tree;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LowestCommonAncestorTest {

    private static final TreeNode ROOT = tree("[3,5,1,6,2,0,8,null,null,7,4]");

    @ParameterizedTest
    @CsvSource({"5, 1, 3", "5, 4, 5", "7, 8, 3", "6, 6, 6", "7, 4, 2"})
    void findsTheDeepestNodeAboveBothValues(int first, int second, int ancestor) {
        assertThat(LowestCommonAncestor.of(ROOT, first, second))
                .map(TreeNode::value)
                .contains(ancestor);
    }

    @ParameterizedTest
    @CsvSource({"5, 42", "42, 43"})
    void findsNothingWhenAValueIsMissing(int first, int second) {
        assertThat(LowestCommonAncestor.of(ROOT, first, second)).isEmpty();
        assertThat(LowestCommonAncestor.of(null, first, second)).isEmpty();
    }
}
