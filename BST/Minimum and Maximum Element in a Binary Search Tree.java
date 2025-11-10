// 🔹 Problem: Find Minimum and Maximum Element in a Binary Search Tree (BST)
//
// --------------------------------------------
// 🔹 Intuition:
// In a BST:
//   - The minimum element lies in the **leftmost node**.
//   - The maximum element lies in the **rightmost node**.
//
// So, we simply traverse left (for min) or right (for max)
// until we reach a null child.
//
// --------------------------------------------
// 🕒 Time Complexity: O(h)
//     where h = height of the BST
//     (O(log N) for balanced BST, O(N) for skewed BST)
//
// 💾 Space Complexity: O(1)
//     Iterative approach → constant extra space
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

    // 🔹 Function to find minimum element in BST
    public int findMin(Node root) {
        if (root == null) {
            throw new IllegalArgumentException("Tree is empty");
        }
        while (root.left != null) {
            root = root.left;  // keep moving left
        }
        return root.data;
    }

    // 🔹 Function to find maximum element in BST
    public int findMax(Node root) {
        if (root == null) {
            throw new IllegalArgumentException("Tree is empty");
        }
        while (root.right != null) {
            root = root.right;  // keep moving right
        }
        return root.data;
    }

    // Example driver code
    public static void main(String[] args) {
        Node root = new Node(8);
        root.left = new Node(3);
        root.right = new Node(10);
        root.left.left = new Node(1);
        root.left.right = new Node(6);
        root.right.right = new Node(14);
        root.right.right.left = new Node(13);

        Solution sol = new Solution();

        System.out.println("Minimum element in BST: " + sol.findMin(root)); // ✅ 1
        System.out.println("Maximum element in BST: " + sol.findMax(root)); // ✅ 14
    }
}
