/**
 * 🔹 Problem: 863. All Nodes Distance K in Binary Tree
 *
 * 🧩 Problem Summary:
 * You are given the root of a binary tree, a target node, and an integer k.
 * Return a list of all the node values that are exactly distance k away from the target node.
 *
 * Distance between two nodes is defined as the number of edges on the shortest path between them.
 *
 * Example:
 * Input:
 *        3
 *       / \
 *      5   1
 *     / \  / \
 *    6  2 0  8
 *      / \
 *     7  4
 *
 * target = 5, k = 2
 * Output = [7, 4, 1]
 *
 * 💡 Intuition:
 * 1. The binary tree has no parent references — we can only move down (left/right).
 * 2. To move both up and down, we first create a mapping from each node to its parent.
 * 3. Once we know each node’s parent, we can perform a BFS starting from the target node,
 *    moving in all three directions (left, right, parent).
 * 4. When our BFS level equals k, all nodes in the queue are exactly k distance away.
 *
 * 🧠 Approach:
 * 1. Use DFS (`addParent`) to store the parent of each node in a HashMap.
 * 2. Use BFS (`getNodes`) starting from target:
 *    - Keep track of visited nodes to avoid cycles.
 *    - At each level of BFS, reduce k by 1.
 *    - Stop when k == 0, and collect all node values in the queue.
 *
 * ⏱️ Time Complexity:
 * - O(N): Each node is visited once during DFS and once during BFS.
 *
 * 💾 Space Complexity:
 * - O(N): For the parent map, queue, and visited set.
 */

import java.util.*;

public class DistanceKInBinaryTree {

    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    class Solution {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();

        public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
            List<Integer> res = new ArrayList<>();
            if (root == null) return res;

            addParent(root);
            getNodes(target, k, res);
            return res;
        }

        // DFS to store each node's parent
        private void addParent(TreeNode root) {
            if (root == null) return;

            if (root.left != null) {
                parentMap.put(root.left, root);
                addParent(root.left);
            }

            if (root.right != null) {
                parentMap.put(root.right, root);
                addParent(root.right);
            }
        }

        // BFS to find nodes at distance k
        private void getNodes(TreeNode target, int k, List<Integer> res) {
            Queue<TreeNode> q = new LinkedList<>();
            Set<TreeNode> visited = new HashSet<>();
            q.offer(target);
            visited.add(target);

            while (!q.isEmpty()) {
                int size = q.size();
                if (k == 0) break;

                while (size-- > 0) {
                    TreeNode curr = q.poll();

                    if (curr.left != null && !visited.contains(curr.left)) {
                        q.add(curr.left);
                        visited.add(curr.left);
                    }

                    if (curr.right != null && !visited.contains(curr.right)) {
                        q.add(curr.right);
                        visited.add(curr.right);
                    }

                    if (parentMap.containsKey(curr) && !visited.contains(parentMap.get(curr))) {
                        q.add(parentMap.get(curr));
                        visited.add(parentMap.get(curr));
                    }
                }
                k--;
            }

            while (!q.isEmpty()) {
                res.add(q.poll().val);
            }
        }
    }
}
