/*
====================================================
📌 Problem: Island Perimeter (LeetCode 463)
====================================================

Given a grid of 0s (water) and 1s (land), there is
exactly one island. Find its perimeter.

====================================================
🧠 Approaches Included
====================================================

1️⃣ DFS (Depth First Search)
2️⃣ BFS (Breadth First Search)
3️⃣ Iterative / Math-based Traversal (Optimal)

====================================================
⏱ Time & Space Complexity
====================================================

1️⃣ DFS
   - Time: O(m * n)
   - Space: O(m * n) (recursion stack)

2️⃣ BFS
   - Time: O(m * n)
   - Space: O(m * n) (queue)

3️⃣ Iterative
   - Time: O(m * n)
   - Space: O(1)

====================================================
*/

import java.util.*;

class Solution {

    /* ====================================================
       1️⃣ DFS APPROACH
       ==================================================== */
    int m, n;
    int peri;

    private void dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) {
            peri++;
            return;
        }

        if (grid[i][j] == -1) return;

        grid[i][j] = -1; // mark visited

        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }

    public int islandPerimeterDFS(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        peri = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j);
                    return peri;
                }
            }
        }
        return 0;
    }


    /* ====================================================
       2️⃣ BFS APPROACH
       ==================================================== */
    private final int[][] directions = {
        {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    private int bfs(int[][] grid, int i, int j) {
        int perimeter = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        grid[i][j] = -1;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            for (int[] d : directions) {
                int ni = curr[0] + d[0];
                int nj = curr[1] + d[1];

                if (ni < 0 || ni >= m || nj < 0 || nj >= n || grid[ni][nj] == 0) {
                    perimeter++;
                } else if (grid[ni][nj] == -1) {
                    continue;
                } else {
                    grid[ni][nj] = -1;
                    queue.offer(new int[]{ni, nj});
                }
            }
        }
        return perimeter;
    }

    public int islandPerimeterBFS(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    return bfs(grid, i, j);
                }
            }
        }
        return 0;
    }


    /* ====================================================
       3️⃣ ITERATIVE / OPTIMAL APPROACH
       ==================================================== */
    public int islandPerimeter(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int perimeter = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 0) continue;

                if (i == 0 || grid[i - 1][j] == 0) perimeter++; // up
                if (i == m - 1 || grid[i + 1][j] == 0) perimeter++; // down
                if (j == 0 || grid[i][j - 1] == 0) perimeter++; // left
                if (j == n - 1 || grid[i][j + 1] == 0) perimeter++; // right
            }
        }
        return perimeter;
    }
}

