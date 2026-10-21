package dev.jaderss.algorithms.backtracking;

import static dev.jaderss.algorithms.Samples.array;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.UniqueElements;

class BacktrackingPropertiesTest {

    @Property
    void thereAreTwoToTheNDistinctSubsetsOfTheInput(@ForAll @Size(max = 10) @UniqueElements List<Integer> values) {
        List<List<Integer>> subsets = Subsets.of(array(values));
        assertThat(subsets).hasSize(1 << values.size()).doesNotHaveDuplicates();
        assertThat(subsets).allSatisfy(subset -> assertThat(values).containsAll(subset));
    }

    @Property
    void thereAreNFactorialDistinctOrderingsOfTheInput(@ForAll @Size(max = 6) @UniqueElements List<Integer> values) {
        List<List<Integer>> permutations = Permutations.of(array(values));
        int factorial = 1;
        for (int size = 2; size <= values.size(); size++) {
            factorial *= size;
        }
        assertThat(permutations).hasSize(factorial).doesNotHaveDuplicates();
        assertThat(permutations)
                .allSatisfy(ordering -> assertThat(ordering).containsExactlyInAnyOrderElementsOf(values));
    }

    @Property
    void combinationsAreSortedSumToTheTargetAndMatchTheCountOfWays(
            @ForAll @Size(min = 1, max = 5) @UniqueElements List<@IntRange(min = 1, max = 9) Integer> candidates,
            @ForAll @IntRange(max = 25) int target) {
        List<List<Integer>> combinations = CombinationSum.of(array(candidates), target);
        assertThat(combinations).doesNotHaveDuplicates().allSatisfy(combination -> {
            assertThat(combination).isSorted().isSubsetOf(candidates);
            assertThat(combination.stream().mapToInt(Integer::intValue).sum()).isEqualTo(target);
        });
        long[] ways = new long[target + 1];
        ways[0] = 1;
        for (int candidate : candidates) {
            for (int amount = candidate; amount <= target; amount++) {
                ways[amount] += ways[amount - candidate];
            }
        }
        assertThat(combinations).hasSize((int) ways[target]);
    }
}
