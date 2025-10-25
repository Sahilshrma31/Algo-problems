/**
 * Problem: LeetCode 987 - Vertical Order Traversal of a Binary Tree
 *
 * Problem Summary:
 * Given a binary tree, return its vertical order traversal.
 *
 * 1. Nodes are grouped by their column (x-coordinate).
 * 2. If two nodes share the same row and column, sort them by node value.
 * 3. Return columns from left to right.
 *
 * Example:
 * Input:
 *         3
 *        / \
 *       9   20
 *           / \
 *          15  7
 * Output: [[9], [3,15], [20], [7]]
 *
 * Intuition:
 * - Assign coordinates to each node: root at (x=0, y=0)
 *   - left child: (x-1, y+1)
 *   - right child: (x+1, y+1)
 * - Use TreeMap for columns (x) to sort columns automatically.
 * - Use TreeMap for rows (y) to sort rows automatically.
 * - Store a list of node values at each (x, y) and sort it if multiple nodes share the same position.
 *
 * Approach:
 * 1. DFS traversal to populate map of coordinates.
 * 2. Use TreeMap<Integer, TreeMap<Integer, List<Integer>>>:
 *      - Outer map → key = column (x)
 *      - Inner map → key = row (y)
 *      - List<Integer> → node values at (x, y)
 * 3. After DFS, iterate through TreeMaps to build the final result.
 *
 * Time Complexity: O(N log N)
 * - DFS visits all nodes → O(N)
 * - TreeMap insertions and sorting → O(N log N) in worst case
 *
 * Space Complexity: O(N)
 * - For the map and recursion stack
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

public class Solution {

    // Map structure: column -> (row -> list of node values)
    TreeMap<Integer, TreeMap<Integer, List<Integer>>> map = new TreeMap<>();

    public List<List<Integer>> verticalTraversal(TreeNode root) {

        // Step 1: DFS traversal to populate the map
        dfs(root, 0, 0);

        // Step 2: Prepare the final result list
        List<List<Integer>> res = new ArrayList<>();

        // Step 3: Iterate over columns in sorted order
        for (Map.Entry<Integer, TreeMap<Integer, List<Integer>>> outerEntry : map.entrySet()) {
            TreeMap<Integer, List<Integer>> innermap = outerEntry.getValue();
            List<Integer> column = new ArrayList<>();

            // Step 4: Iterate over rows in sorted order
            for (Map.Entry<Integer, List<Integer>> innerEntry : innermap.entrySet()) {
                List<Integer> nodeList = innerEntry.getValue();

                // Sort nodes at same (x, y) by value
                Collections.sort(nodeList);

                // Add sorted values to the column
                column.addAll(nodeList);
            }

            // Step 5: Add completed column to result
            res.add(column);
        }

        return res;
    }

    /**
     * DFS function to assign coordinates (x, y) to each node and populate the map.
     *
     * @param root Current node
     * @param x Column index
     * @param y Row index (depth)
     */
    public void dfs(TreeNode root, int x, int y) {
        if (root == null) return;

        // Step 1: Create inner map for this column if it doesn't exist
        if (!map.containsKey(x)) {
            map.put(x, new TreeMap<>());
        }

        TreeMap<Integer, List<Integer>> innermap = map.get(x);

        // Step 2: Create list for this row if it doesn't exist
        if (!innermap.containsKey(y)) {
            innermap.put(y, new ArrayList<>());
        }

        // Step 3: Add current node's value to its (x, y) position
        innermap.get(y).add(root.val);

        // Step 4: Traverse left and right children
        dfs(root.left, x - 1, y + 1);
        dfs(root.right, x + 1, y + 1);
    }

    // Optional: main method for testing
    public static void main(String[] args) {
        Solution sol = new Solution();

        TreeNode root = new TreeNode(3,
                            new TreeNode(9),
                            new TreeNode(20, new TreeNode(15), new TreeNode(7))
                        );

        List<List<Integer>> result = sol.verticalTraversal(root);
        System.out.println(result); // Expected output: [[9], [3,15], [20], [7]]
    }
}
