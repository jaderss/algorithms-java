package dev.jaderss.algorithms.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public final class LargestRectangleInHistogram {

    private LargestRectangleInHistogram() {}

    public static long area(int[] heights) {
        Deque<Integer> rising = new ArrayDeque<>();
        long best = 0;
        for (int index = 0; index <= heights.length; index++) {
            int height = index == heights.length ? 0 : heights[index];
            while (!rising.isEmpty() && heights[rising.peek()] >= height) {
                int tallest = rising.pop();
                int leftBound = rising.isEmpty() ? -1 : rising.peek();
                best = Math.max(best, (long) heights[tallest] * (index - leftBound - 1));
            }
            rising.push(index);
        }
        return best;
    }
}
