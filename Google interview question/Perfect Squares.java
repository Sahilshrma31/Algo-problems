/*
====================================================
📌 Problem: Perfect Squares
(LeetCode 279)
====================================================

Given an integer `n`, return the **least number of perfect square
numbers** (1, 4, 9, 16, ...) that sum to `n`.

====================================================
🧠 Intuition
====================================================

This is a classic **Unbounded Knapsack / Coin Change** problem:
- Perfect squares ≤ n act like "coins"
- We want the **minimum number of coins** to make sum = n

====================================================
🧠 All Approaches Covered
====================================================

1️⃣ Recursion (Brute Force)  
2️⃣ Recursion + Memoization (Top-Down DP)  
3️⃣ Tabulation (2D DP)  
4️⃣ Space Optimized DP (1D DP)   BEST

====================================================
*/

import java.util.*;

class Solution {

    /*
    ====================================================
    1️⃣ RECURSION (Brute Force)  TLE
    ====================================================
    Time Complexity: Exponential
    Space Complexity: O(n) recursion stack
    ====================================================
    */

    private int solveRec(int idx, int target, int[] squares) {

        if (target == 0) return 0;
        if (idx == 0) {
            if (target % squares[0] == 0)
                return target / squares[0];
            return (int) 1e9;
        }

        int notPick = solveRec(idx - 1, target, squares);
        int pick = (int) 1e9;

        if (squares[idx] <= target) {
            pick = 1 + solveRec(idx, target - squares[idx], squares);
        }

        return Math.min(pick, notPick);
    }

    /*
    ====================================================
    2️⃣ MEMOIZATION (Top-Down DP)
    ====================================================
    Time Complexity: O(n * √n)
    Space Complexity: O(n * √n) + recursion stack
    ====================================================
    */

    private int solveMemo(int idx, int target, int[] squares, int[][] dp) {

        if (target == 0) return 0;
        if (idx == 0) {
            if (target % squares[0] == 0)
                return target / squares[0];
            return (int) 1e9;
        }

        if (dp[idx][target] != -1) return dp[idx][target];

        int notPick = solveMemo(idx - 1, target, squares, dp);
        int pick = (int) 1e9;

        if (squares[idx] <= target) {
            pick = 1 + solveMemo(idx, target - squares[idx], squares, dp);
        }

        return dp[idx][target] = Math.min(pick, notPick);
    }

    /*
    ====================================================
    3️⃣ TABULATION (2D DP)
    ====================================================
    Time Complexity: O(n * √n)
    Space Complexity: O(n * √n)
    ====================================================
    */

    private int solveTab(int n, int[] squares) {

        int len = squares.length;
        int[][] dp = new int[len][n + 1];

        // Base case: target = 0
        for (int i = 0; i < len; i++) dp[i][0] = 0;

        // First row
        for (int t = 1; t <= n; t++) {
            if (t % squares[0] == 0)
                dp[0][t] = t / squares[0];
            else
                dp[0][t] = (int) 1e9;
        }

        // Fill DP table
        for (int i = 1; i < len; i++) {
            for (int t = 1; t <= n; t++) {
                int notPick = dp[i - 1][t];
                int pick = (int) 1e9;
                if (squares[i] <= t) {
                    pick = 1 + dp[i][t - squares[i]];
                }
                dp[i][t] = Math.min(pick, notPick);
            }
        }

        return dp[len - 1][n];
    }

    /*
    ====================================================
    4️⃣ SPACE OPTIMIZED DP (1D)  BEST
    ====================================================
    Time Complexity: O(n * √n)
    Space Complexity: O(n)
    ====================================================
    */

    private int solveSpaceOptimized(int n, int[] squares) {

        int[] dp = new int[n + 1];
        Arrays.fill(dp, (int) 1e9);
        dp[0] = 0;

        for (int sq : squares) {
            for (int t = sq; t <= n; t++) {
                dp[t] = Math.min(dp[t], 1 + dp[t - sq]);
            }
        }

        return dp[n];
    }

    /*
    ====================================================
     MAIN FUNCTION (LeetCode Expected)
    ====================================================
    */

    public int numSquares(int n) {

        // Generate perfect squares ≤ n
        int len = (int) Math.sqrt(n);
        int[] squares = new int[len];
        for (int i = 1; i <= len; i++) {
            squares[i - 1] = i * i;
        }

        // ✅ Use best approach
        return solveSpaceOptimized(n, squares);
    }
}

