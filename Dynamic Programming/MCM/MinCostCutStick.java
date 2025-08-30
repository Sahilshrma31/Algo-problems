/*
    🔑 Problem: Minimum Cost to Cut a Stick (LeetCode 1547)

    You are given a stick of length n and an array cuts[] containing positions 
    where cuts must be made. Each time you make a cut, the cost is equal to 
    the length of the stick being cut. Find the minimum total cost to perform all cuts.

    Example:
    n = 7, cuts = [1,3,4,5]
    Output = 16
    Explanation: Cut at 3 → cost 7, stick splits into [0,3] & [3,7]
                 Then cut [0,3] at 1 → cost 3
                 Then cut [3,7] at 4 and 5 → cost 4 + 2 = 6
                 Total = 16

    --------------------------------------------------------
    🔥 Intuition (Striver’s Interval DP):
    - Similar to Matrix Chain Multiplication.
    - Sort cuts and add boundaries (0 and n).
    - For every interval (i, j), try placing a cut at k between i & j.
    - Cost = (length of interval) + cost(left) + cost(right).
    - Answer = dp[0][m+1], where m = cuts.length.
    --------------------------------------------------------
*/

import java.util.*;

public class MinCostCutStick {

    // --------------------------------------------------------
    // Approach 1: Recursion (Brute Force)
    // --------------------------------------------------------
    /*
        TC: Exponential (O(2^m)), tries every possible order of cuts
        SC: O(m) recursion stack
    */
    static class Recursion {
        public int minCost(int n, int[] cuts) {
            int m = cuts.length;
            int[] arr = new int[m + 2];

            arr[0] = 0; arr[m + 1] = n;
            for (int i = 0; i < m; i++) arr[i + 1] = cuts[i];
            Arrays.sort(arr);

            return solve(0, m + 1, arr);
        }

        private int solve(int i, int j, int[] arr) {
            if (i + 1 == j) return 0; // no space for a cut

            int min = Integer.MAX_VALUE;
            for (int k = i + 1; k < j; k++) {
                int cost = arr[j] - arr[i]
                         + solve(i, k, arr)
                         + solve(k, j, arr);
                min = Math.min(min, cost);
            }
            return min;
        }
    }

    // --------------------------------------------------------
    // Approach 2: Memoization (Top-Down DP)
    // --------------------------------------------------------
    /*
        TC: O(m^3)
        SC: O(m^2) for dp + O(m) recursion stack
    */
    static class Memoization {
        public int minCost(int n, int[] cuts) {
            int m = cuts.length;
            int[] arr = new int[m + 2];

            arr[0] = 0; arr[m + 1] = n;
            for (int i = 0; i < m; i++) arr[i + 1] = cuts[i];
            Arrays.sort(arr);

            int[][] dp = new int[m + 2][m + 2];
            for (int[] row : dp) Arrays.fill(row, -1);

            return solve(0, m + 1, arr, dp);
        }

        private int solve(int i, int j, int[] arr, int[][] dp) {
            if (i + 1 == j) return 0;
            if (dp[i][j] != -1) return dp[i][j];

            int min = Integer.MAX_VALUE;
            for (int k = i + 1; k < j; k++) {
                int cost = arr[j] - arr[i]
                         + solve(i, k, arr, dp)
                         + solve(k, j, arr, dp);
                min = Math.min(min, cost);
            }
            return dp[i][j] = min;
        }
    }

    // --------------------------------------------------------
    // Approach 3: Tabulation (Bottom-Up DP, Striver’s way)
    // --------------------------------------------------------
    /*
        TC: O(m^3)
        SC: O(m^2)
    */
class Solution {
    public int minCost(int n, int[] cuts) {
        int m = cuts.length;

        // Step 1: prepare cuts array (add 0 and n as boundaries)
        int[] arr = new int[m + 2];
        arr[0] = 0;
        arr[m + 1] = n;
        for (int i = 0; i < m; i++) arr[i + 1] = cuts[i];
        Arrays.sort(arr);

        // Step 2: dp[i][j] = min cost to cut between arr[i] and arr[j]
        int[][] dp = new int[m + 2][m + 2];

        // Step 3: bottom-up filling
        // i goes backwards so that smaller intervals are solved first
        for (int i = m; i >= 1; i--) {
            for (int j = 1; j <= m; j++) {
                if (i > j) continue;

                int minCost = Integer.MAX_VALUE;

                for (int k = i; k <= j; k++) {
                    int cost = arr[j + 1] - arr[i - 1] + dp[i][k - 1] + dp[k + 1][j];
                    minCost = Math.min(minCost, cost);
                }

                dp[i][j] = minCost;
            }
        }

        return dp[1][m];
    }
}


    // --------------------------------------------------------
    // Main Method (for quick testing)
    // --------------------------------------------------------
    public static void main(String[] args) {
        int n = 7;
        int[] cuts = {1, 3, 4, 5};

        System.out.println("Recursion: " + new Recursion().minCost(n, cuts));
        System.out.println("Memoization: " + new Memoization().minCost(n, cuts));
        System.out.println("Tabulation: " + new Tabulation().minCost(n, cuts));
    }
}
