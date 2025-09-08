/*
Problem: Shortest Path in Binary Matrix (LeetCode #1091)

Problem Summary:
Given an n x n binary matrix 'grid', where 0 represents an empty cell and 1 represents an obstacle,
find the length of the shortest path from the top-left corner (0,0) to the bottom-right corner (n-1,n-1),
where you can move in 8 directions (horizontal, vertical, diagonal). The path can only pass through empty cells (0).

Return the length of the shortest path. If no path exists, return -1.

Input:
- grid: int[][] n x n binary matrix.

Output:
- int: minimum number of steps from start to end or -1 if no path.

Example:
Input: [[0,1],[1,0]]
Output: 2
Explanation: Path is (0,0) → (1,1)

Intuition:
- This is a shortest path problem on a grid.
- Since all moves have equal weight, BFS (Breadth-First Search) guarantees the shortest path.
- Use a distance matrix to track the shortest distance to each cell and prevent revisiting longer paths.

Approach:
1. Check if start or end is blocked → return -1 if true.
2. Initialize a queue for BFS with a Tuple (distance, row, col).
3. Initialize a distance matrix with high values (infinity) and set start cell to 1.
4. For each cell, explore all 8 directions:
   - If the next cell is within bounds, unblocked, and a shorter distance is possible:
       - Update distance
       - Add it to the queue
5. If the bottom-right is reached, return its distance.
6. If BFS completes without reaching end, return -1.

Time Complexity:
- O(n^2) → each cell visited at most once.

Space Complexity:
- O(n^2) → for the distance matrix + queue.

*/

import java.util.*;

class Solution {
    
    // Tuple class to store (distance, row, col)
    class Tuple {
        int dist;
        int row;
        int col;

        Tuple(int d, int r, int c) {
            this.dist = d;
            this.row = r;
            this.col = c;
        }
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        // ✅ Check if start or end is blocked
        if (grid[0][0] == 1 || grid[n-1][n-1] == 1) return -1;

        Queue<Tuple> q = new LinkedList<>();
        int[][] dist = new int[n][n];
        for (int[] row : dist) Arrays.fill(row, (int) 1e9);

        // Start BFS from (0,0) with distance 1
        q.add(new Tuple(1, 0, 0));
        dist[0][0] = 1;

        // 8 possible directions
        int[] dr = {-1,-1,-1,0,0,1,1,1};
        int[] dc = {-1,0,1,-1,1,-1,0,1};

        while (!q.isEmpty()) {
            Tuple node = q.poll();
            int d = node.dist;
            int r = node.row;
            int c = node.col;

            // Reached bottom-right
            if (r == n-1 && c == n-1) return d;

            // Explore all 8 directions
            for (int i = 0; i < 8; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nc >= 0 && nr < n && nc < n && grid[nr][nc] == 0
                        && d + 1 < dist[nr][nc]) {
                    dist[nr][nc] = d + 1;
                    q.add(new Tuple(d + 1, nr, nc));
                }
            }
        }

        return -1; // No path found
    }

    // Example usage
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid1 = {
            {0,1},
            {1,0}
        };
        System.out.println(sol.shortestPathBinaryMatrix(grid1)); // Output: 2

        int[][] grid2 = {
            {0,0,0},
            {1,1,0},
            {1,1,0}
        };
        System.out.println(sol.shortestPathBinaryMatrix(grid2)); // Output: 4

        int[][] grid3 = {
            {1,0,0},
            {1,1,0},
            {1,1,0}
        };
        System.out.println(sol.shortestPathBinaryMatrix(grid3)); // Output: -1
    }
}
