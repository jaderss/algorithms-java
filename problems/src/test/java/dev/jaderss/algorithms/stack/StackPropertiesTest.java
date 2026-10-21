package dev.jaderss.algorithms.stack;

import static dev.jaderss.algorithms.Samples.array;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.IntStream;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;

class StackPropertiesTest {

    @Provide
    Arbitrary<String> balanced() {
        Arbitrary<String> pair = Arbitraries.of("()", "[]", "{}");
        return Arbitraries.recursive(
                () -> Arbitraries.just(""),
                inner -> Arbitraries.oneOf(
                        pair.flatMap(
                                brackets -> inner.map(content -> brackets.charAt(0) + content + brackets.charAt(1))),
                        inner.flatMap(first -> inner.map(second -> first + second))),
                0,
                6);
    }

    @Property
    void balancedBracketsAreValid(@ForAll("balanced") String text) {
        assertThat(ValidParentheses.isValid(text)).isTrue();
    }

    @Property
    void removingOneSymbolFromBalancedBracketsMakesThemInvalid(
            @ForAll("balanced") String text, @ForAll @IntRange(max = 200) int position) {
        if (!text.isEmpty()) {
            int removed = position % text.length();
            assertThat(ValidParentheses.isValid(text.substring(0, removed) + text.substring(removed + 1)))
                    .isFalse();
        }
    }

    @Property
    void waitsMatchScanningForward(
            @ForAll @Size(max = 40) List<@IntRange(min = 30, max = 40) Integer> temperatureList) {
        int[] temperatures = array(temperatureList);
        int[] expected = IntStream.range(0, temperatures.length)
                .map(day -> IntStream.range(day + 1, temperatures.length)
                        .filter(later -> temperatures[later] > temperatures[day])
                        .map(later -> later - day)
                        .findFirst()
                        .orElse(0))
                .toArray();
        assertThat(DailyTemperatures.daysUntilWarmer(temperatures)).containsExactly(expected);
    }

    @Property
    void largestRectangleMatchesCheckingEveryRange(
            @ForAll @Size(max = 30) List<@IntRange(max = 50) Integer> heightList) {
        int[] heights = array(heightList);
        long expected = 0;
        for (int start = 0; start < heights.length; start++) {
            int lowest = Integer.MAX_VALUE;
            for (int end = start; end < heights.length; end++) {
                lowest = Math.min(lowest, heights[end]);
                expected = Math.max(expected, (long) lowest * (end - start + 1));
            }
        }
        assertThat(LargestRectangleInHistogram.area(heights)).isEqualTo(expected);
    }
}
