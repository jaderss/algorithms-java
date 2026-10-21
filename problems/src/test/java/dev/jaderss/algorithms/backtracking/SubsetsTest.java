package dev.jaderss.algorithms.backtracking;

import static dev.jaderss.algorithms.Samples.ints;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class SubsetsTest {

    @Test
    void listsEverySubset() {
        assertThat(Subsets.of(ints("1 2 3")))
                .containsExactlyInAnyOrder(
                        List.of(),
                        List.of(1),
                        List.of(2),
                        List.of(3),
                        List.of(1, 2),
                        List.of(1, 3),
                        List.of(2, 3),
                        List.of(1, 2, 3));
    }

    @Test
    void smallInputsHaveTheEmptySubsetToo() {
        assertThat(Subsets.of(ints("0"))).containsExactlyInAnyOrder(List.of(), List.of(0));
        assertThat(Subsets.of(ints(""))).containsExactly(List.of());
    }
}
