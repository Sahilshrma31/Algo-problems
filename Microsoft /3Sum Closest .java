/*
====================================================
📌 Problem: 3Sum Closest 
====================================================

Given an integer array nums and an integer target,
return the sum of three integers in nums such that
the sum is closest to target.

Assume that each input would have exactly one solution.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
- Sort the array.
- Fix one element and use two pointers for the remaining two.
- Compare the current sum with target and update the closest sum.
- Move pointers based on whether sum is smaller or larger than target.

----------------------------------------------------
🧩 Approach
----------------------------------------------------
1. Sort the array.
2. Loop over index k from 0 to n-3.
3. Use two pointers:
   - l = k + 1
   - r = n - 1
4. Calculate sum = nums[k] + nums[l] + nums[r]
5. Update closest sum if current difference is smaller.
6. Adjust pointers:
   - sum < target → l++
   - sum > target → r--
   - sum == target → return sum immediately

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
- Sorting: O(n log n)
- Two-pointer traversal: O(n^2)
➡ Overall: O(n^2)

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
- O(1) extra space (ignoring sorting space)

====================================================
*/

import java.util.*;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        int minDiff = Integer.MAX_VALUE;
        int closestSum = 0;

        Arrays.sort(nums);

        for (int k = 0; k <= n - 3; k++) {
            int l = k + 1;
            int r = n - 1;

            while (l < r) {
                int sum = nums[k] + nums[l] + nums[r];
                int diff = Math.abs(sum - target);

                if (diff < minDiff) {
                    minDiff = diff;
                    closestSum = sum;
                }

                if (sum < target) {
                    l++;
                } else if (sum > target) {
                    r--;
                } else {
                    // Exact match found
                    return sum;
                }
            }
        }
        return closestSum;
    }
}
