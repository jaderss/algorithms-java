package dev.jaderss.algorithms;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public final class Samples {

    private Samples() {}

    public static int[] ints(String spaceSeparated) {
        return spaceSeparated.isBlank()
                ? new int[0]
                : Arrays.stream(spaceSeparated.strip().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
    }

    public static int[] array(List<Integer> values) {
        return values.stream().mapToInt(Integer::intValue).toArray();
    }

    public static char[][] grid(String... rows) {
        return Stream.of(rows).map(String::toCharArray).toArray(char[][]::new);
    }
}
