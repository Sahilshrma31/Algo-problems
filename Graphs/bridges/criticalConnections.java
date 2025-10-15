/**
 * 🔗 Problem: Critical Connections in a Network (LeetCode #1192)
 *
 * 🧩 Problem Summary:
 * You are given a network of 'n' servers (nodes) numbered from 0 to n-1.
 * They are connected by 'connections' where each connection is an undirected edge.
 * A connection is called a "critical connection" (or bridge) if removing it 
 * disconnects the network (i.e., increases the number of connected components).
 *
 * You need to find all such critical connections in the graph.
 *
 * Example:
 * Input:
 * n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * Output:
 * [[1,3]]
 *
 * 💡 Intuition:
 * If an edge (u, v) is a bridge, then there is no alternative path from v (or its subtree)
 * back to u or any ancestor of u. In other words, v’s subtree cannot reach an earlier
 * discovered vertex than u in the DFS tree.
 *
 * Tarjan’s Algorithm is used to find bridges efficiently using DFS traversal and two arrays:
 *  - disc[u] : Discovery time of vertex u
 *  - low[u]  : The earliest discovered vertex reachable from u (including back edges)
 *
 * 🧠 Approach:
 * 1️⃣ Build an adjacency list from the connections.
 * 2️⃣ Initialize discovery and low-time arrays with -1.
 * 3️⃣ Run DFS traversal:
 *      - Assign discovery and low time.
 *      - Recursively visit neighbors.
 *      - Update low values using children's low values.
 *      - If low[neigh] > disc[curr], then (curr, neigh) is a bridge.
 *
 * ⏱️ Time Complexity: O(V + E)
 * Because every vertex and edge is visited once during DFS traversal.
 *
 * 💾 Space Complexity: O(V + E)
 * For storing adjacency list, recursion stack, and helper arrays.
 *
 * 🧑‍💻 Author: Sahil Sharma
 * 📅 Date: October 2025
 */

import java.util.*;

public class criticalConnections {
    private int time = 0;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // Step 1: Build adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (List<Integer> edge : connections) {
            int src = edge.get(0);
            int dest = edge.get(1);
            graph.get(src).add(dest);
            graph.get(dest).add(src);
        }

        // Step 2: Initialize discovery and low arrays
        int[] disc = new int[n];
        int[] low = new int[n];
        Arrays.fill(disc, -1);

        // Step 3: Store bridges
        List<List<Integer>> bridges = new ArrayList<>();

        // Step 4: DFS for all unvisited nodes
        for (int i = 0; i < n; i++) {
            if (disc[i] == -1) {
                dfs(graph, i, -1, disc, low, bridges);
            }
        }

        return bridges;
    }

    // DFS helper function
    private void dfs(List<List<Integer>> graph, int curr, int parent, int[] disc, int[] low, List<List<Integer>> bridges) {
        disc[curr] = low[curr] = time++;

        for (int neigh : graph.get(curr)) {
            if (neigh == parent) continue; // Skip the edge to parent

            if (disc[neigh] == -1) {
                dfs(graph, neigh, curr, disc, low, bridges);
                low[curr] = Math.min(low[curr], low[neigh]);

                // If there is no back edge from neigh or its subtree to curr or its ancestors
                if (low[neigh] > disc[curr]) {
                    bridges.add(Arrays.asList(curr, neigh));
                }
            } else {
                // Update low[curr] considering back edges
                low[curr] = Math.min(low[curr], disc[neigh]);
            }
        }
    }

    // 🔍 Example run
    public static void main(String[] args) {
        criticalConnections sol = new criticalConnections();
        int n = 4;
        List<List<Integer>> connections = Arrays.asList(
            Arrays.asList(0, 1),
            Arrays.asList(1, 2),
            Arrays.asList(2, 0),
            Arrays.asList(1, 3)
        );

        System.out.println(sol.criticalConnections(n, connections));
        // Output: [[1, 3]]
    }
}
