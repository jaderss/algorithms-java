package dev.jaderss.algorithms.hashing;

import static dev.jaderss.algorithms.Samples.ints;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LongestConsecutiveSequenceTest {

    @ParameterizedTest
    @CsvSource({
        "'100 4 200 1 3 2', 4",
        "'0 3 7 2 5 8 4 6 0 1', 9",
        "'1 0 1 2', 3",
        "'', 0",
        "'-2147483648 2147483647', 1",
        "'2147483646 2147483647', 2"
    })
    void measuresTheLongestRunOfConsecutiveValues(String numbers, int expected) {
        assertThat(LongestConsecutiveSequence.length(ints(numbers))).isEqualTo(expected);
    }
}
