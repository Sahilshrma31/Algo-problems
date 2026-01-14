
/*
LeetCode 994. Rotting Oranges

=====================
📌 Problem Summary:
Find the minimum time for all fresh oranges to rot, or return -1 if impossible.

=====================
💡 Intuition:
- Use Multi-source BFS:
  - All rotten oranges (2) → initial sources
  - Spread level by level → each level = 1 minute
- Track fresh count. If fresh > 0 after BFS → return -1.

=====================
⏱️ Time Complexity: O(m*n)
💾 Space Complexity: O(m*n)
*/

import java.util.*;

class Solution {

    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> q = new LinkedList<>();
        int fresh = 0;

        // Step 1: count fresh & push rotten
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    q.add(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        // If no fresh oranges
        if (fresh == 0) return 0;

        int minutes = 0;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // Step 2: BFS
        while (!q.isEmpty()) {
            int size = q.size();
            boolean rottedThisMinute = false;

            for (int i = 0; i < size; i++) {
                int[] cell = q.poll();
                int r = cell[0];
                int c = cell[1];

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr >= 0 && nr < m && nc >= 0 && nc < n &&
                        grid[nr][nc] == 1) {

                        grid[nr][nc] = 2; // rot it
                        fresh--;
                        q.add(new int[]{nr, nc});
                        rottedThisMinute = true;
                    }
                }
            }

            if (rottedThisMinute) minutes++;
        }

        return fresh == 0 ? minutes : -1;
    }
}
