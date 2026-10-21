package dev.jaderss.algorithms.slidingwindow;

import java.util.ArrayDeque;
import java.util.Deque;

public final class SlidingWindowMaximum {

    private SlidingWindowMaximum() {}

    public static int[] of(int[] numbers, int window) {
        if (window < 1 || window > numbers.length) {
            throw new IllegalArgumentException("window must be between 1 and the array length");
        }
        int[] maxima = new int[numbers.length - window + 1];
        Deque<Integer> candidates = new ArrayDeque<>();
        for (int index = 0; index < numbers.length; index++) {
            if (!candidates.isEmpty() && candidates.getFirst() <= index - window) {
                candidates.removeFirst();
            }
            while (!candidates.isEmpty() && numbers[candidates.getLast()] <= numbers[index]) {
                candidates.removeLast();
            }
            candidates.addLast(index);
            if (index >= window - 1) {
                maxima[index - window + 1] = numbers[candidates.getFirst()];
            }
        }
        return maxima;
    }
}
