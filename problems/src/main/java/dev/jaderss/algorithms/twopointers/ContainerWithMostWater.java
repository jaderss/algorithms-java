package dev.jaderss.algorithms.twopointers;

public final class ContainerWithMostWater {

    private ContainerWithMostWater() {}

    public static long maxArea(int[] heights) {
        int left = 0;
        int right = heights.length - 1;
        long best = 0;
        while (left < right) {
            best = Math.max(best, (long) (right - left) * Math.min(heights[left], heights[right]));
            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }
        return best;
    }
}
