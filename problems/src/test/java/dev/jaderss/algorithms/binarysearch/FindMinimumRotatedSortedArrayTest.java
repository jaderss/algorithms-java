package dev.jaderss.algorithms.binarysearch;

import static dev.jaderss.algorithms.Samples.ints;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FindMinimumRotatedSortedArrayTest {

    @ParameterizedTest
    @CsvSource({"'3 4 5 1 2', 1", "'4 5 6 7 0 1 2', 0", "'11 13 15 17', 11", "'1', 1", "'2 1', 1"})
    void findsTheSmallestValue(String numbers, int expected) {
        assertThat(FindMinimumRotatedSortedArray.min(ints(numbers))).isEqualTo(expected);
    }

    @Test
    void rejectsAnEmptyArray() {
        assertThatIllegalArgumentException().isThrownBy(() -> FindMinimumRotatedSortedArray.min(new int[0]));
    }
}
