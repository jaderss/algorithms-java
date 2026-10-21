package dev.jaderss.algorithms.binarysearch;

public final class FindMinimumRotatedSortedArray {

    private FindMinimumRotatedSortedArray() {}

    public static int min(int[] numbers) {
        if (numbers.length == 0) {
            throw new IllegalArgumentException("numbers must not be empty");
        }
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int middle = (low + high) >>> 1;
            if (numbers[middle] > numbers[high]) {
                low = middle + 1;
            } else {
                high = middle;
            }
        }
        return numbers[low];
    }
}
