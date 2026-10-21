package dev.jaderss.algorithms.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public final class MedianFinder {

    private final PriorityQueue<Integer> lowerHalf = new PriorityQueue<>(Comparator.reverseOrder());
    private final PriorityQueue<Integer> upperHalf = new PriorityQueue<>();

    public void add(int value) {
        lowerHalf.offer(value);
        upperHalf.offer(lowerHalf.poll());
        if (upperHalf.size() > lowerHalf.size()) {
            lowerHalf.offer(upperHalf.poll());
        }
    }

    public double median() {
        if (lowerHalf.isEmpty()) {
            throw new IllegalStateException("no values added yet");
        }
        return lowerHalf.size() > upperHalf.size()
                ? lowerHalf.element()
                : ((double) lowerHalf.element() + upperHalf.element()) / 2;
    }
}
