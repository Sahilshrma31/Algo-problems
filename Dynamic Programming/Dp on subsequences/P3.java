/*
====================================================
📌 Problem: Partition a Set Into Two Subsets
        With Minimum Absolute Sum Difference
====================================================

Given an array of integers, partition it into two
subsets such that the absolute difference between
their subset sums is minimized.

----------------------------------------------------
🧠 Key Idea
----------------------------------------------------
Let totalSum = sum of all elements.

If we pick a subset with sum = S1,
then the other subset has sum = S2 = totalSum - S1

Difference = |S2 - S1|
            = |totalSum - 2*S1|

So the goal is:
👉 Find a subset sum S1 as close as possible to totalSum / 2

----------------------------------------------------
🧩 DP Transformation
----------------------------------------------------
This becomes a classic:
👉 "Subset Sum" problem

We compute all possible subset sums up to totalSum
and then choose the best S1 ≤ totalSum/2.

----------------------------------------------------
🛠 Approaches Covered
----------------------------------------------------
1️⃣ Recursive (Brute Force)  
2️⃣ Memoization (Top-Down DP)  
3️⃣ Tabulation (Bottom-Up DP)  
4️⃣ Space Optimized DP (Best)

----------------------------------------------------
⏱ Complexity
----------------------------------------------------
Let N = size of array
Let S = total sum

Time Complexity:
- Memo / Tab / Space Optimized: O(N * S)

Space Complexity:
- Memo: O(N * S)
- Tabulation: O(N * S)
- Space Optimized: O(S)

====================================================
*/

import java.util.*;

class Solution {

    /* ===========================
       1️⃣ MEMOIZATION APPROACH
       =========================== */

    public int minSubsetSumDifferenceMemo(int[] arr) {
        int n = arr.length;
        int totalSum = 0;
        for (int x : arr) totalSum += x;

        Boolean[][] dp = new Boolean[n][totalSum + 1];

        int minDiff = Integer.MAX_VALUE;

        for (int s1 = 0; s1 <= totalSum / 2; s1++) {
            if (subsetSumMemo(arr, n - 1, s1, dp)) {
                int s2 = totalSum - s1;
                minDiff = Math.min(minDiff, Math.abs(s2 - s1));
            }
        }
        return minDiff;
    }

    private boolean subsetSumMemo(int[] arr, int idx, int target, Boolean[][] dp) {
        if (target == 0) return true;
        if (idx == 0) return arr[0] == target;

        if (dp[idx][target] != null) return dp[idx][target];

        boolean notPick = subsetSumMemo(arr, idx - 1, target, dp);
        boolean pick = false;
        if (arr[idx] <= target) {
            pick = subsetSumMemo(arr, idx - 1, target - arr[idx], dp);
        }

        return dp[idx][target] = pick || notPick;
    }

    /* ===========================
       2️⃣ TABULATION APPROACH
       =========================== */

    public int minSubsetSumDifferenceTab(int[] arr) {
        int n = arr.length;
        int totalSum = 0;
        for (int x : arr) totalSum += x;

        boolean[][] dp = new boolean[n][totalSum + 1];

        // Base case
        for (int i = 0; i < n; i++) dp[i][0] = true;
        if (arr[0] <= totalSum) dp[0][arr[0]] = true;

        for (int i = 1; i < n; i++) {
            for (int target = 1; target <= totalSum; target++) {
                boolean notPick = dp[i - 1][target];
                boolean pick = false;
                if (arr[i] <= target) {
                    pick = dp[i - 1][target - arr[i]];
                }
                dp[i][target] = pick || notPick;
            }
        }

        int minDiff = Integer.MAX_VALUE;
        for (int s1 = 0; s1 <= totalSum / 2; s1++) {
            if (dp[n - 1][s1]) {
                minDiff = Math.min(minDiff,
                        Math.abs((totalSum - s1) - s1));
            }
        }
        return minDiff;
    }

    /* ===============================
       3️⃣ SPACE OPTIMIZED APPROACH
       =============================== */

    public int minSubsetSumDifference(int[] arr) {
        int n = arr.length;
        int totalSum = 0;
        for (int x : arr) totalSum += x;

        boolean[] prev = new boolean[totalSum + 1];
        prev[0] = true;
        if (arr[0] <= totalSum) prev[arr[0]] = true;

        for (int i = 1; i < n; i++) {
            boolean[] curr = new boolean[totalSum + 1];
            curr[0] = true;

            for (int target = 1; target <= totalSum; target++) {
                boolean notPick = prev[target];
                boolean pick = false;
                if (arr[i] <= target) {
                    pick = prev[target - arr[i]];
                }
                curr[target] = pick || notPick;
            }
            prev = curr;
        }

        int minDiff = Integer.MAX_VALUE;
        for (int s1 = 0; s1 <= totalSum / 2; s1++) {
            if (prev[s1]) {
                minDiff = Math.min(minDiff,
                        Math.abs((totalSum - s1) - s1));
            }
        }
        return minDiff;
    }
}

