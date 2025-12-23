/*
====================================================
📌 Problem: Maximum Absolute Sum of Any Subarray
LeetCode: 1749
====================================================

Given an integer array nums, find the maximum absolute
sum of any (possibly empty) subarray.

----------------------------------------------------
🧠 Approach
----------------------------------------------------
We use **Kadane’s Algorithm** twice:
1️⃣ To find the **maximum subarray sum**
2️⃣ To find the **minimum subarray sum**

The answer is:
max( |maximum subarray sum|, |minimum subarray sum| )

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(N)

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(1)

====================================================
*/

class Solution {

    public int maxAbsoluteSum(int[] nums) {
        return Math.max(kadanesMax(nums), kadanesMin(nums));
    }

    // Kadane's Algorithm for Maximum Subarray Sum
    private int kadanesMax(int[] nums) {
        int maxSum = nums[0];
        int currSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(maxSum, currSum);
        }
        return Math.abs(maxSum);
    }

    // Kadane's Algorithm for Minimum Subarray Sum
    private int kadanesMin(int[] nums) {
        int minSum = nums[0];
        int currSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currSum = Math.min(nums[i], currSum + nums[i]);
            minSum = Math.min(minSum, currSum);
        }
        return Math.abs(minSum);
    }
}
