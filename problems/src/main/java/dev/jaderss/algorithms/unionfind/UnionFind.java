package dev.jaderss.algorithms.unionfind;

import java.util.stream.IntStream;

public final class UnionFind {

    private final int[] parent;
    private final int[] rank;
    private int components;

    public UnionFind(int size) {
        parent = IntStream.range(0, size).toArray();
        rank = new int[size];
        components = size;
    }

    public int find(int element) {
        int root = element;
        while (parent[root] != root) {
            root = parent[root];
        }
        int node = element;
        while (parent[node] != root) {
            int next = parent[node];
            parent[node] = root;
            node = next;
        }
        return root;
    }

    public boolean union(int first, int second) {
        int firstRoot = find(first);
        int secondRoot = find(second);
        if (firstRoot == secondRoot) {
            return false;
        }
        int root = rank[firstRoot] >= rank[secondRoot] ? firstRoot : secondRoot;
        int child = root == firstRoot ? secondRoot : firstRoot;
        parent[child] = root;
        if (rank[root] == rank[child]) {
            rank[root]++;
        }
        components--;
        return true;
    }

    public int components() {
        return components;
    }
}
