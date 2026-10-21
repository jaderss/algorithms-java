package dev.jaderss.algorithms.binarysearch;

public final class MedianOfTwoSortedArrays {

    private MedianOfTwoSortedArrays() {}

    public static double median(int[] first, int[] second) {
        if (first.length > second.length) {
            return median(second, first);
        }
        int total = first.length + second.length;
        if (total == 0) {
            throw new IllegalArgumentException("at least one array must have values");
        }
        int leftSize = (total + 1) / 2;
        int low = 0;
        int high = first.length;
        while (low <= high) {
            int firstCut = (low + high) >>> 1;
            int secondCut = leftSize - firstCut;
            int firstLeft = firstCut == 0 ? Integer.MIN_VALUE : first[firstCut - 1];
            int firstRight = firstCut == first.length ? Integer.MAX_VALUE : first[firstCut];
            int secondLeft = secondCut == 0 ? Integer.MIN_VALUE : second[secondCut - 1];
            int secondRight = secondCut == second.length ? Integer.MAX_VALUE : second[secondCut];
            if (firstLeft > secondRight) {
                high = firstCut - 1;
            } else if (secondLeft > firstRight) {
                low = firstCut + 1;
            } else {
                int leftMax = Math.max(firstLeft, secondLeft);
                return total % 2 == 1 ? leftMax : ((double) leftMax + Math.min(firstRight, secondRight)) / 2;
            }
        }
        throw new IllegalArgumentException("arrays must be sorted");
    }
}
