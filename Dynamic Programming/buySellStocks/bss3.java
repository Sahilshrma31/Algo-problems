/*
📌 Problem: Best Time to Buy and Sell Stock IV (LeetCode 188)
------------------------------------------------------------
You are given an integer k and an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete at most k transactions.
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

🔑 Key Insight:
- Each transaction consists of two states: Buy and Sell.
- At most k transactions => 2*k states.
- State machine DP can be applied.

------------------------------------------------------------
Approach 1: Recursive Brute Force (Exponential, TLE)
- Try all possibilities of buy/sell/not doing anything.
- Time: O(2^n), Space: O(n).

Approach 2: DP + Memoization (Top Down)
- State: dp[i][cap], where:
    i = current day
    cap = transaction step (0 = first buy, 1 = first sell, 2 = second buy … up to 2k-1)
- Transition:
    If cap % 2 == 0 → Buy state
    If cap % 2 == 1 → Sell state
- Time: O(n * 2k), Space: O(n * 2k).

Approach 3: DP + Tabulation (Bottom Up)
- Fill dp[n][2k] iteratively from the back.
- Time: O(n * 2k), Space: O(n * 2k).

Approach 4: Space Optimized DP
- Only need next row and current row.
- Time: O(n * 2k), Space: O(2k).

------------------------------------------------------------
Complexities:
- Brute Force: O(2^n), Space O(n)
- Memoization: O(n * k), Space O(n*k) + Recursion stack
- Tabulation: O(n * k), Space O(n*k)
- Space Optimized: O(n * k), Space O(k)

------------------------------------------------------------
*/

import java.util.*;

class Stock_Buy_and_Sell_IV {

    // -----------------------------
    // Approach 1: Recursion (Brute)
    // -----------------------------
    public int maxProfitRecursive(int k, int[] prices) {
        return solveRec(0, 0, k, prices);
    }

    private int solveRec(int i, int cap, int k, int[] prices) {
        if (i == prices.length || cap == 2 * k) return 0;

        if (cap % 2 == 0) { // Buy
            return Math.max(-prices[i] + solveRec(i + 1, cap + 1, k, prices),
                             solveRec(i + 1, cap, k, prices));
        } else { // Sell
            return Math.max(prices[i] + solveRec(i + 1, cap + 1, k, prices),
                             solveRec(i + 1, cap, k, prices));
        }
    }

    // --------------------------------
    // Approach 2: DP + Memoization
    // --------------------------------
    public int maxProfitMemo(int k, int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2 * k];
        for (int[] arr : dp) Arrays.fill(arr, -1);
        return solveMemo(0, 0, k, prices, dp);
    }

    private int solveMemo(int i, int cap, int k, int[] prices, int[][] dp) {
        if (i == prices.length || cap == 2 * k) return 0;
        if (dp[i][cap] != -1) return dp[i][cap];

        if (cap % 2 == 0) { // Buy
            return dp[i][cap] = Math.max(-prices[i] + solveMemo(i + 1, cap + 1, k, prices, dp),
                                          solveMemo(i + 1, cap, k, prices, dp));
        } else { // Sell
            return dp[i][cap] = Math.max(prices[i] + solveMemo(i + 1, cap + 1, k, prices, dp),
                                          solveMemo(i + 1, cap, k, prices, dp));
        }
    }

    // -----------------------------
    // Approach 3: Tabulation
    // -----------------------------
    public int maxProfitTab(int k, int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2 * k + 1]; // default 0

        for (int i = n - 1; i >= 0; i--) {
            for (int cap = 2 * k - 1; cap >= 0; cap--) {
                if (cap % 2 == 0) { // Buy
                    dp[i][cap] = Math.max(-prices[i] + dp[i + 1][cap + 1],
                                           dp[i + 1][cap]);
                } else { // Sell
                    dp[i][cap] = Math.max(prices[i] + dp[i + 1][cap + 1],
                                           dp[i + 1][cap]);
                }
            }
        }

        return dp[0][0];
    }

    // -----------------------------
    // Approach 4: Space Optimized
    // -----------------------------
    public int maxProfitOptimized(int k, int[] prices) {
        int n = prices.length;
        int[] ahead = new int[2 * k + 1];
        int[] curr = new int[2 * k + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int cap = 2 * k - 1; cap >= 0; cap--) {
                if (cap % 2 == 0) { // Buy
                    curr[cap] = Math.max(-prices[i] + ahead[cap + 1],
                                          ahead[cap]);
                } else { // Sell
                    curr[cap] = Math.max(prices[i] + ahead[cap + 1],
                                          ahead[cap]);
                }
            }
            ahead = curr.clone();
        }

        return ahead[0];
    }

    // -----------------------------
    // Runner
    // -----------------------------
    public static void main(String[] args) {
        Stock_Buy_and_Sell_IV obj = new Stock_Buy_and_Sell_IV();
        int[] prices = {3,2,6,5,0,3};
        int k = 2;

        System.out.println("Recursive: " + obj.maxProfitRecursive(k, prices));
        System.out.println("Memoization: " + obj.maxProfitMemo(k, prices));
        System.out.println("Tabulation: " + obj.maxProfitTab(k, prices));
        System.out.println("Optimized: " + obj.maxProfitOptimized(k, prices));
    }
}
