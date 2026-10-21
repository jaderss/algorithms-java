package dev.jaderss.algorithms.backtracking;

import static dev.jaderss.algorithms.Samples.ints;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import org.junit.jupiter.api.Test;

class CombinationSumTest {

    @Test
    void listsEveryWayToReachTheTargetReusingCandidates() {
        assertThat(CombinationSum.of(ints("2 3 6 7"), 7)).containsExactly(List.of(2, 2, 3), List.of(7));
        assertThat(CombinationSum.of(ints("2 3 5"), 8))
                .containsExactly(List.of(2, 2, 2, 2), List.of(2, 3, 3), List.of(3, 5));
    }

    @Test
    void unreachableTargetHasNoCombinationAndZeroHasTheEmptyOne() {
        assertThat(CombinationSum.of(ints("2"), 1)).isEmpty();
        assertThat(CombinationSum.of(ints("2"), 0)).containsExactly(List.of());
    }

    @Test
    void repeatedCandidatesDoNotRepeatCombinations() {
        assertThat(CombinationSum.of(ints("3 1 3"), 3)).containsExactly(List.of(1, 1, 1), List.of(3));
    }

    @Test
    void rejectsCandidatesThatCannotMakeProgress() {
        assertThatIllegalArgumentException().isThrownBy(() -> CombinationSum.of(ints("0 1"), 2));
    }
}
