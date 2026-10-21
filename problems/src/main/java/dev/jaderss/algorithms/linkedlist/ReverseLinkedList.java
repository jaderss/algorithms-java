package dev.jaderss.algorithms.linkedlist;

public final class ReverseLinkedList {

    private ReverseLinkedList() {}

    public static ListNode reverse(ListNode head) {
        ListNode reversed = null;
        ListNode remaining = head;
        while (remaining != null) {
            ListNode next = remaining.next;
            remaining.next = reversed;
            reversed = remaining;
            remaining = next;
        }
        return reversed;
    }
}
