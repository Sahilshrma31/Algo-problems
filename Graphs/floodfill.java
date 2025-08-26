/*
 * 🚀 LeetCode 733. Flood Fill
 *
 * Problem Summary:
 * You are given a 2D image represented as a grid of integers, where each integer is a color.
 * Starting from a given pixel (sr, sc), fill all connected pixels (up, down, left, right)
 * having the same original color with the new given color.
 *
 * -------------------------------------------------------------------------
 * Intuition:
 * - This is a graph traversal problem.
 * - Each pixel is a node, edges exist to its 4-directional neighbors.
 * - We need to recolor all nodes connected to the starting pixel with the same original color.
 * - Two standard approaches:
 *    1. DFS (recursion) → depth-first traversal.
 *    2. BFS (queue)     → level-order traversal.
 *
 * -------------------------------------------------------------------------
 * Approach (DFS):
 * 1. Copy input grid to an answer matrix (so we don’t tamper the original).
 * 2. Recolor the starting pixel.
 * 3. From current pixel, recursively visit 4 neighbors that are still of the original color.
 * 4. Recolor them and continue.
 *
 * Approach (BFS):
 * 1. Copy input grid to an answer matrix.
 * 2. Use a queue → start with (sr, sc).
 * 3. While queue not empty:
 *       - Pop front cell.
 *       - Recolor it.
 *       - Push neighbors that are of original color.
 *
 * -------------------------------------------------------------------------
 * Complexity:
 * - Time:  O(N*M)  → every cell is visited at most once.
 * - Space: O(N*M)  → recursion stack (DFS) or queue (BFS).
 */

import java.util.*;

class Solution {

    // ✅ DFS Approach
    public int[][] floodFillDFS(int[][] image, int sr, int sc, int color) {
        int m = image.length, n = image[0].length;
        int[][] ans = new int[m][n];

        // Copy image into ans
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = image[i][j];
            }
        }

        int originalColor = ans[sr][sc];
        if (originalColor == color) return image;

        dfs(sr, sc, ans, color, originalColor);
        return ans;
    }

    private void dfs(int r, int c, int[][] ans, int newColor, int originalColor) {
        int m = ans.length, n = ans[0].length;

        ans[r][c] = newColor; // recolor current cell

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i], nc = c + dc[i];

            if (nr >= 0 && nc >= 0 && nr < m && nc < n &&
                ans[nr][nc] == originalColor) {
                dfs(nr, nc, ans, newColor, originalColor);
            }
        }
    }


    // ✅ BFS Approach
    public int[][] floodFillBFS(int[][] image, int sr, int sc, int color) {
        int m = image.length, n = image[0].length;
        int[][] ans = new int[m][n];

        // Copy image into ans
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = image[i][j];
            }
        }

        int originalColor = ans[sr][sc];
        if (originalColor == color) return image;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sr, sc});
        ans[sr][sc] = color;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int r = cell[0], c = cell[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i], nc = c + dc[i];

                if (nr >= 0 && nc >= 0 && nr < m && nc < n &&
                    ans[nr][nc] == originalColor) {
                    ans[nr][nc] = color;
                    q.add(new int[]{nr, nc});
                }
            }
        }

        return ans;
    }
}
