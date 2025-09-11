/*
Floyd–Warshall Algorithm
------------------------------------------------------------

📌 Problem Summary:
The Floyd–Warshall algorithm is used to find the shortest distances 
between every pair of vertices in a weighted graph. 

- Works for both directed and undirected graphs.
- Handles positive and negative edge weights.
- Detects negative cycles (if dist[i][i] < 0 for any i).

------------------------------------------------------------

💡 Intuition:
- Start with a distance matrix where dist[i][j] is the direct edge weight 
  from i to j (or INF if no edge).
- Consider each node k as an intermediate point:
    If going from i → k → j is shorter than i → j directly, update dist[i][j].
- Repeat this for all k to ensure all indirect paths are considered.

------------------------------------------------------------

⏱️ Time Complexity:
- O(V^3), since we run three nested loops (for k, i, j).

💾 Space Complexity:
- O(V^2) for the distance matrix.

------------------------------------------------------------
*/

import java.util.*;

class FloydWarshall {
    public void floydWarshall(int[][] dist) {
        int V = dist.length;
        int INF = (int)1e8; // a large number to represent infinity

        // Run Floyd–Warshall
        for (int k = 0; k < V; k++) { // intermediate node
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF &&
                        dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Detect negative cycle
        for (int i = 0; i < V; i++) {
            if (dist[i][i] < 0) {
                System.out.println("Negative cycle detected!");
                return;
            }
        }
    }

    // Demo
    public static void main(String[] args) {
        int INF = (int)1e8;
        int[][] graph = {
            {0,   5,  INF, 10},
            {INF, 0,   3,  INF},
            {INF, INF, 0,   1},
            {INF, INF, INF, 0}
        };

        FloydWarshall fw = new FloydWarshall();
        fw.floydWarshall(graph);

        System.out.println("All-Pairs Shortest Paths:");
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j] == INF) System.out.print("INF ");
                else System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }
}
