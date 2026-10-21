package dev.jaderss.algorithms.unionfind;

import static dev.jaderss.algorithms.unionfind.Edges.edges;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ConnectedComponentsTest {

    @ParameterizedTest
    @CsvSource({"5, '0-1;1-2;3-4', 2", "5, '0-1;1-2;2-3;3-4', 1", "3, '', 3", "0, '', 0", "3, '0-1;1-0;0-1', 2"})
    void countsSeparateGroupsOfNodes(int nodes, String pairs, int expected) {
        assertThat(ConnectedComponents.count(nodes, edges(pairs))).isEqualTo(expected);
    }
}
