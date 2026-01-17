/*
====================================================
📌 Problem: Find Duplicate Subtrees
(LeetCode 652)
====================================================

Given the root of a binary tree, return all **duplicate subtrees**.

Two subtrees are duplicate if:
- They have the same structure
- They have the same node values

For each duplicate subtree, return **only one root node**.

====================================================
🧠 Approach: DFS + Subtree Serialization
====================================================

Core idea:
- Serialize each subtree into a unique string
- Use a HashMap to count how many times a serialization appears

Steps:
1️⃣ Perform DFS traversal
2️⃣ For each node, build a string:
   root.val,leftSubtree,rightSubtree
3️⃣ Store the serialization in a map
4️⃣ If a serialization appears for the **second time**
   → add that subtree root to result

Why second time?
- To avoid adding the same duplicate multiple times

====================================================
⏱ Time Complexity
====================================================
O(n)

Each node is visited once and serialization is stored.

====================================================
📦 Space Complexity
====================================================
O(n)

- HashMap for subtree strings
- Recursion stack

====================================================
*/

import java.util.*;

class Solution {

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {

        Map<String, Integer> map = new HashMap<>();
        List<TreeNode> result = new ArrayList<>();

        dfs(root, map, result);

        return result;
    }

    private String dfs(TreeNode root,
                       Map<String, Integer> map,
                       List<TreeNode> result) {

        if (root == null) {
            return "NULL";
        }

        String serial =
                root.val + "," +
                dfs(root.left, map, result) + "," +
                dfs(root.right, map, result);

        // If seen exactly once before, this is the second time → duplicate
        if (map.getOrDefault(serial, 0) == 1) {
            result.add(root);
        }

        map.put(serial, map.getOrDefault(serial, 0) + 1);

        return serial;
    }
}
