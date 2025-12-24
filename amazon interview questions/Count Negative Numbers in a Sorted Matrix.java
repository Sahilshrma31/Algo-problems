/*
====================================================
📌 Problem: Count Negative Numbers in a Sorted Matrix
====================================================

You are given an m x n matrix `grid` where:
- Each row is sorted in non-increasing order
- Each column is sorted in non-increasing order

Your task is to count the number of negative numbers
in the matrix.

----------------------------------------------------
🧠 Key Observations
----------------------------------------------------
• Rows and columns are sorted.
• Negative numbers appear towards the right
  and bottom of the matrix.

----------------------------------------------------
🧩 Approaches Covered
----------------------------------------------------
1️⃣ Staircase Traversal (Optimal)
2️⃣ Binary Search on Each Row

----------------------------------------------------
⏱ Time & Space Complexity
----------------------------------------------------

Approach 1: Staircase
• Time: O(m + n)
• Space: O(1)

Approach 2: Binary Search per Row
• Time: O(m log n)
• Space: O(1)

====================================================
*/

class Solution {

    /* ====================================================
       1️⃣ STAIRCASE APPROACH (Bottom-Left Traversal)
       ==================================================== */

    public int countNegativesStaircase(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int r = m - 1; // start from bottom-left
        int c = 0;
        int count = 0;

        while (r >= 0 && c < n) {
            if (grid[r][c] < 0) {
                // All elements to the right are also negative
                count += (n - c);
                r--; // move up
            } else {
                c++; // move right
            }
        }
        return count;
    }

    /* ====================================================
       2️⃣ BINARY SEARCH ON EACH ROW
       ==================================================== */

    public int countNegativesBinarySearch(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            int left = 0, right = n - 1;
            int firstNegativeIndex = n;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (grid[i][mid] < 0) {
                    firstNegativeIndex = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            count += (n - firstNegativeIndex);
        }

        return count;
    }

    /* ====================================================
       MAIN METHOD (Default Choice: Optimal Staircase)
       ==================================================== */

    public int countNegatives(int[][] grid) {
        return countNegativesStaircase(grid);
    }
}
