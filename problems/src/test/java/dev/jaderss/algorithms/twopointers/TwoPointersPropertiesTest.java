package dev.jaderss.algorithms.twopointers;

import static dev.jaderss.algorithms.Samples.array;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.Chars;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;

class TwoPointersPropertiesTest {

    @Property
    void palindromeCheckMatchesComparingTheCleanedTextWithItsReverse(
            @ForAll @Chars({'a', 'B', 'b', '1', ' ', ',', 'A'}) String text) {
        String cleaned = text.chars()
                .filter(Character::isLetterOrDigit)
                .map(Character::toLowerCase)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        assertThat(ValidPalindrome.isPalindrome(text))
                .isEqualTo(cleaned.contentEquals(new StringBuilder(cleaned).reverse()));
    }

    @Property
    void threeSumMatchesCheckingEveryTriplet(
            @ForAll @Size(max = 25) List<@IntRange(min = -10, max = 10) Integer> numberList) {
        int[] numbers = array(numberList);
        Set<List<Integer>> expected = new HashSet<>();
        for (int first = 0; first < numbers.length; first++) {
            for (int second = first + 1; second < numbers.length; second++) {
                for (int third = second + 1; third < numbers.length; third++) {
                    if ((long) numbers[first] + numbers[second] + numbers[third] == 0) {
                        expected.add(IntStream.of(numbers[first], numbers[second], numbers[third])
                                .sorted()
                                .boxed()
                                .toList());
                    }
                }
            }
        }
        List<List<Integer>> triplets = ThreeSum.triplets(numbers);
        assertThat(triplets).doesNotHaveDuplicates().containsExactlyInAnyOrderElementsOf(expected);
    }

    @Property
    void maxAreaMatchesCheckingEveryPair(@ForAll @Size(max = 40) List<@IntRange(max = 1000) Integer> heightList) {
        int[] heights = array(heightList);
        long expected = 0;
        for (int left = 0; left < heights.length; left++) {
            for (int right = left + 1; right < heights.length; right++) {
                expected = Math.max(expected, (long) (right - left) * Math.min(heights[left], heights[right]));
            }
        }
        assertThat(ContainerWithMostWater.maxArea(heights)).isEqualTo(expected);
    }

    @Property
    void trappedWaterMatchesTheLowerOfTheTallestWallsOnEachSide(
            @ForAll @Size(max = 40) List<@IntRange(max = 1000) Integer> heightList) {
        int[] heights = array(heightList);
        long expected = 0;
        for (int index = 0; index < heights.length; index++) {
            int leftWall =
                    IntStream.rangeClosed(0, index).map(i -> heights[i]).max().orElseThrow();
            int rightWall = IntStream.range(index, heights.length)
                    .map(i -> heights[i])
                    .max()
                    .orElseThrow();
            expected += Math.min(leftWall, rightWall) - heights[index];
        }
        assertThat(TrappingRainWater.trapped(heights)).isEqualTo(expected);
    }
}
