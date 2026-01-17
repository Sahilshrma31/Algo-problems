/*
====================================================
📌 Problem: Find Mode in Binary Search Tree
(LeetCode 501)
====================================================

Given the root of a Binary Search Tree (BST),
return all the **mode(s)** (most frequently occurring value(s)).

A BST may contain duplicate values.

====================================================
🧠 Key BST Property Used
====================================================

- Inorder traversal of a BST gives values in **sorted order**
- Duplicate values appear **consecutively**

This allows counting frequencies **without extra space**

====================================================
🧠 Approach: Inorder DFS with Frequency Tracking
====================================================

Maintain:
- `currNum`     → current value being processed
- `currStreak` → frequency of current value
- `maxStreak`  → highest frequency seen so far
- `result`     → list of mode values

Steps during inorder traversal:
1️⃣ If current value == previous value → increment streak  
2️⃣ Else → reset streak for new value  
3️⃣ If current streak > maxStreak → clear result & update  
4️⃣ If current streak == maxStreak → add value to result  

====================================================
⏱ Time Complexity
====================================================
O(n)

====================================================
📦 Space Complexity
====================================================
O(h)

h = height of BST (recursion stack)

====================================================
*/

import java.util.*;

class Solution {

    private int currNum;
    private int currStreak;
    private int maxStreak;
    private List<Integer> result = new ArrayList<>();

    private void dfs(TreeNode root) {

        if (root == null) {
            return;
        }

        // Inorder traversal
        dfs(root.left);

        // Process current node
        if (root.val == currNum) {
            currStreak++;
        } else {
            currNum = root.val;
            currStreak = 1;
        }

        if (currStreak > maxStreak) {
            maxStreak = currStreak;
            result.clear();
        }

        if (currStreak == maxStreak) {
            result.add(root.val);
        }

        dfs(root.right);
    }

    public int[] findMode(TreeNode root) {

        // Initialize
        currStreak = 0;
        maxStreak = 0;

        dfs(root);

        // Convert List to Array
        int[] ans = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }

        return ans;
    }
}
