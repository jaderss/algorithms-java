package dev.jaderss.algorithms.linkedlist;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

public final class MergeKSortedLists {

    private MergeKSortedLists() {}

    public static ListNode merge(Collection<ListNode> lists) {
        PriorityQueue<ListNode> heads = new PriorityQueue<>(Comparator.comparingInt(ListNode::value));
        lists.stream().filter(Objects::nonNull).forEach(heads::offer);
        ListNode anchor = new ListNode(0, null);
        ListNode tail = anchor;
        while (!heads.isEmpty()) {
            tail.next = heads.poll();
            tail = tail.next;
            if (tail.next != null) {
                heads.offer(tail.next);
            }
        }
        return anchor.next;
    }
}
