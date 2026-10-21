package dev.jaderss.algorithms.dynamicprogramming;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EditDistanceTest {

    @ParameterizedTest
    @CsvSource({
        "horse, ros, 3",
        "intention, execution, 5",
        "'', '', 0",
        "abc, '', 3",
        "'', abc, 3",
        "kitten, sitting, 3"
    })
    void countsTheFewestInsertsDeletesAndReplacements(String source, String target, int expected) {
        assertThat(EditDistance.between(source, target)).isEqualTo(expected);
    }
}
