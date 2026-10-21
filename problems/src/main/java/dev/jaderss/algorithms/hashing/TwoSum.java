package dev.jaderss.algorithms.hashing;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class TwoSum {

    public record Indices(int first, int second) {}

    private TwoSum() {}

    public static Optional<Indices> find(int[] numbers, int target) {
        Map<Long, Integer> seen = new HashMap<>();
        for (int index = 0; index < numbers.length; index++) {
            Integer partner = seen.get((long) target - numbers[index]);
            if (partner != null) {
                return Optional.of(new Indices(partner, index));
            }
            seen.putIfAbsent((long) numbers[index], index);
        }
        return Optional.empty();
    }
}
