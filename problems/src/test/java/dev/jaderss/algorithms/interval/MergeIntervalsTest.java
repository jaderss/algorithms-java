package dev.jaderss.algorithms.interval;

import static dev.jaderss.algorithms.interval.Intervals.intervals;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MergeIntervalsTest {

    @ParameterizedTest
    @CsvSource(
            delimiter = '|',
            value = {
                "1,3;2,6;8,10;15,18 | 1,6;8,10;15,18",
                "1,4;4,5 | 1,5",
                "1,4;2,3 | 1,4",
                "4,7;1,4 | 1,7",
                "5,5 | 5,5",
                "'' | ''"
            })
    void joinsOverlappingAndTouchingIntervals(String input, String expected) {
        assertThat(MergeIntervals.merge(intervals(input))).containsExactlyElementsOf(intervals(expected));
    }
}
