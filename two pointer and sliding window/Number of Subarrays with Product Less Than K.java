/*
====================================================
📌 Problem: Number of Subarrays with Product Less Than K
(LeetCode 713)
====================================================

Given an array of positive integers nums and an integer k,
return the number of contiguous subarrays where the product
of all the elements in the subarray is strictly less than k.

----------------------------------------------------
🧠 Approach Used
----------------------------------------------------
Sliding Window (Two Pointers)

- Maintain a window [i .. j]
- Keep multiplying nums[j] into product
- If product >= k, shrink window from the left
- Number of valid subarrays ending at j = (j - i + 1)

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n)

----------------------------------------------------
🧠 Space Complexity
----------------------------------------------------
O(1)

====================================================
*/

class Solution {

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;

        int n = nums.length;
        int i = 0;
        int product = 1;
        int count = 0;

        for (int j = 0; j < n; j++) {
            product *= nums[j];

            // Shrink window if product >= k
            while (product >= k) {
                product /= nums[i];
                i++;
            }

            // Count subarrays ending at j
            count += (j - i + 1);
        }

        return count;
    }
}
