/*
Q3. Count Bowl Subarrays
------------------------
📌 Problem Statement (LeetCode):
You are given an integer array nums with distinct elements.

A subarray nums[l...r] of nums is called a bowl if:
1. The subarray has length at least 3. That is, r - l + 1 >= 3.
2. The minimum of its two ends is strictly greater than the maximum of all elements in between. 
   That is, min(nums[l], nums[r]) > max(nums[l + 1], ..., nums[r - 1]).

Return the number of bowl subarrays in nums.

---------------------------------------------------
💡 Intuition:
- For any index `i`, a subarray can be a bowl only if:
  - nums[i] is **not the maximum seen from the left**, and
  - nums[i] is **not the maximum seen from the right**.
- Why? Because if nums[i] is the max from either side, it will break the 
  "bowl shape" condition.
- So, we precompute:
  - `lmax[i]` = maximum element from left up to i.
  - `rmax[i]` = maximum element from right up to i.
- Then, count indices where nums[i] is **strictly smaller** than both lmax[i] and rmax[i].
- That element will be inside some bowl subarray, contributing to the answer.

---------------------------------------------------
⏱ Time Complexity:
- O(n) → single pass for lmax, rmax, and counting.

💾 Space Complexity:
- O(n) → extra arrays lmax and rmax.

---------------------------------------------------
✅ Examples:

Input: nums = [2,5,3,1,4]
Output: 2
Explanation: The bowl subarrays are [3,1,4] and [5,3,1,4].

Input: nums = [5,1,2,3,4]
Output: 3
Explanation: The bowl subarrays are [5,1,2], [5,1,2,3], [5,1,2,3,4].

Input: nums = [1000000000,999999999,999999998]
Output: 0
Explanation: No subarray is a bowl.

---------------------------------------------------
*/

import java.util.*;

public class Solution {
    public long bowlSubarrays(int[] nums) {
        int n = nums.length;

        int lmax[] = new int[n];
        int rmax[] = new int[n];

        // Compute prefix max (left max)
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
            lmax[i] = max;
        }

        // Compute suffix max (right max)
        max = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            max = Math.max(max, nums[i]);
            rmax[i] = max;
        }

        // Count elements that can be inside a "bowl"
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (lmax[i] != nums[i] && rmax[i] != nums[i]) {
                count++;
            }
        }
        return count;
    }

    // Example usage
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = {2, 5, 3, 1, 4};
        int[] nums2 = {5, 1, 2, 3, 4};
        int[] nums3 = {1000000000, 999999999, 999999998};

        System.out.println(sol.bowlSubarrays(nums1)); // Output: 2
        System.out.println(sol.bowlSubarrays(nums2)); // Output: 3
        System.out.println(sol.bowlSubarrays(nums3)); // Output: 0
    }
}
