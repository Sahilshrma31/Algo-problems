/*
====================================================
📌 Problem: Print Longest Common Subsequence (LCS)
====================================================

Given two strings s1 and s2 of lengths n and m,
return their Longest Common Subsequence (LCS).

A subsequence is formed by deleting characters
without changing relative order.

----------------------------------------------------
🧠 Approach 
----------------------------------------------------
1️⃣ Build DP table for LCS length
2️⃣ Backtrack from dp[n][m] to reconstruct LCS
3️⃣ Reverse the constructed string

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n * m)

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(n * m)

====================================================
*/

public class p2{

    public static String findLCS(int n, int m, String s1, String s2) {

        // DP table
        int[][] dp = new int[n + 1][m + 1];

        // Build LCS length table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(
                        dp[i - 1][j],
                        dp[i][j - 1]
                    );
                }
            }
        }

        // Backtrack to build LCS string
        StringBuilder lcs = new StringBuilder();
        int i = n, j = m;

        while (i > 0 && j > 0) {

            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcs.append(s1.charAt(i - 1));
                i--;
                j--;
            }
            else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            }
            else {
                j--;
            }
        }

        return lcs.reverse().toString();
    }
}
