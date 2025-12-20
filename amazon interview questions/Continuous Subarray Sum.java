/*
====================================================
📌 Problem: Continuous Subarray Sum
====================================================

Given an integer array nums and an integer k, return true
if nums has a continuous subarray of size at least 2 whose
elements sum up to a multiple of k.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
If two prefix sums have the same remainder when divided by k,
their difference is divisible by k.

Let:
prefixSum[i] % k == prefixSum[j] % k  (i > j)

Then:
(prefixSum[i] - prefixSum[j]) % k == 0

So the subarray (j+1 to i) has sum divisible by k.

We store the FIRST index where each remainder appears.

----------------------------------------------------
🧩 Approach (HashMap + Prefix Sum)
----------------------------------------------------
1️⃣ Maintain running prefix sum.
2️⃣ Compute remainder = prefixSum % k.
3️⃣ If the same remainder appeared before:
   - Check subarray length ≥ 2.
4️⃣ Store remainder with its first index only.

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(N)

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(min(N, K))  (number of distinct remainders)

====================================================
*/

import java.util.*;

class Solution {

    public boolean checkSubarraySum(int[] nums, int k) {

        // Map to store remainder -> first index
        Map<Integer, Integer> map = new HashMap<>();

        // Base case: remainder 0 at index -1
        map.put(0, -1);

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            int remainder = sum % k;

            if (map.containsKey(remainder)) {
                int prevIndex = map.get(remainder);

                // Subarray length must be at least 2
                if (i - prevIndex >= 2) {
                    return true;
                }
            } else {
                // Store only first occurrence
                map.put(remainder, i);
            }
        }

        return false;
    }
}
