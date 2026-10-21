package dev.jaderss.algorithms.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public final class SerializeBinaryTree {

    private static final String ABSENT = "null";

    private SerializeBinaryTree() {}

    public static String serialize(TreeNode root) {
        List<TreeNode> levelOrder = new ArrayList<>();
        levelOrder.add(root);
        List<String> tokens = new ArrayList<>();
        for (int index = 0; index < levelOrder.size(); index++) {
            TreeNode node = levelOrder.get(index);
            if (node == null) {
                tokens.add(ABSENT);
            } else {
                tokens.add(String.valueOf(node.value()));
                levelOrder.add(node.left());
                levelOrder.add(node.right());
            }
        }
        while (!tokens.isEmpty() && tokens.getLast().equals(ABSENT)) {
            tokens.removeLast();
        }
        return "[" + String.join(",", tokens) + "]";
    }

    public static TreeNode deserialize(String text) {
        String trimmed = text.strip();
        if (!trimmed.startsWith("[") || !trimmed.endsWith("]")) {
            throw new IllegalArgumentException("expected a bracketed list, got " + text);
        }
        String body = trimmed.substring(1, trimmed.length() - 1).strip();
        if (body.isEmpty()) {
            return null;
        }
        Integer[] values = Arrays.stream(body.split(","))
                .map(String::strip)
                .map(token -> token.equals(ABSENT) ? null : Integer.valueOf(token))
                .toArray(Integer[]::new);
        return build(values);
    }

    private static TreeNode build(Integer[] values) {
        int[] left = new int[values.length];
        int[] right = new int[values.length];
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);
        Deque<Integer> parents = new ArrayDeque<>();
        if (values[0] != null) {
            parents.add(0);
        }
        for (int child = 1; child < values.length; child++) {
            if (parents.isEmpty()) {
                throw new IllegalArgumentException("value at position " + child + " has no parent");
            }
            int parent = parents.getFirst();
            if (left[parent] < 0) {
                left[parent] = child;
            } else {
                right[parent] = child;
                parents.removeFirst();
            }
            if (values[child] != null) {
                parents.addLast(child);
            }
        }
        return node(values, left, right, 0);
    }

    private static TreeNode node(Integer[] values, int[] left, int[] right, int index) {
        if (index < 0 || values[index] == null) {
            return null;
        }
        return new TreeNode(
                values[index], node(values, left, right, left[index]), node(values, left, right, right[index]));
    }
}
