package dev.jaderss.algorithms.binarysearch;

import static dev.jaderss.algorithms.Samples.ints;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SearchRotatedSortedArrayTest {

    @ParameterizedTest
    @CsvSource({"'4 5 6 7 0 1 2', 0, 4", "'4 5 6 7 0 1 2', 4, 0", "'1', 1, 0", "'3 1', 1, 1", "'5 1 3', 3, 2"})
    void findsTheIndexOfThePresentTarget(String numbers, int target, int expected) {
        assertThat(SearchRotatedSortedArray.indexOf(ints(numbers), target)).hasValue(expected);
    }

    @ParameterizedTest
    @CsvSource({"'4 5 6 7 0 1 2', 3", "'1', 0", "'', 5"})
    void findsNothingForAnAbsentTarget(String numbers, int target) {
        assertThat(SearchRotatedSortedArray.indexOf(ints(numbers), target)).isEmpty();
    }
}
