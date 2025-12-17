/*
====================================================
📌 Problem: 0/1 Knapsack
====================================================

Given:
- weights[] : weights of items
- values[]  : values of items
- W         : knapsack capacity

You can either take an item or not take it (0/1 choice).
Each item can be used at most once.

Return the maximum value that can be put into the knapsack.

----------------------------------------------------
🧠 Intuition (Striver Style)
----------------------------------------------------
At every index, we have two choices:
1️⃣ Not pick the current item
2️⃣ Pick the current item (only if weight allows)

We choose the option that gives the maximum value.

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
Let:
- N = number of items
- W = knapsack capacity

1️⃣ Recursive:
   Time: O(2^N)
   Space: O(N) (recursion stack)

2️⃣ Memoization:
   Time: O(N * W)
   Space: O(N * W) + O(N)

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

    /* ============================
       1️⃣ RECURSIVE APPROACH
       ============================ */

    public int knapsackRecursive(int[] wt, int[] val, int W, int n) {
        return helperRec(n - 1, W, wt, val);
    }

    private int helperRec(int idx, int W, int[] wt, int[] val) {
        if (idx == 0) {
            if (wt[0] <= W) return val[0];
            return 0;
        }

        int notPick = helperRec(idx - 1, W, wt, val);
        int pick = Integer.MIN_VALUE;

        if (wt[idx] <= W) {
            pick = val[idx] + helperRec(idx - 1, W - wt[idx], wt, val);
        }

        return Math.max(pick, notPick);
    }

    /* ==============================
       2️⃣ MEMOIZATION (TOP-DOWN)
       ============================== */

    public int knapsackMemo(int[] wt, int[] val, int W, int n) {
        int[][] dp = new int[n][W + 1];
        for (int[] row : dp) Arrays.fill(row, -1);

        return helperMemo(n - 1, W, wt, val, dp);
    }

    private int helperMemo(int idx, int W, int[] wt, int[] val, int[][] dp) {
        if (idx == 0) {
            if (wt[0] <= W) return val[0];
            return 0;
        }

        if (dp[idx][W] != -1) return dp[idx][W];

        int notPick = helperMemo(idx - 1, W, wt, val, dp);
        int pick = Integer.MIN_VALUE;

        if (wt[idx] <= W) {
            pick = val[idx] + helperMemo(idx - 1, W - wt[idx], wt, val, dp);
        }

        return dp[idx][W] = Math.max(pick, notPick);
    }

    /* ============================
       3️⃣ TABULATION (BOTTOM-UP)
       ============================ */

    public int knapsackTabulation(int[] wt, int[] val, int W, int n) {
        int[][] dp = new int[n][W + 1];

        // Base case
        for (int w = wt[0]; w <= W; w++) {
            dp[0][w] = val[0];
        }

        for (int i = 1; i < n; i++) {
            for (int w = 0; w <= W; w++) {
                int notPick = dp[i - 1][w];
                int pick = Integer.MIN_VALUE;

                if (wt[i] <= w) {
                    pick = val[i] + dp[i - 1][w - wt[i]];
                }

                dp[i][w] = Math.max(pick, notPick);
            }
        }

        return dp[n - 1][W];
    }

    /* ==============================
       4️⃣ SPACE OPTIMIZED DP
       ============================== */

    public int knapsackSpaceOptimized(int[] wt, int[] val, int W, int n) {
        int[] prev = new int[W + 1];

        // Base case
        for (int w = wt[0]; w <= W; w++) {
            prev[w] = val[0];
        }

        for (int i = 1; i < n; i++) {
            int[] curr = new int[W + 1];
            for (int w = 0; w <= W; w++) {
                int notPick = prev[w];
                int pick = Integer.MIN_VALUE;

                if (wt[i] <= w) {
                    pick = val[i] + prev[w - wt[i]];
                }

                curr[w] = Math.max(pick, notPick);
            }
            prev = curr;
        }

        return prev[W];
    }
}
