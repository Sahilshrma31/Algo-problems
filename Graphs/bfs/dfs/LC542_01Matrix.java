package bfs.dfs;
/*
 * LeetCode 542. 01 Matrix
 *
 * Problem Summary:
 * Given a matrix consisting of 0s and 1s, return a matrix where each cell contains
 * the distance to the nearest 0. You can move in four directions (up, down, left, right).
 *
 * Intuition:
 * - Treat all 0s as sources and perform BFS.
 * - Each time a 1 is reached for the first time, its distance is the shortest from any 0.
 *
 * Approach:
 * 1. Initialize a queue and visited array.
 * 2. Push all 0 cells into the queue and mark them as visited.
 * 3. While the queue is not empty:
 *      - Pop a cell
 *      - Explore its 4 neighbors
 *      - If neighbor is not visited:
 *           - distance = current cell distance + 1
 *           - mark visited and enqueue
 * 4. Return the distance matrix.
 *
 * Time Complexity: O(m*n) - each cell visited once
 * Space Complexity: O(m*n) - queue + visited array
 */

import java.util.*;

public class LC542_01Matrix {
    
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] dist = new int[m][n];
        boolean[][] vis = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();

        // Initialize queue with all 0s
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    q.add(new int[]{i, j});
                    vis[i][j] = true;
                }
            }
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int r = cell[0], c = cell[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nc >= 0 && nr < m && nc < n && !vis[nr][nc]) {
                    dist[nr][nc] = dist[r][c] + 1;
                    vis[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                }
            }
        }

        return dist;
    }

    // Driver code for testing
    public static void main(String[] args) {
        LC542_01Matrix sol = new LC542_01Matrix();
        int[][] mat = {
            {0,0,0},
            {0,1,0},
            {1,1,1}
        };

        int[][] res = sol.updateMatrix(mat);

        System.out.println("Distance Matrix:");
        for (int[] row : res) {
            System.out.println(Arrays.toString(row));
        }
    }
}
