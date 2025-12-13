/**
 * ------------------------------------------------------------
 * Problem: House Robber II (Circular Houses)
 * ------------------------------------------------------------
 * Given an array nums[] where each element represents money in a house,
 * houses are arranged in a circle. You cannot rob two adjacent houses.
 *
 * Return the maximum amount that can be robbed without alerting police.
 *
 * ------------------------------------------------------------
 * Intuition:
 * Because houses are in a circle:
 * - First and last house are adjacent
 * - So we split the problem into TWO linear cases:
 *
 * Case 1: Exclude the first house → consider houses [1 ... n-1]
 * Case 2: Exclude the last house  → consider houses [0 ... n-2]
 *
 * Answer = max(result of both cases)
 *
 * ------------------------------------------------------------
 * Approach:
 * 1. Use classic House Robber DP for linear houses
 * 2. Create two arrays:
 *    - temp1: excludes first house
 *    - temp2: excludes last house
 * 3. Apply DP on both and return max
 *
 * ------------------------------------------------------------
 * Time Complexity:
 * O(N)
 *
 * Space Complexity:
 * O(N)
 * ------------------------------------------------------------
 */

class Solution {

    // Linear House Robber DP
    public int max(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        dp[0] = nums[0]; // base case

        for (int i = 1; i < n; i++) {
            int pick = nums[i];
            if (i > 1) {
                pick += dp[i - 2];
            }
            int notPick = dp[i - 1];
            dp[i] = Math.max(pick, notPick);
        }

        return dp[n - 1];
    }

    // Circular House Robber
    public int rob(int[] nums) {
        int n = nums.length;

        if (n == 1) {
            return nums[0];
        }

        int[] temp1 = new int[n - 1]; // excludes first
        int[] temp2 = new int[n - 1]; // excludes last

        for (int i = 0; i < n; i++) {
            if (i != 0) temp1[i - 1] = nums[i];
            if (i != n - 1) temp2[i] = nums[i];
        }

        return Math.max(max(temp1), max(temp2));
    }
}
