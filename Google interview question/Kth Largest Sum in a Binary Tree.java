/*
====================================================
📌 Problem: Kth Largest Sum in a Binary Tree
(LeetCode 2583)
====================================================

You are given the root of a binary tree and an integer k.

For each level of the tree, compute the sum of values
of all nodes at that level.

Return the k-th largest level sum.
If there are fewer than k levels, return -1.

----------------------------------------------------
🧠 Approach: Level Order Traversal + Min Heap
----------------------------------------------------
1️⃣ Traverse the tree level by level using BFS
2️⃣ Compute sum of each level
3️⃣ Maintain a min-heap of size k to store the
   largest k level sums seen so far
4️⃣ The top of the heap will be the k-th largest sum

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n log k)
- n nodes are traversed
- each level sum insertion/removal costs log k

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(n) for queue + O(k) for heap

====================================================
*/

import java.util.*;

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {

    public long kthLargestLevelSum(TreeNode root, int k) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // Min-heap to keep top k largest level sums
        PriorityQueue<Long> minHeap = new PriorityQueue<>();

        while (!queue.isEmpty()) {

            int size = queue.size();
            long levelSum = 0;

            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                levelSum += curr.val;

                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }

            // Maintain heap size = k
            minHeap.offer(levelSum);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // If less than k levels exist
        return minHeap.size() < k ? -1 : minHeap.peek();
    }
}
