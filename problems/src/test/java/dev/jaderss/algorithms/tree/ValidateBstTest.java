package dev.jaderss.algorithms.tree;

import static dev.jaderss.algorithms.tree.Trees.tree;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ValidateBstTest {

    @ParameterizedTest
    @ValueSource(strings = {"[2,1,3]", "[]", "[2147483647]", "[-2147483648,null,2147483647]", "[5,3,8,1,4,7,9]"})
    void acceptsTreesOrderedAtEveryNode(String levelOrder) {
        assertThat(ValidateBst.isValid(tree(levelOrder))).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"[5,1,4,null,null,3,6]", "[2,2,2]", "[5,4,6,null,null,3,7]", "[1,1]"})
    void rejectsTreesWithAValueOnTheWrongSideOfAnAncestor(String levelOrder) {
        assertThat(ValidateBst.isValid(tree(levelOrder))).isFalse();
    }
}
