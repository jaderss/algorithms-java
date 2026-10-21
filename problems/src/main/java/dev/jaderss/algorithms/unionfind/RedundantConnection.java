package dev.jaderss.algorithms.unionfind;

import java.util.List;
import java.util.Optional;

public final class RedundantConnection {

    private RedundantConnection() {}

    public static Optional<Edge> find(List<Edge> edges) {
        int largestNode = edges.stream()
                .mapToInt(edge -> Math.max(edge.first(), edge.second()))
                .max()
                .orElse(0);
        UnionFind connected = new UnionFind(largestNode + 1);
        return edges.stream()
                .filter(edge -> !connected.union(edge.first(), edge.second()))
                .findFirst();
    }
}
