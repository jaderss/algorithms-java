package dev.jaderss.algorithms.tree;

public final class ValidateBst {

    private ValidateBst() {}

    public static boolean isValid(TreeNode root) {
        return isWithin(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean isWithin(TreeNode node, long lowerExclusive, long upperExclusive) {
        return node == null
                || node.value() > lowerExclusive
                        && node.value() < upperExclusive
                        && isWithin(node.left(), lowerExclusive, node.value())
                        && isWithin(node.right(), node.value(), upperExclusive);
    }
}
