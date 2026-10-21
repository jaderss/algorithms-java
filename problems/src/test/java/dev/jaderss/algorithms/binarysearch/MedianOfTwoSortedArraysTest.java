package dev.jaderss.algorithms.binarysearch;

import static dev.jaderss.algorithms.Samples.ints;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MedianOfTwoSortedArraysTest {

    @ParameterizedTest
    @CsvSource({
        "'1 3', '2', 2.0",
        "'1 2', '3 4', 2.5",
        "'', '1', 1.0",
        "'2', '', 2.0",
        "'1 2 3 4 5', '6', 3.5",
        "'-2147483648', '2147483647', -0.5"
    })
    void findsTheMedianOfBothArraysTogether(String first, String second, double expected) {
        assertThat(MedianOfTwoSortedArrays.median(ints(first), ints(second))).isEqualTo(expected);
    }

    @Test
    void rejectsTwoEmptyArrays() {
        assertThatIllegalArgumentException().isThrownBy(() -> MedianOfTwoSortedArrays.median(new int[0], new int[0]));
    }
}
