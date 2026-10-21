package dev.jaderss.algorithms.trie;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.Chars;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.StringLength;

class TriePropertiesTest {

    @Property
    void trieAnswersLikeScanningTheInsertedWords(
            @ForAll @Size(max = 20) List<@StringLength(max = 5) @Chars({'a', 'b'}) String> words,
            @ForAll @StringLength(max = 5) @Chars({'a', 'b'}) String probe) {
        Trie trie = new Trie();
        words.forEach(trie::insert);
        assertThat(trie.contains(probe)).isEqualTo(words.contains(probe));
        assertThat(trie.startsWith(probe)).isEqualTo(words.stream().anyMatch(word -> word.startsWith(probe)));
    }

    @Property
    void everyWordReadAlongARowIsFound(
            @ForAll @Size(min = 1, max = 4) List<@StringLength(value = 4) @Chars({'a', 'b', 'c'}) String> rows) {
        char[][] board = rows.stream().map(String::toCharArray).toArray(char[][]::new);
        List<String> rowWords = rows.stream().map(row -> row.substring(0, 3)).toList();
        assertThat(WordSearch.find(board, rowWords))
                .containsExactlyInAnyOrderElementsOf(
                        rowWords.stream().distinct().toList());
    }
}
