/*
 * 🧩 Problem: Binary Tree Preorder Traversal (Iterative)
 *
 * Given the root of a binary tree, return the preorder traversal of its nodes' values.
 * Preorder traversal means visiting nodes in this order:
 * Root ➜ Left ➜ Right
 *
 * Example:
 * Input:
 *        1
 *       / \
 *      2   3
 *     / \
 *    4   5
 *
 * Output: [1, 2, 4, 5, 3]
 *
 * ------------------------------------------------------------
 * 💡 Intuition:
 *
 * Preorder traversal visits the root first, then explores the left subtree,
 * and finally the right subtree. To do this iteratively, we use a Stack.
 *
 * Since a Stack is LIFO (Last In, First Out):
 *  → We push the right child first, then the left child.
 *  → This ensures the left child is processed first when popped.
 *
 * ------------------------------------------------------------
 * 🧠 Approach:
 * 1. Create an empty stack and push the root node.
 * 2. While the stack is not empty:
 *      ➤ Pop the top node and add its value to the result list.
 *      ➤ Push its right child first (if exists).
 *      ➤ Push its left child next (if exists).
 * 3. The result list now contains nodes in preorder sequence.
 *
 * ------------------------------------------------------------
 * ⏱️ Time Complexity: O(N)
 * Each node is pushed and popped once.
 *
 * 💾 Space Complexity: O(H)
 * H = height of tree (stack stores nodes on one path at a time).
 * ------------------------------------------------------------
 */

import java.util.*;

class Node {
    int data;
    Node left, right;
    Node(int data) {
        this.data = data;
        left = right = null;
    }
}

public class PreorderTraversal {
    ArrayList<Integer> preorder(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Stack<Node> st = new Stack<>();
        st.push(root);

        while (!st.isEmpty()) {
            Node curr = st.pop();
            res.add(curr.data);

            if (curr.right != null) st.push(curr.right);
            if (curr.left != null) st.push(curr.left);
        }

        return res;
    }
}
