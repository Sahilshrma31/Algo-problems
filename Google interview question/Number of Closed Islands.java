/*
====================================================
📌 Problem: Number of Closed Islands
(LeetCode 1254 / GFG Equivalent)
====================================================

You are given a 2D grid where:
- 0 represents land
- 1 represents water

An island is a group of connected land cells (0s).
A **closed island** is an island that is completely
surrounded by water on all four sides (up, down, left, right)
and does NOT touch the boundary of the grid.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
For every unvisited land cell (0), we try to explore
the entire island using DFS.

While exploring:
- If we ever go out of bounds → island touches boundary → NOT closed
- If all DFS directions stay within grid and end in water → closed island

So:
✔ Closed Island = DFS returns TRUE from all 4 directions
❌ Open Island  = DFS touches boundary anywhere

----------------------------------------------------
🧩 Approach (DFS)
----------------------------------------------------
1️⃣ Traverse the grid
2️⃣ When a land cell (0) is found:
   - Run DFS to explore the island
3️⃣ Mark visited land as water (1) to avoid revisits
4️⃣ If DFS confirms island is closed → increment count

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(m * n)
Each cell is visited at most once.

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(m * n) in worst case (DFS recursion stack)
----------------------------------------------------
*/

class Solution {

    int m, n;

    // DFS to check if island is closed
    public boolean dfs(int[][] grid, int r, int c) {

        // If out of bounds → touches boundary → NOT closed
        if (r < 0 || c < 0 || r >= m || c >= n) {
            return false;
        }

        // If water or already visited → safe boundary
        if (grid[r][c] == 1) {
            return true;
        }

        // Mark current land as visited
        grid[r][c] = 1;

        // Explore all 4 directions
        boolean left  = dfs(grid, r, c - 1);
        boolean right = dfs(grid, r, c + 1);
        boolean up    = dfs(grid, r - 1, c);
        boolean down  = dfs(grid, r + 1, c);

        // Island is closed only if ALL directions are closed
        return left && right && up && down;
    }

    public int closedIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int count = 0;

        // Traverse the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // Start DFS for unvisited land
                if (grid[i][j] == 0) {
                    if (dfs(grid, i, j)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
