/**
 * 🔹 Problem: Top View of Binary Tree
 * -----------------------------------
 * Given the root of a binary tree, print the top view of the tree.
 * The top view of a binary tree is the set of nodes visible when the tree 
 * is viewed from the top.
 *
 * You need to return the list of node values visible from the top, 
 * from the leftmost column to the rightmost column.
 *
 * 🔹 Example:
 * Input:
 *           1
 *         /   \
 *        2     3
 *         \
 *          4
 *           \
 *            5
 *             \
 *              6
 *
 * Output: [2, 1, 3, 6]
 *
 * 🔹 Approach / Intuition:
 * ------------------------
 * We perform a **level order traversal (BFS)** using a queue while 
 * tracking each node’s **horizontal distance (col)** from the root.
 * 
 * - The root node starts at col = 0.
 * - For every left child, col = parent.col - 1
 * - For every right child, col = parent.col + 1
 * 
 * Using a **TreeMap<Integer, Integer>**, we store the first node 
 * encountered at each column because the top view shows only the 
 * topmost node of each vertical column.
 * 
 * Finally, we iterate through the TreeMap (sorted by column) 
 * to construct the result list.
 *
 * 🔹 Time Complexity:  O(N log N)
 *      → Because insertion in TreeMap takes log N and we visit all nodes.
 *
 * 🔹 Space Complexity: O(N)
 *      → For queue and TreeMap storage.
 */

import java.util.*;

// ✅ Binary Tree Node definition
class Node {
    int data;
    Node left, right;

    Node(int val) {
        this.data = val;
        this.left = null;
        this.right = null;
    }
}

class Solution {

    // Helper class to store node with its column
    class CustomNode {
        Node node;
        int col;
        CustomNode(Node node, int col) {
            this.node = node;
            this.col = col;
        }
    }

    // ✅ Main function to return top view of binary tree
    public ArrayList<Integer> topView(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<CustomNode> queue = new LinkedList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();

        // Start BFS traversal with root node at column 0
        queue.offer(new CustomNode(root, 0));

        while (!queue.isEmpty()) {
            CustomNode custom = queue.poll();
            Node node = custom.node;
            int col = custom.col;

            // If column not already present, this is the topmost node
            if (!map.containsKey(col)) {
                map.put(col, node.data);
            }

            // Add left and right children with updated columns
            if (node.left != null) {
                queue.offer(new CustomNode(node.left, col - 1));
            }
            if (node.right != null) {
                queue.offer(new CustomNode(node.right, col + 1));
            }
        }

        // Add top view nodes from leftmost to rightmost
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            res.add(entry.getValue());
        }

        return res;
    }
}
