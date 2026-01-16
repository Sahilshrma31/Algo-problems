/*
====================================================
📌 Problem: Partition List
(LeetCode 86)
====================================================

Given the head of a linked list and a value `x`,
partition the list such that:

- All nodes with values **less than x** come before nodes
  with values **greater than or equal to x**
- The **relative order** of nodes in each partition is preserved

====================================================
🧠 Approach: Two Dummy Lists (Stable Partition)
====================================================

- Create two dummy nodes:
  - `small` → list of nodes with value < x
  - `large` → list of nodes with value ≥ x

- Traverse the original list:
  - Append current node to `small` or `large` list
  - Move pointers forward

- Join the two lists:
  small → large

- Important:
  - Set `largeP.next = null` to avoid cycles

====================================================
⚠️ Edge Cases
====================================================

- Empty list → return null
- All nodes < x or all nodes ≥ x
- Single node list

====================================================
⏱ Time Complexity
====================================================
O(n)

====================================================
📦 Space Complexity
====================================================
O(1)

(Only pointer manipulation, no extra nodes except dummies)

====================================================
*/

class Solution {

    public ListNode partition(ListNode head, int x) {

        // Dummy heads for two lists
        ListNode small = new ListNode(0);
        ListNode large = new ListNode(0);

        ListNode smallP = small;
        ListNode largeP = large;

        ListNode curr = head;

        // Separate nodes into two lists
        while (curr != null) {

            if (curr.val < x) {
                smallP.next = curr;
                smallP = smallP.next;
            } else {
                largeP.next = curr;
                largeP = largeP.next;
            }

            curr = curr.next;
        }

        // Join the two lists
        smallP.next = large.next;
        largeP.next = null; // prevent cycle

        return small.next;
    }
}
