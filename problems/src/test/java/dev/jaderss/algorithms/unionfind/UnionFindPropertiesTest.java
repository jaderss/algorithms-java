package dev.jaderss.algorithms.unionfind;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Stream;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;

class UnionFindPropertiesTest {

    @Property
    void componentCountMatchesBreadthFirstSearch(
            @ForAll @IntRange(min = 1, max = 15) int nodes,
            @ForAll @Size(max = 30) List<@IntRange(max = 14) Integer> firsts,
            @ForAll @Size(max = 30) List<@IntRange(max = 14) Integer> seconds) {
        List<Edge> edges = new ArrayList<>();
        for (int index = 0; index < Math.min(firsts.size(), seconds.size()); index++) {
            edges.add(new Edge(firsts.get(index) % nodes, seconds.get(index) % nodes));
        }
        assertThat(ConnectedComponents.count(nodes, edges)).isEqualTo(searchComponents(nodes, edges));
    }

    @Property
    void theRedundantEdgeIsTheFirstOneWhoseEndsAreAlreadyConnected(
            @ForAll @Size(max = 20) List<@IntRange(min = 1, max = 8) Integer> firsts,
            @ForAll @Size(max = 20) List<@IntRange(min = 1, max = 8) Integer> seconds) {
        List<Edge> edges = new ArrayList<>();
        for (int index = 0; index < Math.min(firsts.size(), seconds.size()); index++) {
            edges.add(new Edge(firsts.get(index), seconds.get(index)));
        }
        int expectedPosition = -1;
        for (int index = 0; index < edges.size() && expectedPosition < 0; index++) {
            Edge edge = edges.get(index);
            if (reachable(9, edges.subList(0, index), edge.first(), edge.second())) {
                expectedPosition = index;
            }
        }
        if (expectedPosition < 0) {
            assertThat(RedundantConnection.find(edges)).isEmpty();
        } else {
            assertThat(RedundantConnection.find(edges)).containsSame(edges.get(expectedPosition));
        }
    }

    private static int searchComponents(int nodes, List<Edge> edges) {
        boolean[] seen = new boolean[nodes];
        int components = 0;
        for (int start = 0; start < nodes; start++) {
            if (!seen[start]) {
                components++;
                mark(nodes, edges, start, seen);
            }
        }
        return components;
    }

    private static boolean reachable(int nodes, List<Edge> edges, int from, int to) {
        boolean[] seen = new boolean[nodes];
        mark(nodes, edges, from, seen);
        return seen[to];
    }

    private static void mark(int nodes, List<Edge> edges, int start, boolean[] seen) {
        Deque<Integer> pending = new ArrayDeque<>(List.of(start));
        seen[start] = true;
        while (!pending.isEmpty()) {
            int node = pending.removeFirst();
            edges.stream()
                    .flatMap(edge -> Stream.of(
                            edge.first() == node ? edge.second() : -1, edge.second() == node ? edge.first() : -1))
                    .filter(next -> next >= 0 && next < nodes && !seen[next])
                    .forEach(next -> {
                        seen[next] = true;
                        pending.addLast(next);
                    });
        }
    }
}
