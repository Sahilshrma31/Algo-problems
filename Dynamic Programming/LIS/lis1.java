/*
 * 📌 Problem: Longest Increasing Subsequence (LIS)
 *
 * Given an integer array nums, return the length of the 
 * longest strictly increasing subsequence.
 *
 * Example:
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: LIS is [2,3,7,101]
 *
 * ---------------------------------------------------------
 * Approaches Implemented:
 *
 * 1. Recursion (Brute Force)        
 * 2. DP + Memoization (Top-Down)    
 * 3. DP + Tabulation (Bottom-Up)    
 * 4. Space Optimized Tabulation     
 * 5. Binary Search (Patience Sort)  
 * ---------------------------------------------------------
 */

import java.util.*;

import LIS.lis1.BinarySearchApproach;
import LIS.lis1.SpaceOptimized;

public class lis1 {

    /* ---------------------------------------------------------
     * 1️⃣ Recursion (Brute Force)
     * Intuition:
     * At each index, we have two choices:
     * - Skip the current element
     * - Take it, if it is greater than the last chosen element
     * Explore both options recursively and return the max.
     *
     * TC: O(2^n)
     * SC: O(n) (recursion stack)
     * --------------------------------------------------------- */
    static class Recursion {
        public int lengthOfLIS(int[] nums) {
            return helper(0, -1, nums);
        }

        private int helper(int i, int prev, int[] nums) {
            if (i == nums.length) return 0;

            int notTake = helper(i + 1, prev, nums);
            int take = 0;
            if (prev == -1 || nums[i] > nums[prev]) {
                take = 1 + helper(i + 1, i, nums);
            }
            return Math.max(notTake, take);
        }
    }

    /* ---------------------------------------------------------
     * 2️⃣ Memoization (Top-Down DP)
     * Intuition:
     * Many subproblems repeat. Use dp[i][prev+1] to store LIS 
     * length starting at index i with last chosen element prev.
     *
     * TC: O(n^2)
     * SC: O(n^2) + O(n) recursion stack
     * --------------------------------------------------------- */
    static class Memoization {
        public int lengthOfLIS(int[] nums) {
            int n = nums.length;
            int[][] dp = new int[n][n + 1];
            for (int[] row : dp) Arrays.fill(row, -1);
            return helper(0, -1, nums, dp);
        }

        private int helper(int i, int prev, int[] nums, int[][] dp) {
            if (i == nums.length) return 0;
            if (dp[i][prev + 1] != -1) return dp[i][prev + 1];

            int notTake = helper(i + 1, prev, nums, dp);
            int take = 0;
            if (prev == -1 || nums[i] > nums[prev]) {
                take = 1 + helper(i + 1, i, nums, dp);
            }
            return dp[i][prev + 1] = Math.max(notTake, take);
        }
    }

    /* ---------------------------------------------------------
     * 3️⃣ Tabulation (Bottom-Up DP)
     * Intuition:
     * Fill the dp table iteratively from end to start.
     * dp[i][prev+1] = LIS length starting at i with last chosen prev.
     *
     * TC: O(n^2)
     * SC: O(n^2)
     * --------------------------------------------------------- */
    static class Tabulation {
        public int lengthOfLIS(int[] nums) {
            int n = nums.length;
            int[][] dp = new int[n + 1][n + 1];

            for (int i = n - 1; i >= 0; i--) {
                for (int prev = i - 1; prev >= -1; prev--) {
                    int notTake = dp[i + 1][prev + 1];
                    int take = 0;
                    if (prev == -1 || nums[i] > nums[prev]) {
                        take = 1 + dp[i + 1][i + 1];
                    }
                    dp[i][prev + 1] = Math.max(notTake, take);
                }
            }
            return dp[0][-1+1];
        }
    }

// notTake = helper(i+1, prev, ...) → dp[i+1][prev+1]

// In recursion, skipping nums[i] means: "move to index i+1, keep prev unchanged".

// In tabulation, that’s already computed at row i+1.

// So instead of calling, we just look up dp[i+1][prev+1].

// take = 1 + helper(i+1, i, ...) → 1 + dp[i+1][i+1]

// In recursion, taking nums[i] means: "move to index i+1, but now prev = i".

// In tabulation, that value is stored in dp[i+1][i+1] (shifted by +1).

// So recursion → array lookup.

    /* ---------------------------------------------------------
     * 4️⃣ Space Optimized Tabulation
     * Intuition:
     * Instead of storing full n*n table, only keep two rows:
     * current row and next row.
     *
     * TC: O(n^2)
     * SC: O(n)
     * --------------------------------------------------------- */
    static class SpaceOptimized {
        public int lengthOfLIS(int[] nums) {
            int n = nums.length;
            int[] next = new int[n + 1];
            int[] curr = new int[n + 1];

            for (int i = n - 1; i >= 0; i--) {
                for (int prev = i - 1; prev >= -1; prev--) {
                    int notTake = next[prev + 1];
                    int take = 0;
                    if (prev == -1 || nums[i] > nums[prev]) {
                        take = 1 + next[i + 1];
                    }
                    curr[prev + 1] = Math.max(notTake, take);
                }
                next = curr.clone();
            }
            return next[0];
        }
    }

    /* ---------------------------------------------------------
     * 5️⃣ Binary Search (Patience Sorting)
     * Intuition:
     * Maintain an array `tails` where tails[i] = smallest ending 
     * value of an increasing subsequence of length i+1.
     * For each number, replace it in tails using binary search.
     *
     * TC: O(n log n)
     * SC: O(n)
     * --------------------------------------------------------- */
    static class BinarySearchApproach {
        public int lengthOfLIS(int[] nums) {
            int[] tails = new int[nums.length];
            int size = 0;
            for (int x : nums) {
                int i = Arrays.binarySearch(tails, 0, size, x);
                if (i < 0) i = -i - 1; 
                tails[i] = x;
                if (i == size) size++;
            }
            return size;
        }
    }
//tarversal of array method
    class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int dp[]=new int[n];
        Arrays.fill(dp,1);
        int maxi=1;
       for(int i=1;i<n;i++){
        for(int prev=0;prev<i;prev++){
            if(nums[prev]<nums[i]){
                dp[i]=Math.max(dp[i],1+dp[prev]);
            }
        }
       }
       for(int num:dp){
        maxi=Math.max(num,maxi);
       }
       return maxi;
    }
}
    /* ---------------------------------------------------------
     * Test Driver
     * --------------------------------------------------------- */
    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};

        System.out.println("Recursion: " + new Recursion().lengthOfLIS(nums));
        System.out.println("Memoization: " + new Memoization().lengthOfLIS(nums));
        System.out.println("Tabulation: " + new Tabulation().lengthOfLIS(nums));
        System.out.println("Space Optimized: " + new SpaceOptimized().lengthOfLIS(nums));
        System.out.println("Binary Search: " + new BinarySearchApproach().lengthOfLIS(nums));
    }
}
