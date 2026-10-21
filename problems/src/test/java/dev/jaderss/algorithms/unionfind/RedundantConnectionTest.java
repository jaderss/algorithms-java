package dev.jaderss.algorithms.unionfind;

import static dev.jaderss.algorithms.unionfind.Edges.edges;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RedundantConnectionTest {

    @ParameterizedTest
    @CsvSource({"'1-2;1-3;2-3', 2, 3", "'1-2;2-3;3-4;1-4;1-5', 1, 4", "'1-2;2-1', 2, 1"})
    void findsTheEdgeThatClosesTheCycle(String pairs, int first, int second) {
        assertThat(RedundantConnection.find(edges(pairs))).contains(new Edge(first, second));
    }

    @Test
    void aTreeHasNoRedundantEdge() {
        assertThat(RedundantConnection.find(edges("1-2;2-3;2-4"))).isEmpty();
        assertThat(RedundantConnection.find(edges(""))).isEmpty();
    }
}
