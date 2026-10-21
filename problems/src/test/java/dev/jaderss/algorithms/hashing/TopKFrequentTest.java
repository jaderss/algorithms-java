package dev.jaderss.algorithms.hashing;

import static dev.jaderss.algorithms.Samples.ints;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class TopKFrequentTest {

    interface TopK {
        List<Integer> of(int[] numbers, int k);
    }

    static Stream<Named<TopK>> implementations() {
        return Stream.of(
                Named.of("buckets", TopKFrequent::byBuckets),
                Named.of("heap", TopKFrequent::byHeap),
                Named.of("sorting", TopKFrequent::bySorting));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void returnsTheMostFrequentValuesFirst(TopK topK) {
        assertThat(topK.of(ints("1 1 1 2 2 3"), 2)).containsExactly(1, 2);
        assertThat(topK.of(ints("1"), 1)).containsExactly(1);
        assertThat(topK.of(ints("4 4 -1 -1 -1 7"), 1)).containsExactly(-1);
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void rejectsKOutsideTheDistinctValueCount(TopK topK) {
        assertThatIllegalArgumentException().isThrownBy(() -> topK.of(ints("1 1 2"), 3));
        assertThatIllegalArgumentException().isThrownBy(() -> topK.of(ints("1 2"), 0));
    }
}
