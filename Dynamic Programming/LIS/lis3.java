// File: LargestDivisibleSubset.java

/*
🔹 Problem: Largest Divisible Subset
Given a set of distinct positive integers `nums`, find the largest subset such that for every pair (Si, Sj) in the subset, either Si % Sj == 0 or Sj % Si == 0.

🔹 Example:
Input:  nums = [1, 2, 4, 8]
Output: [1, 2, 4, 8]

Input:  nums = [1, 2, 3]
Output: [1, 2] or [1, 3]

🔹 Approach:
1. Sort the array. Sorting ensures that if nums[i] % nums[j] == 0, then nums[i] can extend the subset ending at nums[j].
2. Use DP and hash arrays:
   - dp[i] = length of largest divisible subset ending at i
   - hash[i] = previous index in subset
3. For each i, iterate all prev < i:
   - If nums[i] % nums[prev] == 0 and dp[i] < dp[prev] + 1:
       - dp[i] = dp[prev] + 1
       - hash[i] = prev
4. Keep track of index with maximum dp[i] (lastIdx)
5. Reconstruct subset by following hash[] from lastIdx to start

🔹 Time Complexity: O(n^2)
🔹 Space Complexity: O(n)
*/

import java.util.*;

class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        if (n == 0) return res;
        
        Arrays.sort(nums); // sort for divisible subset

        int dp[] = new int[n];
        int hash[] = new int[n];
        int maxi = 1;
        int lastIdx = 0;
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            hash[i] = i;
        }
        
        for (int i = 0; i < n; i++) {
            for (int prev = 0; prev < i; prev++) {
                if (nums[i] % nums[prev] == 0 && dp[i] < 1 + dp[prev]) {
                    dp[i] = 1 + dp[prev];
                    hash[i] = prev;
                }
            }
            if (dp[i] > maxi) {
                maxi = dp[i];
                lastIdx = i;
            }
        }

        // reconstruct subset
        res.add(nums[lastIdx]);
        while (hash[lastIdx] != lastIdx) {
            lastIdx = hash[lastIdx];
            res.add(nums[lastIdx]);
        }
        Collections.reverse(res);
        return res;
    }

    // Driver method to test
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums1 = {1, 2, 4, 8};
        System.out.println("Largest Divisible Subset: " + sol.largestDivisibleSubset(nums1)); 
        // Output: [1, 2, 4, 8]

        int[] nums2 = {1, 2, 3};
        System.out.println("Largest Divisible Subset: " + sol.largestDivisibleSubset(nums2)); 
        // Output: [1, 2] or [1, 3]
    }
}
