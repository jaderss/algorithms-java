package dev.jaderss.algorithms.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

public final class KthLargest {

    private KthLargest() {}

    public static int byHeap(int[] numbers, int k) {
        requireRank(numbers, k);
        PriorityQueue<Integer> largest = new PriorityQueue<>(k + 1);
        for (int number : numbers) {
            largest.offer(number);
            if (largest.size() > k) {
                largest.poll();
            }
        }
        return largest.element();
    }

    public static int byQuickselect(int[] numbers, int k) {
        requireRank(numbers, k);
        int[] values = numbers.clone();
        int target = values.length - k;
        int low = 0;
        int high = values.length - 1;
        while (true) {
            int pivot = values[ThreadLocalRandom.current().nextInt(low, high + 1)];
            int below = low;
            int above = high;
            int index = low;
            while (index <= above) {
                if (values[index] < pivot) {
                    swap(values, below++, index++);
                } else if (values[index] > pivot) {
                    swap(values, index, above--);
                } else {
                    index++;
                }
            }
            if (target < below) {
                high = below - 1;
            } else if (target > above) {
                low = above + 1;
            } else {
                return pivot;
            }
        }
    }

    public static int bySorting(int[] numbers, int k) {
        requireRank(numbers, k);
        int[] sorted = numbers.clone();
        Arrays.sort(sorted);
        return sorted[sorted.length - k];
    }

    private static void requireRank(int[] numbers, int k) {
        if (k < 1 || k > numbers.length) {
            throw new IllegalArgumentException("k must be between 1 and the array length");
        }
    }

    private static void swap(int[] values, int first, int second) {
        int held = values[first];
        values[first] = values[second];
        values[second] = held;
    }
}
