/*
====================================================
📌 Problem: Where Will the Ball Fall
====================================================

You are given an m x n grid where each cell contains:
- 1  → board redirects the ball to the right
- -1 → board redirects the ball to the left

A ball is dropped from the top of each column.
At each row:
- If the ball hits a "V" shape (1 followed by -1 or vice-versa),
  it gets stuck.
- Otherwise, it moves diagonally down.

Return an array where:
- result[i] = column where the ball dropped from column i ends up
- -1 if the ball gets stuck

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
Simulate each ball independently.

For a ball at (row, col):
- If grid[row][col] == 1 → try moving to (row+1, col+1)
- If grid[row][col] == -1 → try moving to (row+1, col-1)

Before moving:
- Check boundary conditions
- Check for V-shaped blockage

----------------------------------------------------
🧩 Approach (Simulation)
----------------------------------------------------
1️⃣ For each column, drop a ball
2️⃣ Move row by row until:
   - Ball reaches bottom → success
   - Ball gets stuck → return -1
3️⃣ Store final column for each ball

----------------------------------------------------
⏱ Time & Space Complexity
----------------------------------------------------
Let m = rows, n = columns

Time Complexity:
- O(m * n)
  (Each ball may traverse all rows)

Space Complexity:
- O(1) extra space (excluding output array)

====================================================
*/

class Solution {

    public int[] findBall(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] result = new int[n];

        // Drop one ball from each column
        for (int ball = 0; ball < n; ball++) {
            int row = 0, col = ball;
            boolean stuck = false;

            while (row < m) {
                // Move right
                if (grid[row][col] == 1) {
                    // Boundary or V-shape check
                    if (col == n - 1 || grid[row][col + 1] == -1) {
                        stuck = true;
                        break;
                    }
                    col++;
                }
                // Move left
                else {
                    // Boundary or V-shape check
                    if (col == 0 || grid[row][col - 1] == 1) {
                        stuck = true;
                        break;
                    }
                    col--;
                }
                row++;
            }

            result[ball] = stuck ? -1 : col;
        }

        return result;
    }
}
