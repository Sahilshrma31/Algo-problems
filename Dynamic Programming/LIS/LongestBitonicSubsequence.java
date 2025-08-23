/*
📌 Problem: Longest Bitonic Subsequence

You are given an array of integers. 
A Bitonic subsequence is a subsequence that first strictly increases 
and then strictly decreases. 

Your task is to find the length of the longest bitonic subsequence in the array.

---

💡 Intuition & Approach (Striver's DP Approach):

1. Compute LIS (Longest Increasing Subsequence) ending at each index `i`.
   - dp1[i] = LIS ending at index i

2. Compute LDS (Longest Decreasing Subsequence) starting from each index `i`.
   - dp2[i] = LDS starting at index i

3. For each index `i`, treat it as the "peak" of a bitonic subsequence:
   - Length = dp1[i] + dp2[i] - 1

4. Valid peak condition:
   - dp1[i] > 1 (it has an increasing part)
   - dp2[i] > 1 (it has a decreasing part)

5. The maximum among all valid peaks is the answer.

---

⏱ Time Complexity: O(N^2)
   - For each element, we compute LIS and LDS with nested loops.

💾 Space Complexity: O(N)
   - Two arrays dp1 and dp2 of size N.

---

✅ Example:
Input:  arr = [1, 4, 3, 2]
Output: 4
Explanation: The longest bitonic subsequence is [1, 4, 3, 2]

Input:  arr = [5, 7, 9]
Output: 0
Explanation: No bitonic subsequence exists as it's strictly increasing only.

*/

import java.util.*;

public class LongestBitonicSubsequence {

    public static int longestBitonicSequence(int n, int[] nums) {
        // Step 1: LIS ending at each index
        int[] dp1 = new int[n];
        Arrays.fill(dp1, 1);

        for (int i = 0; i < n; i++) {
            for (int prev = 0; prev < i; prev++) {
                if (nums[i] > nums[prev] && dp1[i] < 1 + dp1[prev]) {
                    dp1[i] = 1 + dp1[prev];
                }
            }
        }

        // Step 2: LDS starting at each index
        int[] dp2 = new int[n];
        Arrays.fill(dp2, 1);

        for (int i = n - 1; i >= 0; i--) {
            for (int next = n - 1; next > i; next--) {
                if (nums[i] > nums[next] && dp2[i] < 1 + dp2[next]) {
                    dp2[i] = 1 + dp2[next];
                }
            }
        }

        // Step 3: Find maximum bitonic length
        int maxi = 0;
        for (int i = 0; i < n; i++) {
            if (dp1[i] > 1 && dp2[i] > 1) { // ensures valid peak
                maxi = Math.max(maxi, dp1[i] + dp2[i] - 1);
            }
        }

        return maxi;
    }

    // Driver Code
    public static void main(String[] args) {
        int[] nums1 = {1, 4, 3, 2};
        int[] nums2 = {5, 7, 9};

        System.out.println("Example 1: " + longestBitonicSequence(nums1.length, nums1)); // 4
        System.out.println("Example 2: " + longestBitonicSequence(nums2.length, nums2)); // 0
    }
}
