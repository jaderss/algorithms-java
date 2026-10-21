package dev.jaderss.algorithms.linkedlist;

import static dev.jaderss.algorithms.Samples.ints;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class MergeKSortedListsTest {

    @Test
    void splicesEveryListInOrder() {
        assertThat(ListNode.values(MergeKSortedLists.merge(lists("1 4 5", "1 3 4", "2 6"))))
                .containsExactly(1, 1, 2, 3, 4, 4, 5, 6);
    }

    @Test
    void emptyListsMergeToNothing() {
        assertThat(MergeKSortedLists.merge(lists())).isNull();
        assertThat(MergeKSortedLists.merge(lists(""))).isNull();
        assertThat(ListNode.values(MergeKSortedLists.merge(lists("", "3")))).containsExactly(3);
    }

    private static List<ListNode> lists(String... values) {
        return Stream.of(values).map(list -> ListNode.of(ints(list))).toList();
    }
}
