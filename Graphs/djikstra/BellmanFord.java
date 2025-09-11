/*
Bellman-Ford Algorithm
----------------------

📌 Problem Summary:
We are given a weighted directed graph (can contain negative weights).
We need to find the shortest path from a source vertex to all other vertices.
If there exists a negative weight cycle reachable from the source, return {-1}.

---------------------------------------------------
💡 Intuition & Theory:
1. Dijkstra fails with negative weights because once a node is "finalized",
   it assumes no shorter path can be found — which is false with negative edges.

2. Bellman-Ford overcomes this by repeatedly relaxing edges.
   - Relaxation = if dist[u] + wt < dist[v], update dist[v].
   - Repeat this process (V-1) times because the longest shortest path
     in a graph without negative cycles has at most (V-1) edges.

3. After V-1 relaxations:
   - All shortest distances are finalized.
   - If a shorter path is still found, that means there is a negative cycle.

---------------------------------------------------
⏱ Time Complexity:
- O(V * E) → because we check all edges (E) for (V-1) iterations.
- Checking negative cycle = O(E).
- Total: O(V * E).

💾 Space Complexity:
- O(V) for dist[] array.

---------------------------------------------------
✅ Example:
Input: V = 5, edges = {{0,1,-1},{0,2,4},{1,2,3},{1,3,2},{1,4,2},{3,2,5},{3,1,1},{4,3,-3}}, src = 0
Output: {0, -1, 2, -2, 1}
*/

import java.util.*;

public class BellmanFord {
    public int[] bellmanFord(int V, int[][] edges, int src) {
        int dist[] = new int[V];
        Arrays.fill(dist, (int)1e8); // infinity
        dist[src] = 0;

        // Relax edges V-1 times
        for (int count = 0; count < V - 1; count++) {
            for (int edge[] : edges) {
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];

                if (dist[u] != (int)1e8 && dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                }
            }
        }

        // Check for negative weight cycle
        for (int edge[] : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            if (dist[u] != (int)1e8 && dist[u] + wt < dist[v]) {
                return new int[]{-1};
            }
        }

        return dist;
    }

    // Example run
    public static void main(String[] args) {
        BellmanFord bf = new BellmanFord();
        int V = 5;
        int[][] edges = {
            {0,1,-1}, {0,2,4}, {1,2,3}, {1,3,2}, {1,4,2},
            {3,2,5}, {3,1,1}, {4,3,-3}
        };
        int src = 0;

        int[] result = bf.bellmanFord(V, edges, src);
        System.out.println(Arrays.toString(result));
    }
}
