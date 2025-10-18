/*
 * 🧩 Problem: Binary Tree Depth First Traversals
 *
 * Given the root of a binary tree, perform all three depth-first traversals:
 *    1. Preorder  (Root ➜ Left ➜ Right)
 *    2. Inorder   (Left ➜ Root ➜ Right)
 *    3. Postorder (Left ➜ Right ➜ Root)
 *
 * ------------------------------------------------------------
 * 💡 Intuition:
 *
 * DFS (Depth First Search) explores nodes as deep as possible before backtracking.
 * There are three main ways to order the traversal based on when we process the root node.
 *
 * ➤ Preorder: Process root before its children.
 * ➤ Inorder: Process root between its children.
 * ➤ Postorder: Process root after its children.
 *
 * Both recursive and iterative approaches are shown below.
 * ------------------------------------------------------------
 * ⏱️ Time Complexity: O(N)
 * Every node is visited once.
 *
 * 💾 Space Complexity:
 * ➤ Recursive: O(H) due to function call stack.
 * ➤ Iterative: O(H) due to explicit stack.
 * H = height of the tree.
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

public class DFSTraversals {

    /* ------------------------------------------------------------
       ✅ 1. PREORDER TRAVERSAL (Root ➜ Left ➜ Right)
       ------------------------------------------------------------ */
    // Recursive
    public static void preorderRecursive(Node root, List<Integer> res) {
        if (root == null) return;
        res.add(root.data);
        preorderRecursive(root.left, res);
        preorderRecursive(root.right, res);
    }

    // Iterative
    public static List<Integer> preorderIterative(Node root) {
        List<Integer> res = new ArrayList<>();
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

    /* ------------------------------------------------------------
       ✅ 2. INORDER TRAVERSAL (Left ➜ Root ➜ Right)
       ------------------------------------------------------------ */
    // Recursive
    public static void inorderRecursive(Node root, List<Integer> res) {
        if (root == null) return;
        inorderRecursive(root.left, res);
        res.add(root.data);
        inorderRecursive(root.right, res);
    }

    // Iterative
    public static List<Integer> inorderIterative(Node root) {
        List<Integer> res = new ArrayList<>();
        Stack<Node> st = new Stack<>();
        Node curr = root;

        while (curr != null || !st.isEmpty()) {
            while (curr != null) {
                st.push(curr);
                curr = curr.left;
            }
            curr = st.pop();
            res.add(curr.data);
            curr = curr.right;
        }
        return res;
    }

    /* ------------------------------------------------------------
       ✅ 3. POSTORDER TRAVERSAL (Left ➜ Right ➜ Root)
       ------------------------------------------------------------ */
    // Recursive
    public static void postorderRecursive(Node root, List<Integer> res) {
        if (root == null) return;
        postorderRecursive(root.left, res);
        postorderRecursive(root.right, res);
        res.add(root.data);
    }

    // Iterative (Using two stacks)
    public static List<Integer> postorderIterative(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Stack<Node> st1 = new Stack<>();
        Stack<Node> st2 = new Stack<>();
        st1.push(root);

        while (!st1.isEmpty()) {
            Node curr = st1.pop();
            st2.push(curr);

            if (curr.left != null) st1.push(curr.left);
            if (curr.right != null) st1.push(curr.right);
        }

        while (!st2.isEmpty()) {
            res.add(st2.pop().data);
        }

        return res;
    }

    /* ------------------------------------------------------------
       🧪 MAIN METHOD (For Testing)
       ------------------------------------------------------------ */
    public static void main(String[] args) {
        /*
                 1
               /   \
              2     3
             / \   / \
            4   5 6   7
        */
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        List<Integer> preorder = preorderIterative(root);
        List<Integer> inorder = inorderIterative(root);
        List<Integer> postorder = postorderIterative(root);

        System.out.println("Preorder  : " + preorder);
        System.out.println("Inorder   : " + inorder);
        System.out.println("Postorder : " + postorder);
    }
}
