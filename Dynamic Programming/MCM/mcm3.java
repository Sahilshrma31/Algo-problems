/*
   💡 Problem: Burst Balloons (LeetCode 312)

   You are given n balloons, each balloon is painted with a number on it.
   You are asked to burst all the balloons. If you burst balloon i, you will get 
   nums[i-1] * nums[i] * nums[i+1] coins.
   After bursting, nums[i] disappears and the array shrinks.

   Goal: Maximize the number of coins you collect.

   -------------------------------------------------
   🔑 Intuition:
   - Bursting greedily first or last does not work.
   - Instead, consider the **last balloon burst** in a subarray.
   - If balloon `idx` is the last in [i..j], then the coins are:
         arr[i-1] * arr[idx] * arr[j+1]  +  solve(i, idx-1) + solve(idx+1, j)
   - We try all possible `idx` as last balloon and take the maximum.
   - This gives a classic **interval DP** structure.

   -------------------------------------------------
   1️⃣ Recursion (Brute Force)
   - Exponential time (O(2^n))
*/

class SolutionRecursion {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;
        for (int i = 1; i <= n; i++) arr[i] = nums[i - 1];
        return solve(1, n, arr);
    }

    private int solve(int i, int j, int[] arr) {
        if (i > j) return 0;
        int max = 0;
        for (int idx = i; idx <= j; idx++) {
            int coins = arr[i - 1] * arr[idx] * arr[j + 1]
                       + solve(i, idx - 1, arr)
                       + solve(idx + 1, j, arr);
            max = Math.max(max, coins);
        }
        return max;
    }
}

/*
   -------------------------------------------------
   2️⃣ Memoization (Top-down DP)
   - Store results in dp[i][j]
   - TC: O(n^3)
   - SC: O(n^2) + O(n) (recursion stack)
*/



class SolutionMemoization {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;
        for (int i = 1; i <= n; i++) arr[i] = nums[i - 1];

        int[][] dp = new int[n + 2][n + 2];
        for (int[] row : dp) Arrays.fill(row, -1);

        return solve(1, n, arr, dp);
    }

    private int solve(int i, int j, int[] arr, int[][] dp) {
        if (i > j) return 0;
        if (dp[i][j] != -1) return dp[i][j];

        int max = 0;
        for (int idx = i; idx <= j; idx++) {
            int coins = arr[i - 1] * arr[idx] * arr[j + 1]
                       + solve(i, idx - 1, arr, dp)
                       + solve(idx + 1, j, arr, dp);
            max = Math.max(max, coins);
        }
        return dp[i][j] = max;
    }
}

/*
   -------------------------------------------------
   3️⃣ Tabulation (Bottom-up DP)
   - Iterative filling of dp[i][j]
   - TC: O(n^3)
   - SC: O(n^2)
*/

class SolutionTabulation {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;
        for (int i = 1; i <= n; i++) arr[i] = nums[i - 1];

        int[][] dp = new int[n + 2][n + 2];

        // length of interval
        for (int len = 1; len <= n; len++) {
            for (int i = n - len + 1; i >= 1; i--) {
                int j = i + len - 1;
                if (j > n) continue;

                int max = 0;
                for (int idx = i; idx <= j; idx++) {
                    int coins = arr[i - 1] * arr[idx] * arr[j + 1]
                               + dp[i][idx - 1]
                               + dp[idx + 1][j];
                    max = Math.max(max, coins);
                }
                dp[i][j] = max;
            }
        }

        return dp[1][n];
    }
}

/*
   -------------------------------------------------
   ✅ Complexity Summary
   - Recursion:   O(2^n),   SC: O(n)
   - Memoization: O(n^3),   SC: O(n^2) + O(n)
   - Tabulation:  O(n^3),   SC: O(n^2)
*/
