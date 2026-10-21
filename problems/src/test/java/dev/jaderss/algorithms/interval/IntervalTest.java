package dev.jaderss.algorithms.interval;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

class IntervalTest {

    @Test
    void rejectsAStartAfterTheEnd() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Interval(3, 2))
                .withMessage("start 3 is after end 2");
    }
}
