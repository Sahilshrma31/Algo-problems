/*
 * 🧩 Problem: Binary Tree Level Order Traversal (LeetCode #102)
 * 
 * Given the root of a binary tree, return the level order traversal of its nodes' values.
 * (i.e., from left to right, level by level).
 * 
 * Example:
 * Input: [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
 * 
 * ------------------------------------------------------------
 * 💡 Intuition:
 * 
 * We traverse the tree level by level (Breadth-First Search - BFS).
 * At each level, we process all nodes currently in the queue
 * (which belong to that level) and add their children to the queue
 * for the next level.
 * 
 * Each iteration of the while loop processes one level.
 * ------------------------------------------------------------
 * 
 * 🧠 Approach:
 * 1. Use a Queue to perform BFS.
 * 2. Add the root node to the queue.
 * 3. For each level:
 *    - Get the size of the queue (number of nodes in this level).
 *    - Process all those nodes:
 *      → Poll node from the queue.
 *      → Add its value to the current level list.
 *      → Offer its left and right children if they exist.
 *    - Add the current level list to the final result list.
 * 4. Return the result list.
 * 
 * ------------------------------------------------------------
 * ⏱️ Time Complexity: O(N)
 * We visit each node exactly once.
 * 
 * 💾 Space Complexity: O(N)
 * In the worst case (a full binary tree), the queue can hold up to N/2 nodes.
 * 
 * ------------------------------------------------------------
 */

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class LevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        if (root == null) return res;

        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> tempList = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                tempList.add(curr.val);

                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }

            res.add(tempList);
        }

        return res;
    }
}
