package dev.jaderss.algorithms.dynamicprogramming;

import static dev.jaderss.algorithms.Samples.array;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.OptionalInt;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.Chars;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.StringLength;
import net.jqwik.api.constraints.UniqueElements;

class DynamicProgrammingPropertiesTest {

    @Property
    void stairWaysFollowTheFibonacciRecurrence(@ForAll @IntRange(min = 2, max = 91) int steps) {
        assertThat(ClimbingStairs.ways(steps))
                .isEqualTo(ClimbingStairs.ways(steps - 1) + ClimbingStairs.ways(steps - 2));
    }

    @Property
    void fewestCoinsMatchesBreadthFirstSearchOverAmounts(
            @ForAll @Size(min = 1, max = 4) @UniqueElements List<@IntRange(min = 1, max = 12) Integer> coins,
            @ForAll @IntRange(max = 60) int amount) {
        assertThat(CoinChange.fewestCoins(array(coins), amount)).isEqualTo(searchFewest(coins, amount));
    }

    @Property
    void increasingSubsequenceMatchesTheQuadraticRecurrence(
            @ForAll @Size(max = 40) List<@IntRange(min = -10, max = 10) Integer> values) {
        int[] longestEndingAt = new int[values.size()];
        int expected = 0;
        for (int end = 0; end < values.size(); end++) {
            longestEndingAt[end] = 1;
            for (int before = 0; before < end; before++) {
                if (values.get(before) < values.get(end)) {
                    longestEndingAt[end] = Math.max(longestEndingAt[end], longestEndingAt[before] + 1);
                }
            }
            expected = Math.max(expected, longestEndingAt[end]);
        }
        assertThat(LongestIncreasingSubsequence.length(array(values))).isEqualTo(expected);
    }

    @Property
    void editDistanceIsAMetricBoundedByTheLongerText(
            @ForAll @StringLength(max = 8) @Chars({'a', 'b', 'c'}) String first,
            @ForAll @StringLength(max = 8) @Chars({'a', 'b', 'c'}) String second,
            @ForAll @StringLength(max = 8) @Chars({'a', 'b', 'c'}) String third) {
        int distance = EditDistance.between(first, second);
        assertThat(distance)
                .isEqualTo(EditDistance.between(second, first))
                .isBetween(Math.abs(first.length() - second.length()), Math.max(first.length(), second.length()))
                .isLessThanOrEqualTo(EditDistance.between(first, third) + EditDistance.between(third, second));
        assertThat(EditDistance.between(first, first)).isZero();
    }

    private static OptionalInt searchFewest(List<Integer> coins, int amount) {
        boolean[] reached = new boolean[amount + 1];
        reached[0] = true;
        List<Integer> frontier = List.of(0);
        for (int coinsUsed = 0; !frontier.isEmpty(); coinsUsed++) {
            if (frontier.contains(amount)) {
                return OptionalInt.of(coinsUsed);
            }
            frontier = frontier.stream()
                    .flatMap(subtotal -> coins.stream().map(coin -> subtotal + coin))
                    .filter(next -> next <= amount && !reached[next])
                    .distinct()
                    .peek(next -> reached[next] = true)
                    .toList();
        }
        return OptionalInt.empty();
    }
}
