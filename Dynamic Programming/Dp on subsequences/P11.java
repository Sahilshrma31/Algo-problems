/*
====================================================
📌 Problem: Rod Cutting Problem
====================================================

Given a rod of length N inches and an array price[]
where price[i] represents the price of a rod piece
of length (i + 1).

Task:
👉 Cut the rod into pieces such that the total profit
   is maximized.

You are allowed to cut the rod any number of times
(Unbounded Knapsack).

----------------------------------------------------
🧠 Intuition (Striver Style)
----------------------------------------------------
At each rod length, we have two choices:
1️⃣ Cut the rod at current length (take it)
2️⃣ Do not cut at current length (skip it)

Since we can take the same length multiple times,
this becomes an **Unbounded Knapsack** problem.

----------------------------------------------------
🧩 Approaches Covered
----------------------------------------------------
1️⃣ Recursive (Brute Force)
2️⃣ Memoization (Top-Down DP)
3️⃣ Tabulation (Bottom-Up DP)
4️⃣ Space Optimized DP

----------------------------------------------------
⏱ Time & Space Complexity
----------------------------------------------------
Let N = length of rod

1️⃣ Recursion:
   Time: O(2^N)
   Space: O(N) (recursion stack)

2️⃣ Memoization:
   Time: O(N * N)
   Space: O(N * N) + O(N)

3️⃣ Tabulation:
   Time: O(N * N)
   Space: O(N * N)

4️⃣ Space Optimized:
   Time: O(N * N)
   Space: O(N)

====================================================
*/

import java.util.*;

class Solution {

    /* ============================
       1️⃣ RECURSIVE APPROACH
       ============================ */

    public int cutRodRecursive(int[] price, int n) {
        return helperRec(price, n - 1, n);
    }

    private int helperRec(int[] price, int idx, int rodLen) {
        // Base case
        if (idx == 0) {
            return rodLen * price[0];
        }

        int notTake = helperRec(price, idx - 1, rodLen);
        int take = Integer.MIN_VALUE;

        int currLen = idx + 1;
        if (currLen <= rodLen) {
            take = price[idx] + helperRec(price, idx, rodLen - currLen);
        }

        return Math.max(take, notTake);
    }

    /* ===============================
       2️⃣ MEMOIZATION (TOP-DOWN DP)
       =============================== */

    public int cutRodMemo(int[] price, int n) {
        int[][] dp = new int[n][n + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return helperMemo(price, n - 1, n, dp);
    }

    private int helperMemo(int[] price, int idx, int rodLen, int[][] dp) {
        if (idx == 0) {
            return rodLen * price[0];
        }

        if (dp[idx][rodLen] != -1) {
            return dp[idx][rodLen];
        }

        int notTake = helperMemo(price, idx - 1, rodLen, dp);
        int take = Integer.MIN_VALUE;

        int currLen = idx + 1;
        if (currLen <= rodLen) {
            take = price[idx] + helperMemo(price, idx, rodLen - currLen, dp);
        }

        return dp[idx][rodLen] = Math.max(take, notTake);
    }

    /* =============================
       3️⃣ TABULATION (BOTTOM-UP)
       ============================= */

    public int cutRodTabulation(int[] price, int n) {
        int[][] dp = new int[n][n + 1];

        // Base case
        for (int len = 0; len <= n; len++) {
            dp[0][len] = len * price[0];
        }

        for (int idx = 1; idx < n; idx++) {
            for (int len = 0; len <= n; len++) {
                int notTake = dp[idx - 1][len];
                int take = Integer.MIN_VALUE;

                int currLen = idx + 1;
                if (currLen <= len) {
                    take = price[idx] + dp[idx][len - currLen];
                }

                dp[idx][len] = Math.max(take, notTake);
            }
        }

        return dp[n - 1][n];
    }

    /* ================================
       4️⃣ SPACE OPTIMIZED DP
       ================================ */

    public int cutRodSpaceOptimized(int[] price, int n) {
        int[] prev = new int[n + 1];

        // Base case
        for (int len = 0; len <= n; len++) {
            prev[len] = len * price[0];
        }

        for (int idx = 1; idx < n; idx++) {
            int[] curr = new int[n + 1];
            for (int len = 0; len <= n; len++) {
                int notTake = prev[len];
                int take = Integer.MIN_VALUE;

                int currLen = idx + 1;
                if (currLen <= len) {
                    take = price[idx] + curr[len - currLen];
                }

                curr[len] = Math.max(take, notTake);
            }
            prev = curr;
        }

        return prev[n];
    }
}
