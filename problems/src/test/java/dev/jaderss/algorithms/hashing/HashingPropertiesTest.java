package dev.jaderss.algorithms.hashing;

import static dev.jaderss.algorithms.Samples.array;
import static org.assertj.core.api.Assertions.assertThat;

import dev.jaderss.algorithms.hashing.TwoSum.Indices;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.Chars;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.StringLength;

class HashingPropertiesTest {

    @Property
    void twoSumFindsAPairExactlyWhenOneExists(@ForAll @Size(max = 30) int[] numbers, @ForAll int target) {
        boolean pairExists = IntStream.range(0, numbers.length)
                .anyMatch(first -> IntStream.range(first + 1, numbers.length)
                        .anyMatch(second -> (long) numbers[first] + numbers[second] == target));
        Optional<Indices> found = TwoSum.find(numbers, target);
        if (pairExists) {
            assertThat(found)
                    .hasValueSatisfying(
                            indices -> assertThat((long) numbers[indices.first()] + numbers[indices.second()])
                                    .isEqualTo(target));
        } else {
            assertThat(found).isEmpty();
        }
    }

    @Property
    void twoSumFindsPairsBuiltFromTheInput(
            @ForAll @Size(min = 2, max = 30) List<@IntRange(min = -1000, max = 1000) Integer> values) {
        int[] numbers = array(values);
        int target = numbers[0] + numbers[numbers.length - 1];
        assertThat(TwoSum.find(numbers, target))
                .hasValueSatisfying(indices -> assertThat(indices.first()).isLessThan(indices.second()));
    }

    @Property
    void topKImplementationsReturnTheSameFrequencies(
            @ForAll @Size(min = 1, max = 200) List<@IntRange(min = -20, max = 20) Integer> values,
            @ForAll @IntRange(min = 1, max = 41) int k) {
        int[] numbers = array(values);
        Map<Integer, Long> counts =
                values.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Assume.that(k <= counts.size());
        List<Long> expected = counts.values().stream()
                .sorted((first, second) -> Long.compare(second, first))
                .limit(k)
                .toList();
        for (List<Integer> result : List.of(
                TopKFrequent.byBuckets(numbers, k),
                TopKFrequent.byHeap(numbers, k),
                TopKFrequent.bySorting(numbers, k))) {
            assertThat(result).doesNotHaveDuplicates();
            assertThat(result.stream().map(counts::get).toList()).isEqualTo(expected);
        }
    }

    @Property
    void productExceptSelfMatchesMultiplyingEveryOtherValue(@ForAll @Size(max = 20) int[] numbers) {
        int[] expected = IntStream.range(0, numbers.length)
                .map(skipped -> IntStream.range(0, numbers.length)
                        .filter(index -> index != skipped)
                        .map(index -> numbers[index])
                        .reduce(1, (product, value) -> product * value))
                .toArray();
        assertThat(ProductExceptSelf.of(numbers)).containsExactly(expected);
    }

    @Property
    void longestConsecutiveSequenceMatchesScanningTheSortedValues(@ForAll @Size(max = 50) int[] numbers) {
        int[] sorted = Arrays.stream(numbers).distinct().sorted().toArray();
        int longest = sorted.length == 0 ? 0 : 1;
        int run = 1;
        for (int index = 1; index < sorted.length; index++) {
            run = (long) sorted[index] - sorted[index - 1] == 1 ? run + 1 : 1;
            longest = Math.max(longest, run);
        }
        assertThat(LongestConsecutiveSequence.length(numbers)).isEqualTo(longest);
    }

    @Property
    void anagramGroupsPartitionTheInputBySortedLetters(
            @ForAll @Size(max = 30) List<@StringLength(max = 4) @Chars({'a', 'b', 'c'}) String> words) {
        List<List<String>> groups = GroupAnagrams.group(words);
        assertThat(groups.stream().flatMap(List::stream).toList()).containsExactlyInAnyOrderElementsOf(words);
        assertThat(groups.stream().map(group -> sorted(group.getFirst())).toList())
                .doesNotHaveDuplicates();
        assertThat(groups)
                .allSatisfy(group -> assertThat(group.stream().map(HashingPropertiesTest::sorted))
                        .containsOnly(sorted(group.getFirst())));
    }

    private static String sorted(String word) {
        return word.chars()
                .sorted()
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
