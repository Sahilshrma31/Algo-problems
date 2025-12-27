/*
====================================================
📌 Problem: Minimum Size Subarray Sum
====================================================

Given an array of positive integers nums and a positive
integer target, return the minimal length of a contiguous
subarray of which the sum is greater than or equal to target.

If there is no such subarray, return 0.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
This is a classic **Sliding Window** problem.

Since all numbers are positive:
- Expanding the window (moving right pointer) increases sum
- Shrinking the window (moving left pointer) decreases sum

We try to maintain the smallest window whose sum ≥ target.

----------------------------------------------------
🧩 Approach (Sliding Window)
----------------------------------------------------
1. Initialize two pointers `i` (left) and `j` (right)
2. Expand `j` to increase the sum
3. When sum ≥ target:
   - Update minimum length
   - Shrink from left (`i`) to try minimizing window
4. Continue until `j` reaches end

----------------------------------------------------
⏱ Time & Space Complexity
----------------------------------------------------
Time Complexity: O(n)
- Each element is visited at most twice (once by i, once by j)

Space Complexity: O(1)
- Constant extra space

====================================================
*/

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int sum = 0;
        int i = 0;
        int minLen = n + 1;

        for (int j = 0; j < n; j++) {
            sum += nums[j];

            // Shrink window while condition satisfied
            while (sum >= target) {
                minLen = Math.min(minLen, j - i + 1);
                sum -= nums[i];
                i++;
            }
        }

        return (minLen == n + 1) ? 0 : minLen;
    }
}
