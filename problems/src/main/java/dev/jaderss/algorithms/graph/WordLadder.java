package dev.jaderss.algorithms.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.OptionalInt;
import java.util.Set;

public final class WordLadder {

    private WordLadder() {}

    public static OptionalInt length(String begin, String end, Collection<String> words) {
        Set<String> unvisited = new HashSet<>(words);
        if (!unvisited.contains(end)) {
            return OptionalInt.empty();
        }
        unvisited.remove(begin);
        Deque<String> frontier = new ArrayDeque<>(List.of(begin));
        for (int length = 1; !frontier.isEmpty(); length++) {
            for (int remaining = frontier.size(); remaining > 0; remaining--) {
                String word = frontier.removeFirst();
                if (word.equals(end)) {
                    return OptionalInt.of(length);
                }
                frontier.addAll(takeNeighbors(word, unvisited));
            }
        }
        return OptionalInt.empty();
    }

    private static List<String> takeNeighbors(String word, Set<String> unvisited) {
        List<String> neighbors = new ArrayList<>();
        char[] letters = word.toCharArray();
        for (int position = 0; position < letters.length; position++) {
            char original = letters[position];
            for (char replacement = 'a'; replacement <= 'z'; replacement++) {
                letters[position] = replacement;
                String candidate = new String(letters);
                if (unvisited.remove(candidate)) {
                    neighbors.add(candidate);
                }
            }
            letters[position] = original;
        }
        return neighbors;
    }
}
