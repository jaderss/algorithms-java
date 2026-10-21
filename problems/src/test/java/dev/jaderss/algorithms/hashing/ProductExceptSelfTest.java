package dev.jaderss.algorithms.hashing;

import static dev.jaderss.algorithms.Samples.ints;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProductExceptSelfTest {

    @ParameterizedTest
    @CsvSource({"'1 2 3 4', '24 12 8 6'", "'-1 1 0 -3 3', '0 0 9 0 0'", "'0 0', '0 0'", "'5', '1'", "'', ''"})
    void multipliesEverythingButTheValueAtEachPosition(String numbers, String expected) {
        assertThat(ProductExceptSelf.of(ints(numbers))).containsExactly(ints(expected));
    }
}
