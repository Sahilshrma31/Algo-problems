/*
📌 Problem: Best Time to Buy and Sell Stock with Transaction Fee (LeetCode 714)
-------------------------------------------------------------------------------
You are given an array prices where prices[i] is the price of a stock on day i, 
and an integer fee representing a transaction fee.  

You may buy and sell the stock multiple times, but you need to pay the fee for each sell.  
Your goal is to maximize profit.

🔑 Intuition:
- Use Dynamic Programming to track two states:
    1. canBuy = 1 → You can buy today
    2. canBuy = 0 → You can sell today
- If you buy: profit = -price[i] + next_day[0]
- If you sell: profit = price[i] - fee + next_day[1]  (apply transaction fee)
- If you skip: profit = next_day[canBuy]
- Recursive explores all possibilities.
- Memoization caches results to avoid recomputation.
- Tabulation fills DP table bottom-up.
- Space-optimized uses just 2 arrays for current and next day.

------------------------------------------------------------
Complexities:
- Recursive: O(2^n), Space O(n) recursion stack
- Memoization: O(n*2), Space O(n*2)
- Tabulation: O(n*2), Space O(n*2)
- Space Optimized: O(n*2), Space O(2)
*/

import java.util.*;

class Stock_Buy_Sell_Fee {

    // -----------------------------
    // Approach 0: Pure Recursive (Exponential)
    // -----------------------------
    public int maxProfitRecursive(int[] prices, int fee) {
        return solveRec(0, 1, prices, fee); // start at day 0, canBuy=1
    }

    private int solveRec(int i, int canBuy, int[] prices, int fee) {
        if (i == prices.length) return 0;

        if (canBuy == 1) {
            int buy = -prices[i] + solveRec(i + 1, 0, prices, fee);
            int skip = solveRec(i + 1, 1, prices, fee);
            return Math.max(buy, skip);
        } else {
            int sell = prices[i] - fee + solveRec(i + 1, 1, prices, fee);
            int skip = solveRec(i + 1, 0, prices, fee);
            return Math.max(sell, skip);
        }
    }

    // -----------------------------
    // Approach 1: Memoization (Top-Down DP)
    // -----------------------------
    public int maxProfitMemo(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int[] row : dp) Arrays.fill(row, -1);
        return solveMemo(0, 1, prices, fee, dp);
    }

    private int solveMemo(int i, int canBuy, int[] prices, int fee, int[][] dp) {
        if (i == prices.length) return 0;
        if (dp[i][canBuy] != -1) return dp[i][canBuy];

        int profit;
        if (canBuy == 1) {
            int buy = -prices[i] + solveMemo(i + 1, 0, prices, fee, dp);
            int skip = solveMemo(i + 1, 1, prices, fee, dp);
            profit = Math.max(buy, skip);
        } else {
            int sell = prices[i] - fee + solveMemo(i + 1, 1, prices, fee, dp);
            int skip = solveMemo(i + 1, 0, prices, fee, dp);
            profit = Math.max(sell, skip);
        }

        return dp[i][canBuy] = profit;
    }

    // -----------------------------
    // Approach 2: Tabulation (Bottom-Up DP)
    // -----------------------------
    public int maxProfitTab(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2]; // dp[i][canBuy]

        for (int i = n - 1; i >= 0; i--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                if (canBuy == 1) {
                    int buy = -prices[i] + dp[i + 1][0];
                    int skip = dp[i + 1][1];
                    dp[i][1] = Math.max(buy, skip);
                } else {
                    int sell = prices[i] - fee + dp[i + 1][1];
                    int skip = dp[i + 1][0];
                    dp[i][0] = Math.max(sell, skip);
                }
            }
        }

        return dp[0][1]; // start day 0, can buy
    }

    // -----------------------------
    // Approach 3: Space Optimized
    // -----------------------------
    public int maxProfitOptimized(int[] prices, int fee) {
        int n = prices.length;
        int[] ahead = new int[2]; // dp[i+1]
        int[] curr = new int[2];

        for (int i = n - 1; i >= 0; i--) {
            // canBuy = 1
            curr[1] = Math.max(-prices[i] + ahead[0], ahead[1]);

            // canBuy = 0
            curr[0] = Math.max(prices[i] - fee + ahead[1], ahead[0]);

            ahead = curr.clone();
        }

        return curr[1];
    }

    // -----------------------------
    // Runner
    // -----------------------------
    public static void main(String[] args) {
        Stock_Buy_Sell_Fee obj = new Stock_Buy_Sell_Fee();
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;

        System.out.println("Recursive Profit: " + obj.maxProfitRecursive(prices, fee));
        System.out.println("Memoization Profit: " + obj.maxProfitMemo(prices, fee));
        System.out.println("Tabulation Profit: " + obj.maxProfitTab(prices, fee));
        System.out.println("Optimized Profit: " + obj.maxProfitOptimized(prices, fee));
    }
}
