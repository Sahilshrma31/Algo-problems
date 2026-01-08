/*
====================================================
📌 Problem: Maximum Product of Splitted Binary Tree
(LeetCode 1339)
====================================================

You are given the root of a binary tree.
Split the tree by removing exactly one edge such that
the product of the sums of the two resulting subtrees
is maximized.

Return the maximum product modulo 1e9 + 7.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
If we remove an edge, the tree splits into:
- Subtree A with sum = s1
- Remaining tree with sum = totalSum - s1

👉 Product = s1 * (totalSum - s1)

So:
1️⃣ First compute total sum of the tree
2️⃣ Then try every possible subtree as one part
3️⃣ Track the maximum product

----------------------------------------------------
🛠 Approach (Two DFS Passes)
----------------------------------------------------
1️⃣ DFS #1 → Compute total sum of tree
2️⃣ DFS #2 → For every subtree:
     - compute subtree sum
     - calculate product with remaining tree
     - update maximum product

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(N)
- Each node is visited twice

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(H)
- Recursion stack (H = height of tree)

====================================================
*/

class Solution {

    private long totalSum = 0;
    private long maxProduct = 0;
    private static final int MOD = 1_000_000_007;

    public int maxProduct(TreeNode root) {
        // Step 1: calculate total sum of tree
        totalSum = getTotalSum(root);

        // Step 2: calculate maximum product
        getSubtreeSum(root);

        return (int) (maxProduct % MOD);
    }

    // DFS to compute subtree sums and update max product
    private long getSubtreeSum(TreeNode node) {
        if (node == null) return 0;

        long leftSum = getSubtreeSum(node.left);
        long rightSum = getSubtreeSum(node.right);

        long currSum = node.val + leftSum + rightSum;
        long remaining = totalSum - currSum;

        maxProduct = Math.max(maxProduct, currSum * remaining);

        return currSum;
    }

    // DFS to compute total sum of tree
    private long getTotalSum(TreeNode node) {
        if (node == null) return 0;
        return node.val + getTotalSum(node.left) + getTotalSum(node.right);
    }
}
