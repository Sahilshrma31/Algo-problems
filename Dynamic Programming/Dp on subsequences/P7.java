/*
====================================================
📌 Problem: Minimum Coins (Coin Change - Min Coins)
====================================================

You are given an array of coins[] of size N and an integer amount.
You need to find the minimum number of coins required to make up
the given amount.

If it is not possible to make the amount, return -1.

----------------------------------------------------
🧠 Intuition (Striver DP Style)
----------------------------------------------------
For every coin, we have two choices:
1️⃣ Pick the coin (can pick again because unlimited supply)
2️⃣ Do not pick the coin (move to previous coin)

We try all possibilities and take the minimum coins needed.

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
N = number of coins
T = target amount

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

    /* ============================
       1️⃣ RECURSIVE APPROACH
       ============================ */

    public int minCoinsRecursive(int[] coins, int amount) {
        int n = coins.length;
        int ans = helperRec(coins, n - 1, amount);
        return ans >= 1e9 ? -1 : ans;
    }

    private int helperRec(int[] coins, int idx, int target) {
        // Base case
        if (idx == 0) {
            if (target % coins[0] == 0)
                return target / coins[0];
            else
                return (int) 1e9;
        }

        int notPick = helperRec(coins, idx - 1, target);

        int pick = (int) 1e9;
        if (coins[idx] <= target) {
            pick = 1 + helperRec(coins, idx, target - coins[idx]);
        }

        return Math.min(pick, notPick);
    }

    /* ============================
       2️⃣ MEMOIZATION APPROACH
       ============================ */

    public int minCoinsMemo(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        for (int[] row : dp)
            Arrays.fill(row, -1);

        int ans = helperMemo(coins, n - 1, amount, dp);
        return ans >= 1e9 ? -1 : ans;
    }

    private int helperMemo(int[] coins, int idx, int target, int[][] dp) {
        if (idx == 0) {
            if (target % coins[0] == 0)
                return target / coins[0];
            else
                return (int) 1e9;
        }

        if (dp[idx][target] != -1)
            return dp[idx][target];

        int notPick = helperMemo(coins, idx - 1, target, dp);

        int pick = (int) 1e9;
        if (coins[idx] <= target) {
            pick = 1 + helperMemo(coins, idx, target - coins[idx], dp);
        }

        return dp[idx][target] = Math.min(pick, notPick);
    }

    /* ============================
       3️⃣ TABULATION APPROACH
       ============================ */

    public int minCoinsTabulation(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        // Base case
        for (int t = 0; t <= amount; t++) {
            if (t % coins[0] == 0)
                dp[0][t] = t / coins[0];
            else
                dp[0][t] = (int) 1e9;
        }

        for (int i = 1; i < n; i++) {
            for (int t = 0; t <= amount; t++) {
                int notPick = dp[i - 1][t];
                int pick = (int) 1e9;

                if (coins[i] <= t)
                    pick = 1 + dp[i][t - coins[i]];

                dp[i][t] = Math.min(pick, notPick);
            }
        }

        int ans = dp[n - 1][amount];
        return ans >= 1e9 ? -1 : ans;
    }

    /* ===============================
       4️⃣ SPACE OPTIMIZED APPROACH
       =============================== */

    public int minCoinsSpaceOptimized(int[] coins, int amount) {
        int n = coins.length;
        int[] prev = new int[amount + 1];

        // Base case
        for (int t = 0; t <= amount; t++) {
            if (t % coins[0] == 0)
                prev[t] = t / coins[0];
            else
                prev[t] = (int) 1e9;
        }

        for (int i = 1; i < n; i++) {
            int[] curr = new int[amount + 1];
            for (int t = 0; t <= amount; t++) {
                int notPick = prev[t];
                int pick = (int) 1e9;

                if (coins[i] <= t)
                    pick = 1 + curr[t - coins[i]];

                curr[t] = Math.min(pick, notPick);
            }
            prev = curr;
        }

        int ans = prev[amount];
        return ans >= 1e9 ? -1 : ans;
    }
}
