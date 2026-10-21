package dev.jaderss.algorithms.dynamicprogramming;

import static dev.jaderss.algorithms.Samples.ints;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CoinChangeTest {

    @ParameterizedTest
    @CsvSource({"'1 2 5', 11, 3", "'1', 0, 0", "'186 419 83 408', 6249, 20", "'2 5', 6, 3"})
    void findsTheFewestCoinsForTheAmount(String coins, int amount, int expected) {
        assertThat(CoinChange.fewestCoins(ints(coins), amount)).hasValue(expected);
    }

    @Test
    void anAmountTheCoinsCannotMakeHasNoAnswer() {
        assertThat(CoinChange.fewestCoins(ints("2"), 3)).isEmpty();
        assertThat(CoinChange.fewestCoins(ints(""), 1)).isEmpty();
    }

    @Test
    void rejectsNegativeAmountsAndCoinsThatAreNotPositive() {
        assertThatIllegalArgumentException().isThrownBy(() -> CoinChange.fewestCoins(ints("1"), -1));
        assertThatIllegalArgumentException().isThrownBy(() -> CoinChange.fewestCoins(ints("0 1"), 3));
    }
}
