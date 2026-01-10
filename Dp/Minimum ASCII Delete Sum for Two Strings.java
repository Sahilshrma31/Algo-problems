/*
====================================================
📌 Problem: Minimum ASCII Delete Sum for Two Strings
(LeetCode 712)
====================================================

Given two strings s1 and s2, return the minimum
ASCII delete sum needed to make the two strings equal.

You may delete characters from either string.
Cost of deleting a character = its ASCII value.

----------------------------------------------------
🧠 Core DP Idea (Striver Style)
----------------------------------------------------
We compare strings using indices (i, j).

dp[i][j] = minimum ASCII delete sum to make
           s1[i..] and s2[j..] equal

----------------------------------------------------
🛠 Approaches Included
----------------------------------------------------
1️⃣ Memoization (Top-Down DP)
2️⃣ Tabulation (Bottom-Up DP)

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n * m)

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
Memoization : O(n * m) + recursion stack
Tabulation  : O(n * m)

====================================================
*/

import java.util.*;

class Solution {

    int n, m;
    int[][] dp;

    /* =================================================
       1️⃣ MEMOIZATION (Top-Down DP)
       ================================================= */

    public int minimumDeleteSum(String s1, String s2) {
        n = s1.length();
        m = s2.length();

        dp = new int[n + 1][m + 1];
        for (int[] row : dp) Arrays.fill(row, -1);

        return solve(0, 0, s1, s2);
    }

    private int solve(int i, int j, String s1, String s2) {

        // Both strings finished
        if (i == n && j == m) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        // s1 exhausted → delete remaining s2 chars
        if (i == n) {
            return dp[i][j] =
                    s2.charAt(j) + solve(i, j + 1, s1, s2);
        }

        // s2 exhausted → delete remaining s1 chars
        if (j == m) {
            return dp[i][j] =
                    s1.charAt(i) + solve(i + 1, j, s1, s2);
        }

        // Characters match
        if (s1.charAt(i) == s2.charAt(j)) {
            return dp[i][j] =
                    solve(i + 1, j + 1, s1, s2);
        }

        // Delete from s1 or s2
        int deleteS1 =
                s1.charAt(i) + solve(i + 1, j, s1, s2);
        int deleteS2 =
                s2.charAt(j) + solve(i, j + 1, s1, s2);

        return dp[i][j] = Math.min(deleteS1, deleteS2);
    }

    /* =================================================
       2️⃣ TABULATION (Bottom-Up DP)
       ================================================= */

    public int minimumDeleteSumTabulation(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n + 1][m + 1];

        // Base cases:
        // When s1 is exhausted
        for (int j = m - 1; j >= 0; j--) {
            dp[n][j] = dp[n][j + 1] + s2.charAt(j);
        }

        // When s2 is exhausted
        for (int i = n - 1; i >= 0; i--) {
            dp[i][m] = dp[i + 1][m] + s1.charAt(i);
        }

        // Fill table
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {

                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.min(
                            s1.charAt(i) + dp[i + 1][j],
                            s2.charAt(j) + dp[i][j + 1]
                    );
                }
            }
        }

        return dp[0][0];
    }
}
