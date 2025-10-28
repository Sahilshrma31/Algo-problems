/**
 * 🧩 Problem: 257. Binary Tree Paths
 *
 * 🔹 Summary:
 * Given the root of a binary tree, return all root-to-leaf paths as strings.
 * Each path should represent the route from the root to a leaf using "->" as a separator.
 *
 * Example:
 * Input:
 *        1
 *       / \
 *      2   3
 *       \
 *        5
 *
 * Output: ["1->2->5", "1->3"]
 *
 * ----------------------------------------------------
 * 💡 Intuition:
 * Use Depth First Search (DFS) traversal.
 * At each node:
 *  1. Add its value to the current path.
 *  2. If it's a leaf node → store the path in result list.
 *  3. Otherwise, continue DFS for left and right children.
 *
 * Since each recursive call creates a new path string (Strings are immutable in Java),
 * we don't need to backtrack — each recursive call has its own `path` copy.
 *
 * ----------------------------------------------------
 * ⚙️ Code Explanation:
 * - `binaryTreePaths()` initializes the answer list and starts DFS.
 * - `allPaths()` recursively builds the paths.
 * - The same `ans` list is passed by reference (so all paths are collected together).
 * - However, `path` (String) is immutable in Java, so each recursion creates a new one.
 *   👉 That’s why paths from other branches never interfere or overlap.
 *
 * ----------------------------------------------------
 * ⏱️ Time Complexity:  O(N)
 * Every node is visited once.
 *
 * 💾 Space Complexity: O(H)
 * Recursion stack height = H (height of tree),
 * plus the space to store path strings.
 */

class Solution {

    // Helper function (same as allPaths in C++)
    private void allPaths(TreeNode root, String path, List<String> ans) {
        // Base case: if it's a leaf node
        if (root.left == null && root.right == null) {
            ans.add(path);
            return;
        }

        // If left child exists → go left
        if (root.left != null) {
            allPaths(root.left, path + "->" + root.left.val, ans);
        }

        // If right child exists → go right
        if (root.right != null) {
            allPaths(root.right, path + "->" + root.right.val, ans);
        }
    }

    // Main function
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        if (root == null) return ans;

        String path = Integer.toString(root.val);
        allPaths(root, path, ans);
        return ans;
    }
}
