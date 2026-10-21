package dev.jaderss.algorithms.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ThreeSum {

    private ThreeSum() {}

    public static List<List<Integer>> triplets(int[] numbers) {
        int[] sorted = numbers.clone();
        Arrays.sort(sorted);
        List<List<Integer>> triplets = new ArrayList<>();
        for (int first = 0; first < sorted.length - 2 && sorted[first] <= 0; first++) {
            if (first == 0 || sorted[first] != sorted[first - 1]) {
                collectPairs(sorted, first, triplets);
            }
        }
        return List.copyOf(triplets);
    }

    private static void collectPairs(int[] sorted, int first, List<List<Integer>> triplets) {
        int left = first + 1;
        int right = sorted.length - 1;
        while (left < right) {
            long sum = (long) sorted[first] + sorted[left] + sorted[right];
            if (sum < 0) {
                left++;
            } else if (sum > 0) {
                right--;
            } else {
                triplets.add(List.of(sorted[first], sorted[left], sorted[right]));
                do {
                    left++;
                } while (left < right && sorted[left] == sorted[left - 1]);
                right--;
            }
        }
    }
}
