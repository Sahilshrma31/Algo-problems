/*
Problem: Count Square Submatrices with All Ones (LeetCode 1277)

You are given an n x m binary matrix. 
Return the total number of square submatrices that have all 1s.

Example:
Input:
matrix = [
  [0,1,1,1],
  [1,1,1,1],
  [0,1,1,1]
]

Output:
15

Explanation:
There are 10 squares of size 1,
4 squares of size 2,
1 square of size 3.
Total = 15
*/

/* -----------------------------------------------------------
   Intuition:
   - A square can only be formed if the current cell is 1.
   - For each cell, the size of the largest square ending at that cell
     depends on its top, left, and top-left neighbors.
   - If matrix[i][j] == 1:
       dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])
   - The sum of all dp[i][j] values gives total squares.
----------------------------------------------------------- */


/* ===========================================================
   1. Recursive Solution (Exponential)
   Time Complexity: O(3^(n*m)) in worst case
   Space Complexity: O(n*m) recursion stack
   Not efficient, just for intuition.
=========================================================== */
class RecursiveSolution {
    public int countSquares(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result += helper(matrix, i, j);
            }
        }
        return result;
    }

    private int helper(int[][] matrix, int i, int j) {
        if (i < 0 || j < 0 || matrix[i][j] == 0) return 0;
        return 1 + Math.min(helper(matrix, i - 1, j),
                            Math.min(helper(matrix, i, j - 1),
                                     helper(matrix, i - 1, j - 1)));
    }
}


/* ===========================================================
   2. Memoization (Top-Down DP)
   Time Complexity: O(n*m)
   Space Complexity: O(n*m) (dp array + recursion stack)
=========================================================== */
class MemoizedSolution {
    public int countSquares(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int dp[][] = new int[n][m];
        for (int[] row : dp) java.util.Arrays.fill(row, -1);

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result += helper(matrix, i, j, dp);
            }
        }
        return result;
    }

    private int helper(int[][] matrix, int i, int j, int[][] dp) {
        if (i < 0 || j < 0 || matrix[i][j] == 0) return 0;
        if (dp[i][j] != -1) return dp[i][j];
        return dp[i][j] = 1 + Math.min(helper(matrix, i - 1, j, dp),
                                       Math.min(helper(matrix, i, j - 1, dp),
                                                helper(matrix, i - 1, j - 1, dp)));
    }
}


/* ===========================================================
   3. Tabulation (Bottom-Up DP)
   Time Complexity: O(n*m)
   Space Complexity: O(n*m)
=========================================================== */
class TabulationSolution {
    public int countSquares(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int dp[][] = new int[n][m];
        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j];
                } else if (matrix[i][j] == 1) {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j],
                                    Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                }
                result += dp[i][j];
            }
        }
        return result;
    }
}


/* ===========================================================
   4. Space Optimized DP
   Time Complexity: O(n*m)
   Space Complexity: O(m)  (only two rows stored at a time)
=========================================================== */
class SpaceOptimizedSolution {
    public int countSquares(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int[] prev = new int[m];
        int[] curr = new int[m];
        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    curr[j] = matrix[i][j];
                } else if (matrix[i][j] == 1) {
                    curr[j] = 1 + Math.min(curr[j - 1],
                                   Math.min(prev[j], prev[j - 1]));
                } else {
                    curr[j] = 0;
                }
                result += curr[j];
            }
            prev = curr.clone(); // move curr to prev for next row
        }
        return result;
    }
}
