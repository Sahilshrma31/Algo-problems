/*
====================================================
📌 Problem: Minimize Maximum Pair Sum in Array
(LeetCode 1877)
====================================================

You are given an integer array nums of even length.
Pair up the elements such that each element is used
exactly once.

Return the minimum possible value of the maximum
pair sum.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
To minimize the maximum pair sum:
- Pair the smallest number with the largest number.
- Pair the second smallest with the second largest, and so on.

This balances large numbers with small ones, ensuring
no pair sum becomes unnecessarily large.

----------------------------------------------------
🧩 Approach
----------------------------------------------------
1️⃣ Sort the array.
2️⃣ Use two pointers:
   - i → start (smallest)
   - j → end (largest)
3️⃣ Compute pair sums and track the maximum.
4️⃣ Return the minimum possible maximum pair sum.

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
Sorting: O(n log n)
Two-pointer scan: O(n)

Overall: O(n log n)

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(1) extra space (ignoring sorting space)

====================================================
*/

import java.util.*;

class Solution {

    public int minPairSum(int[] nums) {
        Arrays.sort(nums);

        int i = 0;
        int j = nums.length - 1;
        int maxPairSum = 0;

        while (i < j) {
            int sum = nums[i] + nums[j];
            maxPairSum = Math.max(maxPairSum, sum);
            i++;
            j--;
        }

        return maxPairSum;
    }
}
