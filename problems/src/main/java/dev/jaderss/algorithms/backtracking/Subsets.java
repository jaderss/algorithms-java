package dev.jaderss.algorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

public final class Subsets {

    private Subsets() {}

    public static List<List<Integer>> of(int[] numbers) {
        List<List<Integer>> subsets = new ArrayList<>();
        collect(numbers, 0, new ArrayList<>(), subsets);
        return List.copyOf(subsets);
    }

    private static void collect(int[] numbers, int start, List<Integer> chosen, List<List<Integer>> subsets) {
        subsets.add(List.copyOf(chosen));
        for (int index = start; index < numbers.length; index++) {
            chosen.add(numbers[index]);
            collect(numbers, index + 1, chosen, subsets);
            chosen.removeLast();
        }
    }
}
