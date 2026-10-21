package dev.jaderss.algorithms.trie;

import java.util.HashMap;
import java.util.Map;

public final class Trie {

    static final class Node {
        final Map<Character, Node> children = new HashMap<>();
        String word;
    }

    final Node root = new Node();

    public void insert(String word) {
        Node node = root;
        for (char letter : word.toCharArray()) {
            node = node.children.computeIfAbsent(letter, _ -> new Node());
        }
        node.word = word;
    }

    public boolean contains(String word) {
        Node node = find(word);
        return node != null && node.word != null;
    }

    public boolean startsWith(String prefix) {
        Node node = find(prefix);
        return node != null && (node.word != null || !node.children.isEmpty());
    }

    private Node find(String text) {
        Node node = root;
        for (int index = 0; index < text.length() && node != null; index++) {
            node = node.children.get(text.charAt(index));
        }
        return node;
    }
}
