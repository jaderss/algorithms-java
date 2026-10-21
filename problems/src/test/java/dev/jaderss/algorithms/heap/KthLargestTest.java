package dev.jaderss.algorithms.heap;

import static dev.jaderss.algorithms.Samples.ints;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.stream.Stream;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class KthLargestTest {

    interface Selection {
        int of(int[] numbers, int k);
    }

    static Stream<Named<Selection>> implementations() {
        return Stream.of(
                Named.of("heap", KthLargest::byHeap),
                Named.of("quickselect", KthLargest::byQuickselect),
                Named.of("sorting", KthLargest::bySorting));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void picksTheKthValueInDescendingOrder(Selection selection) {
        assertThat(selection.of(ints("3 2 1 5 6 4"), 2)).isEqualTo(5);
        assertThat(selection.of(ints("3 2 3 1 2 4 5 5 6"), 4)).isEqualTo(4);
        assertThat(selection.of(ints("1"), 1)).isEqualTo(1);
        assertThat(selection.of(ints("7 7 7"), 2)).isEqualTo(7);
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void leavesTheInputUntouched(Selection selection) {
        int[] numbers = ints("3 2 1 5 6 4");
        selection.of(numbers, 3);
        assertThat(numbers).containsExactly(3, 2, 1, 5, 6, 4);
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void rejectsARankOutsideTheArray(Selection selection) {
        assertThatIllegalArgumentException().isThrownBy(() -> selection.of(ints("1 2"), 0));
        assertThatIllegalArgumentException().isThrownBy(() -> selection.of(ints("1 2"), 3));
    }
}
