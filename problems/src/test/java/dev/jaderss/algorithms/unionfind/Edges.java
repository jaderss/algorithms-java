package dev.jaderss.algorithms.unionfind;

import static dev.jaderss.algorithms.Samples.ints;

import java.util.List;
import java.util.stream.Stream;

final class Edges {

    private Edges() {}

    static List<Edge> edges(String pairs) {
        return pairs.isBlank()
                ? List.of()
                : Stream.of(pairs.split(";"))
                        .map(pair -> ints(pair.replace('-', ' ')))
                        .map(ends -> new Edge(ends[0], ends[1]))
                        .toList();
    }
}
