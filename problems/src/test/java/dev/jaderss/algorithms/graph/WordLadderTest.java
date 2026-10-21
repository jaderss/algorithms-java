package dev.jaderss.algorithms.graph;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class WordLadderTest {

    private static final List<String> WORDS = List.of("hot", "dot", "dog", "lot", "log", "cog");

    @Test
    void countsTheWordsInTheShortestTransformation() {
        assertThat(WordLadder.length("hit", "cog", WORDS)).hasValue(5);
        assertThat(WordLadder.length("a", "c", List.of("a", "b", "c"))).hasValue(2);
        assertThat(WordLadder.length("hot", "hot", List.of("hot"))).hasValue(1);
    }

    @Test
    void findsNothingWhenTheEndIsMissingOrUnreachable() {
        assertThat(WordLadder.length("hit", "cog", List.of("hot", "dot", "dog", "lot", "log")))
                .isEmpty();
        assertThat(WordLadder.length("hit", "xyz", List.of("hot", "xyz"))).isEmpty();
    }
}
