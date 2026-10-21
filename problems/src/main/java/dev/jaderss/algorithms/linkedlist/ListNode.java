package dev.jaderss.algorithms.linkedlist;

import java.util.ArrayList;
import java.util.List;

public final class ListNode {

    final int value;
    ListNode next;

    ListNode(int value, ListNode next) {
        this.value = value;
        this.next = next;
    }

    public int value() {
        return value;
    }

    public ListNode next() {
        return next;
    }

    public static ListNode of(int... values) {
        ListNode head = null;
        for (int index = values.length - 1; index >= 0; index--) {
            head = new ListNode(values[index], head);
        }
        return head;
    }

    public static List<Integer> values(ListNode head) {
        List<Integer> values = new ArrayList<>();
        for (ListNode node = head; node != null; node = node.next) {
            values.add(node.value);
        }
        return List.copyOf(values);
    }
}
