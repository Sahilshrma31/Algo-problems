/*
====================================================
📌 Problem: Count Partitions With Given Difference
====================================================

Given an array arr[] and an integer diff,
count the number of ways to partition the array
into two subsets S1 and S2 such that:

|sum(S1) - sum(S2)| = diff

----------------------------------------------------
🔍 Key Observation (Striver Insight)
----------------------------------------------------
Let:
S1 - S2 = diff
S1 + S2 = totalSum

⇒ S1 = (totalSum + diff) / 2

So the problem reduces to:
👉 Count number of subsets with sum = target
where target = (totalSum + diff) / 2

If (totalSum + diff) is odd → answer = 0

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
This is a classic "Count Subsets with Sum K" problem.

At every index:
- Pick the element
- Or do not pick the element

Count all valid combinations.

----------------------------------------------------
🧩 Approaches Implemented
----------------------------------------------------
1️⃣ Recursive (Brute Force)
2️⃣ Memoization (Top-Down DP)
3️⃣ Tabulation (Bottom-Up DP)
4️⃣ Space Optimized DP

----------------------------------------------------
⏱ Time & Space Complexity
----------------------------------------------------
Let N = size of array
Let K = target sum

1️⃣ Recursion:
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
    public int countPartitionsRecursive(int[] arr, int diff) {
        int totalSum = 0;
        for (int x : arr) totalSum += x;

        if ((totalSum + diff) % 2 != 0) return 0;
        int target = (totalSum + diff) / 2;

        return helperRec(arr.length - 1, target, arr);
    }

    private int helperRec(int idx, int sum, int[] arr) {
        if (idx == 0) {
            if (sum == 0 && arr[0] == 0) return 2;
            if (sum == 0 || sum == arr[0]) return 1;
            return 0;
        }

        int notPick = helperRec(idx - 1, sum, arr);
        int pick = 0;
        if (arr[idx] <= sum) {
            pick = helperRec(idx - 1, sum - arr[idx], arr);
        }

        return pick + notPick;
    }

    /* ==========================
       2️⃣ MEMOIZATION APPROACH
       ========================== */
    public int countPartitionsMemo(int[] arr, int diff) {
        int totalSum = 0;
        for (int x : arr) totalSum += x;

        if ((totalSum + diff) % 2 != 0) return 0;
        int target = (totalSum + diff) / 2;

        int[][] dp = new int[arr.length][target + 1];
        for (int[] row : dp) Arrays.fill(row, -1);

        return helperMemo(arr.length - 1, target, arr, dp);
    }

    private int helperMemo(int idx, int sum, int[] arr, int[][] dp) {
        if (idx == 0) {
            if (sum == 0 && arr[0] == 0) return 2;
            if (sum == 0 || sum == arr[0]) return 1;
            return 0;
        }

        if (dp[idx][sum] != -1) return dp[idx][sum];

        int notPick = helperMemo(idx - 1, sum, arr, dp);
        int pick = 0;
        if (arr[idx] <= sum) {
            pick = helperMemo(idx - 1, sum - arr[idx], arr, dp);
        }

        return dp[idx][sum] = pick + notPick;
    }

    /* ==========================
       3️⃣ TABULATION APPROACH
       ========================== */
    public int countPartitionsTabulation(int[] arr, int diff) {
        int totalSum = 0;
        for (int x : arr) totalSum += x;

        if ((totalSum + diff) % 2 != 0) return 0;
        int target = (totalSum + diff) / 2;

        int n = arr.length;
        int[][] dp = new int[n][target + 1];

        // Base case
        if (arr[0] == 0) {
            dp[0][0] = 2;
        } else {
            dp[0][0] = 1;
            if (arr[0] <= target) dp[0][arr[0]] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int s = 0; s <= target; s++) {
                int notPick = dp[i - 1][s];
                int pick = 0;
                if (arr[i] <= s) {
                    pick = dp[i - 1][s - arr[i]];
                }
                dp[i][s] = pick + notPick;
            }
        }

        return dp[n - 1][target];
    }

    /* ===============================
       4️⃣ SPACE OPTIMIZED APPROACH
       =============================== */
    public int countPartitionsSpaceOptimized(int[] arr, int diff) {
        int totalSum = 0;
        for (int x : arr) totalSum += x;

        if ((totalSum + diff) % 2 != 0) return 0;
        int target = (totalSum + diff) / 2;

        int[] prev = new int[target + 1];

        if (arr[0] == 0) {
            prev[0] = 2;
        } else {
            prev[0] = 1;
            if (arr[0] <= target) prev[arr[0]] = 1;
        }

        for (int i = 1; i < arr.length; i++) {
            int[] curr = new int[target + 1];
            for (int s = 0; s <= target; s++) {
                int notPick = prev[s];
                int pick = 0;
                if (arr[i] <= s) {
                    pick = prev[s - arr[i]];
                }
                curr[s] = pick + notPick;
            }
            prev = curr;
        }

        return prev[target];
    }
}

