package dev.jaderss.algorithms.slidingwindow;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MinimumWindowSubstringTest {

    @ParameterizedTest
    @CsvSource({
        "ADOBECODEBANC, ABC, BANC",
        "a, a, a",
        "a, aa, ''",
        "aa, aa, aa",
        "ab, '', ''",
        "'', a, ''",
        "bba, ab, ba"
    })
    void findsTheShortestWindowContainingEveryRequiredCharacter(String text, String required, String expected) {
        assertThat(MinimumWindowSubstring.of(text, required)).isEqualTo(expected);
    }
}
