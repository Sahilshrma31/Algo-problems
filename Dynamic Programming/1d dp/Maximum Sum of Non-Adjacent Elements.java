/**
 * ============================================================
 * Problem: Maximum Sum of Non-Adjacent Elements
 * ============================================================
 *
 * Problem Summary:
 * ----------------
 * Given an array of integers arr[], find the maximum sum such that
 * no two chosen elements are adjacent in the array.
 *
 * Example:
 * --------
 * Input  : [3, 2, 7, 10]
 * Output : 13  (3 + 10)
 *
 * Input  : [5, 5, 10, 100, 10, 5]
 * Output : 110 (5 + 100 + 5)
 *
 * ------------------------------------------------------------
 * Intuition:
 * ----------
 * For every index i, we have two choices:
 * 1. Pick arr[i]  → then we cannot pick arr[i-1]
 * 2. Not pick arr[i] → then result is same as previous index
 *
 * So,
 * dp[i] = max(
 *      arr[i] + dp[i-2],   // pick
 *      dp[i-1]             // not pick
 * )
 *
 * ------------------------------------------------------------
 * Approaches:
 * -----------
 * 1. Recursive (Brute Force)
 * 2. Memoization (Top-Down DP)
 * 3. Tabulation (Bottom-Up DP)
 * 4. Space Optimized DP
 *
 * ============================================================
 */

class Solution {

    /* =========================================================
     * 1️⃣ Recursive Approach (Brute Force)
     * =========================================================
     * Time Complexity: O(2^N)
     * Space Complexity: O(N)  (recursion stack)
     */
    public int maxSumRecursive(int[] arr, int idx) {
        if (idx < 0) return 0;
        if (idx == 0) return arr[0];

        int pick = arr[idx] + maxSumRecursive(arr, idx - 2);
        int notPick = maxSumRecursive(arr, idx - 1);

        return Math.max(pick, notPick);
    }

    /* =========================================================
     * 2️⃣ Memoization (Top-Down DP)
     * =========================================================
     * Time Complexity: O(N)
     * Space Complexity: O(N) (dp array + recursion stack)
     */
    public int maxSumMemo(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) dp[i] = -1;
        return helper(arr, n - 1, dp);
    }

    private int helper(int[] arr, int idx, int[] dp) {
        if (idx < 0) return 0;
        if (idx == 0) return arr[0];

        if (dp[idx] != -1) return dp[idx];

        int pick = arr[idx] + helper(arr, idx - 2, dp);
        int notPick = helper(arr, idx - 1, dp);

        return dp[idx] = Math.max(pick, notPick);
    }

    /* =========================================================
     * 3️⃣ Tabulation (Bottom-Up DP)
     * =========================================================
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public int findMaxSum(int[] arr) {
        int n = arr.length;
        if (n == 0) return 0;

        int[] dp = new int[n];

        dp[0] = arr[0]; // base case

        for (int i = 1; i < n; i++) {
            int pick = arr[i];
            if (i > 1) {
                pick += dp[i - 2];
            }

            int notPick = dp[i - 1];

            dp[i] = Math.max(pick, notPick);
        }

        return dp[n - 1];
    }

    /* =========================================================
     * 4️⃣ Space Optimized DP
     * =========================================================
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public int maxSumSpaceOptimized(int[] arr) {
        int n = arr.length;
        if (n == 0) return 0;

        int prev2 = 0;        // dp[i-2]
        int prev1 = arr[0];  // dp[i-1]

        for (int i = 1; i < n; i++) {
            int pick = arr[i] + prev2;
            int notPick = prev1;

            int curr = Math.max(pick, notPick);

            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }
}
