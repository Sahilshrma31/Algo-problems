/*
    🔹 Shortest Path in Graph with Unit Weights (BFS)

    📌 Problem Statement:
    Given a graph with N nodes (0 to N-1) where all edges have weight = 1,
    find the shortest path from a given source node 'src' to all other nodes.
    If a node is unreachable, mark its distance as -1.

    Example:
    N = 6
    Edges = [(0,1), (0,2), (1,3), (2,4), (3,5), (4,5)]
    src = 0

    Adjacency List:
    0 → [1,2]
    1 → [0,3]
    2 → [0,4]
    3 → [1,5]
    4 → [2,5]
    5 → [3,4]

    Output:
    dist = [0,1,1,2,2,3]

    Explanation:
    - Distance from 0 → 1 = 1
    - Distance from 0 → 2 = 1
    - Distance from 0 → 3 = 2 (via 1)
    - Distance from 0 → 4 = 2 (via 2)
    - Distance from 0 → 5 = 3 (via 3 or 4)


    🔹 Intuition:
    - Since all edges have equal weight (1), we don’t need Dijkstra’s algorithm.
    - BFS works because it explores nodes level by level → 
      the first time we visit a node, it's guaranteed to be the shortest path.
    - Maintain a distance array initialized to infinity.
    - Update distance when a node is reached with fewer steps.

    🔹 Time Complexity:  O(N + M)
        - N = number of nodes
        - M = number of edges
        - Each node & edge is processed at most once.

    🔹 Space Complexity: O(N + M)
        - Adjacency list storage + BFS queue + distance array
*/

import java.util.*;

class Solution {
    public int[] shortestPath(ArrayList<ArrayList<Integer>> adj, int src) {
        int n = adj.size();
        int dist[] = new int[n];

        // Step 1: Initialize all distances with "infinity"
        Arrays.fill(dist, (int)1e9);

        // Step 2: BFS queue initialization
        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        dist[src] = 0; // Distance to source = 0

        // Step 3: Standard BFS traversal
        while (!q.isEmpty()) {
            int node = q.poll();

            for (int neigh : adj.get(node)) {
                // If a shorter path is found
                if (dist[node] + 1 < dist[neigh]) {
                    dist[neigh] = dist[node] + 1;
                    q.add(neigh);
                }
            }
        }

        // Step 4: Replace infinity with -1 for unreachable nodes
        for (int i = 0; i < n; i++) {
            if (dist[i] == (int)1e9) {
                dist[i] = -1;
            }
        }

        return dist;
    }
}
