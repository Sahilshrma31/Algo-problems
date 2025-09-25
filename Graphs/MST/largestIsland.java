/**
 * Problem: Making A Large Island (LeetCode #827)
 *
 * You are given an n x n binary matrix grid. You can change at most one 0 to 1.
 * Return the size of the largest island possible in the grid after performing this operation.
 * An island is a group of 1's connected 4-directionally (up, down, left, right).
 *
 * Example:
 * Input: [[1,0],[0,1]]
 * Output: 3
 *
 * ---------------------------
 * Intuition:
 * ---------------------------
 * 1. Use Union-Find (Disjoint Set Union - DSU) to group all existing 1’s into connected components.
 *    Each component will store its size.
 *
 * 2. For each 0 cell, check its 4 neighbors. If they belong to different components,
 *    calculate the potential size if this 0 is flipped to 1 (sum of unique neighbor component sizes + 1).
 *
 * 3. Track the maximum size while iterating.
 *
 * 4. Edge case: if the grid is already full of 1’s, simply return the size of the largest component.
 *
 * ---------------------------
 * Time Complexity:
 * ---------------------------
 * - Building DSU: O(n^2 * α(n^2))  ~ O(n^2) (α = inverse Ackermann, very small)
 * - For each 0 cell, checking neighbors: O(4) = O(1) per cell → O(n^2)
 * - Overall: O(n^2)
 *
 * ---------------------------
 * Space Complexity:
 * ---------------------------
 * - DSU parent and size arrays: O(n^2)
 * - HashSet for neighbors: O(4) = O(1)
 * - Overall: O(n^2)
 */

import java.util.*;

class Solution {

    // Helper to check boundaries
    private boolean isValid(int r, int c, int n) {
        return !(r >= n || c >= n || r < 0 || c < 0);
    }

    // Disjoint Set (Union-Find) class
    class DS {
        int[] parent, size;

        DS(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int a) {
            if (a == parent[a]) return a;
            return parent[a] = find(parent[a]);
        }

        void union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            if (pa == pb) return;
            if (size[pa] < size[pb]) {
                parent[pa] = pb;
                size[pb] += size[pa];
            } else {
                parent[pb] = pa;
                size[pa] += size[pb];
            }
        }
    }

    public int largestIsland(int[][] grid) {
        int n = grid.length;
        DS ds = new DS(n * n);

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        // Step 1: Union all adjacent 1s
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int node = i * n + j;
                    for (int k = 0; k < 4; k++) {
                        int newx = i + dx[k];
                        int newy = j + dy[k];
                        if (isValid(newx, newy, n) && grid[newx][newy] == 1) {
                            int newNode = newx * n + newy;
                            ds.union(node, newNode);
                        }
                    }
                }
            }
        }

        int ans = 0;

        // Step 2: Try flipping each 0 to 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> set = new HashSet<>();
                    for (int k = 0; k < 4; k++) {
                        int newx = i + dx[k];
                        int newy = j + dy[k];
                        if (isValid(newx, newy, n) && grid[newx][newy] == 1) {
                            set.add(ds.find(newx * n + newy));
                        }
                    }
                    int temp = 1; // flipping this cell
                    for (int root : set) {
                        temp += ds.size[root];
                    }
                    ans = Math.max(ans, temp);
                }
            }
        }

        // Step 3: If grid already full of 1s, take max size
        for (int i = 0; i < n * n; i++) {
            ans = Math.max(ans, ds.size[ds.find(i)]);
        }

        return ans;
    }
}
