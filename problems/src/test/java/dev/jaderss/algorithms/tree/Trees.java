package dev.jaderss.algorithms.tree;

import java.util.List;
import java.util.Random;

final class Trees {

    private Trees() {}

    static TreeNode tree(String levelOrder) {
        return SerializeBinaryTree.deserialize(levelOrder);
    }

    static String levelOrder(TreeNode root) {
        return SerializeBinaryTree.serialize(root);
    }

    static TreeNode randomShape(List<Integer> values, Random random) {
        TreeNode root = null;
        for (int value : values) {
            root = insertAtRandomLeaf(root, value, random);
        }
        return root;
    }

    static TreeNode searchTree(List<Integer> values) {
        TreeNode root = null;
        for (int value : values) {
            root = insertInOrder(root, value);
        }
        return root;
    }

    private static TreeNode insertAtRandomLeaf(TreeNode node, int value, Random random) {
        if (node == null) {
            return TreeNode.leaf(value);
        }
        return random.nextBoolean()
                ? new TreeNode(node.value(), insertAtRandomLeaf(node.left(), value, random), node.right())
                : new TreeNode(node.value(), node.left(), insertAtRandomLeaf(node.right(), value, random));
    }

    private static TreeNode insertInOrder(TreeNode node, int value) {
        if (node == null) {
            return TreeNode.leaf(value);
        }
        if (value < node.value()) {
            return new TreeNode(node.value(), insertInOrder(node.left(), value), node.right());
        }
        return value > node.value()
                ? new TreeNode(node.value(), node.left(), insertInOrder(node.right(), value))
                : node;
    }
}
