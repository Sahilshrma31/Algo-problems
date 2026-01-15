/*
====================================================
📌 Problem: Is Graph Bipartite?
(LeetCode 785)
====================================================

Given an undirected graph, determine whether it is bipartite.

A graph is bipartite if we can divide its nodes into two sets
such that:
- No two adjacent nodes are in the same set
- Equivalently, the graph can be colored using 2 colors
  with no adjacent nodes having the same color

The graph may be disconnected.

====================================================
 Approach: BFS + Graph Coloring
====================================================

- Maintain a `color[]` array:
  0  -> uncolored
  1  -> color A
 -1  -> color B

- For each node:
  - If it is uncolored, start BFS from that node
  - Assign initial color = 1
  - For every neighbor:
      - If uncolored → assign opposite color
      - If same color as current → NOT bipartite

- If all components satisfy bipartite condition → return true

====================================================
⏱ Time Complexity
====================================================
O(V + E)
V = number of vertices
E = number of edges

====================================================
📦 Space Complexity
====================================================
O(V)
- color array
- BFS queue

====================================================
*/

import java.util.*;

class Solution {

    public boolean isBipartite(int[][] graph) {

        int n = graph.length;
        int[] color = new int[n]; // 0 = uncolored, 1 and -1 are two colors

        // Graph may be disconnected
        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {
                if (!bfs(graph, i, color)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean bfs(int[][] graph, int start, int[] color) {

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        color[start] = 1; // assign first color

        while (!queue.isEmpty()) {

            int node = queue.poll();

            for (int neighbor : graph[node]) {

                // If neighbor is uncolored → color it with opposite color
                if (color[neighbor] == 0) {
                    color[neighbor] = -color[node];
                    queue.offer(neighbor);
                }
                // If neighbor has same color → not bipartite
                else if (color[neighbor] == color[node]) {
                    return false;
                }
            }
        }

        return true;
    }
}
