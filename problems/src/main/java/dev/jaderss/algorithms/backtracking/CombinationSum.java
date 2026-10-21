package dev.jaderss.algorithms.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CombinationSum {

    private final int[] candidates;
    private final List<Integer> chosen = new ArrayList<>();
    private final List<List<Integer>> combinations = new ArrayList<>();

    private CombinationSum(int[] candidates) {
        this.candidates = candidates;
    }

    public static List<List<Integer>> of(int[] candidates, int target) {
        if (Arrays.stream(candidates).anyMatch(candidate -> candidate <= 0)) {
            throw new IllegalArgumentException("candidates must be positive");
        }
        int[] sorted = Arrays.stream(candidates).distinct().sorted().toArray();
        CombinationSum search = new CombinationSum(sorted);
        search.collect(0, target);
        return List.copyOf(search.combinations);
    }

    private void collect(int start, int remaining) {
        if (remaining == 0) {
            combinations.add(List.copyOf(chosen));
            return;
        }
        for (int index = start; index < candidates.length && candidates[index] <= remaining; index++) {
            chosen.add(candidates[index]);
            collect(index, remaining - candidates[index]);
            chosen.removeLast();
        }
    }
}
