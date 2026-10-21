package dev.jaderss.algorithms.trie;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class TrieTest {

    @Test
    void distinguishesWholeWordsFromPrefixes() {
        Trie trie = new Trie();
        trie.insert("apple");
        assertThat(trie.contains("apple")).isTrue();
        assertThat(trie.contains("app")).isFalse();
        assertThat(trie.startsWith("app")).isTrue();
        trie.insert("app");
        assertThat(trie.contains("app")).isTrue();
    }

    @Test
    void emptyTextIsAPrefixOnlyOnceSomethingIsInserted() {
        Trie trie = new Trie();
        assertThat(trie.startsWith("")).isFalse();
        assertThat(trie.contains("")).isFalse();
        trie.insert("");
        assertThat(trie.startsWith("")).isTrue();
        assertThat(trie.contains("")).isTrue();
    }

    @Test
    void unknownTextIsNeitherAWordNorAPrefix() {
        Trie trie = new Trie();
        trie.insert("tree");
        assertThat(trie.contains("trees")).isFalse();
        assertThat(trie.startsWith("trex")).isFalse();
    }
}
