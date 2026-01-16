/*
====================================================
📌 Problem: Odd Even Linked List
(LeetCode 328)
====================================================

Given the head of a singly linked list, group all nodes positioned
at **odd indices together**, followed by nodes at **even indices**.

⚠️ Indexing is **1-based**, not based on node values.

You must:
- Do it in-place
- Maintain relative order within odd and even groups

====================================================
🧠 Approach: Two Pointers (Odd & Even Lists)
====================================================

- Use two pointers:
  - `odd`  → tracks nodes at odd positions
  - `even` → tracks nodes at even positions

- Store the starting node of even list (`evenStart`)
  so we can attach it after odd list at the end

- Rewire pointers:
  - odd.next = even.next
  - even.next = even.next.next

- Move both pointers forward
- Finally connect odd list with even list

====================================================
⚠️ Edge Cases
====================================================

- Empty list → return null
- Single node → already valid
- Two nodes → already valid

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

class Solution {

    public ListNode oddEvenList(ListNode head) {

        // Edge case
        if (head == null) {
            return null;
        }

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenStart = even;

        // Re-arrange nodes
        while (even != null && even.next != null) {

            odd.next = even.next;
            odd = odd.next;

            even.next = odd.next;
            even = even.next;
        }

        // Attach even list after odd list
        odd.next = evenStart;

        return head;
    }
}
