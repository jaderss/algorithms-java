package dev.jaderss.algorithms.dynamicprogramming;

import java.util.Arrays;
import java.util.OptionalInt;

public final class CoinChange {

    private static final int UNREACHABLE = Integer.MAX_VALUE;

    private CoinChange() {}

    public static OptionalInt fewestCoins(int[] coins, int amount) {
        if (amount < 0 || Arrays.stream(coins).anyMatch(coin -> coin <= 0)) {
            throw new IllegalArgumentException("amount must not be negative and coins must be positive");
        }
        int[] fewest = new int[amount + 1];
        Arrays.fill(fewest, 1, amount + 1, UNREACHABLE);
        for (int subtotal = 1; subtotal <= amount; subtotal++) {
            for (int coin : coins) {
                if (coin <= subtotal && fewest[subtotal - coin] != UNREACHABLE) {
                    fewest[subtotal] = Math.min(fewest[subtotal], fewest[subtotal - coin] + 1);
                }
            }
        }
        return fewest[amount] == UNREACHABLE ? OptionalInt.empty() : OptionalInt.of(fewest[amount]);
    }
}
