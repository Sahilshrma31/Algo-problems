/*
====================================================
📌 Problem: Subtree with All the Deepest Nodes
(LeetCode 865)
====================================================

Given the root of a binary tree, return the smallest
subtree that contains **all the deepest nodes**.

----------------------------------------------------
🧠 Key Understanding
----------------------------------------------------
- Deepest nodes = nodes at maximum depth
- The required subtree root is:
  ✔ Either the current root
  ✔ Or lies completely in left / right subtree

----------------------------------------------------
🛠 Approaches Included
----------------------------------------------------
1️⃣ Optimal DFS (Height + Node together) → O(N)
2️⃣ Brute Force (Repeated Height Calculation) → O(N²) worst case

----------------------------------------------------
⏱ Time & Space Complexity
----------------------------------------------------

1️⃣ Optimal DFS:
   Time: O(N)
   Space: O(H) recursion stack

2️⃣ Brute Force:
   Time: O(N²) in skewed tree
   Space: O(H)

====================================================
*/

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {

    /* =================================================
       1️⃣ OPTIMAL DFS APPROACH (Single Traversal)
       ================================================= */

    class Pair {
        TreeNode node;
        int height;

        Pair(TreeNode node, int height) {
            this.node = node;
            this.height = height;
        }
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).node;
    }

    private Pair dfs(TreeNode root) {

        if (root == null) {
            return new Pair(null, -1);
        }

        Pair left = dfs(root.left);
        Pair right = dfs(root.right);

        if (left.height == right.height) {
            return new Pair(root, left.height + 1);
        } else if (left.height > right.height) {
            return new Pair(left.node, left.height + 1);
        } else {
            return new Pair(right.node, right.height + 1);
        }
    }

    /* =================================================
       2️⃣ BRUTE FORCE APPROACH
       (Repeated Height Calculation)
       ================================================= */

    public TreeNode subtreeWithAllDeepestBrute(TreeNode root) {

        if (root == null) return null;

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        if (leftHeight == rightHeight) {
            return root;
        } else if (leftHeight > rightHeight) {
            return subtreeWithAllDeepestBrute(root.left);
        } else {
            return subtreeWithAllDeepestBrute(root.right);
        }
    }

    // Height of binary tree
    private int height(TreeNode root) {
        if (root == null) return -1;

        int left = height(root.left);
        int right = height(root.right);

        return Math.max(left, right) + 1;
    }
}
