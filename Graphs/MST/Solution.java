/**
 * Problem: Most Stones Removed with Same Row or Column
 * Platform: LeetCode
 * Approach: Disjoint Set Union (DSU)
 * 
 * PS Intuition:
 * 1. Each stone is a node; stones sharing the same row or column are connected.
 * 2. Use DSU to group connected stones into components.
 * 3. Maximum stones removed = Total stones - Number of connected components.
 * 4. Map rows and columns uniquely by offsetting column indices to avoid collision.
 * 5. Count unique components using HashSet over all involved nodes.
 */

import java.util.*;

public class Solution {
    // Disjoint Set Union (Union by Rank + Path Compression)
    class DisjointSet{
        int parent[];
        int rank[];

        public DisjointSet(int n){
            parent = new int[n];
            rank = new int[n];
            for(int i=0; i<n; i++){
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int node){
            if(parent[node] != node){
                parent[node] = find(parent[node]); // Path compression
            }
            return parent[node];
        }

        public void union(int a, int b){
            int parA = find(a);
            int parB = find(b);

            if(parA == parB) return;

            if(rank[parA] > rank[parB]){
                parent[parB] = parA;
            } else if(rank[parA] < rank[parB]){
                parent[parA] = parB;
            } else {
                parent[parA] = parB;
                rank[parB]++;
            }
        }
    }

    public int removeStones(int[][] stones) {
        int n = stones.length;
        int maxrow = 0, maxcol = 0;

        // Find max row and column index to size DSU array
        for(int[] it : stones){
            maxrow = Math.max(maxrow, it[0]);
            maxcol = Math.max(maxcol, it[1]);
        }

        DisjointSet dsu = new DisjointSet(maxrow + maxcol + 2);
        HashSet<Integer> set = new HashSet<>();

        // Union row and column indices with offset to avoid collision
        for(int[] it : stones){
            int row = it[0];
            int col = it[1] + maxrow + 1;
            dsu.union(row, col);
            set.add(row);
            set.add(col);
        }

        // Count number of connected components
        int cnt = 0;
        for(int it : set){
            if(dsu.find(it) == it){
                cnt++;
            }
        }

        return n - cnt; // Maximum stones removed
    }

    // Example usage / test
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] stones = {{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}};
        System.out.println("Max stones removed: " + sol.removeStones(stones)); // Output: 5
    }
}