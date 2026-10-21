package dev.jaderss.algorithms.hashing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public final class TopKFrequent {

    private TopKFrequent() {}

    public static List<Integer> byBuckets(int[] numbers, int k) {
        Map<Integer, Integer> counts = counts(numbers, k);
        List<List<Integer>> buckets = Stream.<List<Integer>>generate(ArrayList::new)
                .limit(Collections.max(counts.values()) + 1L)
                .toList();
        counts.forEach((value, count) -> buckets.get(count).add(value));
        return buckets.reversed().stream().flatMap(List::stream).limit(k).toList();
    }

    public static List<Integer> byHeap(int[] numbers, int k) {
        PriorityQueue<Entry<Integer, Integer>> heap = new PriorityQueue<>(Entry.comparingByValue());
        for (Entry<Integer, Integer> entry : counts(numbers, k).entrySet()) {
            heap.offer(entry);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        List<Integer> leastFrequentFirst = new ArrayList<>(k);
        while (!heap.isEmpty()) {
            leastFrequentFirst.add(heap.poll().getKey());
        }
        return List.copyOf(leastFrequentFirst.reversed());
    }

    public static List<Integer> bySorting(int[] numbers, int k) {
        return counts(numbers, k).entrySet().stream()
                .sorted(Entry.<Integer, Integer>comparingByValue().reversed())
                .limit(k)
                .map(Entry::getKey)
                .toList();
    }

    private static Map<Integer, Integer> counts(int[] numbers, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int number : numbers) {
            counts.merge(number, 1, Integer::sum);
        }
        if (k < 1 || k > counts.size()) {
            throw new IllegalArgumentException("k must be between 1 and the number of distinct values");
        }
        return counts;
    }
}
