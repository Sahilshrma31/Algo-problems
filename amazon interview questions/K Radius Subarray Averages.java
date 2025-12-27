/*
====================================================
📌 Problem: K Radius Subarray Averages (LeetCode 2090)
====================================================

You are given an integer array nums and an integer k.

For each index i, calculate the average of the subarray
centered at i with radius k, i.e. from index (i - k) to (i + k).

If there are fewer than k elements before or after index i,
the average is -1.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
Brute force would be too slow because for each index,
we would sum up (2k + 1) elements.

To optimize this:
➡️ Use Prefix Sum to get subarray sums in O(1) time.

----------------------------------------------------
🧩 Approach (Prefix Sum)
----------------------------------------------------
1️⃣ If k == 0, every element is its own average → return nums.
2️⃣ If array length < (2k + 1), no valid subarray → return all -1.
3️⃣ Build prefix sum array.
4️⃣ For each valid center i:
   - Sum = prefix[right] - prefix[left - 1]
   - Average = sum / (2k + 1)
5️⃣ Store result, others remain -1.

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n)

----------------------------------------------------
🗂 Space Complexity
----------------------------------------------------
O(n) (prefix sum array)

====================================================
*/

import java.util.*;

class Solution {

    public int[] getAverages(int[] nums, int k) {
        int n = nums.length;

        // Result array initialized with -1
        int[] result = new int[n];
        Arrays.fill(result, -1);

        // If k == 0, each element is its own average
        if (k == 0) return nums;

        // If not enough elements to form window
        if (n < (2 * k + 1)) return result;

        // Build prefix sum
        long[] prefixSum = new long[n];
        prefixSum[0] = nums[0];

        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        // Calculate averages
        for (int i = k; i < n - k; i++) {
            int left = i - k;
            int right = i + k;

            long sum = prefixSum[right];
            if (left > 0) {
                sum -= prefixSum[left - 1];
            }

            result[i] = (int) (sum / (2 * k + 1));
        }

        return result;
    }
}
