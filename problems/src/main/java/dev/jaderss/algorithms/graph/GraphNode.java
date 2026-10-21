package dev.jaderss.algorithms.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class GraphNode {

    private final int value;
    private final List<GraphNode> neighbors = new ArrayList<>();

    public GraphNode(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public List<GraphNode> neighbors() {
        return Collections.unmodifiableList(neighbors);
    }

    public void addNeighbor(GraphNode neighbor) {
        neighbors.add(neighbor);
    }
}
