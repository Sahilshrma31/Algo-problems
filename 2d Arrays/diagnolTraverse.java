/*
LeetCode 498. Diagonal Traverse

🔹 Problem Summary:
Given an m x n matrix, return all elements of the matrix in diagonal order.
We start from the top-left element and move diagonally upward-right and downward-left in a zig-zag pattern.

Example:
Input:
mat = [[1,2,3],
       [4,5,6],
       [7,8,9]]

Output:
[1,2,4,7,5,3,6,8,9]

---

🔹 Intuition:
- Each diagonal in the matrix is uniquely identified by (row + col).
- Group all elements with the same (i+j) together.
- If the diagonal index is even, reverse its order (zig-zag requirement).
- If the diagonal index is odd, keep it in normal order.
- Append all diagonals into the final result.

---

🔹 Approach:
1. Traverse the matrix and use a HashMap to group elements by (i+j).
2. Iterate over the diagonals from 0 to (m+n-2).
3. Reverse diagonals when needed (for zig-zag).
4. Collect all elements into a result list, then convert to int[].

---

🔹 Complexity:
- Time Complexity: O(m*n) → Each element is visited once.
- Space Complexity: O(m*n) → HashMap stores all diagonals.

*/

import java.util.*;

class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        // Step 1: Group by i+j
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!map.containsKey(i + j)) {
                    map.put(i + j, new ArrayList<>());
                }
                map.get(i + j).add(mat[i][j]);
            }
        }

        // Step 2: Traverse diagonals with zig-zag pattern
        ArrayList<Integer> res = new ArrayList<>();
        boolean flip = true;
        for (int k = 0; k <= m + n - 2; k++) {
            ArrayList<Integer> diagonal = map.get(k);
            if (diagonal == null) continue;

            if (flip) {
                Collections.reverse(diagonal);
            }
            res.addAll(diagonal);
            flip = !flip;
        }

        // Step 3: Convert result list to int[]
        int[] result = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        return result;
    }
}
