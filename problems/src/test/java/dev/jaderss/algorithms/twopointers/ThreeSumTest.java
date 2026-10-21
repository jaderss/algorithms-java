package dev.jaderss.algorithms.twopointers;

import static dev.jaderss.algorithms.Samples.ints;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class ThreeSumTest {

    @Test
    void findsEveryDistinctTripletThatSumsToZero() {
        assertThat(ThreeSum.triplets(ints("-1 0 1 2 -1 -4"))).containsExactly(List.of(-1, -1, 2), List.of(-1, 0, 1));
    }

    @Test
    void reportsRepeatedZerosOnce() {
        assertThat(ThreeSum.triplets(ints("0 0 0 0"))).containsExactly(List.of(0, 0, 0));
    }

    @Test
    void findsNothingWhenNoTripletSumsToZero() {
        assertThat(ThreeSum.triplets(ints("0 1 1"))).isEmpty();
        assertThat(ThreeSum.triplets(ints("1 -1"))).isEmpty();
        assertThat(ThreeSum.triplets(ints("2147483647 2147483647 2"))).isEmpty();
    }
}
