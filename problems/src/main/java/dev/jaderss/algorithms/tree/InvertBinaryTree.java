package dev.jaderss.algorithms.tree;

public final class InvertBinaryTree {

    private InvertBinaryTree() {}

    public static TreeNode invert(TreeNode root) {
        return root == null ? null : new TreeNode(root.value(), invert(root.right()), invert(root.left()));
    }
}
