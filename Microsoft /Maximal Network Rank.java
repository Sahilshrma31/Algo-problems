/*
====================================================
📌 Problem: Maximal Network Rank (LeetCode 1615)
====================================================

The network rank of two different cities i and j is:
= (number of roads connected to i)
+ (number of roads connected to j)
- (1 if there is a direct road between i and j)

Goal:
Find the maximum network rank among all pairs of cities.

====================================================
🧠 Approaches Included
====================================================

1️⃣ Optimized Approach (Adjacency Set)
2️⃣ Brute Force Approach (Adjacency Matrix)

====================================================
⏱ Time & Space Complexity
====================================================

Optimized (HashSet):
- Time: O(n² + m)
- Space: O(n + m)

Brute Force (Matrix):
- Time: O(n² + m)
- Space: O(n²)

====================================================
*/

import java.util.*;

class Solution {

    /* ====================================================
       1️⃣ OPTIMIZED APPROACH (Adjacency Set)
       ==================================================== */
    public int maximalNetworkRank(int n, int[][] roads) {

        Map<Integer, Set<Integer>> adj = new HashMap<>();

        for (int i = 0; i < n; i++) {
            adj.put(i, new HashSet<>());
        }

        for (int[] road : roads) {
            adj.get(road[0]).add(road[1]);
            adj.get(road[1]).add(road[0]);
        }

        int maxRank = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                int rank = adj.get(i).size() + adj.get(j).size();

                // subtract common road if exists
                if (adj.get(i).contains(j)) {
                    rank--;
                }

                maxRank = Math.max(maxRank, rank);
            }
        }

        return maxRank;
    }


    /* ====================================================
       2️⃣ BRUTE FORCE APPROACH (Adjacency Matrix)
       ==================================================== */
    public int maximalNetworkRankBrute(int n, int[][] roads) {

        int[][] adj = new int[n][n];
        int[] degree = new int[n];

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            adj[u][v] = 1;
            adj[v][u] = 1;
            degree[u]++;
            degree[v]++;
        }

        int maxRank = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                int rank = degree[i] + degree[j];

                if (adj[i][j] == 1) {
                    rank--;
                }

                maxRank = Math.max(maxRank, rank);
            }
        }

        return maxRank;
    }
}
