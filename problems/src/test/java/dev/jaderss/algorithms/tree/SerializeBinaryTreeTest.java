package dev.jaderss.algorithms.tree;

import static dev.jaderss.algorithms.tree.TreeNode.leaf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SerializeBinaryTreeTest {

    @Test
    void readsLevelOrderWithGapsForMissingChildren() {
        assertThat(SerializeBinaryTree.deserialize("[1,2,3,null,null,4,5]"))
                .isEqualTo(new TreeNode(1, leaf(2), new TreeNode(3, leaf(4), leaf(5))));
    }

    @ParameterizedTest
    @ValueSource(strings = {"[1,2,3,null,null,4,5]", "[]", "[1]", "[1,null,2,null,3]", "[-2147483648,2147483647]"})
    void writesBackTheSameText(String levelOrder) {
        assertThat(SerializeBinaryTree.serialize(SerializeBinaryTree.deserialize(levelOrder)))
                .isEqualTo(levelOrder);
    }

    @Test
    void emptyTreeIsAnEmptyList() {
        assertThat(SerializeBinaryTree.serialize(null)).isEqualTo("[]");
        assertThat(SerializeBinaryTree.deserialize(" [ ] ")).isNull();
        assertThat(SerializeBinaryTree.deserialize("[null]")).isNull();
    }

    @Test
    void toleratesWhitespaceAroundValues() {
        assertThat(SerializeBinaryTree.deserialize("[ 1 , null , 2 ]")).isEqualTo(new TreeNode(1, null, leaf(2)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2", "[null,1]", "[1,x]", "[1,null,null,2]"})
    void rejectsMalformedText(String text) {
        assertThatIllegalArgumentException().isThrownBy(() -> SerializeBinaryTree.deserialize(text));
    }
}
