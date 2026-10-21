package dev.jaderss.algorithms.binarysearch;

import java.util.OptionalInt;

public final class SearchRotatedSortedArray {

    private SearchRotatedSortedArray() {}

    public static OptionalInt indexOf(int[] numbers, int target) {
        int low = 0;
        int high = numbers.length - 1;
        while (low <= high) {
            int middle = (low + high) >>> 1;
            if (numbers[middle] == target) {
                return OptionalInt.of(middle);
            }
            boolean leftHalfSorted = numbers[low] <= numbers[middle];
            boolean inLeftHalf = leftHalfSorted
                    ? numbers[low] <= target && target < numbers[middle]
                    : !(numbers[middle] < target && target <= numbers[high]);
            if (inLeftHalf) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return OptionalInt.empty();
    }
}
