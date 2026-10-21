package dev.jaderss.algorithms.graph;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

class CloneGraphTest {

    @Test
    void copiesEveryNodeAndEdge() {
        GraphNode original = graph(List.of(List.of(2, 4), List.of(1, 3), List.of(2, 4), List.of(1, 3)));
        GraphNode copy = CloneGraph.copy(original);
        assertThat(adjacency(copy)).isEqualTo(adjacency(original));
        assertThat(reachable(copy)).doesNotContainAnyElementsOf(reachable(original));
    }

    @Test
    void copiesASelfLoopAndALoneNode() {
        GraphNode loop = new GraphNode(1);
        loop.addNeighbor(loop);
        GraphNode copy = CloneGraph.copy(loop);
        assertThat(copy.neighbors()).containsExactly(copy);
        assertThat(adjacency(CloneGraph.copy(new GraphNode(7)))).isEqualTo(Map.of(7, List.of()));
    }

    @Test
    void copyOfNothingIsNothing() {
        assertThat(CloneGraph.copy(null)).isNull();
    }

    private static GraphNode graph(List<List<Integer>> neighbors) {
        List<GraphNode> nodes = IntStream.rangeClosed(1, neighbors.size())
                .mapToObj(GraphNode::new)
                .toList();
        for (int index = 0; index < neighbors.size(); index++) {
            for (int neighbor : neighbors.get(index)) {
                nodes.get(index).addNeighbor(nodes.get(neighbor - 1));
            }
        }
        return nodes.getFirst();
    }

    private static Map<Integer, List<Integer>> adjacency(GraphNode start) {
        Map<Integer, List<Integer>> adjacency = new TreeMap<>();
        for (GraphNode node : reachable(start)) {
            adjacency.put(
                    node.value(),
                    node.neighbors().stream().map(GraphNode::value).toList());
        }
        return adjacency;
    }

    private static Set<GraphNode> reachable(GraphNode start) {
        Set<GraphNode> seen = Collections.newSetFromMap(new IdentityHashMap<>());
        Deque<GraphNode> pending = new ArrayDeque<>(List.of(start));
        while (!pending.isEmpty()) {
            GraphNode node = pending.removeFirst();
            if (seen.add(node)) {
                pending.addAll(node.neighbors());
            }
        }
        return seen;
    }
}
