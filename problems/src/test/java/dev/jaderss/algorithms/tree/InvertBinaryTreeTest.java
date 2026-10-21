package dev.jaderss.algorithms.tree;

import static dev.jaderss.algorithms.tree.Trees.levelOrder;
import static dev.jaderss.algorithms.tree.Trees.tree;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class InvertBinaryTreeTest {

    @ParameterizedTest
    @CsvSource(
            delimiter = '|',
            value = {"[4,2,7,1,3,6,9] | [4,7,2,9,6,3,1]", "[2,1,3] | [2,3,1]", "[] | []", "[1,2] | [1,null,2]"})
    void mirrorsEveryLevel(String original, String mirrored) {
        assertThat(levelOrder(InvertBinaryTree.invert(tree(original)))).isEqualTo(mirrored);
    }
}
