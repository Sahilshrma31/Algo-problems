/*
====================================================
📌 Problem: Subarrays Divisible by K
====================================================

Given an integer array nums and an integer k,
return the total number of non-empty subarrays
whose sum is divisible by k.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
If two prefix sums have the same remainder when
divided by k, then the subarray between them
has a sum divisible by k.

(prefixSum[j] - prefixSum[i]) % k == 0
⇒ prefixSum[j] % k == prefixSum[i] % k

----------------------------------------------------
🧩 Approach (Prefix Sum + HashMap)
----------------------------------------------------
1. Maintain a running prefix sum.
2. Compute remainder = prefixSum % k.
3. If the same remainder has appeared before,
   it contributes that many subarrays.
4. Store frequency of remainders in a HashMap.
5. Handle negative remainders by adding k.

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n)

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(k) (or O(n) in worst case)

====================================================
*/

import java.util.*;

class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int n = nums.length;

        Map<Integer, Integer> mp = new HashMap<>();
        int sum = 0;

        // remainder 0 occurs once initially
        mp.put(0, 1);

        int result = 0;

        for (int i = 0; i < n; i++) {
            sum += nums[i];

            int rem = sum % k;

            // handle negative remainder
            if (rem < 0) {
                rem += k;
            }

            if (mp.containsKey(rem)) {
                result += mp.get(rem);
            }

            mp.put(rem, mp.getOrDefault(rem, 0) + 1);
        }

        return result;
    }
}
