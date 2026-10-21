package dev.jaderss.algorithms.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.IdentityHashMap;
import java.util.Map;

public final class CloneGraph {

    private CloneGraph() {}

    public static GraphNode copy(GraphNode start) {
        if (start == null) {
            return null;
        }
        Map<GraphNode, GraphNode> copies = new IdentityHashMap<>();
        copies.put(start, new GraphNode(start.value()));
        Deque<GraphNode> pending = new ArrayDeque<>();
        pending.add(start);
        while (!pending.isEmpty()) {
            GraphNode original = pending.removeFirst();
            for (GraphNode neighbor : original.neighbors()) {
                GraphNode neighborCopy = copies.get(neighbor);
                if (neighborCopy == null) {
                    neighborCopy = new GraphNode(neighbor.value());
                    copies.put(neighbor, neighborCopy);
                    pending.addLast(neighbor);
                }
                copies.get(original).addNeighbor(neighborCopy);
            }
        }
        return copies.get(start);
    }
}
