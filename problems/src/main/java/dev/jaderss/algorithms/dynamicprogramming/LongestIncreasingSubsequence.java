package dev.jaderss.algorithms.dynamicprogramming;

import java.util.Arrays;

public final class LongestIncreasingSubsequence {

    private LongestIncreasingSubsequence() {}

    public static int length(int[] numbers) {
        int[] smallestTails = new int[numbers.length];
        int length = 0;
        for (int number : numbers) {
            int position = Arrays.binarySearch(smallestTails, 0, length, number);
            int insertion = position >= 0 ? position : -(position + 1);
            smallestTails[insertion] = number;
            if (insertion == length) {
                length++;
            }
        }
        return length;
    }
}
