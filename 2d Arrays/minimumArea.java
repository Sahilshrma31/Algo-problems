/*
🔹 Problem: Minimum Area Enclosing All 1s in a Grid

You are given a 2D binary grid (containing only 0s and 1s).  
Find the minimum area of a rectangle that encloses **all the 1s** in the grid.  
The rectangle sides must be parallel to the grid axes.

If there are no 1s in the grid, return 0.

---

🔹 Approach / Intuition:
We need to compute the smallest rectangle that bounds all the 1s.

1. Initialize:
   - minRow = n (max possible row index + 1)
   - maxRow = -1 (smaller than min row index)
   - minCol = m (max possible col index + 1)
   - maxCol = -1 (smaller than min col index)

2. Traverse the grid:
   - For each cell that is 1:
     - Update minRow, maxRow, minCol, maxCol

3. After traversal:
   - If no 1s were found (maxRow == -1), return 0
   - Otherwise compute:
       width  = maxRow - minRow + 1
       height = maxCol - minCol + 1
       area   = width * height

---

🔹 Complexity:
- Time Complexity: O(n * m) → each cell is checked once  
- Space Complexity: O(1) → only variables to track min/max bounds

*/

class Solution {
    public int minimumArea(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int minRow = n, maxRow = -1;
        int minCol = m, maxCol = -1;

        // Step 1: Traverse grid and update bounds
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    minRow = Math.min(minRow, i);
                    maxRow = Math.max(maxRow, i);
                    minCol = Math.min(minCol, j);
                    maxCol = Math.max(maxCol, j);
                }
            }
        }

        // Step 2: If no 1s found, return 0
        if (maxRow == -1) return 0;

        // Step 3: Compute area
        int height = maxRow - minRow + 1;
        int width = maxCol - minCol + 1;

        return height * width;
    }
}
