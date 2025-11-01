/*
 * File: CountCompleteTreeNodes.java
 * Author: Sahil Sharma
 *
 * 🧩 Problem Summary:
 * You are given the root of a complete binary tree.
 * Your task is to count the number of nodes in the tree efficiently.
 *
 * A complete binary tree is a binary tree in which every level,
 * except possibly the last, is completely filled, and all nodes are as far left as possible.
 *
 * Example:
 * Input: [1,2,3,4,5,6]
 * Output: 6
 *
 * 🧠 Intuition:
 * - A perfect binary tree (completely full) with height h has exactly (2^h - 1) nodes.
 * - In a complete binary tree:
 *   - If the height of the left subtree equals the height of the right subtree,
 *     it means this tree is perfect, so we can directly use (2^h - 1).
 *   - Otherwise, we recursively count the left and right subtrees.
 *
 * 🪜 Approach:
 * 1. Compute the height of the left-most path and right-most path.
 * 2. If they are equal → the tree is perfect → return (1 << height) - 1.
 * 3. Else, recursively count the nodes in left and right subtrees + 1 (for root).
 *
 * 🧮 Dry Run Example:
 *           1
 *         /   \
 *        2     3
 *       / \   /
 *      4  5  6
 *
 * leftHeight(1) = 3 (1 → 2 → 4)
 * rightHeight(1) = 3 (1 → 3 → 6)
 * => both equal ⇒ (1 << 3) - 1 = 7 - 1 = 7 ❌
 * Wait! This tree is not perfect, so recursion kicks in.
 *
 * countNodes(1) = 1 + countNodes(2) + countNodes(3)
 * → countNodes(2) = 3, countNodes(3) = 2
 * → total = 6 ✅
 *
 * ⏱️ Time Complexity: O(log² N)
 * - Height computation: O(log N)
 * - Recursive calls: O(log N)
 * => Total: O(log² N)
 *
 * 💾 Space Complexity: O(log N)
 * - Due to recursion stack.
 */

class Solution {
    // Helper function to find the height of left-most path
    public int getleft(TreeNode root) {
        int cnt = 0;
        while (root != null) {
            cnt++;
            root = root.left;
        }
        return cnt;
    }

    // Helper function to find the height of right-most path
    public int getright(TreeNode root) {
        int cnt = 0;
        while (root != null) {
            cnt++;
            root = root.right;
        }
        return cnt;
    }

    // Main function to count nodes in a complete binary tree
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = getleft(root);
        int right = getright(root);

        // If left height == right height, the tree is perfect
        if (left == right) {
            return (1 << left) - 1;
        }

        // Otherwise, recursively count left and right subtrees
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
