/*
-------------------------------------------------------
 🟢 Problem: Best Time to Buy and Sell Stock II
-------------------------------------------------------
 You are given an array prices where prices[i] is the 
 price of a given stock on the ith day.

 You may complete as many transactions as you like 
 (i.e., buy one and sell one share of the stock multiple times).
 
 BUT you may not engage in multiple transactions simultaneously 
 (i.e., you must sell the stock before you buy again).

 Return the maximum profit you can achieve.

 Example:
 Input: prices = [7,1,5,3,6,4]
 Output: 7
 Explanation: Buy on day 2 (price=1) and sell on day 3 (price=5), 
 profit = 4. Then buy on day 4 (price=3) and sell on day 5 (price=6), profit = 3.
 Total Profit = 7.

 -------------------------------------------------------
 Approaches:
   1. Recursion (Brute Force)      -> O(2^n)
   2. Memoization (Top Down DP)    -> O(n*2), O(n*2)
   3. Tabulation (Bottom Up DP)    -> O(n*2), O(n*2)
   4. Space Optimization (2 arrays)-> O(n*2), O(2)
   5. Super Optimized (2 variables)-> O(n), O(1)
-------------------------------------------------------
*/

import java.util.*;

// ---------------------------------------------------
// 1. Recursive (Brute Force)
// ---------------------------------------------------
class RecursiveSolution {
    public int maxProfit(int[] prices) {
        return solve(0, 1, prices);
    }

    private int solve(int i, int buy, int[] prices) {
        if (i == prices.length) return 0;

        if (buy == 1) {
            int buyToday = -prices[i] + solve(i+1, 0, prices);
            int skip = solve(i+1, 1, prices);
            return Math.max(buyToday, skip);
        } else {
            int sellToday = prices[i] + solve(i+1, 1, prices);
            int hold = solve(i+1, 0, prices);
            return Math.max(sellToday, hold);
        }
    }
}
// TC: O(2^n), SC: O(n)


// ---------------------------------------------------
// 2. Memoization (Top-Down DP)
// ---------------------------------------------------
class MemoizationSolution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int[] row : dp) Arrays.fill(row, -1);
        return solve(0, 1, prices, dp);
    }

    private int solve(int i, int buy, int[] prices, int[][] dp) {
        if (i == prices.length) return 0;
        if (dp[i][buy] != -1) return dp[i][buy];

        if (buy == 1) {
            int buyToday = -prices[i] + solve(i+1, 0, prices, dp);
            int skip = solve(i+1, 1, prices, dp);
            return dp[i][buy] = Math.max(buyToday, skip);
        } else {
            int sellToday = prices[i] + solve(i+1, 1, prices, dp);
            int hold = solve(i+1, 0, prices, dp);
            return dp[i][buy] = Math.max(sellToday, hold);
        }
    }
}
// TC: O(n*2), SC: O(n*2) + O(n)


// ---------------------------------------------------
// 3. Tabulation (Bottom-Up DP)
// ---------------------------------------------------
class TabulationSolution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n+1][2];

        dp[n][0] = dp[n][1] = 0;

        for (int i = n-1; i >= 0; i--) {
            dp[i][1] = Math.max(-prices[i] + dp[i+1][0], dp[i+1][1]);
            dp[i][0] = Math.max(prices[i] + dp[i+1][1], dp[i+1][0]);
        }

        return dp[0][1];
    }
}
// TC: O(n*2), SC: O(n*2)


// ---------------------------------------------------
// 4. Space Optimization (ahead & curr arrays)
// ---------------------------------------------------
class SpaceOptimizedSolution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] ahead = new int[2], curr = new int[2];

        ahead[0] = ahead[1] = 0;

        for (int i = n-1; i >= 0; i--) {
            curr[1] = Math.max(-prices[i] + ahead[0], ahead[1]);
            curr[0] = Math.max(prices[i] + ahead[1], ahead[0]);
            ahead = curr.clone();
        }

        return ahead[1];
    }
}
// TC: O(n*2), SC: O(2)


// ---------------------------------------------------
// 5. Super Optimized (2 variables only)
// ---------------------------------------------------
class SuperOptimizedSolution {
    public int maxProfit(int[] prices) {
        int aheadBuy = 0, aheadSell = 0;
        int currBuy, currSell;

        for (int i = prices.length-1; i >= 0; i--) {
            currBuy = Math.max(-prices[i] + aheadSell, aheadBuy);
            currSell = Math.max(prices[i] + aheadBuy, aheadSell);
            aheadBuy = currBuy;
            aheadSell = currSell;
        }

        return aheadBuy;
    }
}
// TC: O(n), SC: O(1)


// ---------------------------------------------------
// Main Class (File name must be bss1.java)
// ---------------------------------------------------
public class bss1 {
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};

        System.out.println("Recursive       : " + new RecursiveSolution().maxProfit(prices));
        System.out.println("Memoization     : " + new MemoizationSolution().maxProfit(prices));
        System.out.println("Tabulation      : " + new TabulationSolution().maxProfit(prices));
        System.out.println("Space Optimized : " + new SpaceOptimizedSolution().maxProfit(prices));
        System.out.println("Super Optimized : " + new SuperOptimizedSolution().maxProfit(prices));
    }
}
