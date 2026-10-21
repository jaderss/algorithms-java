package dev.jaderss.algorithms.heap;

import static dev.jaderss.algorithms.Samples.array;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;

class HeapPropertiesTest {

    @Property
    void everySelectionAgreesWithSorting(
            @ForAll @Size(min = 1, max = 60) List<@IntRange(min = -10, max = 10) Integer> values,
            @ForAll @IntRange(min = 1, max = 60) int k) {
        Assume.that(k <= values.size());
        int[] numbers = array(values);
        int expected = values.stream().sorted().toList().get(values.size() - k);
        assertThat(List.of(
                        KthLargest.byHeap(numbers, k),
                        KthLargest.byQuickselect(numbers, k),
                        KthLargest.bySorting(numbers, k)))
                .containsOnly(expected);
    }

    @Property
    void medianMatchesSortingEverythingSeenSoFar(@ForAll @Size(min = 1, max = 60) List<Integer> values) {
        MedianFinder finder = new MedianFinder();
        List<Integer> seen = new ArrayList<>();
        for (int value : values) {
            finder.add(value);
            seen.add(value);
            List<Integer> sorted = seen.stream().sorted().toList();
            int middle = sorted.size() / 2;
            double expected = sorted.size() % 2 == 1
                    ? sorted.get(middle)
                    : ((double) sorted.get(middle - 1) + sorted.get(middle)) / 2;
            assertThat(finder.median()).isEqualTo(expected);
        }
    }
}
