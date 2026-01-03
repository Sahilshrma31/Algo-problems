/*
====================================================
📌 Problem: Count Good Subarrays
(LeetCode 2537)
====================================================

You are given an integer array nums and an integer k.

A subarray is called GOOD if the number of pairs
(i, j) such that:
- i < j
- nums[i] == nums[j]

is at least k.

Return the total number of GOOD subarrays.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
We use a Sliding Window + Frequency Map.

Key idea:
- While expanding the window (right pointer),
  count how many equal pairs are formed.
- Once pairs >= k, every extension of this window
  to the right will also remain valid.

So we can directly add (n - j) to the answer.

----------------------------------------------------
🛠 Approach
----------------------------------------------------
1️⃣ Use two pointers i (left) and j (right)
2️⃣ Maintain a HashMap to store frequency
3️⃣ Track number of equal pairs formed
4️⃣ Shrink window when pairs >= k
5️⃣ Count valid subarrays efficiently

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n) — Each element enters and leaves the window once

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(n) — HashMap for frequency tracking

====================================================
*/

import java.util.*;

class Solution {

    public long countGood(int[] nums, int k) {

        int n = nums.length;
        int i = 0, j = 0;

        // Frequency map
        HashMap<Integer, Integer> map = new HashMap<>();

        long pairs = 0;
        long result = 0;

        while (j < n) {

            // Add pairs formed by nums[j]
            pairs += map.getOrDefault(nums[j], 0);
            map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);

            // Shrink window while condition is satisfied
            while (pairs >= k) {
                result += (n - j);

                map.put(nums[i], map.get(nums[i]) - 1);
                pairs -= map.get(nums[i]);
                i++;
            }

            j++;
        }

        return result;
    }
}
