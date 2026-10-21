package dev.jaderss.algorithms.linkedlist;

import static dev.jaderss.algorithms.Samples.array;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;

class LinkedListPropertiesTest {

    @Property
    void reversingYieldsTheValuesBackwards(@ForAll @Size(max = 50) List<Integer> values) {
        assertThat(ListNode.values(ReverseLinkedList.reverse(ListNode.of(array(values)))))
                .containsExactlyElementsOf(values.reversed());
    }

    @Property
    void mergingSortedListsMatchesSortingTheirConcatenation(
            @ForAll @Size(max = 8) List<@Size(max = 10) List<@IntRange(min = -20, max = 20) Integer>> lists) {
        List<List<Integer>> sortedLists =
                lists.stream().map(list -> list.stream().sorted().toList()).toList();
        List<Integer> expected =
                sortedLists.stream().flatMap(List::stream).sorted().toList();
        assertThat(ListNode.values(MergeKSortedLists.merge(nodes(sortedLists)))).isEqualTo(expected);
        if (sortedLists.size() == 2) {
            List<ListNode> pair = nodes(sortedLists);
            assertThat(ListNode.values(MergeTwoSortedLists.merge(pair.getFirst(), pair.getLast())))
                    .isEqualTo(expected);
        }
    }

    @Property
    void cacheBehavesLikeAnAccessOrderedLinkedHashMap(
            @ForAll @IntRange(min = 1, max = 5) int capacity,
            @ForAll @Size(max = 60) List<@IntRange(max = 8) Integer> keys,
            @ForAll @Size(max = 60) List<Boolean> writes) {
        LruCache<Integer, Integer> cache = new LruCache<>(capacity);
        Map<Integer, Integer> model = new LinkedHashMap<>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
        for (int step = 0; step < Math.min(keys.size(), writes.size()); step++) {
            int key = keys.get(step);
            if (writes.get(step)) {
                cache.put(key, step);
                model.put(key, step);
            } else {
                assertThat(cache.get(key)).isEqualTo(Optional.ofNullable(model.get(key)));
            }
            assertThat(cache.size()).isEqualTo(model.size());
        }
    }

    private static List<ListNode> nodes(List<List<Integer>> lists) {
        return lists.stream().map(list -> ListNode.of(array(list))).toList();
    }
}
