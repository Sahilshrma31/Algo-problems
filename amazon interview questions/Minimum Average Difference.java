/*
====================================================
📌 Problem: Minimum Average Difference (LeetCode 2256)
====================================================

You are given an integer array nums of length n.
For each index i, compute the absolute difference between:

- the average of the first (i + 1) elements
- the average of the remaining (n - i - 1) elements

Return the index with the minimum average difference.
If multiple answers exist, return the smallest index.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
- Precompute the total sum of the array.
- Traverse from left to right while maintaining a prefix sum.
- At each index:
  - Compute left average using prefix sum.
  - Compute right average using remaining sum.
- Track the index with the minimum absolute difference.

----------------------------------------------------
🧩 Approach
----------------------------------------------------
1️⃣ Compute total sum of the array.
2️⃣ Iterate over array:
   - Update prefix sum
   - Compute left & right averages
   - Compute absolute difference
   - Update answer if smaller difference is found

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
    public int minimumAverageDifference(int[] nums) {
        int n = nums.length;

        long totalSum = 0;
        for (int x : nums) {
            totalSum += x;
        }

        long leftSum = 0;
        int resIndex = 0;
        long minDiff = Long.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            leftSum += nums[i];
            long rightSum = totalSum - leftSum;

            int leftCount = i + 1;
            int rightCount = n - leftCount;

            long leftAvg = leftSum / leftCount;
            long rightAvg = (rightCount == 0) ? 0 : rightSum / rightCount;

            long diff = Math.abs(leftAvg - rightAvg);

            if (diff < minDiff) {
                minDiff = diff;
                resIndex = i;
            }
        }

        return resIndex;
    }
}
