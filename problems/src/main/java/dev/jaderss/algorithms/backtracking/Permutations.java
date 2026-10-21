package dev.jaderss.algorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

public final class Permutations {

    private Permutations() {}

    public static List<List<Integer>> of(int[] numbers) {
        List<List<Integer>> permutations = new ArrayList<>();
        collect(numbers, new boolean[numbers.length], new ArrayList<>(), permutations);
        return List.copyOf(permutations);
    }

    private static void collect(int[] numbers, boolean[] used, List<Integer> chosen, List<List<Integer>> permutations) {
        if (chosen.size() == numbers.length) {
            permutations.add(List.copyOf(chosen));
            return;
        }
        for (int index = 0; index < numbers.length; index++) {
            if (!used[index]) {
                used[index] = true;
                chosen.add(numbers[index]);
                collect(numbers, used, chosen, permutations);
                chosen.removeLast();
                used[index] = false;
            }
        }
    }
}
