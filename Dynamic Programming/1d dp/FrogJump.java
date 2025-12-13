import java.util.Arrays;

/**
 * Frog Jump (DP)
 *
 * Problem Summary:
 * ----------------
 * Given an array height[] where height[i] represents the height of the i-th stair.
 * A frog starts from stair 0 and wants to reach stair (n-1).
 *
 * From stair i, the frog can jump to:
 *   - stair i + 1
 *   - stair i + 2
 *
 * Cost of a jump = |height[current] - height[previous]|
 *
 * Task:
 * -----
 * Find the minimum total cost to reach the last stair.
 *
 * ------------------------------------------------------------------
 *
 * Intuition:
 * ----------
 * To reach stair i, the frog must have come from:
 *   - stair (i - 1), OR
 *   - stair (i - 2)
 *
 * So, the minimum cost to reach stair i is:
 *
 *   dp[i] = min(
 *              dp[i-1] + |height[i] - height[i-1]|,
 *              dp[i-2] + |height[i] - height[i-2]|
 *           )
 *
 * Base Case:
 * ----------
 * dp[0] = 0
 * (No cost to stand on the first stair)
 *
 * This is a classic Dynamic Programming problem with overlapping subproblems.
 */

public class FrogJump {

    /* ================================================================
     * 1️⃣ Pure Recursion (Brute Force)
     * ================================================================
     *
     * Definition:
     * solve(i) = minimum cost to reach stair i
     *
     * Recurrence:
     * solve(i) = min(
     *               solve(i-1) + |h[i] - h[i-1]|,
     *               solve(i-2) + |h[i] - h[i-2]|
     *             )
     *
     * Base:
     * solve(0) = 0
     *
     * Time Complexity: O(2^n)
     * Space Complexity: O(n) (recursion stack)
     */
    public int frogJumpRecursion(int[] height) {
        return solveRec(height, height.length - 1);
    }

    private int solveRec(int[] height, int idx) {
        if (idx == 0) return 0;

        int left = solveRec(height, idx - 1)
                   + Math.abs(height[idx] - height[idx - 1]);

        int right = Integer.MAX_VALUE;
        if (idx > 1) {
            right = solveRec(height, idx - 2)
                    + Math.abs(height[idx] - height[idx - 2]);
        }

        return Math.min(left, right);
    }

    /* ================================================================
     * 2️⃣ Memoization (Top-Down DP)
     * ================================================================
     *
     * Idea:
     * -----
     * Store the result of solve(i) in dp[i]
     * so we don’t recompute the same subproblem.
     *
     * dp[i] = minimum cost to reach stair i
     *
     * Time Complexity: O(n)
     * Space Complexity:
     *   O(n) for dp array
     *   O(n) recursion stack
     */
    public int frogJumpMemo(int[] height) {
        int n = height.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return solveMemo(height, dp, n - 1);
    }

    private int solveMemo(int[] height, int[] dp, int idx) {
        if (idx == 0) return 0;

        if (dp[idx] != -1) return dp[idx];

        int left = solveMemo(height, dp, idx - 1)
                   + Math.abs(height[idx] - height[idx - 1]);

        int right = Integer.MAX_VALUE;
        if (idx > 1) {
            right = solveMemo(height, dp, idx - 2)
                    + Math.abs(height[idx] - height[idx - 2]);
        }

        return dp[idx] = Math.min(left, right);
    }

    /* ================================================================
     * 3️⃣ Tabulation (Bottom-Up DP)
     * ================================================================
     *
     * Build dp[] from 0 to n-1 iteratively.
     *
     * dp[0] = 0
     *
     * for i = 1 to n-1:
     *   dp[i] = min(
     *              dp[i-1] + |h[i] - h[i-1]|,
     *              dp[i-2] + |h[i] - h[i-2]| (if i > 1)
     *           )
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int frogJumpTabulation(int[] height) {
        int n = height.length;
        int[] dp = new int[n];

        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            int left = dp[i - 1]
                       + Math.abs(height[i] - height[i - 1]);

            int right = Integer.MAX_VALUE;
            if (i > 1) {
                right = dp[i - 2]
                        + Math.abs(height[i] - height[i - 2]);
            }

            dp[i] = Math.min(left, right);
        }

        return dp[n - 1];
    }

    /* ================================================================
     * 4️⃣ Space Optimized DP
     * ================================================================
     *
     * Observation:
     * ------------
     * dp[i] depends only on:
     *   - dp[i-1]
     *   - dp[i-2]
     *
     * So we don’t need a full dp array.
     *
     * Use:
     *   prev  = dp[i-1]
     *   prev2 = dp[i-2]
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int frogJumpSpaceOptimized(int[] height) {
        int n = height.length;

        int prev2 = 0; // dp[0]
        int prev = 0;  // dp[1] (will be updated)

        for (int i = 1; i < n; i++) {
            int left = prev
                       + Math.abs(height[i] - height[i - 1]);

            int right = Integer.MAX_VALUE;
            if (i > 1) {
                right = prev2
                        + Math.abs(height[i] - height[i - 2]);
            }

            int curr = Math.min(left, right);
            prev2 = prev;
            prev = curr;
        }

        return prev;
    }

    // Optional main() for testing
    public static void main(String[] args) {
        FrogJump fj = new FrogJump();

        int[] height = {30, 20, 50, 10, 40};

        System.out.println("Recursion: " + fj.frogJumpRecursion(height));
        System.out.println("Memoization: " + fj.frogJumpMemo(height));
        System.out.println("Tabulation: " + fj.frogJumpTabulation(height));
        System.out.println("Space Optimized: " + fj.frogJumpSpaceOptimized(height));
    }
}
