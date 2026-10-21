package dev.jaderss.algorithms.interval;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public final class NonOverlappingIntervals {

    private NonOverlappingIntervals() {}

    public static int removalsNeeded(Collection<Interval> intervals) {
        List<Interval> byEnd = intervals.stream()
                .sorted(Comparator.comparingInt(Interval::end).thenComparingInt(Interval::start))
                .toList();
        int removals = 0;
        long keptEnd = Long.MIN_VALUE;
        for (Interval interval : byEnd) {
            if (interval.start() >= keptEnd) {
                keptEnd = interval.end();
            } else {
                removals++;
            }
        }
        return removals;
    }
}
