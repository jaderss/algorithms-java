package dev.jaderss.algorithms.dynamicprogramming;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ClimbingStairsTest {

    @ParameterizedTest
    @CsvSource({"0, 1", "1, 1", "2, 2", "3, 3", "5, 8", "45, 1836311903", "91, 7540113804746346429"})
    void countsTheWaysToClimbInStepsOfOneOrTwo(int steps, long expected) {
        assertThat(ClimbingStairs.ways(steps)).isEqualTo(expected);
    }

    @Test
    void refusesCountsThatDoNotFitALong() {
        assertThatExceptionOfType(ArithmeticException.class).isThrownBy(() -> ClimbingStairs.ways(92));
    }

    @Test
    void rejectsNegativeSteps() {
        assertThatIllegalArgumentException().isThrownBy(() -> ClimbingStairs.ways(-1));
    }
}
