/**
 * 🔹 Problem: Lowest Common Ancestor of a Binary Tree
 * --------------------------------------------------
 * Given a binary tree, find the lowest common ancestor (LCA) 
 * of two given nodes p and q.
 *
 * The LCA is defined as the lowest node in the tree that has both
 * p and q as descendants (where a node can be a descendant of itself).
 *
 * Example:
 * Input:
 *           3
 *         /   \
 *        5     1
 *       / \   / \
 *      6  2  0   8
 *        / \
 *       7   4
 *
 * p = 5, q = 1 → LCA = 3
 * p = 5, q = 4 → LCA = 5
 *
 * --------------------------------------------------
 * ✅ Approach 1 (Brute Force)
 * --------------------------------------------------
 * 1️⃣ Find path from root to node p.
 * 2️⃣ Find path from root to node q.
 * 3️⃣ Compare both paths until they differ.
 * 4️⃣ The last common node is the LCA.
 *
 * 🔹 Time Complexity: O(N)
 * 🔹 Space Complexity: O(N)
 *
 * --------------------------------------------------
 * ✅ Approach 2 (Optimized / Better Approach)
 * --------------------------------------------------
 * 1️⃣ Traverse tree recursively.
 * 2️⃣ If current node is null → return null.
 * 3️⃣ If node == p or node == q → return node.
 * 4️⃣ Recursively find in left and right subtrees.
 * 5️⃣ If both left and right are non-null → current node is LCA.
 * 6️⃣ Else return the non-null one.
 *
 * 🔹 Time Complexity: O(N)
 * 🔹 Space Complexity: O(H) → recursion stack (H = height of tree)
 */

import java.util.*;

// ✅ Binary Tree Node definition
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class LCA {

    // --------------------------------------------------
    // 🐢 Approach 1: Brute Force using Root-to-Node Paths
    // --------------------------------------------------
    public TreeNode lowestCommonAncestorBrute(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> path1 = new ArrayList<>();
        List<TreeNode> path2 = new ArrayList<>();

        // Get paths for both nodes
        getPath(root, p, path1);
        getPath(root, q, path2);

        // Compare both paths to find the last common node
        int i = 0;
        for (; i < path1.size() && i < path2.size(); i++) {
            if (path1.get(i) != path2.get(i)) break;
        }

        // The last common node before they differ
        return path1.get(i - 1);
    }

    private boolean getPath(TreeNode root, TreeNode target, List<TreeNode> path) {
        if (root == null) return false;

        path.add(root);

        if (root == target) return true;

        if (getPath(root.left, target, path) || getPath(root.right, target, path)) return true;

        // Backtrack if target not found in this path
        path.remove(path.size() - 1);
        return false;
    }

    // --------------------------------------------------
    // ⚡ Approach 2: Optimized Recursive (Better Approach)
    // --------------------------------------------------
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null) return right;
        if (right == null) return left;
        return root; // both sides are non-null → current node is LCA
    }

    // --------------------------------------------------
    // 🧠 Example usage (for testing)
    // --------------------------------------------------
    public static void main(String[] args) {
        /*
                 3
               /   \
              5     1
             / \   / \
            6  2  0   8
              / \
             7   4
        */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        TreeNode p = root.left;       // 5
        TreeNode q = root.left.right.right; // 4

        LCA sol = new LCA();
        TreeNode lca1 = sol.lowestCommonAncestorBrute(root, p, q);
        TreeNode lca2 = sol.lowestCommonAncestor(root, p, q);

        System.out.println("🔹 LCA (Brute Force): " + lca1.val);
        System.out.println("⚡ LCA (Optimized): " + lca2.val);
    }
}
