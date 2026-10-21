package dev.jaderss.algorithms.stack;

import static dev.jaderss.algorithms.Samples.ints;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DailyTemperaturesTest {

    @ParameterizedTest
    @CsvSource({
        "'73 74 75 71 69 72 76 73', '1 1 4 2 1 1 0 0'",
        "'30 40 50 60', '1 1 1 0'",
        "'30 60 90', '1 1 0'",
        "'50 50 50', '0 0 0'",
        "'', ''"
    })
    void countsTheDaysUntilAWarmerOne(String temperatures, String expected) {
        assertThat(DailyTemperatures.daysUntilWarmer(ints(temperatures))).containsExactly(ints(expected));
    }
}
