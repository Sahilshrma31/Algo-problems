/*
====================================================
📌 Problem: Linked List Random Node
(LeetCode 382)
====================================================

You are given the head of a singly linked list.
Implement a class such that `getRandom()` returns
a random node’s value from the list.

Each node must have **equal probability** of being chosen.

====================================================
🧠 Approach: Convert Linked List to Array
====================================================

- Traverse the linked list once in the constructor
- Store all node values in an ArrayList
- For `getRandom()`:
  - Generate a random index in range [0, n)
  - Return the value at that index

This guarantees **uniform randomness** because
each index has equal probability.

====================================================
⚠️ Constraints & Notes
====================================================

- `getRandom()` may be called multiple times
- Preprocessing is allowed
- Uses extra space for simplicity and clarity

====================================================
⏱ Time Complexity
====================================================
Constructor: O(n)  
getRandom(): O(1)

====================================================
📦 Space Complexity
====================================================
O(n)

====================================================
*/

import java.util.*;

class Solution {

    private List<Integer> list;
    private Random rand;

    public Solution(ListNode head) {

        list = new ArrayList<>();
        rand = new Random();

        ListNode temp = head;
        while (temp != null) {
            list.add(temp.val);
            temp = temp.next;
        }
    }

    public int getRandom() {

        int n = list.size();
        int randomIndex = rand.nextInt(n);
        return list.get(randomIndex);
    }
}
