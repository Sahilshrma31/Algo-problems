/**
 * 🔹 Problem: Right Side View of Binary Tree
 * ------------------------------------------
 * Given the root of a binary tree, imagine standing on the right side of it
 * and return the values of the nodes you can see ordered from top to bottom.
 *
 * 🔹 Example:
 * Input:
 *        1
 *       / \
 *      2   3
 *       \   \
 *        5   4
 *
 * Output: [1, 3, 4]
 *
 * 🔹 Approach / Intuition:
 * ------------------------
 * We perform a **Level Order Traversal (BFS)** using a queue.
 * 
 * - At each level, we process all nodes.
 * - The **last node** in that level (i.e., the rightmost one) will be visible 
 *   from the right side.
 * - Hence, when we reach the last node of a level, we add it to the result.
 *
 * 🔹 Steps:
 * 1. Push root node into queue.
 * 2. For each level:
 *      - Process all nodes in that level.
 *      - Add the last node's value to the result list.
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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            // Process all nodes at this level
            while (size > 0) {
                TreeNode node = queue.poll();
                size--;

                // Add child nodes for next level
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }

                // ✅ If this is the last node of the level, add to result
                if (size == 0) {
                    res.add(node.val);
                }
            }
        }

        return res;
    }
}
