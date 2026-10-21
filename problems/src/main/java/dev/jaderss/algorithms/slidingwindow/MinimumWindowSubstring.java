package dev.jaderss.algorithms.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public final class MinimumWindowSubstring {

    private MinimumWindowSubstring() {}

    public static String of(String text, String required) {
        if (required.isEmpty()) {
            return "";
        }
        Map<Character, Integer> needed = new HashMap<>();
        for (char letter : required.toCharArray()) {
            needed.merge(letter, 1, Integer::sum);
        }
        int missing = required.length();
        int bestStart = 0;
        int bestLength = Integer.MAX_VALUE;
        int start = 0;
        for (int end = 0; end < text.length(); end++) {
            Integer stillNeeded = needed.computeIfPresent(text.charAt(end), (letter, count) -> count - 1);
            if (stillNeeded != null && stillNeeded >= 0) {
                missing--;
            }
            while (missing == 0) {
                if (end - start + 1 < bestLength) {
                    bestStart = start;
                    bestLength = end - start + 1;
                }
                Integer released = needed.computeIfPresent(text.charAt(start), (letter, count) -> count + 1);
                if (released != null && released > 0) {
                    missing++;
                }
                start++;
            }
        }
        return bestLength == Integer.MAX_VALUE ? "" : text.substring(bestStart, bestStart + bestLength);
    }
}
