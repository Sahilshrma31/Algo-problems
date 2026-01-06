/*
====================================================
📌 Problem: Longest Palindromic Subsequence (LPS)
====================================================

Given a string s, return the length of the
Longest Palindromic Subsequence.

----------------------------------------------------
🧠 Core Logic (LCS-based – Striver Style)
----------------------------------------------------
A palindrome reads the same forward and backward.

So:
👉 LPS(s) = LCS(s, reverse(s))

We reduce LPS to the classic LCS problem and
implement it using all DP approaches:
1️⃣ Recursion
2️⃣ Memoization (Top-Down DP)
3️⃣ Tabulation (Bottom-Up DP)
4️⃣ Space Optimized DP

----------------------------------------------------
⏱ Time & Space Complexity Summary
----------------------------------------------------

1️⃣ Recursion:
   Time: O(2^(n+n)) ≈ O(4^n)
   Space: O(n) (recursion stack)

2️⃣ Memoization:
   Time: O(n²)
   Space: O(n²) + recursion stack

3️⃣ Tabulation:
   Time: O(n²)
   Space: O(n²)

4️⃣ Space Optimized:
   Time: O(n²)
   Space: O(n)

====================================================
*/

import java.util.*;

class Solution {

    /* =================================================
       Helper: Reverse String
       ================================================= */

    private String reverse(String s) {
        char[] arr = s.toCharArray();
        int l = 0, r = s.length() - 1;
        while (l < r) {
            char temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
        return new String(arr);
    }

    /* =================================================
       1️⃣ RECURSION (Brute Force using LCS logic)
       ================================================= */

    public int longestPalindromeSubseqRecursion(String s) {
        String rev = reverse(s);
        return lcsRec(s, rev, s.length() - 1, rev.length() - 1);
    }

    private int lcsRec(String s1, String s2, int i, int j) {

        if (i < 0 || j < 0) return 0;

        if (s1.charAt(i) == s2.charAt(j)) {
            return 1 + lcsRec(s1, s2, i - 1, j - 1);
        }

        return Math.max(
            lcsRec(s1, s2, i - 1, j),
            lcsRec(s1, s2, i, j - 1)
        );
    }

    /* =================================================
       2️⃣ MEMOIZATION (Top-Down DP)
       ================================================= */

    public int longestPalindromeSubseqMemo(String s) {

        String rev = reverse(s);
        int n = s.length();

        int[][] dp = new int[n][n];
        for (int[] row : dp) Arrays.fill(row, -1);

        return lcsMemo(s, rev, n - 1, n - 1, dp);
    }

    private int lcsMemo(String s1, String s2, int i, int j, int[][] dp) {

        if (i < 0 || j < 0) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        if (s1.charAt(i) == s2.charAt(j)) {
            return dp[i][j] =
                1 + lcsMemo(s1, s2, i - 1, j - 1, dp);
        }

        return dp[i][j] = Math.max(
            lcsMemo(s1, s2, i - 1, j, dp),
            lcsMemo(s1, s2, i, j - 1, dp)
        );
    }

    /* =================================================
       3️⃣ TABULATION (Bottom-Up DP)
       ================================================= */

    public int longestPalindromeSubseqTabulation(String s) {

        String rev = reverse(s);
        int n = s.length();

        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {

                if (s.charAt(i - 1) == rev.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(
                        dp[i - 1][j],
                        dp[i][j - 1]
                    );
                }
            }
        }

        return dp[n][n];
    }

    /* =================================================
       4️⃣ SPACE OPTIMIZED DP
       ================================================= */

    public int longestPalindromeSubseqSpaceOptimized(String s) {

        String rev = reverse(s);
        int n = s.length();

        int[] prev = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int[] curr = new int[n + 1];

            for (int j = 1; j <= n; j++) {

                if (s.charAt(i - 1) == rev.charAt(j - 1)) {
                    curr[j] = 1 + prev[j - 1];
                } else {
                    curr[j] = Math.max(
                        prev[j],
                        curr[j - 1]
                    );
                }
            }
            prev = curr;
        }

        return prev[n];
    }
}

