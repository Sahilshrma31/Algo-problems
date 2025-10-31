/**
 * 🔥 Problem: Amount of Time to Infect the Binary Tree
 * ---------------------------------------------------
 * LeetCode 2385 / GFG: Burning Tree
 * 
 * You are given the root of a binary tree and an integer `start` 
 * representing the value of the node where a fire starts.
 * 
 * In one second, the fire spreads from any burning node 
 * to its left child, right child, and parent node.
 * 
 * Return the minimum number of seconds required to burn the entire tree.
 *
 * ---------------------------------------------------
 * 🧠 Intuition:
 * ---------------------------------------------------
 * 1️⃣ We can think of this as a graph problem:
 *      - Each node is connected to its left, right, and parent.
 * 2️⃣ First, we create a parent map for all nodes using DFS.
 * 3️⃣ Then, we find the node from where fire starts (`start`).
 * 4️⃣ Finally, we perform a BFS starting from the `start` node.
 *      - Each BFS level represents 1 second of fire spread.
 * 5️⃣ The total number of BFS levels = minimum time to burn the entire tree.
 * 
 * ---------------------------------------------------
 * ⚙️ Approach:
 * ---------------------------------------------------
 * 1️⃣ Build a map of each node’s parent using DFS (`createParent`).
 * 2️⃣ Find the starting node with value == start (`findStart`).
 * 3️⃣ BFS traversal (`timeTaken`) from that start node:
 *      - Spread to left, right, and parent if not yet visited.
 *      - Use a flag `infected` to track if new nodes caught fire.
 *      - Increase time when infection spreads in that round.
 * 
 * ---------------------------------------------------
 * ⏱️ Time Complexity:
 * O(N) — We visit each node once to:
 *   • Build parent map (DFS) → O(N)
 *   • BFS traversal to spread fire → O(N)
 * 
 * ---------------------------------------------------
 * 💾 Space Complexity:
 * O(N) — For:
 *   • Parent map
 *   • Queue
 *   • Visited set
 * 
 * ---------------------------------------------------
 * ✅ Example:
 * Input:
 *      1
 *     / \
 *    5   3
 *     \  / \
 *     4 10  6
 *    / \
 *   9   2
 * start = 3
 *
 * Output: 4
 *
 * Explanation:
 * Fire spreads in this order:
 * 3 → (10,6,1) → (5) → (4) → (9,2)
 */

import java.util.*;

public class BurningTree {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    static class Solution {
        Map<TreeNode, TreeNode> map = new HashMap<>();

        public int amountOfTime(TreeNode root, int start) {
            if (root == null) return 0;

            createParent(root);
            TreeNode startNode = findStart(root, start);
            return timeTaken(startNode);
        }

        // BFS to simulate fire spreading
        public int timeTaken(TreeNode startNode) {
            Queue<TreeNode> q = new LinkedList<>();
            Set<TreeNode> visited = new HashSet<>();
            int time = 0;

            q.offer(startNode);
            visited.add(startNode);

            while (!q.isEmpty()) {
                int size = q.size();
                boolean infected = false;

                for (int i = 0; i < size; i++) {
                    TreeNode node = q.poll();

                    // spread to left child
                    if (node.left != null && !visited.contains(node.left)) {
                        infected = true;
                        visited.add(node.left);
                        q.add(node.left);
                    }

                    // spread to right child
                    if (node.right != null && !visited.contains(node.right)) {
                        infected = true;
                        visited.add(node.right);
                        q.add(node.right);
                    }

                    // spread to parent
                    if (map.containsKey(node) && !visited.contains(map.get(node))) {
                        infected = true;
                        visited.add(map.get(node));
                        q.add(map.get(node));
                    }
                }

                if (infected) time++;
            }

            return time;
        }

        // DFS to create parent mapping
        public void createParent(TreeNode root) {
            if (root == null) return;

            if (root.left != null) map.put(root.left, root);
            if (root.right != null) map.put(root.right, root);

            createParent(root.left);
            createParent(root.right);
        }

        // DFS to find start node
        public TreeNode findStart(TreeNode root, int start) {
            if (root == null) return null;
            if (root.val == start) return root;

            TreeNode left = findStart(root.left, start);
            if (left != null) return left;

            return findStart(root.right, start);
        }
    }

    // 🔹 Example Usage
    public static void main(String[] args) {
        BurningTree.Solution sol = new BurningTree.Solution();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(6);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(9);
        root.left.right.right = new TreeNode(2);

        int start = 3;
        System.out.println("Minimum time to burn tree = " + sol.amountOfTime(root, start));
    }
}
