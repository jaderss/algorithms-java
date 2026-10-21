package dev.jaderss.algorithms.hashing;

import static java.util.stream.Collectors.toSet;

import java.util.Arrays;
import java.util.Set;

public final class LongestConsecutiveSequence {

    private LongestConsecutiveSequence() {}

    public static int length(int[] numbers) {
        Set<Integer> values = Arrays.stream(numbers).boxed().collect(toSet());
        int longest = 0;
        for (int value : values) {
            if (!contains(values, value - 1L)) {
                int length = 1;
                while (contains(values, (long) value + length)) {
                    length++;
                }
                longest = Math.max(longest, length);
            }
        }
        return longest;
    }

    private static boolean contains(Set<Integer> values, long candidate) {
        return candidate >= Integer.MIN_VALUE && candidate <= Integer.MAX_VALUE && values.contains((int) candidate);
    }
}
