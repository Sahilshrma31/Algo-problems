/*
====================================================
📌 Problem: Longest Common Substring
====================================================

Given two strings s1 and s2, find the length of the
Longest Common Substring (continuous characters).

⚠️ NOTE:
- Substring must be contiguous
- This is DIFFERENT from Longest Common Subsequence

----------------------------------------------------
🧠  All Approaches Covered
----------------------------------------------------
1️⃣ Recursion (Brute Force)
2️⃣ Memoization (Top-Down DP)
3️⃣ Tabulation (Bottom-Up DP)
4️⃣ Space Optimized DP

----------------------------------------------------
⏱ Time & Space Complexity Summary
----------------------------------------------------

1️⃣ Recursion:
   Time: O(2^(n+m))
   Space: O(n+m)

2️⃣ Memoization:
   Time: O(n*m)
   Space: O(n*m) + recursion stack

3️⃣ Tabulation:
   Time: O(n*m)
   Space: O(n*m)

4️⃣ Space Optimized:
   Time: O(n*m)
   Space: O(m)

====================================================
*/

import java.util.*;

class Solution {

    public int longestCommonSubstringTabulation(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n + 1][m + 1];
        int maxLen = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    maxLen = Math.max(maxLen, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return maxLen;
    }

    /* =================================================
       4️⃣ SPACE OPTIMIZED DP
       ================================================= */

    public int longestCommonSubstringSpaceOptimized(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        int[] prev = new int[m + 1];
        int maxLen = 0;

        for (int i = 1; i <= n; i++) {
            int[] curr = new int[m + 1];

            for (int j = 1; j <= m; j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    curr[j] = 1 + prev[j - 1];
                    maxLen = Math.max(maxLen, curr[j]);
                } else {
                    curr[j] = 0;
                }
            }
            prev = curr;
        }

        return maxLen;
    }
}
