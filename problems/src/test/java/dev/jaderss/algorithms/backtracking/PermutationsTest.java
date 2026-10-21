package dev.jaderss.algorithms.backtracking;

import static dev.jaderss.algorithms.Samples.ints;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class PermutationsTest {

    @Test
    void listsEveryOrdering() {
        assertThat(Permutations.of(ints("1 2 3")))
                .containsExactlyInAnyOrder(
                        List.of(1, 2, 3),
                        List.of(1, 3, 2),
                        List.of(2, 1, 3),
                        List.of(2, 3, 1),
                        List.of(3, 1, 2),
                        List.of(3, 2, 1));
    }

    @Test
    void smallInputsHaveOneOrTwoOrderings() {
        assertThat(Permutations.of(ints("0 1"))).containsExactlyInAnyOrder(List.of(0, 1), List.of(1, 0));
        assertThat(Permutations.of(ints("1"))).containsExactly(List.of(1));
        assertThat(Permutations.of(ints(""))).containsExactly(List.of());
    }
}
