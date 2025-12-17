/*
====================================================
📌 Problem: Find Original Array From Doubled Array
====================================================

You are given an integer array `changed`, which is formed by taking
an original array, appending twice the value of every element, and
then shuffling the result.

Your task is to recover and return the original array.
If it is not possible, return an empty array.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
If `changed` is valid:
- Its length must be even.
- For every number `x`, there must be a corresponding `2*x`.

We:
1. Sort the array (so smaller numbers are processed first).
2. Use a frequency map to track counts.
3. For each number `x`, try to pair it with `2*x`.
4. Reduce frequencies as we form valid pairs.

If at any point pairing is not possible → return empty array.

----------------------------------------------------
🧩 Approach
----------------------------------------------------
1️⃣ If array length is odd → return empty.
2️⃣ Sort the array.
3️⃣ Build a frequency HashMap.
4️⃣ Iterate through sorted array:
   - Skip if current number count is 0.
   - Check if `2 * num` exists with count > 0.
   - Add `num` to result and decrease both counts.
5️⃣ Convert result list to array.

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
- Sorting: O(N log N)
- Traversal with HashMap: O(N)
➡️ Overall: O(N log N)

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
- HashMap: O(N)
- Result array: O(N)
➡️ Overall: O(N)

====================================================
*/

import java.util.*;

class Solution {

    public int[] findOriginalArray(int[] changed) {

        int n = changed.length;

        // If length is odd, cannot form original array
        if (n % 2 != 0) {
            return new int[0];
        }

        // Sort array
        Arrays.sort(changed);

        // Frequency map
        HashMap<Integer, Integer> freq = new HashMap<>();

        for (int num : changed) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        ArrayList<Integer> result = new ArrayList<>();

        for (int num : changed) {

            // Skip if already used
            if (freq.get(num) == 0) continue;

            int twice = num * 2;

            // If double not available, invalid case
            if (!freq.containsKey(twice) || freq.get(twice) == 0) {
                return new int[0];
            }

            // Add to original array
            result.add(num);

            // Decrease frequencies
            freq.put(num, freq.get(num) - 1);
            freq.put(twice, freq.get(twice) - 1);
        }

        // Convert list to array
        int[] ans = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }

        return ans;
    }
}
