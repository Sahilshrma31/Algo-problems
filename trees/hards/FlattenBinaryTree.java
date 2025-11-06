// LeetCode 114: Flatten Binary Tree to Linked List
// Author: Striver's Approach (Recursive & Iterative)
// Intuition, Approach, TC, SC included

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class FlattenBinaryTree {

    // ----------------------------
    // 🌳 Approach 1: Recursive (Reverse Preorder)
    // ----------------------------
    // Intuition:
    //   - We traverse in reverse preorder (Right -> Left -> Root)
    //   - Maintain a 'prev' pointer which points to the previously processed node.
    //   - For each node, make right = prev and left = null.
    //   - This flattens the tree into a linked list in preorder order.
    //
    // Time Complexity: O(N)
    // Space Complexity: O(H) due to recursion stack (H = height of tree)

    static class SolutionRecursive {
        private TreeNode prev = null;

        public void flatten(TreeNode root) {
            if (root == null) return;

            // Step 1: Flatten right subtree first
            flatten(root.right);
            // Step 2: Flatten left subtree
            flatten(root.left);

            // Step 3: Rearrange pointers
            root.right = prev;
            root.left = null;
            prev = root;
        }
    }

    // ----------------------------
    // ⚡ Approach 2: Iterative (Morris Traversal)
    // ----------------------------
    // Intuition:
    //   - For each node, if it has a left child, find the rightmost node of the left subtree.
    //   - Attach the current node's right subtree to that rightmost node.
    //   - Move left subtree to right and set left = null.
    //   - Repeat for the next right node.
    //
    // Time Complexity: O(N)
    // Space Complexity: O(1)

    static class SolutionIterative {
        public void flatten(TreeNode root) {
            TreeNode curr = root;

            while (curr != null) {
                if (curr.left != null) {
                    TreeNode prev = curr.left;

                    // Find the rightmost node of left subtree
                    while (prev.right != null) {
                        prev = prev.right;
                    }

                    // Attach original right subtree
                    prev.right = curr.right;

                    // Move left subtree to right
                    curr.right = curr.left;
                    curr.left = null;
                }
                curr = curr.right;
            }
        }
    }

    // ----------------------------
    // ✅ Example Usage (Test)
    // ----------------------------
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);

        // Using Recursive solution
        SolutionRecursive recursive = new SolutionRecursive();
        recursive.flatten(root);

        // Print flattened tree
        TreeNode curr = root;
        System.out.print("Flattened Tree (Recursive): ");
        while (curr != null) {
            System.out.print(curr.val + " -> ");
            curr = curr.right;
        }
        System.out.println("null");

        // Recreate tree for iterative test
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);

        // Using Iterative solution
        SolutionIterative iterative = new SolutionIterative();
        iterative.flatten(root);

        // Print flattened tree
        curr = root;
        System.out.print("Flattened Tree (Iterative): ");
        while (curr != null) {
            System.out.print(curr.val + " -> ");
            curr = curr.right;
        }
        System.out.println("null");
    }
}