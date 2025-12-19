/*
====================================================
📌 Problem: Path with Maximum Probability
(LeetCode 1514)
====================================================

You are given an undirected graph with n nodes.
Each edge has a success probability.

You are given:
- n nodes (0 to n-1)
- edges[i] = [u, v]
- succProb[i] = probability of success for edge i
- start_node
- end_node

Find the maximum probability of reaching end_node
from start_node.

If no path exists, return 0.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
This is a graph shortest-path–like problem, but instead
of minimizing distance, we want to MAXIMIZE probability.

Key idea:
- Probabilities multiply along a path
- We want the path with the maximum product

This is a perfect fit for:
👉 Dijkstra’s Algorithm (modified for maximum probability)

----------------------------------------------------
🧩 Approach (Dijkstra – Max Heap)
----------------------------------------------------
1️⃣ Build adjacency list with (neighbor, probability)
2️⃣ Maintain an array `result[]` where:
    result[i] = maximum probability to reach node i
3️⃣ Use a max-heap (priority queue) based on probability
4️⃣ Start from `start_node` with probability = 1
5️⃣ Relax edges:
    if (currentProb * edgeProb > storedProb)
        update and push into PQ
6️⃣ Return result[end_node]

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(E log V)

E = number of edges  
V = number of vertices  

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(V + E)
- Adjacency list
- Priority queue
- Probability array

====================================================
*/

import java.util.*;

class Solution {

    static class Pair {
        int node;
        double prob;

        Pair(int node, double prob) {
            this.node = node;
            this.prob = prob;
        }
    }

    public double maxProbability(
            int n,
            int[][] edges,
            double[] succProb,
            int start_node,
            int end_node
    ) {

        // Step 1: Build adjacency list
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            double prob = succProb[i];

            adj.get(u).add(new Pair(v, prob));
            adj.get(v).add(new Pair(u, prob));
        }

        // Step 2: Probability array
        double[] result = new double[n];
        result[start_node] = 1.0;

        // Step 3: Max-heap (priority queue)
        PriorityQueue<Pair> pq = new PriorityQueue<>(
                (a, b) -> Double.compare(b.prob, a.prob)
        );

        pq.offer(new Pair(start_node, 1.0));

        // Step 4: Dijkstra
        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int currNode = curr.node;
            double currProb = curr.prob;

            for (Pair neigh : adj.get(currNode)) {
                double newProb = currProb * neigh.prob;

                if (newProb > result[neigh.node]) {
                    result[neigh.node] = newProb;
                    pq.offer(new Pair(neigh.node, newProb));
                }
            }
        }

        return result[end_node];
    }
}
