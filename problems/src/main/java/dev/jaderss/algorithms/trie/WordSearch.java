package dev.jaderss.algorithms.trie;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public final class WordSearch {

    private final char[][] board;
    private final boolean[][] used;
    private final Set<String> found = new HashSet<>();

    private WordSearch(char[][] board) {
        this.board = board;
        this.used = new boolean[board.length][];
        for (int row = 0; row < board.length; row++) {
            used[row] = new boolean[board[row].length];
        }
    }

    public static Set<String> find(char[][] board, Collection<String> words) {
        Trie trie = new Trie();
        words.forEach(trie::insert);
        WordSearch search = new WordSearch(board);
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                search.visit(row, column, trie.root);
            }
        }
        return Set.copyOf(search.found);
    }

    private void visit(int row, int column, Trie.Node parent) {
        if (row < 0 || row >= board.length || column < 0 || column >= board[row].length || used[row][column]) {
            return;
        }
        Trie.Node node = parent.children.get(board[row][column]);
        if (node == null) {
            return;
        }
        if (node.word != null) {
            found.add(node.word);
        }
        used[row][column] = true;
        visit(row - 1, column, node);
        visit(row + 1, column, node);
        visit(row, column - 1, node);
        visit(row, column + 1, node);
        used[row][column] = false;
    }
}
