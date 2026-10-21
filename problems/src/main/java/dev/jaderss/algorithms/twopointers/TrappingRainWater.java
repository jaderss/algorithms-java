package dev.jaderss.algorithms.twopointers;

public final class TrappingRainWater {

    private TrappingRainWater() {}

    public static long trapped(int[] heights) {
        int left = 0;
        int right = heights.length - 1;
        int leftWall = 0;
        int rightWall = 0;
        long water = 0;
        while (left < right) {
            if (heights[left] < heights[right]) {
                leftWall = Math.max(leftWall, heights[left]);
                water += leftWall - heights[left++];
            } else {
                rightWall = Math.max(rightWall, heights[right]);
                water += rightWall - heights[right--];
            }
        }
        return water;
    }
}
