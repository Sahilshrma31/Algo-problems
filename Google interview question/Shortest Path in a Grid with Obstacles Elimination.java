/*
====================================================
📌 Problem: Shortest Path in a Grid with Obstacles Elimination
(LeetCode 1293)
====================================================

You are given an m x n grid where:
- 0 represents an empty cell
- 1 represents an obstacle

You start at (0, 0) and want to reach (m-1, n-1).
You can move in 4 directions: up, left, down, right.

You are allowed to eliminate at most `k` obstacles.
Return the minimum number of steps to reach the destination,
or -1 if it is not possible.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
This is a shortest path problem → use BFS.

But the twist is:
Reaching the same cell with different remaining obstacle eliminations
is NOT the same state.

So the state becomes:
(row, col, remaining_k)

We must track visited states in 3D.

----------------------------------------------------
🧩 Approach (BFS + State)
----------------------------------------------------
1️⃣ Use BFS to explore the grid level by level (steps).
2️⃣ Each queue element stores:
   {row, col, remaining eliminations}
3️⃣ Use a 3D visited array:
   visited[row][col][remaining_k]
4️⃣ For each move:
   - If cell is empty → move normally
   - If cell has obstacle → move only if remaining_k > 0
5️⃣ First time reaching destination → return steps.

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(m * n * k)

Each cell can be visited with different `k` values.

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(m * n * k)

For visited array + BFS queue.

====================================================
*/

import java.util.*;

class Solution {

    // Directions: up, left, down, right
    int[][] directions = {
        {-1, 0},
        {0, -1},
        {1, 0},
        {0, 1}
    };

    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        // Queue stores {row, col, remaining obstacles}
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, k});

        // visited[row][col][remaining_k]
        boolean[][][] visited = new boolean[m][n][k + 1];
        visited[0][0][k] = true;

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];
                int obs = curr[2];

                // Destination reached
                if (r == m - 1 && c == n - 1) {
                    return steps;
                }

                for (int[] d : directions) {
                    int nr = r + d[0];
                    int nc = c + d[1];

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;

                    // Empty cell
                    if (grid[nr][nc] == 0 && !visited[nr][nc][obs]) {
                        visited[nr][nc][obs] = true;
                        queue.offer(new int[]{nr, nc, obs});
                    }
                    // Obstacle cell
                    else if (grid[nr][nc] == 1 && obs > 0 && !visited[nr][nc][obs - 1]) {
                        visited[nr][nc][obs - 1] = true;
                        queue.offer(new int[]{nr, nc, obs - 1});
                    }
                }
            }
            steps++;
        }

        return -1;
    }
}
