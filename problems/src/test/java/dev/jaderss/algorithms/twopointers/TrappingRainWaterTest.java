package dev.jaderss.algorithms.twopointers;

import static dev.jaderss.algorithms.Samples.ints;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TrappingRainWaterTest {

    @ParameterizedTest
    @CsvSource({"'0 1 0 2 1 0 1 3 2 1 2 1', 6", "'4 2 0 3 2 5', 9", "'3 2 1', 0", "'2 0 2', 2", "'', 0"})
    void addsUpTheWaterHeldBetweenBars(String heights, long expected) {
        assertThat(TrappingRainWater.trapped(ints(heights))).isEqualTo(expected);
    }
}
