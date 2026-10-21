package dev.jaderss.algorithms.graph;

import static dev.jaderss.algorithms.Samples.grid;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class NumberOfIslandsTest {

    @Test
    void countsConnectedLand() {
        assertThat(NumberOfIslands.count(grid("11110", "11010", "11000", "00000")))
                .isEqualTo(1);
        assertThat(NumberOfIslands.count(grid("11000", "11000", "00100", "00011")))
                .isEqualTo(3);
    }

    @Test
    void diagonalLandIsNotConnected() {
        assertThat(NumberOfIslands.count(grid("10", "01"))).isEqualTo(2);
    }

    @Test
    void waterOrNothingHasNoIslands() {
        assertThat(NumberOfIslands.count(grid("000", "000"))).isZero();
        assertThat(NumberOfIslands.count(grid())).isZero();
    }

    @Test
    void leavesTheGridUntouched() {
        char[][] map = grid("110", "011");
        NumberOfIslands.count(map);
        assertThat(map).isDeepEqualTo(grid("110", "011"));
    }
}
