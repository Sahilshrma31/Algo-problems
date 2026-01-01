/*
====================================================
📌 Problem: Max Subarray Length with Frequency ≤ K
(LeetCode / Sliding Window)
====================================================

Given an integer array nums and an integer k,
return the maximum length of a subarray such that
the frequency of each element in the subarray is
less than or equal to k.

----------------------------------------------------
🧠 Approach:
Sliding Window + HashMap
----------------------------------------------------
- Use two pointers (i, j)
- Maintain frequency of elements in current window
- Shrink window when any element frequency > k
- Track maximum window size

----------------------------------------------------
⏱ Time Complexity:
O(N)

----------------------------------------------------
📦 Space Complexity:
O(N) (HashMap)
====================================================
*/

import java.util.*;

class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int i = 0, j = 0;
        int maxLen = 0;
        int n = nums.length;

        HashMap<Integer, Integer> freq = new HashMap<>();

        while (j < n) {
            freq.put(nums[j], freq.getOrDefault(nums[j], 0) + 1);

            while (freq.get(nums[j]) > k) {
                freq.put(nums[i], freq.get(nums[i]) - 1);
                i++;
            }

            maxLen = Math.max(maxLen, j - i + 1);
            j++;
        }

        return maxLen;
    }
}
