package dev.jaderss.algorithms.tree;

public final class MaximumDepth {

    private MaximumDepth() {}

    public static int of(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(of(root.left()), of(root.right()));
    }
}
