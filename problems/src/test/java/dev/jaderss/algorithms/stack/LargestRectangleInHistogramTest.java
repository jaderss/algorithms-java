package dev.jaderss.algorithms.stack;

import static dev.jaderss.algorithms.Samples.ints;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LargestRectangleInHistogramTest {

    @ParameterizedTest
    @CsvSource({"'2 1 5 6 2 3', 10", "'2 4', 4", "'3 3 3', 9", "'0', 0", "'', 0", "'2147483647 2147483647', 4294967294"
    })
    void findsTheLargestRectangleUnderTheBars(String heights, long expected) {
        assertThat(LargestRectangleInHistogram.area(ints(heights))).isEqualTo(expected);
    }
}
