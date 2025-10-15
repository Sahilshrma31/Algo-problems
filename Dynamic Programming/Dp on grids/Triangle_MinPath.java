/*
LeetCode 120. Triangle
--------------------------------
Problem:
You are given a triangle array, return the minimum path sum from top to bottom.
At each step, you may move to an adjacent number of the row below.

Example:
Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11
Explanation: 2 -> 3 -> 5 -> 1 = 11

--------------------------------
Approach (Striver DP Series):
1. Recursion (brute force) → Try all paths
2. Memoization (top-down DP) → Cache results to avoid recomputation
3. Tabulation (bottom-up DP) → Fill DP table iteratively
4. Space Optimization → Use only O(n) space instead of full DP table

--------------------------------
Time Complexity:
- Recursion: O(2^(N*N)) worst (very large)
- Memoization: O(N*N)
- Tabulation: O(N*N)
- Space Optimized: O(N)

Space Complexity:
- Recursion: O(N) (stack depth)
- Memoization: O(N*N)
- Tabulation: O(N*N)
- Space Optimized: O(N)
*/

import java.util.*;

public class Triangle_MinPath {

    // ----------------- 1. Recursion -----------------
    private static int recur(int i, int j, List<List<Integer>> triangle) {
        int n = triangle.size();
        if (i == n - 1) return triangle.get(i).get(j); // base case, last row

        int down = triangle.get(i).get(j) + recur(i + 1, j, triangle);
        int diag = triangle.get(i).get(j) + recur(i + 1, j + 1, triangle);

        return Math.min(down, diag);
    }

    // ----------------- 2. Memoization -----------------
    private static int memo(int i, int j, List<List<Integer>> triangle, int[][] dp) {
        int n = triangle.size();
        if (i == n - 1) return triangle.get(i).get(j);
        if (dp[i][j] != -1) return dp[i][j];

        int down = triangle.get(i).get(j) + memo(i + 1, j, triangle, dp);
        int diag = triangle.get(i).get(j) + memo(i + 1, j + 1, triangle, dp);

        return dp[i][j] = Math.min(down, diag);
    }

    // ----------------- 3. Tabulation -----------------
    private static int tabulation(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];

        // base case: last row = same as triangle last row
        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = triangle.get(n - 1).get(j);
        }

        // bottom-up calculation
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                int down = triangle.get(i).get(j) + dp[i + 1][j];
                int diag = triangle.get(i).get(j) + dp[i + 1][j + 1];
                dp[i][j] = Math.min(down, diag);
            }
        }
        return dp[0][0];
    }

    // ----------------- 4. Space Optimized -----------------
    private static int spaceOptimized(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] front = new int[n];
        int[] curr = new int[n];

        // base case: last row
        for (int j = 0; j < n; j++) {
            front[j] = triangle.get(n - 1).get(j);
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                int down = triangle.get(i).get(j) + front[j];
                int diag = triangle.get(i).get(j) + front[j + 1];
                curr[j] = Math.min(down, diag);
            }
            front = curr.clone(); // update for next row
        }

        return front[0];
    }

    // ----------------- DRIVER -----------------
    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));

        int n = triangle.size();

        // Recursion
        System.out.println("Recursion: " + recur(0, 0, triangle));

        // Memoization
        int[][] dp = new int[n][n];
        for (int[] row : dp) Arrays.fill(row, -1);
        System.out.println("Memoization: " + memo(0, 0, triangle, dp));

        // Tabulation
        System.out.println("Tabulation: " + tabulation(triangle));

        // Space Optimized
        System.out.println("Space Optimized: " + spaceOptimized(triangle));
    }
}
