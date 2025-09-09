// File: Solution.java
// Problem: Number of Ways to Arrive at Destination (LeetCode 1976)
//
// 🔹 Problem Summary:
// You are given a weighted undirected graph with `n` nodes and `roads` edges.
// Each road has (u, v, time). You start at node 0 and want to reach node n-1
// in the shortest possible time. Return the number of different shortest paths
// modulo 1e9+7.
//
// 🔹 Intuition:
// - This is a shortest path counting problem.
// - We need to find the shortest time from 0 → (n-1) and count how many
//   distinct paths achieve that minimum time.
// - Standard Dijkstra finds shortest distances. Here, we extend it:
//   If we find a shorter path → update distance & copy path count.
//   If we find an equally short path → add path counts.
//
// 🔹 Approach:
// 1. Build adjacency list for the graph.
// 2. Use Dijkstra’s algorithm with a priority queue.
// 3. Maintain:
//    - `dist[]`: shortest distance from source to each node.
//    - `ways[]`: number of shortest paths to each node.
// 4. When relaxing edges:
//    - If `newDist < dist[v]`, update distance & set ways[v] = ways[u].
//    - If `newDist == dist[v]`, add ways[u] into ways[v].
// 5. Answer = ways[n-1] % (1e9+7).
//
// 🔹 Time Complexity:
// - Building adjacency list: O(E)
// - Dijkstra with PQ: O((V + E) log V)
// Overall: O(E log V)
//
// 🔹 Space Complexity:
// - Adjacency list: O(V + E)
// - Distance + ways array: O(V)
// - Priority queue: O(V)
// Overall: O(V + E)

import java.util.*;

public class Solution {
    class Pair {
        int node;
        int weight;
        Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    class State {
        long dist;
        int node;
        State(long dist, int node) {
            this.dist = dist;
            this.node = node;
        }
    }

    public int countPaths(int n, int[][] roads) {
        int mod = (int)1e9 + 7;

        // Build adjacency list
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] r : roads) {
            int u = r[0], v = r[1], wt = r[2];
            adj.get(u).add(new Pair(v, wt));
            adj.get(v).add(new Pair(u, wt));
        }

        // Dijkstra setup
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        int[] ways = new int[n];
        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));

        dist[0] = 0;
        ways[0] = 1;
        pq.add(new State(0, 0));

        while (!pq.isEmpty()) {
            State curr = pq.poll();
            long d = curr.dist;
            int node = curr.node;

            // Skip outdated states
            if (d > dist[node]) continue;

            for (Pair edge : adj.get(node)) {
                int neigh = edge.node;
                long newDist = d + edge.weight;

                if (newDist < dist[neigh]) {
                    dist[neigh] = newDist;
                    ways[neigh] = ways[node];
                    pq.add(new State(newDist, neigh));
                } else if (newDist == dist[neigh]) {
                    ways[neigh] = (ways[neigh] + ways[node]) % mod;
                }
            }
        }

        return ways[n - 1] % mod;
    }
}
