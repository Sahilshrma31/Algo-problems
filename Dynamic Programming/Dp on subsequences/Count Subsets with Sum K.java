/*
====================================================
📌 Problem: Count Subsets with Sum K
====================================================

Given an array of integers arr[] and an integer K,
count the total number of subsets whose sum is exactly K.

A subset can be empty or non-empty, but order does not matter.

----------------------------------------------------
🧠 Intuition (Striver DP Style)
----------------------------------------------------
At each index, we have two choices:
1️⃣ Pick the current element
2️⃣ Do NOT pick the current element

We recursively try both choices and count valid subsets
whose sum becomes exactly K.

Since overlapping subproblems exist, we optimize using DP.

----------------------------------------------------
🧩 Approaches Covered
----------------------------------------------------
1️⃣ Recursive (Brute Force)
2️⃣ Memoization (Top-Down DP)
3️⃣ Tabulation (Bottom-Up DP)
4️⃣ Space Optimized DP

----------------------------------------------------
⏱ Time & Space Complexity
----------------------------------------------------
Let N = size of array, K = target sum

1️⃣ Recursive:
   Time: O(2^N)
   Space: O(N) (recursion stack)

2️⃣ Memoization:
   Time: O(N * K)
   Space: O(N * K) + O(N)

3️⃣ Tabulation:
   Time: O(N * K)
   Space: O(N * K)

4️⃣ Space Optimized:
   Time: O(N * K)
   Space: O(K)

====================================================
*/

import java.util.*;

class Solution {

    /* ==========================
       1️⃣ RECURSIVE APPROACH
       ========================== */
    public int countSubsetsRecursive(int[] arr, int k) {
        return helperRec(arr, arr.length - 1, k);
    }

    private int helperRec(int[] arr, int idx, int target) {
        if (idx == 0) {
            if (target == 0 && arr[0] == 0) return 2;
            if (target == 0 || target == arr[0]) return 1;
            return 0;
        }

        int notPick = helperRec(arr, idx - 1, target);
        int pick = 0;
        if (arr[idx] <= target) {
            pick = helperRec(arr, idx - 1, target - arr[idx]);
        }

        return pick + notPick;
    }

    /* ==========================
       2️⃣ MEMOIZATION APPROACH
       ========================== */
    public int countSubsetsMemo(int[] arr, int k) {
        int n = arr.length;
        int[][] dp = new int[n][k + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return helperMemo(arr, n - 1, k, dp);
    }

    private int helperMemo(int[] arr, int idx, int target, int[][] dp) {
        if (idx == 0) {
            if (target == 0 && arr[0] == 0) return 2;
            if (target == 0 || target == arr[0]) return 1;
            return 0;
        }

        if (dp[idx][target] != -1) return dp[idx][target];

        int notPick = helperMemo(arr, idx - 1, target, dp);
        int pick = 0;
        if (arr[idx] <= target) {
            pick = helperMemo(arr, idx - 1, target - arr[idx], dp);
        }

        return dp[idx][target] = pick + notPick;
    }

    /* ==========================
       3️⃣ TABULATION APPROACH
       ========================== */
    public int countSubsetsTabulation(int[] arr, int k) {
        int n = arr.length;
        int[][] dp = new int[n][k + 1];

        // Base case
        if (arr[0] == 0) dp[0][0] = 2;
        else dp[0][0] = 1;

        if (arr[0] != 0 && arr[0] <= k) {
            dp[0][arr[0]] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int target = 0; target <= k; target++) {
                int notPick = dp[i - 1][target];
                int pick = 0;
                if (arr[i] <= target) {
                    pick = dp[i - 1][target - arr[i]];
                }
                dp[i][target] = pick + notPick;
            }
        }

        return dp[n - 1][k];
    }

    /* ==============================
       4️⃣ SPACE OPTIMIZED APPROACH
       ============================== */
    public int countSubsetsSpaceOptimized(int[] arr, int k) {
        int n = arr.length;
        int[] prev = new int[k + 1];

        if (arr[0] == 0) prev[0] = 2;
        else prev[0] = 1;

        if (arr[0] != 0 && arr[0] <= k) {
            prev[arr[0]] = 1;
        }

        for (int i = 1; i < n; i++) {
            int[] curr = new int[k + 1];
            for (int target = 0; target <= k; target++) {
                int notPick = prev[target];
                int pick = 0;
                if (arr[i] <= target) {
                    pick = prev[target - arr[i]];
                }
                curr[target] = pick + notPick;
            }
            prev = curr;
        }

        return prev[k];
    }
}
