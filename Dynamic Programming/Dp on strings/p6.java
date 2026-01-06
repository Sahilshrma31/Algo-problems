/*
====================================================
📌 Problem: Minimum Insertions/Deletions to Convert
    String A to String B
====================================================

You are given two strings:
- String A (length n)
- String B (length m)

You are allowed only:
1️⃣ Insertions
2️⃣ Deletions

Find the minimum number of operations required
to convert String A into String B.

----------------------------------------------------
🧠 Key Insight (Striver Style)
----------------------------------------------------
To convert A → B using only insertions & deletions:

• Characters that are part of the
  Longest Common Subsequence (LCS)
  do NOT need any operation.

• All other characters must be:
  - Deleted from A
  - Inserted into A to match B

----------------------------------------------------
🧩 Formula
----------------------------------------------------
Let:
L = length of LCS(A, B)

Minimum Deletions = n - L
Minimum Insertions = m - L

Total Operations = (n - L) + (m - L)

----------------------------------------------------
🛠 Approaches Covered
----------------------------------------------------
1️⃣ Recursion (LCS)
2️⃣ Memoization (Top-Down DP)
3️⃣ Tabulation (Bottom-Up DP)
4️⃣ Space Optimized DP

----------------------------------------------------
⏱ Time & Space Complexity
----------------------------------------------------
All DP approaches:
Time: O(n * m)

Space:
- Recursion: O(n + m)
- Memoization: O(n * m)
- Tabulation: O(n * m)
- Space Optimized: O(m)

====================================================
*/

import java.util.*;

class Solution {

    /* =================================================
       1️⃣ RECURSION (Brute Force LCS)
       ================================================= */

    public int minOperationsRecursion(String a, String b) {
        int lcs = lcsRec(a, b, a.length() - 1, b.length() - 1);
        return (a.length() - lcs) + (b.length() - lcs);
    }

    private int lcsRec(String a, String b, int i, int j) {
        if (i < 0 || j < 0) return 0;

        if (a.charAt(i) == b.charAt(j)) {
            return 1 + lcsRec(a, b, i - 1, j - 1);
        }

        return Math.max(
            lcsRec(a, b, i - 1, j),
            lcsRec(a, b, i, j - 1)
        );
    }

    /* =================================================
       2️⃣ MEMOIZATION (Top-Down DP)
       ================================================= */

    public int minOperationsMemo(String a, String b) {

        int n = a.length();
        int m = b.length();

        int[][] dp = new int[n][m];
        for (int[] row : dp) Arrays.fill(row, -1);

        int lcs = lcsMemo(a, b, n - 1, m - 1, dp);
        return (n - lcs) + (m - lcs);
    }

    private int lcsMemo(String a, String b, int i, int j, int[][] dp) {
        if (i < 0 || j < 0) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        if (a.charAt(i) == b.charAt(j)) {
            return dp[i][j] =
                1 + lcsMemo(a, b, i - 1, j - 1, dp);
        }

        return dp[i][j] = Math.max(
            lcsMemo(a, b, i - 1, j, dp),
            lcsMemo(a, b, i, j - 1, dp)
        );
    }

    /* =================================================
       3️⃣ TABULATION (Bottom-Up DP)
       ================================================= */

    public int minOperationsTabulation(String a, String b) {

        int n = a.length();
        int m = b.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(
                        dp[i - 1][j],
                        dp[i][j - 1]
                    );
                }
            }
        }

        int lcs = dp[n][m];
        return (n - lcs) + (m - lcs);
    }

    /* =================================================
       4️⃣ SPACE OPTIMIZED DP
       ================================================= */

    public int minOperationsSpaceOptimized(String a, String b) {

        int n = a.length();
        int m = b.length();

        int[] prev = new int[m + 1];

        for (int i = 1; i <= n; i++) {
            int[] curr = new int[m + 1];

            for (int j = 1; j <= m; j++) {

                if (a.charAt(i - 1) == b.charAt(j - 1)) {
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

        int lcs = prev[m];
        return (n - lcs) + (m - lcs);
    }
}

