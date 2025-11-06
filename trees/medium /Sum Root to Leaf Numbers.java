/**
 * Leetcode 129. Sum Root to Leaf Numbers
 *
 * Problem Summary:
 * ----------------
 * You are given the root of a binary tree where each node contains a digit (0–9).
 * Each root-to-leaf path represents a number formed by concatenating the digits along the path.
 * You must return the total sum of all the numbers formed from all root-to-leaf paths.
 *
 * Example:
 * Input: [4,9,0,5,1]
 * Output: 1026
 *
 * Explanation:
 * Paths are 495, 491, and 40 → 495 + 491 + 40 = 1026
 *
 * --------------------------------------------------------------------------
 * ✅ Brute Force Approach:
 * --------------------------------------------------------------------------
 * Intuition:
 * - Traverse all root-to-leaf paths.
 * - Store all numbers as strings.
 * - Convert each string to an integer and sum them up.
 *
 * Approach:
 * 1. Use DFS to collect all root-to-leaf paths as strings.
 * 2. Convert each string to an integer.
 * 3. Sum all integers.
 *
 * Time Complexity:  O(N * H) → O(N²) in worst case (string concatenation per level)
 * Space Complexity: O(H) for recursion + O(N) for storing all path strings.
 */

import java.util.*;

class BruteForceSolution {
    public int sumNumbers(TreeNode root) {
        List<String> paths = new ArrayList<>();
        collectPaths(root, "", paths);
        int sum = 0;
        for (String num : paths) {
            sum += Integer.parseInt(num);
        }
        return sum;
    }

    private void collectPaths(TreeNode node, String curr, List<String> paths) {
        if (node == null) return;

        curr += node.val; // append digit

        if (node.left == null && node.right == null) {
            paths.add(curr); // reached leaf
            return;
        }

        collectPaths(node.left, curr, paths);
        collectPaths(node.right, curr, paths);
    }
}

/**
 * --------------------------------------------------------------------------
 * ✅ Optimal Approach (DFS with running sum)
 * --------------------------------------------------------------------------
 * Intuition:
 * - Instead of building strings, keep track of the numeric value while traversing.
 * - Multiply the previous sum by 10 and add the current node’s value.
 * - When a leaf node is reached, return the numeric value formed so far.
 *
 * Approach:
 * 1. Traverse the tree using DFS.
 * 2. Keep a running sum `currNum = currNum * 10 + node.val`.
 * 3. When a leaf node is reached, return `currNum`.
 * 4. Add results from left and right subtrees.
 *
 * Time Complexity:  O(N) — visit each node once
 * Space Complexity: O(H) — recursion stack (H = height of tree)
 */

class OptimalSolution {
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int prevSum) {
        if (root == null) {
            return 0;
        }

        prevSum = prevSum * 10 + root.val;

        if (root.left == null && root.right == null) {
            return prevSum;
        }

        return dfs(root.left, prevSum) + dfs(root.right, prevSum);
    }
}

/**
 * TreeNode class definition for reference
 */
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

/**
 * --------------------------------------------------------------------------
 * Example Driver Code
 * --------------------------------------------------------------------------
 */
class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(9);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(1);

        OptimalSolution opt = new OptimalSolution();
        BruteForceSolution brute = new BruteForceSolution();

        System.out.println("Brute Force Output: " + brute.sumNumbers(root));  // 1026
        System.out.println("Optimal Output: " + opt.sumNumbers(root));        // 1026
    }
}
