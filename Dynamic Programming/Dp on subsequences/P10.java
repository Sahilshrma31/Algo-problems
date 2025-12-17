/*
====================================================
📌 Problem: Unbounded Knapsack
====================================================

You are given:
- An array wt[] representing weights of items
- An array val[] representing values of items
- An integer W representing knapsack capacity

You can take an item **any number of times**.

Goal:
Maximize the total value such that total weight ≤ W.

----------------------------------------------------
🧠 Intuition (Striver Style)
----------------------------------------------------
Unlike 0/1 Knapsack, here after picking an item,
you are allowed to pick the **same item again**.

So when we pick item i:
- We stay at the same index (i), not (i-1)

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
Let N = number of items, W = capacity

1️⃣ Recursion:
   Time: Exponential
   Space: O(W) (recursion stack)

2️⃣ Memoization:
   Time: O(N * W)
   Space: O(N * W) + stack

3️⃣ Tabulation:
   Time: O(N * W)
   Space: O(N * W)

4️⃣ Space Optimized:
   Time: O(N * W)
   Space: O(W)

====================================================
*/

import java.util.*;

class Solution {

    /* =======================
       1️⃣ RECURSIVE APPROACH
       ======================= */

    public int unboundedKnapsackRec(int[] wt, int[] val, int W) {
        int n = wt.length;
        return helperRec(n - 1, W, wt, val);
    }

    private int helperRec(int idx, int W, int[] wt, int[] val) {
        // Base case
        if (idx == 0) {
            return (W / wt[0]) * val[0];
        }

        int notTake = helperRec(idx - 1, W, wt, val);
        int take = Integer.MIN_VALUE;

        if (wt[idx] <= W) {
            take = val[idx] + helperRec(idx, W - wt[idx], wt, val);
        }

        return Math.max(take, notTake);
    }

    /* =========================
       2️⃣ MEMOIZATION APPROACH
       ========================= */

    public int unboundedKnapsackMemo(int[] wt, int[] val, int W) {
        int n = wt.length;
        int[][] dp = new int[n][W + 1];

        for (int[] row : dp) Arrays.fill(row, -1);

        return helperMemo(n - 1, W, wt, val, dp);
    }

    private int helperMemo(int idx, int W, int[] wt, int[] val, int[][] dp) {
        if (idx == 0) {
            return (W / wt[0]) * val[0];
        }

        if (dp[idx][W] != -1) return dp[idx][W];

        int notTake = helperMemo(idx - 1, W, wt, val, dp);
        int take = Integer.MIN_VALUE;

        if (wt[idx] <= W) {
            take = val[idx] + helperMemo(idx, W - wt[idx], wt, val, dp);
        }

        return dp[idx][W] = Math.max(take, notTake);
    }

    /* ======================
       3️⃣ TABULATION
       ====================== */

    public int unboundedKnapsackTab(int[] wt, int[] val, int W) {
        int n = wt.length;
        int[][] dp = new int[n][W + 1];

        // Base case
        for (int w = 0; w <= W; w++) {
            dp[0][w] = (w / wt[0]) * val[0];
        }

        for (int i = 1; i < n; i++) {
            for (int w = 0; w <= W; w++) {
                int notTake = dp[i - 1][w];
                int take = Integer.MIN_VALUE;

                if (wt[i] <= w) {
                    take = val[i] + dp[i][w - wt[i]];
                }

                dp[i][w] = Math.max(take, notTake);
            }
        }

        return dp[n - 1][W];
    }

    /* =============================
       4️⃣ SPACE OPTIMIZED DP
       ============================= */

    public int unboundedKnapsackSpaceOpt(int[] wt, int[] val, int W) {
        int n = wt.length;
        int[] dp = new int[W + 1];

        // Base case
        for (int w = 0; w <= W; w++) {
            dp[w] = (w / wt[0]) * val[0];
        }

        for (int i = 1; i < n; i++) {
            for (int w = 0; w <= W; w++) {
                int notTake = dp[w];
                int take = Integer.MIN_VALUE;

                if (wt[i] <= w) {
                    take = val[i] + dp[w - wt[i]];
                }

                dp[w] = Math.max(take, notTake);
            }
        }

        return dp[W];
    }
}
