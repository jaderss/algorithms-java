package dev.jaderss.algorithms.linkedlist;

import static dev.jaderss.algorithms.Samples.ints;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ReverseLinkedListTest {

    @ParameterizedTest
    @CsvSource({"'1 2 3 4 5', '5 4 3 2 1'", "'1 2', '2 1'", "'7', '7'", "'', ''"})
    void reversesTheList(String values, String expected) {
        assertThat(ListNode.values(ReverseLinkedList.reverse(ListNode.of(ints(values)))))
                .containsExactlyElementsOf(Arrays.stream(ints(expected)).boxed().toList());
    }
}
