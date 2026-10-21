package dev.jaderss.algorithms.trie;

import static dev.jaderss.algorithms.Samples.grid;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class WordSearchTest {

    @Test
    void findsWordsTracedThroughAdjacentCells() {
        char[][] board = grid("oaan", "etae", "ihkr", "iflv");
        assertThat(WordSearch.find(board, List.of("oath", "pea", "eat", "rain")))
                .containsExactlyInAnyOrder("eat", "oath");
    }

    @Test
    void neverReusesACellWithinOneWord() {
        assertThat(WordSearch.find(grid("ab", "cd"), List.of("abcb"))).isEmpty();
        assertThat(WordSearch.find(grid("a"), List.of("aa", "a"))).containsExactly("a");
    }

    @Test
    void reportsAWordOnceEvenWhenItAppearsTwice() {
        assertThat(WordSearch.find(grid("aba"), List.of("a", "ab"))).containsExactlyInAnyOrder("a", "ab");
    }
}
