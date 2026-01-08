/*
 * 🧩 Problem: LeetCode 474. Ones and Zeroes (Medium)
 * ------------------------------------------------------------
 * 📄 Problem Summary:
 * You are given:
 *   - strs: an array of binary strings
 *   - m: maximum number of 0’s allowed
 *   - n: maximum number of 1’s allowed
 *
 * Task:
 *   Find the size of the largest subset of strs such that 
 *   the total number of 0’s ≤ m and the total number of 1’s ≤ n.
 *
 * ------------------------------------------------------------
 * 🧠 Example 1:
 * Input:  strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * Output: 4
 * Explanation: 
 *   The largest subset with ≤ 5 zeros and ≤ 3 ones is {"10", "0001", "1", "0"}.
 *
 * 🧠 Example 2:
 * Input:  strs = ["10", "0", "1"], m = 1, n = 1
 * Output: 2
 * Explanation:
 *   The largest subset is {"0", "1"}.
 *
 * ------------------------------------------------------------
 * 🔒 Constraints:
 *   1 <= strs.length <= 600
 *   1 <= strs[i].length <= 100
 *   strs[i] consists only of '0' and '1'.
 *   1 <= m, n <= 100
 *
 * ------------------------------------------------------------
 * 💡 Intuition:
 * This is a 0/1 Knapsack problem variant:
 *   - Each string consumes some number of zeros and ones.
 *   - You can’t exceed m zeros and n ones.
 *   - Maximize number of strings chosen.
 *
 * ------------------------------------------------------------
 * 🔁 Approaches Overview:
 *
 * 1️⃣ Brute Force Recursion — try all subsets → O(2^N)
 * 2️⃣ Recursion + Memoization (Top-Down DP) → O(N × M × N)
 * 3️⃣ Bottom-Up DP (Tabulation) → O(N × M × N) but optimized space
 *
 * ------------------------------------------------------------
 * 🧾 Complexity Summary:
 * ------------------------------------------------------------
 * | Approach      | Time Complexity | Space Complexity | Feasible? |
 * |----------------|----------------|------------------|------------|
 * | 1. Brute Force | O(2^N)         | O(N)             | ❌ Too slow |
 * | 2. Memoization | O(N×M×N)       | O(N×M×N)         | ✅ Good     |
 * | 3. Bottom-Up   | O(N×M×N)       | O(M×N)           | ✅✅ Optimal |
 * ------------------------------------------------------------
 */

import java.util.*;

// ------------------------------------------------------------
// 🧮 Approach 1: Brute Force Recursion (Exponential)
// ------------------------------------------------------------
class SolutionBruteForce {
    public int findMaxForm(String[] strs, int m, int n) {
        return helper(strs, 0, m, n);
    }

    private int helper(String[] strs, int idx, int m, int n) {
        if (idx == strs.length) return 0;

        int[] count = countNum(strs[idx]);
        int zeros = count[0];
        int ones = count[1];

        // Option 1: skip
        int notTake = helper(strs, idx + 1, m, n);

        // Option 2: take (if fits)
        int take = 0;
        if (zeros <= m && ones <= n) {
            take = 1 + helper(strs, idx + 1, m - zeros, n - ones);
        }

        return Math.max(take, notTake);
    }

    private int[] countNum(String s) {
        int zeros = 0, ones = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') zeros++;
            else ones++;
        }
        return new int[]{zeros, ones};
    }
}

// ------------------------------------------------------------
// 🧮 Approach 2: Memoization (Top-Down DP)
// ------------------------------------------------------------
class SolutionMemoization {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] dp = new int[strs.length][m + 1][n + 1];
        for (int[][] layer : dp)
            for (int[] row : layer)
                Arrays.fill(row, -1);

        return helper(strs, 0, m, n, dp);
    }

    private int helper(String[] strs, int idx, int m, int n, int[][][] dp) {
        if (idx == strs.length) return 0;

        if (dp[idx][m][n] != -1) return dp[idx][m][n];

        int[] count = countNum(strs[idx]);
        int zeros = count[0];
        int ones = count[1];

        int notTake = helper(strs, idx + 1, m, n, dp);

        int take = 0;
        if (zeros <= m && ones <= n) {
            take = 1 + helper(strs, idx + 1, m - zeros, n - ones, dp);
        }

        return dp[idx][m][n] = Math.max(take, notTake);
    }

    private int[] countNum(String s) {
        int zeros = 0, ones = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') zeros++;
            else ones++;
        }
        return new int[]{zeros, ones};
    }
}

// ------------------------------------------------------------
// 🧮 Approach 3: Bottom-Up Dynamic Programming (Optimal)
// ------------------------------------------------------------
class SolutionBottomUp {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        for (String s : strs) {
            int[] count = countNum(s);
            int zeros = count[0];
            int ones = count[1];

            // Update backwards (classic 0/1 Knapsack)
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], 1 + dp[i - zeros][j - ones]);
                }
            }
        }

        return dp[m][n];
    }

    private int[] countNum(String s) {
        int zeros = 0, ones = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') zeros++;
            else ones++;
        }
        return new int[]{zeros, ones};
    }
}

// ------------------------------------------------------------
// 🧪 Example Test Runner (for local testing / GitHub demo)
// ------------------------------------------------------------
public class Solution {
    public static void main(String[] args) {
        String[] strs1 = {"10", "0001", "111001", "1", "0"};
        int m1 = 5, n1 = 3;

        String[] strs2 = {"10", "0", "1"};
        int m2 = 1, n2 = 1;

        SolutionBottomUp sol = new SolutionBottomUp();

        System.out.println("✅ Testcase 1 Output: " + sol.findMaxForm(strs1, m1, n1)); // Expected: 4
        System.out.println("✅ Testcase 2 Output: " + sol.findMaxForm(strs2, m2, n2)); // Expected: 2
    }
}

