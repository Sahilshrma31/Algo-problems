// 🔹 Problem: Search in a Binary Search Tree (BST)
// Given the root of a BST and a key, determine if the key exists in the BST.

// --------------------------------------------
// 🔹 Approach: Iterative BST Search
// --------------------------------------------
// ✅ Traverse the BST starting from the root.
// ✅ If current node value == key → return true
// ✅ If current node value < key → go to right child
// ✅ If current node value > key → go to left child
// ✅ Continue until null → return false (key not found)
//
// --------------------------------------------
// 🕒 Time Complexity: O(h)
//      where h = height of the BST
//      (Best: O(log N) for balanced BST, Worst: O(N) for skewed BST)
//
// 💾 Space Complexity: O(1)
//      No extra recursion stack or data structures used
// --------------------------------------------

// Definition for a Binary Tree Node
class Node {
    int data;
    Node left, right;

    Node(int item) {
        data = item;
        left = right = null;
    }
}

public class Solution {

    // Function to search for a key in BST
    public boolean search(Node root, int key) {
        while (root != null && root.data != key) {
            // move right if key is greater, else move left
            root = (root.data < key) ? root.right : root.left;
        }
        // if root becomes null → key not found
        return root != null;
    }
