package dev.jaderss.algorithms.slidingwindow;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LongestSubstringWithoutRepeatingTest {

    @ParameterizedTest
    @CsvSource({"abcabcbb, 3", "bbbbb, 1", "pwwkew, 3", "'', 0", "' ', 1", "abba, 2", "dvdf, 3"})
    void measuresTheLongestRunWithoutARepeatedCharacter(String text, int expected) {
        assertThat(LongestSubstringWithoutRepeating.length(text)).isEqualTo(expected);
    }
}
