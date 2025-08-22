/*
📌 Problem: Best Time to Buy and Sell Stock with Cooldown (LeetCode 309)
------------------------------------------------------------------------
You are given an array prices where prices[i] is the price of a stock on day i. 
You may buy and sell the stock any number of times with the following restrictions:
- After you sell your stock, you cannot buy stock on the next day (cooldown one day).
- You may not engage in multiple transactions simultaneously.

🔑 Intuition:
- Use Dynamic Programming to track two states:
    1. canBuy = 1 → You can buy today
    2. canBuy = 0 → You can sell today
- If you buy: profit = -price[i] + dp[i+1][0]
- If you sell: profit = price[i] + dp[i+2][1]  (cooldown applied)
- If you skip: profit = dp[i+1][canBuy]
- Fill DP table backwards (tabulation) to get maximum profit at day 0.

------------------------------------------------------------
Complexities:
- Time: O(n)
- Space: O(n) for tabulation, O(1) for space-optimized

*/

import java.util.*;

class Stock_Buy_Sell_Cooldown {

    // -----------------------------
    // Approach 1: Tabulation (Bottom-Up)
    // -----------------------------
    public int maxProfitTab(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;

        // dp[i][canBuy]: max profit from day i with state canBuy
        int[][] dp = new int[n + 2][2]; // +2 for safe i+2 (cooldown)

        for (int i = n - 1; i >= 0; i--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                if (canBuy == 1) {
                    // Option 1: Buy today, then next day must sell
                    int buy = -prices[i] + dp[i + 1][0];
                    // Option 2: Skip today
                    int notBuy = dp[i + 1][1];
                    dp[i][1] = Math.max(buy, notBuy);
                } else {
                    // Option 1: Sell today, cooldown next day
                    int sell = prices[i] + dp[i + 2][1];
                    // Option 2: Skip today
                    int notSell = dp[i + 1][0];
                    dp[i][0] = Math.max(sell, notSell);
                }
            }
        }

        return dp[0][1]; // Start at day 0, can buy
    }

    // -----------------------------
    // Approach 2: Space Optimized
    // -----------------------------
    public int maxProfitOptimized(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;

        int[] ahead1 = new int[2]; // dp[i+1]
        int[] ahead2 = new int[2]; // dp[i+2]
        int[] curr = new int[2];

        for (int i = n - 1; i >= 0; i--) {
            // canBuy = 1
            int buy = -prices[i] + ahead1[0];
            int notBuy = ahead1[1];
            curr[1] = Math.max(buy, notBuy);

            // canBuy = 0
            int sell = prices[i] + ahead2[1]; // cooldown applied
            int notSell = ahead1[0];
            curr[0] = Math.max(sell, notSell);

            // Move current to ahead arrays
            ahead2 = ahead1.clone();
            ahead1 = curr.clone();
        }

        return curr[1];
    }

    // -----------------------------
    // Runner
    // -----------------------------
    public static void main(String[] args) {
        Stock_Buy_Sell_Cooldown obj = new Stock_Buy_Sell_Cooldown();
        int[] prices = {1,2,3,0,2};

        System.out.println("Tabulation Profit: " + obj.maxProfitTab(prices));
        System.out.println("Optimized Profit: " + obj.maxProfitOptimized(prices));
    }
}
