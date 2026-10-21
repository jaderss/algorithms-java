package dev.jaderss.algorithms.twopointers;

import static dev.jaderss.algorithms.Samples.ints;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ContainerWithMostWaterTest {

    @ParameterizedTest
    @CsvSource({"'1 8 6 2 5 4 8 3 7', 49", "'1 1', 1", "'5', 0", "'', 0", "'2147483647 1 2147483647', 4294967294"})
    void findsTheLargestAreaBetweenTwoLines(String heights, long expected) {
        assertThat(ContainerWithMostWater.maxArea(ints(heights))).isEqualTo(expected);
    }
}
