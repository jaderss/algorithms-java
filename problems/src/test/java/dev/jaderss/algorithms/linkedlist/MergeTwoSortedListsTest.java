package dev.jaderss.algorithms.linkedlist;

import static dev.jaderss.algorithms.Samples.ints;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MergeTwoSortedListsTest {

    @ParameterizedTest
    @CsvSource({"'1 2 4', '1 3 4', '1 1 2 3 4 4'", "'', '', ''", "'', '0', '0'", "'5 6', '1', '1 5 6'"})
    void splicesBothListsInOrder(String first, String second, String expected) {
        assertThat(ListNode.values(MergeTwoSortedLists.merge(ListNode.of(ints(first)), ListNode.of(ints(second)))))
                .containsExactlyElementsOf(Arrays.stream(ints(expected)).boxed().toList());
    }
}
