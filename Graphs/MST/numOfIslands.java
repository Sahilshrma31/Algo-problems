/**
 * Problem: Number of Islands II (LeetCode #305 / GFG variant)
 *
 * You are given an empty grid of size rows x cols. A list of operators is provided where each operator adds a land cell (1) 
 * to the grid at position (r, c). After each addition, return the current number of islands.
 * An island is a group of connected 1’s connected 4-directionally (up, down, left, right).
 *
 * Example:
 * Input: rows = 4, cols = 5, operators = [[1,1],[0,1],[3,3],[3,4]]
 * Output: [1,1,2,2]
 *
 * ---------------------------
 * Intuition:
 * ---------------------------
 * 1. Start with a water-only grid (all 0’s).
 * 2. Each operator converts a water cell into land.
 * 3. Use Disjoint Set Union (DSU/Union-Find) to dynamically connect new land with its 4-directional neighbors if they are also land.
 * 4. Keep track of connected components count (islands):
 *      - When a new land is added, count++.
 *      - If it merges with any neighbor island, decrement count.
 * 5. Append count to the answer list after every operator.
 *
 * ---------------------------
 * Time Complexity:
 * ---------------------------
 * - For each operator: O(4 * α(N)) ~ O(α(N)) (amortized, almost constant), where N = rows * cols
 * - For q operators: O(q * α(N))
 *
 * ---------------------------
 * Space Complexity:
 * ---------------------------
 * - DSU parent and rank arrays: O(rows * cols)
 * - Visited grid: O(rows * cols)
 * - Answer list: O(q)
 * - Overall: O(rows * cols + q)
 */

import java.util.*;

class Solution {

    // Disjoint Set Union (DSU) with path compression and union by rank
    class DSU {
        int[] parent, rank;

        public DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        public int find(int node) {
            if (parent[node] != node)
                parent[node] = find(parent[node]);  // Path compression
            return parent[node];
        }

        public void union(int u, int v) {
            int parentU = find(u);
            int parentV = find(v);

            if (parentU == parentV) return;

            if (rank[parentU] < rank[parentV]) {
                parent[parentU] = parentV;
            } else if (rank[parentU] > rank[parentV]) {
                parent[parentV] = parentU;
            } else {
                parent[parentV] = parentU;
                rank[parentU]++;
            }
        }
    }

    // Helper to check valid cell coordinates
    public boolean isValid(int newr, int newc, int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        return newr >= 0 && newc >= 0 && newr < n && newc < m;
    }

    // Main function to calculate number of islands after each operator
    public List<Integer> numOfIslands(int rows, int cols, int[][] operators) {
        int n = operators.length;
        List<Integer> ans = new ArrayList<>();
        DSU dsu = new DSU(rows * cols);
        int cnt = 0; // island count
        int vis[][] = new int[rows][cols];

        for (int[] it : operators) {
            int r = it[0];
            int c = it[1];

            // If cell is already land, just push current count
            if (vis[r][c] == 1) {
                ans.add(cnt);
                continue;
            }

            vis[r][c] = 1;
            cnt++;

            // Directions: left, top, right, bottom
            int dr[] = {-1, 0, 0, 1};
            int dc[] = {0, -1, 1, 0};

            for (int i = 0; i < 4; i++) {
                int newr = r + dr[i];
                int newc = c + dc[i];

                if (isValid(newr, newc, vis) && vis[newr][newc] == 1) {
                    int node = cols * r + c;
                    int adjNode = cols * newr + newc;

                    if (dsu.find(adjNode) != dsu.find(node)) {
                        cnt--;
                        dsu.union(adjNode, node);
                    }
                }
            }

            ans.add(cnt);
        }

        return ans;
    }
}
