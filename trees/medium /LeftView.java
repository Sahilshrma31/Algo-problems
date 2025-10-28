/**
 * 🔹 Problem: Left Side View of Binary Tree
 * ------------------------------------------
 * Given the root of a binary tree, imagine standing on the left side of it
 * and return the values of the nodes you can see ordered from top to bottom.
 *
 * 🔹 Example:
 * Input:
 *        1
 *       / \
 *      2   3
 *     / \   \
 *    4   5   6
 *
 * Output: [1, 2, 4]
 *
 * 🔹 Approach / Intuition:
 * ------------------------
 * We perform a **Level Order Traversal (BFS)** using a queue.
 * 
 * - At each level, we process all nodes.
 * - The **first node** encountered at that level (i.e., the leftmost one)
 *   will be visible from the left side.
 * - Hence, when we start processing a level, we add the first node's value
 *   to our result list.
 *
 * 🔹 Steps:
 * 1. Push root node into queue.
 * 2. For each level:
 *      - Add the first node's value to the result.
 *      - Push its left and right children into the queue.
 * 3. Return the list.
 *
 * 🔹 Time Complexity:  O(N)
 *      → Each node is visited once.
 *
 * 🔹 Space Complexity: O(N)
 *      → For queue storage in BFS.
 */

import java.util.*;

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
    public List<Integer> leftSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                // ✅ Add the first node of each level (leftmost)
                if (i == 0) {
                    res.add(node.val);
                }

                // Add left and right children for next level
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        return res;
    }
}
