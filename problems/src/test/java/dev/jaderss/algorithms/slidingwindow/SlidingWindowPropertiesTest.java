package dev.jaderss.algorithms.slidingwindow;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
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

class SlidingWindowPropertiesTest {

    @Property
    void longestUniqueRunMatchesCheckingEverySubstring(
            @ForAll @StringLength(max = 30) @Chars({'a', 'b', 'c', 'd'}) String text) {
        int expected = 0;
        for (int start = 0; start < text.length(); start++) {
            for (int end = start + 1; end <= text.length(); end++) {
                if (text.substring(start, end).chars().distinct().count() == end - start) {
                    expected = Math.max(expected, end - start);
                }
            }
        }
        assertThat(LongestSubstringWithoutRepeating.length(text)).isEqualTo(expected);
    }

    @Property
    void minimumWindowMatchesCheckingEverySubstring(
            @ForAll @StringLength(max = 20) @Chars({'a', 'b', 'c'}) String text,
            @ForAll @StringLength(min = 1, max = 4) @Chars({'a', 'b', 'c'}) String required) {
        int shortest = Integer.MAX_VALUE;
        for (int start = 0; start < text.length(); start++) {
            for (int end = start + 1; end <= text.length(); end++) {
                if (covers(text.substring(start, end), required)) {
                    shortest = Math.min(shortest, end - start);
                }
            }
        }
        String window = MinimumWindowSubstring.of(text, required);
        if (shortest == Integer.MAX_VALUE) {
            assertThat(window).isEmpty();
        } else {
            assertThat(window)
                    .hasSize(shortest)
                    .satisfies(found -> assertThat(covers(found, required)).isTrue());
            assertThat(text).contains(window);
        }
    }

    @Property
    void windowMaximaMatchScanningEachWindow(
            @ForAll @Size(min = 1, max = 40) int[] numbers, @ForAll @IntRange(min = 1, max = 40) int window) {
        Assume.that(window <= numbers.length);
        int[] expected = IntStream.rangeClosed(0, numbers.length - window)
                .map(start -> IntStream.range(start, start + window)
                        .map(i -> numbers[i])
                        .max()
                        .orElseThrow())
                .toArray();
        assertThat(SlidingWindowMaximum.of(numbers, window)).containsExactly(expected);
    }

    private static boolean covers(String window, String required) {
        Map<Integer, Long> available = counts(window);
        return counts(required).entrySet().stream()
                .allMatch(entry -> available.getOrDefault(entry.getKey(), 0L) >= entry.getValue());
    }

    private static Map<Integer, Long> counts(String text) {
        return text.chars().boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
