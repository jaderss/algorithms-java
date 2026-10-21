package dev.jaderss.algorithms.hashing;

import static dev.jaderss.algorithms.Samples.ints;
import static org.assertj.core.api.Assertions.assertThat;

import dev.jaderss.algorithms.hashing.TwoSum.Indices;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TwoSumTest {

    @ParameterizedTest
    @CsvSource({"'2 7 11 15', 9, 0, 1", "'3 2 4', 6, 1, 2", "'3 3', 6, 0, 1", "'-3 4 3 90', 0, 0, 2"})
    void findsTheIndicesOfThePairThatAddsUpToTarget(String numbers, int target, int first, int second) {
        assertThat(TwoSum.find(ints(numbers), target)).contains(new Indices(first, second));
    }

    @ParameterizedTest
    @CsvSource({"'', 1", "'5', 10", "'1 2 3', 7", "'2147483647 1', -2147483648"})
    void findsNothingWhenNoPairAddsUpWithoutOverflow(String numbers, int target) {
        assertThat(TwoSum.find(ints(numbers), target)).isEmpty();
    }
}
