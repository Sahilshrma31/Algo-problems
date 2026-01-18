/*
====================================================
📌 Problem: Profitable Schemes
(LeetCode 879)
====================================================

You are given:
- `n`        → total number of people available
- `minProfit` → minimum profit required
- `group[i]`  → number of people required for the i-th crime
- `profit[i]` → profit gained from the i-th crime

Task:
Count the number of **different schemes** such that:
- Total people used ≤ n
- Total profit ≥ minProfit

Return the answer modulo 1e9 + 7.

====================================================
🧠 Core Idea
====================================================

This is a **3D Dynamic Programming** problem:

State definition:
dp[idx][profit][people]
→ number of ways considering crimes [0..idx]
   with current profit = profit
   and people used = people

Key tricks:
- Profit is **capped at minProfit**
  (because profit ≥ minProfit is equivalent)
- Either:
  - pick a crime
  - or skip it

====================================================
🧠 Approach: Recursion + Memoization (Top-Down DP)
====================================================

At each crime index:
1️⃣ Not pick the crime  
2️⃣ Pick the crime (if people constraint allows)

Base case:
- When idx < 0:
  - If current profit ≥ minProfit → valid scheme

====================================================
⏱ Time Complexity
====================================================
O(m * minProfit * n)

m = number of crimes

====================================================
📦 Space Complexity
====================================================
O(m * minProfit * n)

====================================================
*/

import java.util.*;

class Solution {

    private int maxPeople;
    private int[][][] dp;
    private static final int MOD = (int) 1e9 + 7;

    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {

        int m = group.length;
        maxPeople = n;

        // dp[idx][profit][people]
        dp = new int[m][minProfit + 1][n + 1];

        for (int i = 0; i < m; i++) {
            for (int p = 0; p <= minProfit; p++) {
                Arrays.fill(dp[i][p], -1);
            }
        }

        return helper(m - 1, 0, 0, minProfit, group, profit);
    }

    private int helper(int idx,
                       int currProfit,
                       int people,
                       int minProfit,
                       int[] group,
                       int[] profit) {

        // Base case
        if (idx < 0) {
            return currProfit >= minProfit ? 1 : 0;
        }

        if (dp[idx][currProfit][people] != -1) {
            return dp[idx][currProfit][people];
        }

        // Option 1: Do not pick current crime
        int notPick = helper(idx - 1, currProfit, people,
                             minProfit, group, profit) % MOD;

        // Option 2: Pick current crime (if possible)
        int pick = 0;
        if (people + group[idx] <= maxPeople) {

            int newProfit = Math.min(
                    currProfit + profit[idx],
                    minProfit
            );

            pick = helper(idx - 1,
                          newProfit,
                          people + group[idx],
                          minProfit,
                          group,
                          profit) % MOD;
        }

        dp[idx][currProfit][people] = (pick + notPick) % MOD;
        return dp[idx][currProfit][people];
    }
}
