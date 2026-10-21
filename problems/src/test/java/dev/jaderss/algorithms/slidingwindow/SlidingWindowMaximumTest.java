package dev.jaderss.algorithms.slidingwindow;

import static dev.jaderss.algorithms.Samples.ints;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SlidingWindowMaximumTest {

    @ParameterizedTest
    @CsvSource({"'1 3 -1 -3 5 3 6 7', 3, '3 3 5 5 6 7'", "'1', 1, '1'", "'9 8 7', 1, '9 8 7'", "'1 2 3', 3, '3'"})
    void reportsTheLargestValueInEachWindow(String numbers, int window, String expected) {
        assertThat(SlidingWindowMaximum.of(ints(numbers), window)).containsExactly(ints(expected));
    }

    @Test
    void rejectsWindowsThatDoNotFit() {
        assertThatIllegalArgumentException().isThrownBy(() -> SlidingWindowMaximum.of(ints("1 2"), 0));
        assertThatIllegalArgumentException().isThrownBy(() -> SlidingWindowMaximum.of(ints("1 2"), 3));
    }
}
