package dev.jaderss.algorithms.unionfind;

import java.util.Collection;

public final class ConnectedComponents {

    private ConnectedComponents() {}

    public static int count(int nodes, Collection<Edge> edges) {
        UnionFind components = new UnionFind(nodes);
        edges.forEach(edge -> components.union(edge.first(), edge.second()));
        return components.components();
    }
}
