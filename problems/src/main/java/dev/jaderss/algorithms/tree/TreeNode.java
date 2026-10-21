package dev.jaderss.algorithms.tree;

public record TreeNode(int value, TreeNode left, TreeNode right) {

    public static TreeNode leaf(int value) {
        return new TreeNode(value, null, null);
    }
}
