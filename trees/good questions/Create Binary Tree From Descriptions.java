/*
====================================================
📌 Problem: Create Binary Tree From Descriptions
(LeetCode 2196)
====================================================

You are given an array `descriptions` where:
descriptions[i] = [parent, child, isLeft]

- parent → value of parent node
- child  → value of child node
- isLeft = 1 → child is left child
- isLeft = 0 → child is right child

All node values are unique.
Return the **root of the constructed binary tree**.

====================================================
🧠 Approach: HashMap + HashSet
====================================================

Key observations:
- Every node appears either as a parent or a child
- The **root** is the only node that is NEVER a child

Steps:
1️⃣ Use a HashMap to store value → TreeNode mapping  
2️⃣ Use a HashSet to track all child nodes  
3️⃣ For each description:
   - Create parent & child nodes if they don’t exist
   - Attach child to parent (left/right based on isLeft)
   - Mark child in the set
4️⃣ Iterate again to find the node that is NOT in child set
   → this node is the root

====================================================
⏱ Time Complexity
====================================================
O(n)

n = number of descriptions

====================================================
📦 Space Complexity
====================================================
O(n)

- HashMap for nodes
- HashSet for child tracking

====================================================
*/

import java.util.*;

class Solution {

    // value -> TreeNode
    HashMap<Integer, TreeNode> map = new HashMap<>();

    // stores all nodes that appear as children
    HashSet<Integer> childSet = new HashSet<>();

    public TreeNode createBinaryTree(int[][] descriptions) {

        // Step 1: Build nodes and connections
        for (int[] d : descriptions) {

            int parent = d[0];
            int child = d[1];
            boolean isLeft = d[2] == 1;

            childSet.add(child);

            map.putIfAbsent(parent, new TreeNode(parent));
            map.putIfAbsent(child, new TreeNode(child));

            if (isLeft) {
                map.get(parent).left = map.get(child);
            } else {
                map.get(parent).right = map.get(child);
            }
        }

        // Step 2: Find root (node that never appeared as child)
        for (int[] d : descriptions) {
            int parent = d[0];
            if (!childSet.contains(parent)) {
                return map.get(parent);
            }
        }

        return null;
    }
}
