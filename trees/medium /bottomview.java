/**
 * 🔹 Problem: Bottom View of Binary Tree
 * -----------------------------------
 * Given the root of a binary tree, print the bottom view of the tree.
 * The bottom view of a binary tree is the set of nodes visible 
 * when the tree is viewed from the bottom.
 *
 * You need to return the list of node values visible from the bottom, 
 * from the leftmost column to the rightmost column.
 *
 * 🔹 Example:
 * Input:
 *           20
 *          /  \
 *        8     22
 *       / \      \
 *      5   3      25
 *         / \
 *        10 14
 *
 * Output: [5, 10, 3, 14, 25]
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
 * Using a **TreeMap<Integer, Integer>**, we keep updating the value 
 * for each column whenever we encounter a new node.
 * 
 * This ensures that the last (bottommost) node for each column 
 * is stored in the map.
 * 
 * Finally, we iterate through the TreeMap (sorted by column) 
 * to construct the bottom view result list.
 *
 * 🔹 Note:
 * --------
 * If we wanted to print the *left side node first* when nodes 
 * are on the same column and level, we can add the **right child first** 
 * and then the **left child** into the queue.
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

    // ✅ Main function to return bottom view of binary tree
    public ArrayList<Integer> bottomView(Node root) {
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

            // ✅ For bottom view, always overwrite the node for each column
            map.put(col, node.data);

            // Add left and right children with updated columns
            if (node.left != null) {
                queue.offer(new CustomNode(node.left, col - 1));
            }
            if (node.right != null) {
                queue.offer(new CustomNode(node.right, col + 1));
            }

            /*
             ⚙️ NOTE:
             If you want the *left side node first* when two nodes are 
             on the same level and column, enqueue the RIGHT child before the LEFT child:
             
                 if (node.right != null) queue.offer(new CustomNode(node.right, col + 1));
                 if (node.left != null)  queue.offer(new CustomNode(node.left, col - 1));
             
             This ensures left nodes overwrite right ones later.
            */
        }

        // Add bottom view nodes from leftmost to rightmost
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            res.add(entry.getValue());
        }

        return res;
    }
}
