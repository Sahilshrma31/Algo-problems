/*
====================================================
📌 Problem: Count the Number of Good Subarrays
(LeetCode 2537)
====================================================

You are given an integer array nums and an integer k.

A subarray is called GOOD if it contains at least k
pairs of equal elements.

A pair (i, j) is valid if:
- i < j
- nums[i] == nums[j]

Return the total number of GOOD subarrays.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
We use a Sliding Window approach with a HashMap.

Key idea:
- Maintain a window [i, j]
- Track frequency of elements
- Track number of equal pairs in the window

When adding nums[j]:
- New pairs formed = current frequency of nums[j]

If pairs >= k:
- All subarrays ending at j and starting from i..j are valid
- Count += (n - j)

Then shrink the window from the left.

----------------------------------------------------
🧩 Approach (Sliding Window + HashMap)
----------------------------------------------------
1️⃣ Use two pointers i and j
2️⃣ Maintain:
   - HashMap for frequency
   - pairs = total equal pairs in window
3️⃣ Expand j → add pairs
4️⃣ Shrink i while pairs >= k
5️⃣ Accumulate answer

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n)
- Each element enters and leaves the window once

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(n)
- HashMap in worst case

====================================================
*/

import java.util.*;

class Solution {

    public long countGood(int[] nums, int k) {

        int n = nums.length;

        int i = 0;
        int j = 0;

        // frequency map
        HashMap<Integer, Integer> map = new HashMap<>();

        long pairs = 0;   // number of equal pairs in window
        long result = 0;  // answer

        while (j < n) {

            // Add new element nums[j]
            pairs += map.getOrDefault(nums[j], 0);
            map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);

            // Shrink window while condition satisfied
            while (pairs >= k) {
                result += (n - j); // all subarrays ending at j are valid

                map.put(nums[i], map.get(nums[i]) - 1);
                pairs -= map.get(nums[i]);
                i++;
            }

            j++;
        }

        return result;
    }
}
