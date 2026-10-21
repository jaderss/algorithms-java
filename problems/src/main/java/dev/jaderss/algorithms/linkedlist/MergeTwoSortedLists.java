package dev.jaderss.algorithms.linkedlist;

public final class MergeTwoSortedLists {

    private MergeTwoSortedLists() {}

    public static ListNode merge(ListNode first, ListNode second) {
        ListNode anchor = new ListNode(0, null);
        ListNode tail = anchor;
        ListNode left = first;
        ListNode right = second;
        while (left != null && right != null) {
            if (left.value <= right.value) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }
        tail.next = left != null ? left : right;
        return anchor.next;
    }
}
