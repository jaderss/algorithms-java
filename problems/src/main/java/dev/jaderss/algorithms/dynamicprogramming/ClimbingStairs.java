package dev.jaderss.algorithms.dynamicprogramming;

public final class ClimbingStairs {

    private ClimbingStairs() {}

    public static long ways(int steps) {
        if (steps < 0) {
            throw new IllegalArgumentException("steps must not be negative");
        }
        long beforePrevious = 1;
        long previous = 1;
        for (int step = 2; step <= steps; step++) {
            long current = Math.addExact(beforePrevious, previous);
            beforePrevious = previous;
            previous = current;
        }
        return previous;
    }
}
