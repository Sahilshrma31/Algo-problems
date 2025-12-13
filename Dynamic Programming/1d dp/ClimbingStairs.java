/**
 * LeetCode 70. Climbing Stairs
 *
 * Problem Summary:
 * ----------------
 * You are climbing a staircase. It takes 'n' steps to reach the top.
 * Each time you can climb either 1 step or 2 steps.
 * Return the number of distinct ways to reach the top.
 *
 * Example:
 * n = 2 -> (1+1), (2)                   => 2 ways
 * n = 3 -> (1+1+1), (1+2), (2+1)        => 3 ways
 *
 * Key Observation:
 * ----------------
 * To reach step 'n', you could come from:
 *  - step (n - 1) by taking 1 step, or
 *  - step (n - 2) by taking 2 steps.
 *
 * So:
 *   ways(n) = ways(n - 1) + ways(n - 2)
 *
 * This is exactly like the Fibonacci sequence.
 *
 * Approaches:
 * -----------
 * 1) Pure Recursion (Exponential)
 *    - Definition: ways(n) = ways(n - 1) + ways(n - 2)
 *    - Base cases: ways(0) = 1, ways(1) = 1
 *    Time Complexity:  O(2^n)
 *    Space Complexity: O(n) recursion stack (height of recursion tree)
 *
 * 2) Recursion + Memoization (Top-Down DP)
 *    - Store results of subproblems in a dp[] array.
 *    - If dp[n] already computed, reuse it.
 *    Time Complexity:  O(n)
 *    Space Complexity: O(n) for dp[] + O(n) recursion stack
 *
 * 3) Tabulation (Bottom-Up DP)
 *    - Iteratively fill dp[0..n].
 *    - dp[0] = 1, dp[1] = 1
 *    - For i from 2 to n: dp[i] = dp[i - 1] + dp[i - 2]
 *    Time Complexity:  O(n)
 *    Space Complexity: O(n)
 *
 * 4) Space Optimized (Iterative Fibonacci)
 *    - We only need last two values, not full dp[].
 *    - Use two variables: prev2 (dp[i-2]), prev (dp[i-1]).
 *    Time Complexity:  O(n)
 *    Space Complexity: O(1)
 *
 * Note:
 * -----
 * All approaches return the same answer, but efficiency improves from
 * pure recursion -> memoization -> tabulation -> space optimized.
 */

public class ClimbingStairs {

    /* ------------------------------------------------------------------
     * 1) Pure Recursion
     * ------------------------------------------------------------------
     * Direct translation of the recurrence:
     * ways(n) = ways(n - 1) + ways(n - 2)
     * Base:
     *   n == 0 -> 1 way (you are already there, do nothing)
     *   n == 1 -> 1 way (take one step)
     */
    public int climbStairsRecursion(int n) {
        // Base cases
        if (n == 0 || n == 1) {
            return 1;
        }

        // Recursive relation
        return climbStairsRecursion(n - 1) + climbStairsRecursion(n - 2);
    }


    /* ------------------------------------------------------------------
     * 2) Recursion + Memoization (Top-Down DP)
     * ------------------------------------------------------------------
     * We add a dp[] array to remember computed values.
     * dp[i] = number of ways to reach step i.
     *
     * Steps:
     *  - Initialize dp[] with -1 (meaning "not computed yet").
     *  - On each call, if dp[n] != -1, return dp[n].
     *  - Otherwise compute normally and store in dp[n].
     */
    public int climbStairsMemo(int n) {
        int[] dp = new int[n + 1];

        // Initialize dp with -1 to mark uncomputed states
        for (int i = 0; i <= n; i++) {
            dp[i] = -1;
        }

        return solveMemo(n, dp);
    }

    // Helper function for memoization
    private int solveMemo(int n, int[] dp) {
        // Base cases
        if (n == 0 || n == 1) {
            return 1;
        }

        // If already computed, directly return the stored value
        if (dp[n] != -1) {
            return dp[n];
        }

        // Compute and store the result
        dp[n] = solveMemo(n - 1, dp) + solveMemo(n - 2, dp);
        return dp[n];
    }


    /* ------------------------------------------------------------------
     * 3) Tabulation (Bottom-Up DP)
     * ------------------------------------------------------------------
     * Build the solution iteratively.
     *
     * dp[0] = 1  // 1 way to stay at step 0
     * dp[1] = 1  // 1 way to reach step 1 (single step)
     *
     * For i from 2 to n:
     *    dp[i] = dp[i - 1] + dp[i - 2]
     */
    public int climbStairsTabulation(int n) {
        // Edge case: if n == 0 or n == 1, answer is 1 directly.
        if (n == 0 || n == 1) {
            return 1;
        }

        int[] dp = new int[n + 1];

        dp[0] = 1;           // base case
        if (n >= 1) {
            dp[1] = 1;       // base case, only if n >= 1 to avoid out-of-bounds when n == 0
        }

        // Fill dp array from 2 to n
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }


    /* ------------------------------------------------------------------
     * 4) Space Optimized (Fibonacci Style)
     * ------------------------------------------------------------------
     * Instead of using an array, we just keep track of:
     *  - prev2 = dp[i - 2]
     *  - prev  = dp[i - 1]
     *
     * For each i >= 2:
     *    curr = prev + prev2
     *    then shift:
     *       prev2 = prev
     *       prev  = curr
     */
    public int climbStairsSpaceOptimized(int n) {
        // Edge case: same as previous
        if (n == 0 || n == 1) {
            return 1;
        }

        int prev2 = 1; // dp[0]
        int prev = 1;  // dp[1]

        // i starts from 2 because dp[0] and dp[1] are already known
        for (int i = 2; i <= n; i++) {
            int curr = prev + prev2; // dp[i] = dp[i-1] + dp[i-2]
            prev2 = prev;            // shift prev2 to previous prev
            prev = curr;             // prev becomes current
        }

        // prev holds dp[n]
        return prev;
    }


    // Optional main for local testing (you can remove this if pushing only solution class)
    public static void main(String[] args) {
        ClimbingStairs solver = new ClimbingStairs();

        int n = 5;

        System.out.println("n = " + n);
        System.out.println("1) Recursion: " + solver.climbStairsRecursion(n));
        System.out.println("2) Memoization: " + solver.climbStairsMemo(n));
        System.out.println("3) Tabulation: " + solver.climbStairsTabulation(n));
        System.out.println("4) Space Optimized: " + solver.climbStairsSpaceOptimized(n));
    }
}
