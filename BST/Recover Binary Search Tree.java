/*
 * LeetCode: Recover Binary Search Tree
 * ------------------------------------
 * Problem Summary:
 * A BST has exactly two nodes swapped by mistake. Your task is to restore the BST
 * without altering the tree structure.
 *
 * Intuition:
 * -----------------------------
 * BST inorder traversal always gives a sorted list. When two nodes are swapped,
 * this sorted order breaks.
 *
 * There are two situations:
 * 1. Adjacent Swap:
 *    Inorder: [..., x, y, ...] becomes [..., y, x, ...]
 *    -> Only one violation occurs.
 *
 * 2. Non-Adjacent Swap:
 *    Inorder: [..., a, ..., b, ...] swapped becomes [..., b, ..., a, ...]
 *    -> Two violations occur.
 *
 * APPROACH 1: Inorder + Store Array
 * ----------------------------------
 * Steps:
 *   1. Do inorder traversal and store all values in a list.
 *   2. Sort the list.
 *   3. Do inorder traversal again and rewrite the sorted values into the tree.
 *
 * Time Complexity:  O(n log n)
 * Space Complexity: O(n)
 */
import java.util.*;

class SolutionApproach1 {
    ArrayList<Integer> arr = new ArrayList<>();
    int idx = 0;

    public void recoverTree(TreeNode root) {
        storeInorder(root);
        Collections.sort(arr);
        rewriteInorder(root);
    }

    private void storeInorder(TreeNode root) {
        if (root == null) return;
        storeInorder(root.left);
        arr.add(root.val);
        storeInorder(root.right);
    }

    private void rewriteInorder(TreeNode root) {
        if (root == null) return;
        rewriteInorder(root.left);
        root.val = arr.get(idx++);
        rewriteInorder(root.right);
    }
}


/*
 * APPROACH 2: Inorder Traversal + Detect Violations
 * --------------------------------------------------
 * Intuition:
 * During inorder traversal, track previous node.
 * If prev.val > current.val => violation.
 *
 * Case 1: Non-adjacent swap → two violations
 *   first = prev
 *   middle = curr
 *   last = curr
 *
 * Case 2: Adjacent swap → one violation
 *   first = prev
 *   middle = curr
 *
 * After traversal:
 *   If last != null → swap(first, last)
 *   Else → swap(first, middle)
 *
 * Time Complexity:  O(n)
 * Space Complexity: O(h)  (recursion stack)
 */

class SolutionApproach2 {
    TreeNode first = null;
    TreeNode middle = null;
    TreeNode last = null;
    TreeNode prev = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {
        inorder(root);

        if (first != null && last != null) {
            int temp = first.val;
            first.val = last.val;
            last.val = temp;
        } else if (first != null && middle != null) {
            int temp = first.val;
            first.val = middle.val;
            middle.val = temp;
        }
    }

    private void inorder(TreeNode root) {
        if (root == null) return;

        inorder(root.left);

        if (prev != null && prev.val > root.val) {
            if (first == null) {
                first = prev;
                middle = root;
            } else {
                last = root;
            }
        }

        prev = root;
        inorder(root.right);
    }
}
