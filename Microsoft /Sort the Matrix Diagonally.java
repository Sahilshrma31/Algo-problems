/*
====================================================
📌 Problem: Sort the Matrix Diagonally
====================================================

Given an m x n matrix, sort each diagonal in ascending order.
A diagonal is defined by all elements having the same (row - column) value.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
Elements lying on the same diagonal have the same value of (i - j).

Steps:
1️⃣ Group elements by diagonal key = (i - j)
2️⃣ Sort each diagonal independently
3️⃣ Place elements back into the matrix from bottom-right to top-left
   so that we can pop the largest element efficiently

----------------------------------------------------
🧩 Approach
----------------------------------------------------
1. Traverse the matrix and store elements in a HashMap
   where key = (i - j) and value = list of diagonal elements.
2. Sort each list.
3. Traverse matrix from bottom-right and refill diagonals by removing
   last element (largest remaining).

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
Let m = rows, n = columns

• Grouping elements: O(m * n)
• Sorting diagonals: Overall O(m * n log(min(m, n)))
• Refilling matrix: O(m * n)

➡️ Total Time Complexity: **O(m * n log(min(m, n)))**

----------------------------------------------------
💾 Space Complexity
----------------------------------------------------
• HashMap storing all elements: O(m * n)

➡️ Total Space Complexity: **O(m * n)**

====================================================
*/

import java.util.*;

class Solution {
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        // Map to store diagonals: key = i - j
        Map<Integer, List<Integer>> map = new HashMap<>();

        // Step 1: Store elements by diagonals
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int key = i - j;
                map.putIfAbsent(key, new ArrayList<>());
                map.get(key).add(mat[i][j]);
            }
        }

        // Step 2: Sort each diagonal
        for (List<Integer> list : map.values()) {
            Collections.sort(list);
        }

        // Step 3: Fill matrix from bottom-right
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int key = i - j;
                List<Integer> list = map.get(key);
                mat[i][j] = list.remove(list.size() - 1);
            }
        }

        return mat;
    }
}
