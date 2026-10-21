package dev.jaderss.algorithms.heap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import org.junit.jupiter.api.Test;

class MedianFinderTest {

    @Test
    void tracksTheMedianAsValuesArrive() {
        MedianFinder finder = new MedianFinder();
        finder.add(1);
        finder.add(2);
        assertThat(finder.median()).isEqualTo(1.5);
        finder.add(3);
        assertThat(finder.median()).isEqualTo(2.0);
    }

    @Test
    void averagesExtremeValuesWithoutOverflow() {
        MedianFinder finder = new MedianFinder();
        finder.add(Integer.MAX_VALUE);
        finder.add(Integer.MAX_VALUE);
        assertThat(finder.median()).isEqualTo(Integer.MAX_VALUE);
    }

    @Test
    void hasNoMedianBeforeAnyValue() {
        assertThatIllegalStateException().isThrownBy(() -> new MedianFinder().median());
    }
}
