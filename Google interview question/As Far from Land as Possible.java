/*
====================================================
📌 Problem: As Far from Land as Possible
(LeetCode 1162)
====================================================

Given an n x n grid containing only 0s (water) and 1s (land),
find the maximum distance from any water cell to the nearest
land cell.

Distance is measured using Manhattan distance.

If:
- there is no land OR
- the grid is all land
return -1.

====================================================
🧠 Approach: Multi-Source BFS
====================================================

- Push all land cells (1s) into the queue initially
- Perform BFS level by level
- Each BFS layer represents increasing distance by 1
- Convert visited water cells to land to avoid revisits
- The last BFS level processed gives the maximum distance

====================================================
⏱ Time Complexity
====================================================
O(n * n)

====================================================
📦 Space Complexity
====================================================
O(n * n)  (queue in worst case)

====================================================
*/

import java.util.*;

class Solution {

    public int maxDistance(int[][] grid) {

        int n = grid.length;
        Queue<int[]> queue = new LinkedList<>();

        // Step 1: Add all land cells to queue
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        // Edge cases
        if (queue.isEmpty() || queue.size() == n * n) {
            return -1;
        }

        int distance = 0;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // Step 2: Multi-source BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean expanded = false;

            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int r = cell[0];
                int c = cell[1];

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr >= 0 && nr < n && nc >= 0 && nc < n &&
                        grid[nr][nc] == 0) {

                        grid[nr][nc] = 1; // mark visited
                        queue.offer(new int[]{nr, nc});
                        expanded = true;
                    }
                }
            }

            if (expanded) {
                distance++;
            }
        }

        return distance;
    }
}
