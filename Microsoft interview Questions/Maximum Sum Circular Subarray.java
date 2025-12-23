/*
====================================================
📌 Problem: Maximum Sum Circular Subarray
LeetCode: 918. Maximum Sum Circular Subarray
====================================================

🔹 Problem Summary
----------------------------------------------------
Given a circular integer array nums, return the maximum
possible sum of a non-empty subarray of nums.

In a circular array, the end of the array connects to
the beginning.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
There are TWO possible cases for the maximum subarray sum:

1️⃣ Normal (Non-Circular) Subarray
   → Use standard Kadane’s Algorithm to find max subarray sum.

2️⃣ Circular Subarray
   → The max circular subarray sum =
     (Total Sum of Array) - (Minimum Subarray Sum)

Why?
Removing the minimum subarray from the middle gives the
maximum sum wrapping around the ends.

⚠️ Edge Case:
If all elements are negative, circular sum becomes 0.
So we must return the maximum (least negative) element.

----------------------------------------------------
🧩 Approach
----------------------------------------------------
1. Use Kadane’s Algorithm to find:
   - Maximum Subarray Sum
   - Minimum Subarray Sum
2. Compute total sum of array
3. Calculate circular sum = totalSum - minSubarraySum
4. Return:
   - max(maxSubarraySum, circularSum) if maxSubarraySum > 0
   - else maxSubarraySum

----------------------------------------------------
⏱ Time & Space Complexity
----------------------------------------------------
Time Complexity:  O(N)
Space Complexity: O(1)

====================================================
*/

class Solution {

    // Kadane's Algorithm for Maximum Subarray Sum
    public int kadanesMax(int[] nums) {
        int sum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(nums[i], sum + nums[i]);
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    // Kadane's Algorithm for Minimum Subarray Sum
    public int kadanesMin(int[] nums) {
        int sum = nums[0];
        int minSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            sum = Math.min(nums[i], sum + nums[i]);
            minSum = Math.min(minSum, sum);
        }
        return minSum;
    }

    // Main Function
    public int maxSubarraySumCircular(int[] nums) {
        int totalSum = 0;
        for (int x : nums) totalSum += x;

        int maxSum = kadanesMax(nums);
        int minSum = kadanesMin(nums);

        int circularSum = totalSum - minSum;

       
       if (maxSum > 0) {
            return Math.max(maxSum, circularSum);
        }
         // If all numbers are negative
        return maxSum;
    }
}
