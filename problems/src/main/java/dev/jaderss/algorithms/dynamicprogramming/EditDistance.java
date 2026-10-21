package dev.jaderss.algorithms.dynamicprogramming;

import java.util.stream.IntStream;

public final class EditDistance {

    private EditDistance() {}

    public static int between(String source, String target) {
        int[] previous = IntStream.rangeClosed(0, target.length()).toArray();
        for (int sourceIndex = 1; sourceIndex <= source.length(); sourceIndex++) {
            int[] current = new int[target.length() + 1];
            current[0] = sourceIndex;
            for (int targetIndex = 1; targetIndex <= target.length(); targetIndex++) {
                current[targetIndex] = source.charAt(sourceIndex - 1) == target.charAt(targetIndex - 1)
                        ? previous[targetIndex - 1]
                        : 1
                                + Math.min(
                                        previous[targetIndex - 1],
                                        Math.min(previous[targetIndex], current[targetIndex - 1]));
            }
            previous = current;
        }
        return previous[target.length()];
    }
}
