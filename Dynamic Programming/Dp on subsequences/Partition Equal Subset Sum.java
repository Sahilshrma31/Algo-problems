/*
====================================================
📌 Problem: Partition Equal Subset Sum
====================================================

Given an integer array nums, determine if the array
can be partitioned into two subsets such that the
sum of elements in both subsets is equal.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
If total sum of the array is odd → partition is
IMPOSSIBLE.

If total sum is even, the problem reduces to:
👉 Can we find a subset with sum = totalSum / 2 ?

This is a classic **Subset Sum = K** problem.

----------------------------------------------------
🧩 Approaches Covered (Striver Style)
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
K = totalSum / 2

1️⃣ Recursion:
   Time: O(2^N)
   Space: O(N)

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

    public boolean canPartitionRecursive(int[] nums) {
        int totalSum = 0;
        for (int num : nums) totalSum += num;

        if (totalSum % 2 != 0) return false;

        return subsetSumRec(nums, nums.length - 1, totalSum / 2);
    }

    private boolean subsetSumRec(int[] nums, int idx, int target) {
        if (target == 0) return true;
        if (idx == 0) return nums[0] == target;

        boolean notPick = subsetSumRec(nums, idx - 1, target);
        boolean pick = false;

        if (nums[idx] <= target) {
            pick = subsetSumRec(nums, idx - 1, target - nums[idx]);
        }

        return pick || notPick;
    }

    /* ==========================
       2️⃣ MEMOIZATION APPROACH
       ========================== */

    public boolean canPartitionMemo(int[] nums) {
        int totalSum = 0;
        for (int num : nums) totalSum += num;

        if (totalSum % 2 != 0) return false;

        int target = totalSum / 2;
        int[][] dp = new int[nums.length][target + 1];

        for (int[] row : dp) Arrays.fill(row, -1);

        return subsetSumMemo(nums, nums.length - 1, target, dp);
    }

    private boolean subsetSumMemo(int[] nums, int idx, int target, int[][] dp) {
        if (target == 0) return true;
        if (idx == 0) return nums[0] == target;

        if (dp[idx][target] != -1) {
            return dp[idx][target] == 1;
        }

        boolean notPick = subsetSumMemo(nums, idx - 1, target, dp);
        boolean pick = false;

        if (nums[idx] <= target) {
            pick = subsetSumMemo(nums, idx - 1, target - nums[idx], dp);
        }

        dp[idx][target] = (pick || notPick) ? 1 : 0;
        return dp[idx][target] == 1;
    }

    /* ======================
       3️⃣ TABULATION DP
       ====================== */

    public boolean canPartitionTabulation(int[] nums) {
        int totalSum = 0;
        for (int num : nums) totalSum += num;

        if (totalSum % 2 != 0) return false;

        int target = totalSum / 2;
        int n = nums.length;

        boolean[][] dp = new boolean[n][target + 1];

        // Base case
        for (int i = 0; i < n; i++) dp[i][0] = true;
        if (nums[0] <= target) dp[0][nums[0]] = true;

        for (int i = 1; i < n; i++) {
            for (int t = 1; t <= target; t++) {
                boolean notPick = dp[i - 1][t];
                boolean pick = false;

                if (nums[i] <= t) {
                    pick = dp[i - 1][t - nums[i]];
                }

                dp[i][t] = pick || notPick;
            }
        }

        return dp[n - 1][target];
    }

    /* =============================
       4️⃣ SPACE OPTIMIZED DP
       ============================= */

    public boolean canPartitionSpaceOptimized(int[] nums) {
        int totalSum = 0;
        for (int num : nums) totalSum += num;

        if (totalSum % 2 != 0) return false;

        int target = totalSum / 2;
        boolean[] prev = new boolean[target + 1];

        prev[0] = true;
        if (nums[0] <= target) prev[nums[0]] = true;

        for (int i = 1; i < nums.length; i++) {
            boolean[] curr = new boolean[target + 1];
            curr[0] = true;

            for (int t = 1; t <= target; t++) {
                boolean notPick = prev[t];
                boolean pick = false;

                if (nums[i] <= t) {
                    pick = prev[t - nums[i]];
                }

                curr[t] = pick || notPick;
            }
            prev = curr;
        }

        return prev[target];
    }
}
