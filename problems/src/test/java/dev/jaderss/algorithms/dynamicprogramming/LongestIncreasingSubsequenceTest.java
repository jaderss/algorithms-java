package dev.jaderss.algorithms.dynamicprogramming;

import static dev.jaderss.algorithms.Samples.ints;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LongestIncreasingSubsequenceTest {

    @ParameterizedTest
    @CsvSource({"'10 9 2 5 3 7 101 18', 4", "'0 1 0 3 2 3', 4", "'7 7 7 7 7 7 7', 1", "'', 0", "'5 4 3', 1"})
    void measuresTheLongestStrictlyIncreasingSubsequence(String numbers, int expected) {
        assertThat(LongestIncreasingSubsequence.length(ints(numbers))).isEqualTo(expected);
    }
}
