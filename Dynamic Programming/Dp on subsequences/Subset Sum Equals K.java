/*
====================================================
📌 Problem: Subset Sum Equals K
====================================================

Given an array of integers arr[] and an integer K,
determine whether there exists a subset whose sum
is exactly equal to K.

You may choose or not choose each element.
Each element can be used at most once.

----------------------------------------------------
🧠 Intuition 
----------------------------------------------------
For every index i, we have two choices:
1️⃣ Pick the element arr[i]
2️⃣ Do not pick the element arr[i]

The problem reduces to:
"Can we make sum = K using elements from index 0..i?"

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
Let:
N = number of elements
K = target sum

1️⃣ Recursion:
   Time: O(2^N)
   Space: O(N) (recursion stack)

2️⃣ Memoization:
   Time: O(N * K)
   Space: O(N * K) + O(N) stack

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

    /* =======================
       1️⃣ RECURSIVE APPROACH
       ======================= */

    public boolean subsetSumRecursive(int[] arr, int k) {
        return helperRec(arr, arr.length - 1, k);
    }

    private boolean helperRec(int[] arr, int idx, int target) {
        if (target == 0) return true;
        if (idx == 0) return arr[0] == target;

        boolean notPick = helperRec(arr, idx - 1, target);
        boolean pick = false;

        if (arr[idx] <= target) {
            pick = helperRec(arr, idx - 1, target - arr[idx]);
        }

        return pick || notPick;
    }

    /* =========================
       2️⃣ MEMOIZATION APPROACH
       ========================= */

    public boolean subsetSumMemo(int[] arr, int k) {
        int n = arr.length;
        int[][] dp = new int[n][k + 1];

        for (int[] row : dp) Arrays.fill(row, -1);

        return helperMemo(arr, n - 1, k, dp);
    }

    private boolean helperMemo(int[] arr, int idx, int target, int[][] dp) {
        if (target == 0) return true;
        if (idx == 0) return arr[0] == target;

        if (dp[idx][target] != -1) {
            return dp[idx][target] == 1;
        }

        boolean notPick = helperMemo(arr, idx - 1, target, dp);
        boolean pick = false;

        if (arr[idx] <= target) {
            pick = helperMemo(arr, idx - 1, target - arr[idx], dp);
        }

        dp[idx][target] = (pick || notPick) ? 1 : 0;
        return dp[idx][target] == 1;
    }

    /* ======================
       3️⃣ TABULATION DP
       ====================== */

    public boolean subsetSumTabulation(int[] arr, int k) {
        int n = arr.length;
        boolean[][] dp = new boolean[n][k + 1];

        // Base case
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        if (arr[0] <= k) {
            dp[0][arr[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            for (int target = 1; target <= k; target++) {

                boolean notPick = dp[i - 1][target];
                boolean pick = false;

                if (arr[i] <= target) {
                    pick = dp[i - 1][target - arr[i]];
                }

                dp[i][target] = pick || notPick;
            }
        }

        return dp[n - 1][k];
    }

    /* =============================
       4️⃣ SPACE OPTIMIZED DP
       ============================= */

    public boolean subsetSumSpaceOptimized(int[] arr, int k) {
        int n = arr.length;
        boolean[] prev = new boolean[k + 1];

        prev[0] = true;
        if (arr[0] <= k) prev[arr[0]] = true;

        for (int i = 1; i < n; i++) {
            boolean[] curr = new boolean[k + 1];
            curr[0] = true;

            for (int target = 1; target <= k; target++) {
                boolean notPick = prev[target];
                boolean pick = false;

                if (arr[i] <= target) {
                    pick = prev[target - arr[i]];
                }

                curr[target] = pick || notPick;
            }
            prev = curr;
        }

        return prev[k];
    }
}
