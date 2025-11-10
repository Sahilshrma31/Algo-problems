/*
    🧩 Problem: LeetCode 41 - First Missing Positive
    🔗 Link: https://leetcode.com/problems/first-missing-positive/
    ⚙️ Difficulty: Hard
    🧠 Topic: Array, Pigeonhole Principle, Index Marking

    ----------------------------------------------------------------------------
    📜 Problem Summary:
    ----------------------------------------------------------------------------
    Given an unsorted integer array nums, find the smallest missing positive 
    integer. You must implement an algorithm that runs in O(n) time and uses 
    constant extra space (O(1)).

    Example:
        Input: [3, 4, -1, 1]
        Output: 2

        Input: [1, 2, 0]
        Output: 3

    ----------------------------------------------------------------------------
    💡 Intuition (Simple Hinglish Explanation):
    ----------------------------------------------------------------------------
    We only care about numbers in range [1, n], where n = array length.

    - Negative numbers, zeros, or values > n can be ignored, 
      because the smallest missing positive will always be within [1, n + 1].

    So, we use the array itself to "mark" which numbers exist.

    🔸 Step 1: Replace all invalid numbers (≤0 or >n) with n+1 
              (a number we can safely ignore later).

    🔸 Step 2: For each valid number `val` in [1, n], 
               mark its presence by making nums[val - 1] negative.

    🔸 Step 3: The first index with a positive number 
               → means that index + 1 is missing.

    If all indices are marked (negative), it means all numbers 1..n are present, 
    so the answer is n + 1.

    ----------------------------------------------------------------------------
    🧮 Example Dry Run:
    ----------------------------------------------------------------------------
    nums = [3, 4, -1, 1]
    n = 4

    Step 1 → Replace invalids:
        [3, 4, 5, 1]

    Step 2 → Mark presence:
        val=3 → mark index 2 → [3, 4, -5, 1]
        val=4 → mark index 3 → [3, 4, -5, -1]
        val=5 → ignore (>n)
        val=1 → mark index 0 → [-3, 4, -5, -1]

    Step 3 → Scan:
        index 1 → positive → missing = 1 + 1 = 2 ✅

    Output = 2

    ----------------------------------------------------------------------------
    ⏱️ Time Complexity:
        O(n) — each element visited constant times (marking and scanning)

    💾 Space Complexity:
        O(1) — only constant extra variables used (in-place marking)

    ----------------------------------------------------------------------------
    ✅ Approach Summary:
        1️⃣ Replace invalid numbers (≤0 or >n) with n+1.
        2️⃣ For each number in range [1, n], mark its index position negative.
        3️⃣ Find the first index where nums[i] > 0 → return i+1.
        4️⃣ If none found → return n+1.
*/

class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // Step 1: Replace invalid numbers with n+1
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] >= n + 1) {
                nums[i] = n + 1;
            }
        }

        // Step 2: Mark presence of valid numbers (1..n)
        for (int i = 0; i < n; i++) {
            int el = Math.abs(nums[i]);
            if (el == n + 1) continue;
            int idx = el - 1;
            if (nums[idx] > 0) {
                nums[idx] = -nums[idx];
            }
        }

        // Step 3: Find the first index that isn't marked
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }

        // Step 4: If all 1..n exist, missing = n+1
        return n + 1;
    }
}
