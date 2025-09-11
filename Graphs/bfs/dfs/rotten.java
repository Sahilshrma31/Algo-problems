
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
        int m = grid.length, n = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        int fresh = 0;   // count of fresh oranges
        
        // Step 1: Add all rotten oranges to queue, count fresh
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    q.offer(new int[]{i, j, 0}); // row, col, time
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        // Step 2: BFS
        int time = 0;
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0], c = curr[1], t = curr[2];
            time = Math.max(time, t); // track max time
            
            // Check 4 directions
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                
                // If fresh orange found
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == 1) {
                    grid[nr][nc] = 2; // rot it
                    fresh--;          // decrease fresh count
                    q.offer(new int[]{nr, nc, t+1}); // add to queue
                }
            }
        }
        
        // Step 3: Check if all fresh oranges are rotten
        return fresh == 0 ? time : -1;
    }
}
