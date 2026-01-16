/*
====================================================
📌 Problem: Delete the Middle Node of a Linked List
(LeetCode 2095)
====================================================

You are given the head of a singly linked list.

Delete the **middle node**, and return the head of the modified list.

Definition of middle:
- If the list has even length → delete the ⌊n / 2⌋-th node (0-indexed)
- If the list has odd length → delete the exact middle node

====================================================
🧠 Approach: Slow & Fast Pointer (Two Pointers)
====================================================

- Use two pointers:
  - `slow` moves 1 step at a time
  - `fast` moves 2 steps at a time

- When `fast` reaches the end:
  - `slow` will be at the middle node

- Maintain `prevSlow` to track the node before `slow`
- Remove the middle node by:
  prevSlow.next = slow.next

====================================================
⚠️ Edge Cases
====================================================

- Empty list → return null
- Single node list → return null

====================================================
⏱ Time Complexity
====================================================
O(n)

====================================================
📦 Space Complexity
====================================================
O(1)

====================================================
*/
import java.util.*;
class Solution {

    public ListNode deleteMiddle(ListNode head) {

        // Edge case: empty list or single node
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode prevSlow = null;

        // Move slow by 1 and fast by 2
        while (fast != null && fast.next != null) {
            prevSlow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // Delete the middle node
        prevSlow.next = slow.next;

        return head;
    }
}
