/*
====================================================
📌 Problem: Minimum Length Subarray With Sum ≥ K
====================================================

You are given an integer array nums and an integer k.

Define the sum of a subarray as the sum of its
DISTINCT elements only.

Return the minimum length of a subarray such that
the sum of its distinct elements is at least k.

If no such subarray exists, return -1.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
- Use a Sliding Window technique
- Maintain:
  • A HashMap to track frequency of elements
  • A running sum of DISTINCT elements only
- Expand the window to the right
- Shrink from the left when the condition is satisfied

----------------------------------------------------
🛠 Approach (Sliding Window + HashMap)
----------------------------------------------------
1️⃣ Use two pointers (i, r)
2️⃣ Add nums[r] into the map
   - If it appears first time, add to sum
3️⃣ While sum ≥ k:
   - Update minimum length
   - Remove nums[i] from window
   - If its frequency becomes zero, subtract from sum
4️⃣ Continue until end

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n)
- Each element is added and removed at most once

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(n)
- HashMap in worst case

====================================================
*/

import java.util.*;

class Solution {

    public int minLength(int[] nums, int k) {

        int n = nums.length;
        int left = 0;
        int right = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        long sum = 0;

        int minLen = (int) 1e9;

        while (right < n) {

            // Add nums[right]
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

            // Add to sum only if first occurrence
            if (map.get(nums[right]) == 1) {
                sum += nums[right];
            }

            // Shrink window while condition satisfied
            while (sum >= k) {
                minLen = Math.min(minLen, right - left + 1);

                map.put(nums[left], map.get(nums[left]) - 1);

                if (map.get(nums[left]) == 0) {
                    sum -= nums[left];
                    map.remove(nums[left]);
                }
                left++;
            }

            right++;
        }

        return minLen == (int) 1e9 ? -1 : minLen;
    }
}
