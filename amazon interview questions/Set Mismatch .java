/*
====================================================
📌 Problem: Set Mismatch (LeetCode 645)
====================================================

You are given an integer array `nums` of size `n`
containing numbers from 1 to n.

❌ One number is duplicated
❌ One number is missing

Your task is to return:
[duplicate, missing]

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
We know:
• Numbers should be from 1 to n
• Each number should appear exactly once

Idea:
👉 Use the array itself as a visited marker.
👉 Mark visited indices by making values negative.

If we encounter a number whose index is already
negative → that number is duplicated.

After marking:
• The index which remains positive → missing number

----------------------------------------------------
🧩 Approach (Index Marking Technique)
----------------------------------------------------
1️⃣ Traverse the array:
   - Take absolute value of nums[i]
   - Convert it to index = value - 1
   - If nums[index] is already negative → duplicate found
   - Else mark nums[index] as negative

2️⃣ Traverse array again:
   - The index with positive value means missing number

----------------------------------------------------
⏱ Time & Space Complexity
----------------------------------------------------
Time Complexity:  O(n)
Space Complexity: O(1)  (No extra space used)

----------------------------------------------------
✅ Constraints Handled
----------------------------------------------------
• In-place solution
• No extra arrays
• Optimal time & space

====================================================
*/

import java.util.*;

class Solution {

    public int[] findErrorNums(int[] nums) {

        int n = nums.length;
        int duplicate = -1;
        int missing = -1;

        // Step 1: Find duplicate
        for (int i = 0; i < n; i++) {
            int val = Math.abs(nums[i]);   // actual number
            int idx = val - 1;             // mapped index

            // If already visited → duplicate
            if (nums[idx] < 0) {
                duplicate = val;
            } else {
                nums[idx] = -nums[idx];    // mark visited
            }
        }

        // Step 2: Find missing
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                missing = i + 1;
                break;
            }
        }

        return new int[]{duplicate, missing};
    }
}
