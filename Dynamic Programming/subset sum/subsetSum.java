import java.util.*;

/*
Problem: Subset Sum Equal to K (Striver's Approach)

We are given an array of integers nums and an integer k. 
Return true if there exists a subset of nums whose sum equals k, otherwise return false.

Approach: Pick / Not Pick (Recursion -> Memoization -> Tabulation -> Space Optimized)
*/

class Solution {

    // -------------------- 1. Recursion --------------------
    public boolean subsetSumRecursive(int[] nums, int k) {
        return f(nums.length - 1, k, nums);
    }

    private boolean f(int idx, int target, int[] nums) {
        if (target == 0) return true;   // base: target achieved
        if (idx == 0) return nums[0] == target; // only one element left

        boolean notPick = f(idx - 1, target, nums);
        boolean pick = false;
        if (nums[idx] <= target) {
            pick = f(idx - 1, target - nums[idx], nums);
        }
        return pick || notPick;
    }

    // -------------------- 2. Memoization --------------------
    public boolean subsetSumMemo(int[] nums, int k) {
        int n = nums.length;
        Boolean[][] dp = new Boolean[n][k + 1];
        return fMemo(n - 1, k, nums, dp);
    }

    private boolean fMemo(int idx, int target, int[] nums, Boolean[][] dp) {
        if (target == 0) return true;
        if (idx == 0) return nums[0] == target;
        if (dp[idx][target] != null) return dp[idx][target];

        boolean notPick = fMemo(idx - 1, target, nums, dp);
        boolean pick = false;
        if (nums[idx] <= target) {
            pick = fMemo(idx - 1, target - nums[idx], nums, dp);
        }
        return dp[idx][target] = pick || notPick;
    }

    // -------------------- 3. Tabulation --------------------
    public boolean subsetSumTab(int[] nums, int k) {
        int n = nums.length;
        boolean[][] dp = new boolean[n][k + 1];

        // base case: target=0 is always true
        for (int i = 0; i < n; i++) dp[i][0] = true;

        // base case: only first element
        if (nums[0] <= k) dp[0][nums[0]] = true;

        for (int idx = 1; idx < n; idx++) {
            for (int target = 1; target <= k; target++) {
                boolean notPick = dp[idx - 1][target];
                boolean pick = false;
                if (nums[idx] <= target) {
                    pick = dp[idx - 1][target - nums[idx]];
                }
                dp[idx][target] = pick || notPick;
            }
        }

        return dp[n - 1][k];
    }

    // -------------------- 4. Space Optimized --------------------
    public boolean subsetSumSpace(int[] nums, int k) {
        int n = nums.length;
        boolean[] prev = new boolean[k + 1];

        prev[0] = true;
        if (nums[0] <= k) prev[nums[0]] = true;

        for (int idx = 1; idx < n; idx++) {
            boolean[] curr = new boolean[k + 1];
            curr[0] = true;
            for (int target = 1; target <= k; target++) {
                boolean notPick = prev[target];
                boolean pick = false;
                if (nums[idx] <= target) {
                    pick = prev[target - nums[idx]];
                }
                curr[target] = pick || notPick;
            }
            prev = curr;
        }

        return prev[k];
    }

    // -------------------- Driver Code --------------------
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums1 = {1, 2, 3};
        int k1 = 5;
        System.out.println("Recursive: " + sol.subsetSumRecursive(nums1, k1));
        System.out.println("Memoization: " + sol.subsetSumMemo(nums1, k1));
        System.out.println("Tabulation: " + sol.subsetSumTab(nums1, k1));
        System.out.println("Space Optimized: " + sol.subsetSumSpace(nums1, k1));

        int[] nums2 = {1, 2, 5};
        int k2 = 4;
        System.out.println("\nRecursive: " + sol.subsetSumRecursive(nums2, k2));
        System.out.println("Memoization: " + sol.subsetSumMemo(nums2, k2));
        System.out.println("Tabulation: " + sol.subsetSumTab(nums2, k2));
        System.out.println("Space Optimized: " + sol.subsetSumSpace(nums2, k2));
    }
}

/*
-------------------- Complexity --------------------
1. Recursion:
   - Time: O(2^n)
   - Space: O(n) recursion stack

2. Memoization:
   - Time: O(n * k)
   - Space: O(n * k) + O(n) recursion stack

3. Tabulation:
   - Time: O(n * k)
   - Space: O(n * k)

4. Space Optimized:
   - Time: O(n * k)
   - Space: O(k)
*/
