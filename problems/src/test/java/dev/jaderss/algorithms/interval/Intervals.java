package dev.jaderss.algorithms.interval;

import static dev.jaderss.algorithms.Samples.ints;

import java.util.List;
import java.util.stream.Stream;

final class Intervals {

    private Intervals() {}

    static List<Interval> intervals(String ranges) {
        return ranges.isBlank()
                ? List.of()
                : Stream.of(ranges.split(";"))
                        .map(range -> ints(range.replace(',', ' ')))
                        .map(ends -> new Interval(ends[0], ends[1]))
                        .toList();
    }
}
