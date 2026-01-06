/*
====================================================
📌 Problem: Delete Nodes And Return Forest
(LeetCode 1110)
====================================================

You are given the root of a binary tree and an array
to_delete. You need to delete all nodes whose values
are present in to_delete and return the remaining
forest (list of tree roots).

----------------------------------------------------
🧠 Intuition 
----------------------------------------------------
We traverse the tree in **postorder** (left → right → root)
because:
- We must decide what to do with children first
- Then decide whether to delete the current node

If a node is deleted:
✔ Its non-null children become new roots in the forest

If a node is NOT deleted:
✔ It remains part of the tree

----------------------------------------------------
🧩 Approach
----------------------------------------------------
1️⃣ Store all values to delete in a HashSet for O(1) lookup
2️⃣ Use a recursive helper that:
   - Processes left & right subtrees
   - If current node is in delete set:
       • add its children to result list
       • return null
   - Else:
       • return current node
3️⃣ After recursion, if root is not deleted,
   add it to result

----------------------------------------------------
⏱ Time & Space Complexity
----------------------------------------------------
Time Complexity:
O(N)
- Each node is visited exactly once

Space Complexity:
O(N)
- HashSet for deleted values
- Recursion stack (worst-case skewed tree)
- Output list for forest roots

====================================================
*/

import java.util.*;

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

    // Set to store nodes that must be deleted
    Set<Integer> deleteSet = new HashSet<>();

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {

        // Result forest
        List<TreeNode> result = new ArrayList<>();

        // Fill delete set
        for (int val : to_delete) {
            deleteSet.add(val);
        }

        // Process tree
        root = deleteHelper(root, result);

        // If root is not deleted, add it to forest
        if (root != null) {
            result.add(root);
        }

        return result;
    }

    // Postorder DFS
    private TreeNode deleteHelper(TreeNode node, List<TreeNode> result) {

        if (node == null) return null;

        node.left = deleteHelper(node.left, result);
        node.right = deleteHelper(node.right, result);

        // If current node needs to be deleted
        if (deleteSet.contains(node.val)) {

            // Its children become new roots
            if (node.left != null) result.add(node.left);
            if (node.right != null) result.add(node.right);

            return null; // delete current node
        }

        return node; // keep node
    }
}
