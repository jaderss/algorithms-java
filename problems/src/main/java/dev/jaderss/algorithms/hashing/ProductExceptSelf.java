package dev.jaderss.algorithms.hashing;

public final class ProductExceptSelf {

    private ProductExceptSelf() {}

    public static int[] of(int[] numbers) {
        int[] products = new int[numbers.length];
        int prefix = 1;
        for (int index = 0; index < numbers.length; index++) {
            products[index] = prefix;
            prefix *= numbers[index];
        }
        int suffix = 1;
        for (int index = numbers.length - 1; index >= 0; index--) {
            products[index] *= suffix;
            suffix *= numbers[index];
        }
        return products;
    }
}
