package dev.jaderss.algorithms.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public final class LargestRectangleInHistogram {

    private LargestRectangleInHistogram() {}

    public static long area(int[] heights) {
        Deque<Integer> rising = new ArrayDeque<>();
        long best = 0;
        for (int index = 0; index < heights.length; index++) {
            while (!rising.isEmpty() && heights[rising.peek()] >= heights[index]) {
                best = Math.max(best, widestAt(heights, rising, index));
            }
            rising.push(index);
        }
        while (!rising.isEmpty()) {
            best = Math.max(best, widestAt(heights, rising, heights.length));
        }
        return best;
    }

    private static long widestAt(int[] heights, Deque<Integer> rising, int rightBound) {
        int tallest = rising.pop();
        int leftBound = rising.isEmpty() ? -1 : rising.peek();
        return (long) heights[tallest] * (rightBound - leftBound - 1);
    }
}
