package dev.jaderss.algorithms.interval;

public record Interval(int start, int end) {

    public Interval {
        if (start > end) {
            throw new IllegalArgumentException("start " + start + " is after end " + end);
        }
    }
}
