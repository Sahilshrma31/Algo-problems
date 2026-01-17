/*
====================================================
📌 Problem: Balance a Binary Search Tree
(LeetCode 1382)
====================================================

You are given the root of a **Binary Search Tree (BST)**.
Return a **balanced BST** with the same node values.

A balanced BST ensures:
- Height difference between left and right subtrees ≤ 1
- Better performance for search operations

====================================================
🧠 Approach: Inorder Traversal + Divide & Conquer
====================================================

Key idea:
- Inorder traversal of a BST gives a **sorted list**

Steps:
1️⃣ Perform inorder DFS and store values in a list  
2️⃣ Build a balanced BST from the sorted list:
   - Choose middle element as root
   - Recursively build left and right subtrees

This guarantees a height-balanced BST.

====================================================
⏱ Time Complexity
====================================================
O(n)

====================================================
📦 Space Complexity
====================================================
O(n)

- List to store inorder traversal
- Recursion stack

====================================================
*/

import java.util.*;

class Solution {

    private List<Integer> inorderList = new ArrayList<>();

    public TreeNode balanceBST(TreeNode root) {

        // Step 1: Inorder traversal to get sorted values
        inorderDFS(root);

        // Step 2: Build balanced BST
        return buildBST(0, inorderList.size() - 1);
    }

    private void inorderDFS(TreeNode root) {

        if (root == null) {
            return;
        }

        inorderDFS(root.left);
        inorderList.add(root.val);
        inorderDFS(root.right);
    }

    private TreeNode buildBST(int left, int right) {

        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;

        TreeNode node = new TreeNode(inorderList.get(mid));
        node.left = buildBST(left, mid - 1);
        node.right = buildBST(mid + 1, right);

        return node;
    }
}

