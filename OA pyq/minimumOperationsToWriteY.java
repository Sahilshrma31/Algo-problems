/**
 * LeetCode 3071. Minimum Operations to Write the Letter Y on a Grid
 * 
 * Problem Statement:
 * -------------------
 * You are given an odd-sized n x n grid where each cell contains a value in {0,1,2}.
 * You want to transform the grid so that:
 *   1. All cells that form the shape of the letter 'Y' have the same value.
 *   2. All cells that do not belong to the 'Y' shape have a different value.
 * 
 * In one operation, you can change the value of any cell to either 0, 1, or 2.
 * Return the minimum number of operations required to achieve this configuration.
 * 
 * The 'Y' shape consists of:
 *   - The two diagonals from the top row to the middle cell.
 *   - The vertical line from the middle cell down to the bottom row.
 * 
 * Example:
 * --------
 * Input: grid = [[1,2,2],
 *                [2,1,2],
 *                [2,2,1]]
 * Output: 3
 * 
 * Explanation: Make the 'Y' cells all = 1, and non-'Y' cells = 2 with 3 operations.
 * 
 * -----------------------------------------------------------------
 * Time Complexity (TC): O(n^2 * 6) = O(n^2) 
 *   - For each of the 6 possible (y, notY) assignments, we check all n^2 cells.
 * 
 * Space Complexity (SC): O(1) 
 *   - We only use a few integer counters, no extra DP arrays.
 * 
 * -----------------------------------------------------------------
 */

class Solution {
    public int minimumOperationsToWriteY(int[][] grid) {
        int ans = Integer.MAX_VALUE;
        // Try all (y, notY) pairs → total 6 possibilities
        for (int y = 0; y < 3; y++) {
            for (int noty = 0; noty < 3; noty++) {
                if (y != noty) {
                    ans = Math.min(ans, tryThis(grid, y, noty));
                }
            }
        }
        return ans;
    }

    // Helper to compute cost if Y-cells are assigned 'y' and non-Y cells 'noty'
    private int tryThis(int[][] grid, int y, int noty) {
        int n = grid.length;
        int cost = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean inY = false;

                // Y definition
                if ((i <= n / 2 && (i == j || i + j == n - 1)) || 
                    (i > n / 2 && j == n / 2)) {
                    inY = true;
                }

                if (inY) {
                    if (grid[i][j] != y) cost++;
                } else {
                    if (grid[i][j] != noty) cost++;
                }
            }
        }
        return cost;
    }
}
