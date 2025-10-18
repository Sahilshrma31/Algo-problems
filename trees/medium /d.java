/*
 * Problem: Check if Two Binary Trees are Identical
 *
 * Given two binary trees, determine if they are identical.
 * Two binary trees are identical if they have the same structure and node values.
 *
 * Example:
 * Tree 1:       Tree 2:
 *     1             1
 *    / \           / \
 *   2   3         2   3
 * Output: true
 *
 * Approaches:
 * 1. Recursive Approach (DFS)
 *    - Base cases:
 *        a) Both nodes null → identical
 *        b) One node null → not identical
 *        c) Node values differ → not identical
 *    - Recurse on left and right subtrees
 *    Time Complexity: O(n) → visit each node once
 *    Space Complexity: O(h) → recursion stack (h = tree height)
 *
 * 2. BFS/Iterative Approach
 *    - Use two queues to traverse both trees level by level
 *    - At each step, compare node values and structure
 *    - If mismatch, return false
 *    Time Complexity: O(n)
 *    Space Complexity: O(n) → queues store nodes
 */

import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {

    // 1. Recursive Approach (DFS)
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;       // Both null → identical
        if(p == null || q == null) return false;      // One null → not identical
        if(p.val != q.val) return false;              // Value mismatch → not identical

        // Recurse on left and right subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // 2. BFS/Iterative Approach
    public boolean isSameTreeBFS(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();

        queue1.add(p);
        queue2.add(q);

        while(!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();

            if(node1 == null && node2 == null) continue;   // Both null, continue
            if(node1 == null || node2 == null) return false; // One null → not identical
            if(node1.val != node2.val) return false;       // Value mismatch → not identical

            // Add children to queues
            queue1.add(node1.left);
            queue1.add(node1.right);
            queue2.add(node2.left);
            queue2.add(node2.right);
        }

        return queue1.isEmpty() && queue2.isEmpty();
    }
}

/*
 * Complexity Analysis:
 *
 * Recursive Approach:
 * - Time: O(n) → Each node visited once
 * - Space: O(h) → Recursion stack, h = tree height
 *
 * BFS/Iterative Approach:
 * - Time: O(n) → Each node visited once
 * - Space: O(n) → Queue can store up to all nodes in a level (worst-case)
 *
 * Notes:
 * - Recursive approach is simpler and more common
 * - BFS approach avoids recursion stack and is safe for very deep trees
 */
