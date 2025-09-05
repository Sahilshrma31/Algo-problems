/*
    🔹 Shortest Path in Directed Acyclic Graph (DAG)

    📌 Problem Statement:
    Given a weighted DAG with N vertices and M edges, and a source node (src = 0 by default on GFG),
    find the shortest path from src to all vertices. If a node is unreachable, return -1 for it.

    Example:
    N = 6, M = 7
    Edges = [
        (0,1,2), (0,4,1), (4,5,4), (4,2,2),
        (1,2,3), (2,3,6), (5,3,1)
    ]
    src = 0

    Output: [0,2,3,6,1,5]

    🔹 Intuition:
    - Normal Dijkstra doesn’t work efficiently for DAG because we don’t need a priority queue.
    - A DAG has no cycles, so we can apply Topological Sort:
        1. Sort all nodes in topological order.
        2. Relax edges in this order (like Dynamic Programming on graphs).
        3. First time we visit a node in topo order, we already know the shortest path to it.

    🔹 Time Complexity: O(N + M)
    🔹 Space Complexity: O(N + M)
*/

import java.util.*;

class Solution {
    // Function to perform Topological Sort (DFS)
    private void topoSort(int node, ArrayList<ArrayList<int[]>> adj, boolean[] vis, Stack<Integer> st) {
        vis[node] = true;
        for (int[] edge : adj.get(node)) {
            int v = edge[0];
            if (!vis[v]) {
                topoSort(v, adj, vis, st);
            }
        }
        st.push(node);
    }

    public int[] shortestPath(int N, int M, int[][] edges) {
        // Step 1: Build adjacency list
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) adj.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];
            adj.get(u).add(new int[]{v, wt});
        }

        // Step 2: Topological Sort
        boolean[] vis = new boolean[N];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < N; i++) {
            if (!vis[i]) topoSort(i, adj, vis, st);
        }

        // Step 3: Initialize distance array
        int[] dist = new int[N];
        Arrays.fill(dist, (int)1e9);
        dist[0] = 0; // source = 0

        // Step 4: Process nodes in Topo order
        while (!st.isEmpty()) {
            int node = st.pop();
            if (dist[node] != (int)1e9) {
                for (int[] edge : adj.get(node)) {
                    int v = edge[0];
                    int wt = edge[1];
                    if (dist[node] + wt < dist[v]) {
                        dist[v] = dist[node] + wt;
                    }
                }
            }
        }

        // Step 5: Replace INF with -1 for unreachable nodes
        for (int i = 0; i < N; i++) {
            if (dist[i] == (int)1e9) dist[i] = -1;
        }

        return dist;
    }
}
