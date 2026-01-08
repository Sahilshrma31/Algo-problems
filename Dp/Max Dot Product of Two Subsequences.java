/*
====================================================
📌 Problem: Max Dot Product of Two Subsequences
(LeetCode 1458)
====================================================

Given two integer arrays nums1 and nums2,
return the maximum dot product between
non-empty subsequences of nums1 and nums2.

----------------------------------------------------
🧠 Key Challenge
----------------------------------------------------
- Subsequences must be NON-EMPTY
- Numbers can be NEGATIVE
- Classic LCS-style DP, but with multiplication

To handle all-negative cases, we use a very small
negative constant (NEG).

----------------------------------------------------
🧩 DP State Definition
----------------------------------------------------
dp[i][j] = maximum dot product using:
           nums1[i..end] and nums2[j..end]

----------------------------------------------------
🛠 Approaches Covered (Striver Style)
----------------------------------------------------
1️⃣ Recursion (Brute Force)
2️⃣ Memoization (Top-Down DP)
3️⃣ Tabulation (Bottom-Up DP)

----------------------------------------------------
⏱ Time & Space Complexity
----------------------------------------------------
Let m = nums1.length, n = nums2.length

1️⃣ Recursion:
   Time: O(2^(m+n))
   Space: O(m+n) (recursion stack)

2️⃣ Memoization:
   Time: O(m * n)
   Space: O(m * n) + recursion stack

3️⃣ Tabulation:
   Time: O(m * n)
   Space: O(m * n)

====================================================
*/

import java.util.*;

class Solution {

    static final int NEG = -100000000;

    /* =================================================
       1️⃣ RECURSION (Brute Force)
       ================================================= */

    public int maxDotProductRecursion(int[] nums1, int[] nums2) {
        return helperRec(nums1, nums2, 0, 0);
    }

    private int helperRec(int[] a, int[] b, int i, int j) {

        if (i == a.length || j == b.length) {
            return NEG;
        }

        int val = a[i] * b[j];

        // Option 1: take both current elements
        int takeBoth = val + Math.max(0, helperRec(a, b, i + 1, j + 1));

        // Option 2: skip one element
        int skipA = helperRec(a, b, i + 1, j);
        int skipB = helperRec(a, b, i, j + 1);

        return Math.max(val, Math.max(takeBoth, Math.max(skipA, skipB)));
    }

    /* =================================================
       2️⃣ MEMOIZATION (Top-Down DP)
       ================================================= */

    public int maxDotProductMemo(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int n = nums2.length;

        int[][] dp = new int[m][n];
        for (int[] row : dp) Arrays.fill(row, Integer.MIN_VALUE);

        return helperMemo(nums1, nums2, 0, 0, dp);
    }

    private int helperMemo(int[] a, int[] b, int i, int j, int[][] dp) {

        if (i == a.length || j == b.length) {
            return NEG;
        }

        if (dp[i][j] != Integer.MIN_VALUE) {
            return dp[i][j];
        }

        int val = a[i] * b[j];

        int takeBoth = val + Math.max(0, helperMemo(a, b, i + 1, j + 1, dp));
        int skipA = helperMemo(a, b, i + 1, j, dp);
        int skipB = helperMemo(a, b, i, j + 1, dp);

        return dp[i][j] =
                Math.max(val, Math.max(takeBoth, Math.max(skipA, skipB)));
    }

    /* =================================================
       3️⃣ TABULATION (Bottom-Up DP)
       ================================================= */

    public int maxDotProduct(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int n = nums2.length;

        int[][] dp = new int[m + 1][n + 1];

        // Initialize with NEG
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = NEG;
            }
        }

        // Fill DP table
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                int val = nums1[i] * nums2[j];

                int takeBoth = val + Math.max(0, dp[i + 1][j + 1]);
                int skipNums1 = dp[i + 1][j];
                int skipNums2 = dp[i][j + 1];

                dp[i][j] = Math.max(
                        val,
                        Math.max(takeBoth, Math.max(skipNums1, skipNums2))
                );
            }
        }

        return dp[0][0];
    }
}
