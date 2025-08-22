/*
-------------------------------------------------------
 🟢 Problem: Best Time to Buy and Sell Stock III
-------------------------------------------------------
 You are given an array prices where prices[i] is the 
 price of a given stock on the ith day.

 You may complete at most **two transactions** 
 (i.e., buy one and sell one share of the stock twice).

 Note: You may not engage in multiple transactions 
 simultaneously (must sell before you buy again).

 Return the maximum profit you can achieve.

 Example:
 Input: prices = [3,3,5,0,0,3,1,4]
 Output: 6
 Explanation: Buy on day 4 (price=0) and sell on day 6 (price=3), 
 profit = 3. Then buy on day 7 (price=1) and sell on day 8 (price=4), profit = 3.
 Total Profit = 6.

 -------------------------------------------------------
 Approaches:
   1. Recursion (Brute Force) -> O(2^n * 4)
   2. Memoization (Top Down DP) -> O(n*2*3), O(n*2*3)
   3. Tabulation (Bottom Up DP) -> O(n*2*3), O(n*2*3)
   4. Space Optimization (ahead & curr arrays) -> O(n*2*3), O(2*3)
   5. Super Optimized (rolling variables) -> O(n), O(1)
   6. DP n*4 Approach (treat cap as 0..4) -> O(n*4), O(n*4)
-------------------------------------------------------
*/

import java.util.*;

// ---------------------------------------------------
// 1. Recursive (Brute Force)
// ---------------------------------------------------
class RecursiveSolution {
    public int maxProfit(int[] prices) {
        return solve(0, 1, 2, prices);
    }

    // i = day, buy=1/0, cap = transactions left
    private int solve(int i, int buy, int cap, int[] prices) {
        if (i == prices.length || cap == 0) return 0;

        if (buy == 1) {
            int buyToday = -prices[i] + solve(i+1, 0, cap, prices);
            int skip = solve(i+1, 1, cap, prices);
            return Math.max(buyToday, skip);
        } else {
            int sellToday = prices[i] + solve(i+1, 1, cap-1, prices);
            int hold = solve(i+1, 0, cap, prices);
            return Math.max(sellToday, hold);
        }
    }
}
// TC: O(2^n * 4), SC: O(n)


// ---------------------------------------------------
// 2. Memoization (Top-Down DP)
// ---------------------------------------------------
class MemoizationSolution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n][2][3]; // i, buy, cap(0-2)
        for (int[][] mat : dp) 
            for (int[] row : mat) 
                Arrays.fill(row, -1);
        return solve(0, 1, 2, prices, dp);
    }

    private int solve(int i, int buy, int cap, int[] prices, int[][][] dp) {
        if (i == prices.length || cap == 0) return 0;
        if (dp[i][buy][cap] != -1) return dp[i][buy][cap];

        if (buy == 1) {
            int buyToday = -prices[i] + solve(i+1, 0, cap, prices, dp);
            int skip = solve(i+1, 1, cap, prices, dp);
            return dp[i][buy][cap] = Math.max(buyToday, skip);
        } else {
            int sellToday = prices[i] + solve(i+1, 1, cap-1, prices, dp);
            int hold = solve(i+1, 0, cap, prices, dp);
            return dp[i][buy][cap] = Math.max(sellToday, hold);
        }
    }
}
// TC: O(n*2*3), SC: O(n*2*3) + O(n)


// ---------------------------------------------------
// 3. Tabulation (Bottom-Up DP)
// ---------------------------------------------------
class TabulationSolution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n+1][2][3];

        // base: dp[n][*][*] = 0 already

        for (int i = n-1; i >= 0; i--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= 2; cap++) {
                    if (buy == 1) {
                        dp[i][buy][cap] = Math.max(-prices[i] + dp[i+1][0][cap], dp[i+1][1][cap]);
                    } else {
                        dp[i][buy][cap] = Math.max(prices[i] + dp[i+1][1][cap-1], dp[i+1][0][cap]);
                    }
                }
            }
        }

        return dp[0][1][2];
    }
}
// TC: O(n*2*3), SC: O(n*2*3)


// ---------------------------------------------------
// 4. Space Optimized (ahead & curr arrays)
// ---------------------------------------------------
class SpaceOptimizedSolution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] ahead = new int[2][3];
        int[][] curr = new int[2][3];

        for (int i = n-1; i >= 0; i--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= 2; cap++) {
                    if (buy == 1) {
                        curr[buy][cap] = Math.max(-prices[i] + ahead[0][cap], ahead[1][cap]);
                    } else {
                        curr[buy][cap] = Math.max(prices[i] + ahead[1][cap-1], ahead[0][cap]);
                    }
                }
            }
            ahead = curr.clone();
        }

        return ahead[1][2];
    }
}
// TC: O(n*2*3), SC: O(2*3)


// ---------------------------------------------------
// 5. Super Optimized (2 variables arrays)
// ---------------------------------------------------
class SuperOptimizedSolution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] ahead = new int[5]; // cap 0..4
        int[] curr = new int[5];

        for (int i = n-1; i >= 0; i--) {
            for (int cap = 1; cap <= 4; cap++) {
                if (cap % 2 == 1) { // odd => buy
                    curr[cap] = Math.max(-prices[i] + ahead[cap-1], ahead[cap]);
                } else { // even => sell
                    curr[cap] = Math.max(prices[i] + ahead[cap-1], ahead[cap]);
                }
            }
            ahead = curr.clone();
        }

        return ahead[4]; // max profit with 4 operations (2 buys + 2 sells)
    }
}
// TC: O(n*4), SC: O(4)


// ---------------------------------------------------
// 6. DP n*4 Approach (Cap Method: 0..4 steps)
// ---------------------------------------------------
class Nby4Approach {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n+1][5]; // cap = 0..4

        for (int i = n-1; i >= 0; i--) {
            for (int cap = 1; cap <= 4; cap++) {
                if (cap % 2 == 1) { // odd -> buy
                    dp[i][cap] = Math.max(-prices[i] + dp[i+1][cap-1], dp[i+1][cap]);
                } else { // even -> sell
                    dp[i][cap] = Math.max(prices[i] + dp[i+1][cap-1], dp[i+1][cap]);
                }
            }
        }

        return dp[0][4];
    }
}
// TC: O(n*4), SC: O(n*4)


// ---------------------------------------------------
// Main Driver for Testing
// ---------------------------------------------------
public class bss1 {
    public static void main(String[] args) {
        int[] prices = {3,3,5,0,0,3,1,4};

        System.out.println("Recursive: " + new RecursiveSolution().maxProfit(prices));
        System.out.println("Memoization: " + new MemoizationSolution().maxProfit(prices));
        System.out.println("Tabulation: " + new TabulationSolution().maxProfit(prices));
        System.out.println("Space Optimized: " + new SpaceOptimizedSolution().maxProfit(prices));
        System.out.println("Super Optimized: " + new SuperOptimizedSolution().maxProfit(prices));
        System.out.println("N*4 Approach: " + new Nby4Approach().maxProfit(prices));
    }
}
