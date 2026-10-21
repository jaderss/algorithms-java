package dev.jaderss.algorithms.binarysearch;

import static dev.jaderss.algorithms.Samples.array;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.UniqueElements;

class BinarySearchPropertiesTest {

    @Property
    void searchFindsEveryValueOfARotatedArrayAndNothingElse(
            @ForAll @Size(max = 40) @UniqueElements List<@IntRange(min = -100, max = 100) Integer> distinctValues,
            @ForAll @IntRange(max = 40) int rotation,
            @ForAll @IntRange(min = -101, max = 101) int probe) {
        int[] values = array(distinctValues);
        int[] rotated = rotate(values, rotation);
        for (int index = 0; index < rotated.length; index++) {
            assertThat(SearchRotatedSortedArray.indexOf(rotated, rotated[index]))
                    .hasValue(index);
        }
        if (Arrays.stream(values).noneMatch(value -> value == probe)) {
            assertThat(SearchRotatedSortedArray.indexOf(rotated, probe)).isEmpty();
        }
    }

    @Property
    void minimumOfARotatedArrayIsItsSmallestValue(
            @ForAll @Size(min = 1, max = 40) @UniqueElements int[] values, @ForAll @IntRange(max = 40) int rotation) {
        assertThat(FindMinimumRotatedSortedArray.min(rotate(values, rotation)))
                .isEqualTo(Arrays.stream(values).min().orElseThrow());
    }

    @Property
    void medianMatchesSortingBothArraysTogether(
            @ForAll @Size(max = 30) int[] first, @ForAll @Size(max = 30) int[] second) {
        int[] merged = IntStream.concat(Arrays.stream(first), Arrays.stream(second))
                .sorted()
                .toArray();
        if (merged.length > 0) {
            int middle = merged.length / 2;
            double expected =
                    merged.length % 2 == 1 ? merged[middle] : ((double) merged[middle - 1] + merged[middle]) / 2;
            assertThat(MedianOfTwoSortedArrays.median(sorted(first), sorted(second)))
                    .isEqualTo(expected);
        }
    }

    private static int[] rotate(int[] values, int rotation) {
        int[] sorted = sorted(values);
        return IntStream.range(0, sorted.length)
                .map(index -> sorted[(index + rotation) % sorted.length])
                .toArray();
    }

    private static int[] sorted(int[] values) {
        int[] sorted = values.clone();
        Arrays.sort(sorted);
        return sorted;
    }
}
