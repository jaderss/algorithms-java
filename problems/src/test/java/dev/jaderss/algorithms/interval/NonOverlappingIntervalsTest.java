package dev.jaderss.algorithms.interval;

import static dev.jaderss.algorithms.interval.Intervals.intervals;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class NonOverlappingIntervalsTest {

    @ParameterizedTest
    @CsvSource(
            delimiter = '|',
            value = {
                "1,2;2,3;3,4;1,3 | 1",
                "1,2;1,2;1,2 | 2",
                "1,2;2,3 | 0",
                "1,10;2,3;4,5 | 1",
                "1,1;0,1 | 0",
                "'' | 0"
            })
    void countsTheFewestRemovalsThatLeaveNoOverlap(String input, int expected) {
        assertThat(NonOverlappingIntervals.removalsNeeded(intervals(input))).isEqualTo(expected);
    }
}
