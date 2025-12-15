/*
=========================================================
🍒 Problem: Cherry Pickup II
=========================================================

You are given an m x n grid where each cell contains
some cherries.

Two robots start from:
- Robot 1 at (0, 0)
- Robot 2 at (0, n-1)

Both robots move from top row to bottom row.

At each step, a robot can move to:
- down-left  (j - 1)
- down       (j)
- down-right (j + 1)

If both robots land on the same cell,
cherries are counted only once.

Your task is to return the maximum number of cherries
both robots can collect.

---------------------------------------------------------
🧠 Intuition (Striver Style)
---------------------------------------------------------
At each row, we need to know:
- column of robot1 (j1)
- column of robot2 (j2)

State:
dp[i][j1][j2] = maximum cherries collected
from row i to last row when
robot1 is at column j1 and robot2 at j2.

From each state, both robots have 3 moves each,
so total 3 × 3 = 9 transitions.

---------------------------------------------------------
🧩 Approaches Covered
---------------------------------------------------------
1️⃣ Recursion (Brute Force)
2️⃣ Memoization (Top-Down DP)
3️⃣ Tabulation (Bottom-Up DP)
4️⃣ Space Optimized DP

---------------------------------------------------------
⏱ Time & Space Complexity
---------------------------------------------------------
Let m = rows, n = columns

1️⃣ Recursion:
   Time: O(9^m)
   Space: O(m)

2️⃣ Memoization:
   Time: O(m * n * n * 9)
   Space: O(m * n * n)

3️⃣ Tabulation:
   Time: O(m * n * n * 9)
   Space: O(m * n * n)

4️⃣ Space Optimized:
   Time: O(m * n * n * 9)
   Space: O(n * n)

=========================================================
*/

import java.util.*;

class Solution {

    /* ==========================
       1️⃣ RECURSIVE APPROACH
       ========================== */

    public int cherryPickupRecursive(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        return helperRec(grid, 0, 0, n - 1);
    }

    private int helperRec(int[][] grid, int i, int j1, int j2) {
        int m = grid.length;
        int n = grid[0].length;

        if (j1 < 0 || j2 < 0 || j1 >= n || j2 >= n)
            return (int) -1e9;

        if (i == m - 1) {
            if (j1 == j2) return grid[i][j1];
            return grid[i][j1] + grid[i][j2];
        }

        int maxi = (int) -1e9;

        for (int dj1 = -1; dj1 <= 1; dj1++) {
            for (int dj2 = -1; dj2 <= 1; dj2++) {
                int value = (j1 == j2)
                        ? grid[i][j1]
                        : grid[i][j1] + grid[i][j2];

                value += helperRec(grid, i + 1, j1 + dj1, j2 + dj2);
                maxi = Math.max(maxi, value);
            }
        }
        return maxi;
    }

    /* ==========================
       2️⃣ MEMOIZATION
       ========================== */

    public int cherryPickupMemo(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] dp = new int[m][n][n];

        for (int[][] layer : dp)
            for (int[] row : layer)
                Arrays.fill(row, -1);

        return helperMemo(grid, 0, 0, n - 1, dp);
    }

    private int helperMemo(int[][] grid, int i, int j1, int j2, int[][][] dp) {
        int m = grid.length;
        int n = grid[0].length;

        if (j1 < 0 || j2 < 0 || j1 >= n || j2 >= n)
            return (int) -1e9;

        if (i == m - 1) {
            if (j1 == j2) return grid[i][j1];
            return grid[i][j1] + grid[i][j2];
        }

        if (dp[i][j1][j2] != -1)
            return dp[i][j1][j2];

        int maxi = (int) -1e9;

        for (int dj1 = -1; dj1 <= 1; dj1++) {
            for (int dj2 = -1; dj2 <= 1; dj2++) {
                int value = (j1 == j2)
                        ? grid[i][j1]
                        : grid[i][j1] + grid[i][j2];

                value += helperMemo(grid, i + 1, j1 + dj1, j2 + dj2, dp);
                maxi = Math.max(maxi, value);
            }
        }

        return dp[i][j1][j2] = maxi;
    }

    /* ==========================
       3️⃣ TABULATION
       ========================== */

    public int cherryPickupTabulation(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] dp = new int[m][n][n];

        // Base case (last row)
        for (int j1 = 0; j1 < n; j1++) {
            for (int j2 = 0; j2 < n; j2++) {
                if (j1 == j2)
                    dp[m - 1][j1][j2] = grid[m - 1][j1];
                else
                    dp[m - 1][j1][j2] = grid[m - 1][j1] + grid[m - 1][j2];
            }
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j1 = 0; j1 < n; j1++) {
                for (int j2 = 0; j2 < n; j2++) {
                    int maxi = (int) -1e9;

                    for (int dj1 = -1; dj1 <= 1; dj1++) {
                        for (int dj2 = -1; dj2 <= 1; dj2++) {
                            int nj1 = j1 + dj1;
                            int nj2 = j2 + dj2;

                            if (nj1 >= 0 && nj2 >= 0 && nj1 < n && nj2 < n) {
                                int value = (j1 == j2)
                                        ? grid[i][j1]
                                        : grid[i][j1] + grid[i][j2];

                                value += dp[i + 1][nj1][nj2];
                                maxi = Math.max(maxi, value);
                            }
                        }
                    }
                    dp[i][j1][j2] = maxi;
                }
            }
        }
        return dp[0][0][n - 1];
    }

    /* ==========================
       4️⃣ SPACE OPTIMIZED
       ========================== */

    public int cherryPickupSpaceOptimized(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] front = new int[n][n];

        // Base case
        for (int j1 = 0; j1 < n; j1++) {
            for (int j2 = 0; j2 < n; j2++) {
                if (j1 == j2)
                    front[j1][j2] = grid[m - 1][j1];
                else
                    front[j1][j2] = grid[m - 1][j1] + grid[m - 1][j2];
            }
        }

        for (int i = m - 2; i >= 0; i--) {
            int[][] curr = new int[n][n];

            for (int j1 = 0; j1 < n; j1++) {
                for (int j2 = 0; j2 < n; j2++) {
                    int maxi = (int) -1e9;

                    for (int dj1 = -1; dj1 <= 1; dj1++) {
                        for (int dj2 = -1; dj2 <= 1; dj2++) {
                            int nj1 = j1 + dj1;
                            int nj2 = j2 + dj2;

                            if (nj1 >= 0 && nj2 >= 0 && nj1 < n && nj2 < n) {
                                int value = (j1 == j2)
                                        ? grid[i][j1]
                                        : grid[i][j1] + grid[i][j2];

                                value += front[nj1][nj2];
                                maxi = Math.max(maxi, value);
                            }
                        }
                    }
                    curr[j1][j2] = maxi;
                }
            }
            front = curr;
        }
        return front[0][n - 1];
    }
}
