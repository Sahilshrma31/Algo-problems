/*
====================================================
📌 Problem: Add One Row to Tree
(LeetCode 623)
====================================================

Given the root of a binary tree, add a row of nodes
with value `val` at the given depth `depth`.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
If depth == 1:
- Create a new root with value `val`
- Attach the original tree as its left child

Otherwise:
- Traverse the tree
- When we reach depth - 1:
    • Insert new nodes as left and right children
    • Attach original children accordingly

----------------------------------------------------
🛠 Approaches Implemented
----------------------------------------------------
1️⃣ DFS (Recursive) – Striver style
2️⃣ BFS (Level Order Traversal)

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(N) – every node is visited once

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
DFS  : O(H) recursion stack (H = height of tree)
BFS  : O(N) queue in worst case

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

    /* =====================================
       1️⃣ DFS (RECURSIVE) APPROACH
       ===================================== */

    private TreeNode addDFS(TreeNode root, int val, int depth, int currDepth) {
        if (root == null) return null;

        if (currDepth == depth - 1) {
            TreeNode leftTemp = root.left;
            TreeNode rightTemp = root.right;

            root.left = new TreeNode(val);
            root.right = new TreeNode(val);

            root.left.left = leftTemp;
            root.right.right = rightTemp;

            return root;
        }

        root.left = addDFS(root.left, val, depth, currDepth + 1);
        root.right = addDFS(root.right, val, depth, currDepth + 1);

        return root;
    }

    /* =====================================
       2️⃣ BFS (LEVEL ORDER) APPROACH
       ===================================== */

    private TreeNode addBFS(TreeNode root, int val, int depth) {
        if (root == null) return null;

        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);

        int currDepth = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            if (currDepth == depth - 1) {
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();

                    TreeNode leftTemp = node.left;
                    TreeNode rightTemp = node.right;

                    node.left = new TreeNode(val);
                    node.right = new TreeNode(val);

                    node.left.left = leftTemp;
                    node.right.right = rightTemp;
                }
                break;
            }

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            currDepth++;
        }

        return root;
    }

    /* =====================================
       MAIN FUNCTION (uses DFS by default)
       ===================================== */

    public TreeNode addOneRow(TreeNode root, int val, int depth) {

        // Special case: insert at root
        if (depth == 1) {
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return newRoot;
        }

        // Choose DFS or BFS
        // return addBFS(root, val, depth);
        return addDFS(root, val, depth, 1);
    }
}
