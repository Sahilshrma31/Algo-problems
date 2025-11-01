/**
 * 🌳 Leetcode 958 — Check Completeness of a Binary Tree
 * 
 * ✅ Problem:
 * Given the root of a binary tree, determine whether it is a complete binary tree.
 *
 * A complete binary tree is one where:
 *  - Every level, except possibly the last, is fully filled.
 *  - All nodes in the last level are as far left as possible.
 *
 * Example:
 *     1
 *    / \
 *   2   3
 *  / \  /
 * 4  5 6
 * ✅ Complete
 *
 *     1
 *    / \
 *   2   3
 *    \  /
 *     5 6
 * ❌ Not Complete
 *
 * ------------------------------------------------------------
 * 🧠 BFS Approach Intuition:
 * - Perform level order traversal.
 * - Once a `null` node is found, no non-null node should appear afterward.
 * - If we see a node after a null → NOT complete.
 *
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 * ------------------------------------------------------------
 * 🧠 DFS Approach Intuition (Index-based like heap):
 * - Label nodes like heap: root = 1, left = 2*i, right = 2*i+1
 * - For a complete tree, all indices must be ≤ total nodes.
 * - Count total nodes → do DFS assigning indices.
 *
 * Time Complexity: O(N)
 * Space Complexity: O(H) [Recursion stack]
 * ------------------------------------------------------------
 */

import java.util.*;

public class CheckCompletenessOfBinaryTree {

    // ------------------------------------------------------------
    // ✅ BFS Approach
    // ------------------------------------------------------------
    static class BFS_Solution {
        public boolean isCompleteTree(TreeNode root) {
            if (root == null) return true;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            boolean foundNull = false;

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();

                if (node == null) {
                    foundNull = true;
                } else {
                    if (foundNull) return false; // non-null after null → not complete
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
            return true;
        }
    }

    // ------------------------------------------------------------
    // ✅ DFS Approach (Index-based validation)
    // ------------------------------------------------------------
    static class DFS_Solution {
        public boolean isCompleteTree(TreeNode root) {
            int totalNodes = countNodes(root);
            return dfs(root, 1, totalNodes);
        }

        private int countNodes(TreeNode root) {
            if (root == null) return 0;
            return 1 + countNodes(root.left) + countNodes(root.right);
        }

        private boolean dfs(TreeNode root, int index, int total) {
            if (root == null) return true;
            if (index > total) return false;

            return dfs(root.left, 2 * index, total)
                && dfs(root.right, 2 * index + 1, total);
        }
    }

    // ------------------------------------------------------------
    // ✅ TreeNode Definition
    // ------------------------------------------------------------
    public static class TreeNode {
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

    // ------------------------------------------------------------
    // ✅ Example Test (Optional)
    // ------------------------------------------------------------
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        BFS_Solution bfs = new BFS_Solution();
        DFS_Solution dfs = new DFS_Solution();

        System.out.println("BFS: " + bfs.isCompleteTree(root)); // true
        System.out.println("DFS: " + dfs.isCompleteTree(root)); // true
    }
}
