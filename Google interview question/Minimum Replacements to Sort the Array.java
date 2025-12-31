/*
====================================================
📌 Problem: Minimum Replacements to Sort the Array
LeetCode: 2366
Difficulty: Hard
====================================================

You are given an integer array nums.

In one operation, you can replace any element nums[i]
with any two positive integers that sum up to nums[i].

Your task is to return the minimum number of operations
required to make the array sorted in non-decreasing order.

----------------------------------------------------
🧠 Key Observation
----------------------------------------------------
• We must ensure nums[i] <= nums[i+1] for all i
• Splitting elements is allowed, but merging is NOT
• Therefore, we process the array from RIGHT → LEFT

----------------------------------------------------
💡 Intuition (Greedy from Right to Left)
----------------------------------------------------
If nums[i] <= nums[i+1], no action needed.

If nums[i] > nums[i+1]:
- We must split nums[i] into smaller parts
- Each part must be <= nums[i+1]
- The best strategy is to split nums[i] into the
  minimum number of parts such that:
      each part ≤ nums[i+1]

If we split nums[i] into `parts`:
- Number of operations added = parts - 1
- New value at index i becomes nums[i] / parts

----------------------------------------------------
🧩 Approach
----------------------------------------------------
1️⃣ Traverse array from right to left
2️⃣ If nums[i] <= nums[i+1], continue
3️⃣ Otherwise:
   - Calculate how many parts nums[i] must be split into
   - Add (parts - 1) to operations
   - Update nums[i] to the largest valid split value
4️⃣ Return total operations

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n)

----------------------------------------------------
🗂 Space Complexity
----------------------------------------------------
O(1) (in-place modification)

====================================================
*/

class Solution {

    public long minimumReplacement(int[] nums) {
        int n = nums.length;
        long operations = 0;

        // Traverse from right to left
        for (int i = n - 2; i >= 0; i--) {

            // If already valid, no action needed
            if (nums[i] <= nums[i + 1]) continue;

            // Calculate number of parts required
            long parts = nums[i] / nums[i + 1];

            // If not divisible, we need one extra part
            if (nums[i] % nums[i + 1] != 0) {
                parts++;
            }

            // Each split adds (parts - 1) operations
            operations += parts - 1;

            // Update nums[i] to the largest possible value
            nums[i] = nums[i] / (int) parts;
        }

        return operations;
    }
}
