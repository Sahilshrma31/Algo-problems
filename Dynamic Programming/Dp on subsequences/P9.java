/*
====================================================
📌 Problem: Coin Change II
====================================================

You are given an integer array coins[] representing
different coin denominations and an integer amount.

Your task is to find the number of combinations that
make up the given amount.

You can use each coin unlimited times.

----------------------------------------------------
🧠 Intuition (Striver DP Style)
----------------------------------------------------
This is an **Unbounded Knapsack – Count Ways** problem.

At each coin index, we have two choices:
1️⃣ Take the coin (stay at same index, since unlimited)
2️⃣ Not take the coin (move to previous index)

We count all valid combinations.

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
- N = number of coins
- T = target amount

1️⃣ Recursion:
   Time: Exponential
   Space: O(T) recursion stack

2️⃣ Memoization:
   Time: O(N * T)
   Space: O(N * T) + O(T)

3️⃣ Tabulation:
   Time: O(N * T)
   Space: O(N * T)

4️⃣ Space Optimized:
   Time: O(N * T)
   Space: O(T)

====================================================
*/

import java.util.*;

class Solution {

    /* =========================
       1️⃣ RECURSIVE APPROACH
       ========================= */
    public int changeRecursive(int amount, int[] coins) {
        return helperRec(coins.length - 1, amount, coins);
    }

    private int helperRec(int idx, int target, int[] coins) {
        if (idx == 0) {
            return (target % coins[0] == 0) ? 1 : 0;
        }

        int notTake = helperRec(idx - 1, target, coins);
        int take = 0;

        if (coins[idx] <= target) {
            take = helperRec(idx, target - coins[idx], coins);
        }

        return take + notTake;
    }

    /* =========================
       2️⃣ MEMOIZATION APPROACH
       ========================= */
    public int changeMemo(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        for (int[] row : dp) Arrays.fill(row, -1);

        return helperMemo(n - 1, amount, coins, dp);
    }

    private int helperMemo(int idx, int target, int[] coins, int[][] dp) {
        if (idx == 0) {
            return (target % coins[0] == 0) ? 1 : 0;
        }

        if (dp[idx][target] != -1) return dp[idx][target];

        int notTake = helperMemo(idx - 1, target, coins, dp);
        int take = 0;

        if (coins[idx] <= target) {
            take = helperMemo(idx, target - coins[idx], coins, dp);
        }

        return dp[idx][target] = take + notTake;
    }

    /* ======================
       3️⃣ TABULATION DP
       ====================== */
    public int changeTabulation(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        // Base Case
        for (int t = 0; t <= amount; t++) {
            if (t % coins[0] == 0) dp[0][t] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int t = 0; t <= amount; t++) {
                int notTake = dp[i - 1][t];
                int take = 0;

                if (coins[i] <= t) {
                    take = dp[i][t - coins[i]];
                }

                dp[i][t] = take + notTake;
            }
        }

        return dp[n - 1][amount];
    }

    /* =============================
       4️⃣ SPACE OPTIMIZED DP
       ============================= */
    public int changeSpaceOptimized(int amount, int[] coins) {
        int n = coins.length;
        int[] prev = new int[amount + 1];

        // Base Case
        for (int t = 0; t <= amount; t++) {
            if (t % coins[0] == 0) prev[t] = 1;
        }

        for (int i = 1; i < n; i++) {
            int[] curr = new int[amount + 1];
            for (int t = 0; t <= amount; t++) {
                int notTake = prev[t];
                int take = 0;

                if (coins[i] <= t) {
                    take = curr[t - coins[i]];
                }

                curr[t] = take + notTake;
            }
            prev = curr;
        }

        return prev[amount];
    }
}
