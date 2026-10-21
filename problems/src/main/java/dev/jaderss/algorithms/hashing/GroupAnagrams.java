package dev.jaderss.algorithms.hashing;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public final class GroupAnagrams {

    private GroupAnagrams() {}

    public static List<List<String>> group(List<String> words) {
        return List.copyOf(words.stream()
                .collect(Collectors.groupingBy(
                        GroupAnagrams::signature, LinkedHashMap::new, Collectors.toUnmodifiableList()))
                .values());
    }

    private static String signature(String word) {
        char[] letters = word.toCharArray();
        Arrays.sort(letters);
        return new String(letters);
    }
}
