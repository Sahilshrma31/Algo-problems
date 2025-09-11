/*
1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance
-------------------------------------------------------------------------------

📌 Problem Summary:
You are given:
- An integer n (number of cities, 0-indexed).
- An array of edges (u, v, weight), representing undirected weighted edges.
- An integer distanceThreshold.

For each city, find how many other cities are reachable with a path distance 
<= distanceThreshold. 
Return the city with the *smallest* number of reachable neighbors.
If multiple cities have the same value, return the city with the *largest index*.

-------------------------------------------------------------------------------

💡 Intuition:
- We need shortest paths between all pairs of cities.
- Since n ≤ 100 (small), we can use Floyd–Warshall (O(n^3)).
- After computing all shortest paths:
  - Count how many cities are reachable within distanceThreshold for each city.
  - Track the city with the minimum reachable count.
  - In case of ties, choose the city with the larger index.

-------------------------------------------------------------------------------

⏱️ Time Complexity:
- Floyd–Warshall: O(n^3)
- Counting neighbors: O(n^2)
- Total: O(n^3), which is fine since n ≤ 100.

💾 Space Complexity:
- We maintain a distance matrix of size n x n.
- Space: O(n^2)

-------------------------------------------------------------------------------
*/

import java.util.*;

class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int INF = (int)1e9;
        int[][] dist = new int[n][n];

        // Initialize distances
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        // Fill edge weights (undirected graph)
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], wt = edge[2];
            dist[u][v] = wt;
            dist[v][u] = wt;
        }

        // Floyd–Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Find city with smallest neighbors count (tie → larger index)
        int minReachable = Integer.MAX_VALUE;
        int resultCity = -1;

        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && dist[i][j] <= distanceThreshold) {
                    count++;
                }
            }

            if (count <= minReachable) {
                minReachable = count;
                resultCity = i;
            }
        }

        return resultCity;
    }
}
