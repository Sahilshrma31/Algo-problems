/*

 * 🧩 Problem Summary:
 * Given the head of a linked list and an array of integers 'nums',
 * remove all nodes from the linked list whose values are present in 'nums'.
 *
 * Example:
 * Input:
 * nums = [2, 4]
 * Linked List = 1 -> 2 -> 3 -> 4 -> 5
 *
 * Output:
 * 1 -> 3 -> 5
 *
 * 🧠 Intuition:
 * - We need to efficiently check if a node’s value is in nums.
 * - Using a HashSet allows O(1) lookup time.
 * - Then, we simply traverse the list and "unlink" the nodes whose values are found in the set.
 * - To handle deletions at the head easily, we use a dummy node.
 *
 * 🪜 Approach:
 * 1. Add all elements of nums into a HashSet for O(1) lookup.
 * 2. Create a dummy node pointing to the head (handles head deletion cleanly).
 * 3. Traverse the linked list with two pointers:
 *    - 'prev' → the last valid node kept.
 *    - 'curr' → the current node being checked.
 * 4. If curr.val is in the set, unlink it (prev.next = curr.next).
 *    Otherwise, move prev forward.
 * 5. Finally, return dummy.next (which is the new head).
 *
 * ⏱️ Time Complexity:  O(N + M)
 *   - N = number of nodes in linked list
 *   - M = number of elements in nums[]
 *   Building HashSet: O(M)
 *   Traversing LinkedList: O(N)
 *
 * 💾 Space Complexity: O(M)
 *   - For storing nums[] in HashSet
 */

import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
    }
}

public class ModifiedLinkedList {

    // Function to modify linked list
    public ListNode modifiedList(int[] nums, ListNode head) {
        // Step 1: Store all nums in a HashSet for quick lookup
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        // Step 2: Use dummy node to handle edge case where head needs deletion
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Step 3: Traverse the list
        ListNode curr = head;
        ListNode prev = dummy;

        while (curr != null) {
            if (set.contains(curr.val)) {
                // Delete the current node
                prev.next = curr.next;
            } else {
                // Keep the current node
                prev = curr;
            }
            curr = curr.next;
        }

        // Step 4: Return the new head
        return dummy.next;
    }

    // Utility function to print linked list
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    // Driver Code
    public static void main(String[] args) {
        // Example Linked List: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int[] nums = {2, 4}; // Values to delete

        ModifiedLinkedList solution = new ModifiedLinkedList();
        ListNode newHead = solution.modifiedList(nums, head);

        System.out.println("✅ Modified Linked List:");
        printList(newHead);
    }
}
