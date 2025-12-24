/*
====================================================
📌 Problem: Shortest Bridge (LeetCode 934)
====================================================

You are given an n x n binary matrix grid where:
- 1 represents land
- 0 represents water

There are exactly two islands in the grid.
An island is a group of connected 1s (4-directionally).

You can flip 0s to 1s.
Return the minimum number of 0s you must flip to connect the two islands.

----------------------------------------------------
🧠 Approach (Striver Style: DFS + BFS)
----------------------------------------------------
1️⃣ Use DFS to find the first island.
   - Mark all its cells as visited
   - Push them into a queue

2️⃣ Use BFS from all cells of the first island simultaneously.
   - Expand layer by layer into water (0s)
   - The moment we hit a cell of the second island → answer found

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n²)

----------------------------------------------------
🧮 Space Complexity
----------------------------------------------------
O(n²)

====================================================
*/

import java.util.*;

class Solution {

    int n;
    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    public int shortestBridge(int[][] grid) {
        n = grid.length;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        boolean found = false;

        // Step 1: Find first island using DFS
        for (int i = 0; i < n && !found; i++) {
            for (int j = 0; j < n && !found; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, queue, visited, i, j);
                    found = true;
                }
            }
        }

        // Step 2: BFS to expand island until second island is reached
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int[] curr = queue.poll();
                int r = curr[0], c = curr[1];

                for (int[] d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];

                    if (nr < 0 || nc < 0 || nr >= n || nc >= n || visited[nr][nc])
                        continue;

                    if (grid[nr][nc] == 1)
                        return steps;

                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
            steps++;
        }

        return -1;
    }

    // DFS to mark first island
    private void dfs(int[][] grid, Queue<int[]> queue, boolean[][] visited, int r, int c) {
        if (r < 0 || c < 0 || r >= n || c >= n || visited[r][c] || grid[r][c] == 0)
            return;

        visited[r][c] = true;
        queue.offer(new int[]{r, c});

        for (int[] d : dirs) {
            dfs(grid, queue, visited, r + d[0], c + d[1]);
        }
    }
}
