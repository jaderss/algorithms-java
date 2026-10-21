package dev.jaderss.algorithms.tree;

import static dev.jaderss.algorithms.tree.Trees.tree;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MaximumDepthTest {

    @ParameterizedTest
    @CsvSource(
            delimiter = '|',
            value = {"[3,9,20,null,null,15,7] | 3", "[1,null,2] | 2", "[] | 0", "[1] | 1"})
    void countsNodesOnTheLongestRootToLeafPath(String levelOrder, int expected) {
        assertThat(MaximumDepth.of(tree(levelOrder))).isEqualTo(expected);
    }
}
