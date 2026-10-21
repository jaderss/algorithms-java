package dev.jaderss.algorithms.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public final class LongestSubstringWithoutRepeating {

    private LongestSubstringWithoutRepeating() {}

    public static int length(String text) {
        Map<Character, Integer> lastSeen = new HashMap<>();
        int start = 0;
        int longest = 0;
        for (int end = 0; end < text.length(); end++) {
            Integer previous = lastSeen.put(text.charAt(end), end);
            if (previous != null && previous >= start) {
                start = previous + 1;
            }
            longest = Math.max(longest, end - start + 1);
        }
        return longest;
    }
}
