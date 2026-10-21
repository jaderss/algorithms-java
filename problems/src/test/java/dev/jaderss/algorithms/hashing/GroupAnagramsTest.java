package dev.jaderss.algorithms.hashing;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class GroupAnagramsTest {

    @Test
    void groupsWordsWithTheSameLettersInFirstSeenOrder() {
        assertThat(GroupAnagrams.group(List.of("eat", "tea", "tan", "ate", "nat", "bat")))
                .containsExactly(List.of("eat", "tea", "ate"), List.of("tan", "nat"), List.of("bat"));
    }

    @Test
    void emptyWordIsItsOwnGroup() {
        assertThat(GroupAnagrams.group(List.of(""))).containsExactly(List.of(""));
    }

    @Test
    void noWordsMeansNoGroups() {
        assertThat(GroupAnagrams.group(List.of())).isEmpty();
    }
}
