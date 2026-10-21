package dev.jaderss.algorithms.interval;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;

class IntervalPropertiesTest {

    @Property
    void mergedIntervalsAreSortedSeparatedAndCoverTheSamePoints(
            @ForAll @Size(max = 15) List<@IntRange(max = 30) Integer> starts,
            @ForAll @Size(max = 15) List<@IntRange(max = 5) Integer> lengths) {
        List<Interval> intervals = intervals(starts, lengths);
        List<Interval> merged = MergeIntervals.merge(intervals);
        for (int index = 1; index < merged.size(); index++) {
            assertThat(merged.get(index).start())
                    .isGreaterThan(merged.get(index - 1).end());
        }
        assertThat(IntStream.rangeClosed(0, 35))
                .allSatisfy(point -> assertThat(covers(merged, point)).isEqualTo(covers(intervals, point)));
    }

    @Property
    void removalsLeaveTheLargestSetOfIntervalsThatOnlyTouch(
            @ForAll @Size(max = 10) List<@IntRange(max = 20) Integer> starts,
            @ForAll @Size(max = 10) List<@IntRange(max = 6) Integer> lengths) {
        List<Interval> intervals = intervals(starts, lengths);
        int largestCompatible = 0;
        for (int subset = 0; subset < 1 << intervals.size(); subset++) {
            List<Interval> chosen = new ArrayList<>();
            for (int index = 0; index < intervals.size(); index++) {
                if ((subset & 1 << index) != 0) {
                    chosen.add(intervals.get(index));
                }
            }
            if (compatible(chosen)) {
                largestCompatible = Math.max(largestCompatible, chosen.size());
            }
        }
        assertThat(NonOverlappingIntervals.removalsNeeded(intervals)).isEqualTo(intervals.size() - largestCompatible);
    }

    private static List<Interval> intervals(List<Integer> starts, List<Integer> lengths) {
        return IntStream.range(0, Math.min(starts.size(), lengths.size()))
                .mapToObj(index -> new Interval(starts.get(index), starts.get(index) + lengths.get(index)))
                .toList();
    }

    private static boolean covers(List<Interval> intervals, int point) {
        return intervals.stream().anyMatch(interval -> interval.start() <= point && point <= interval.end());
    }

    private static boolean compatible(List<Interval> intervals) {
        for (int first = 0; first < intervals.size(); first++) {
            for (int second = first + 1; second < intervals.size(); second++) {
                Interval a = intervals.get(first);
                Interval b = intervals.get(second);
                if (a.start() < b.end() && b.start() < a.end()) {
                    return false;
                }
            }
        }
        return true;
    }
}
