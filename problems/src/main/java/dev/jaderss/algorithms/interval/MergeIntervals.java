package dev.jaderss.algorithms.interval;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public final class MergeIntervals {

    private MergeIntervals() {}

    public static List<Interval> merge(Collection<Interval> intervals) {
        List<Interval> merged = new ArrayList<>();
        intervals.stream().sorted(Comparator.comparingInt(Interval::start)).forEach(interval -> {
            if (!merged.isEmpty() && merged.getLast().end() >= interval.start()) {
                Interval last = merged.removeLast();
                merged.addLast(new Interval(last.start(), Math.max(last.end(), interval.end())));
            } else {
                merged.addLast(interval);
            }
        });
        return List.copyOf(merged);
    }
}
