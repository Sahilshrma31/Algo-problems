/*
Minimum Spanning Tree (Prim's Algorithm)
------------------------------------------------------------

📌 Problem Statement:
Given an undirected, connected, weighted graph with V vertices and E edges, 
find the weight of the Minimum Spanning Tree (MST).

MST is a subset of edges that connects all vertices with minimum total edge weight,
without forming any cycles.

------------------------------------------------------------

💡 Intuition:
We use **Prim’s Algorithm**:
- Start from an arbitrary node (say node 0).
- Use a min-heap (priority queue) to always pick the smallest edge leading to an unvisited node.
- Keep adding edges until all nodes are included in the MST.
- Sum of these chosen edges = weight of MST.

------------------------------------------------------------

🛠️ Approach:
1. Build an adjacency list for the graph.
2. Use a min-heap storing `{weight, node}`.
3. Start from node 0, push `{0, 0}` into heap.
4. While heap is not empty:
   - Pop the smallest weight edge.
   - If node already visited → skip.
   - Otherwise, mark visited, add edge weight to MST sum.
   - Push all adjacent edges into heap if neighbor not visited.
5. Return total MST weight.

------------------------------------------------------------

⏱️ Time Complexity:
- Building adjacency list: O(E)
- Each edge pushed/popped from heap at most once: O(E log E)
- Overall: **O(E log V)**

💾 Space Complexity:
- Adjacency list: O(V + E)
- Visited array + heap: O(V + E)
- Overall: **O(V + E)**

------------------------------------------------------------
*/

import java.util.*;

class Solution {
    public int spanningTree(int V, int[][] edges) {
        // Step 1: Build adjacency list
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], wt = edge[2];
            adj.get(u).add(new int[]{v, wt});
            adj.get(v).add(new int[]{u, wt});
        }

        // Step 2: Min-heap (weight, node)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[]{0, 0}); // (weight=0, start node=0)

        // Step 3: MST construction
        boolean[] vis = new boolean[V];
        int sum = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int wt = curr[0];
            int node = curr[1];

            if (vis[node]) continue;
            vis[node] = true;
            sum += wt;

            for (int[] neigh : adj.get(node)) {
                int adjNode = neigh[0];
                int edgeWt = neigh[1];
                if (!vis[adjNode]) {
                    pq.add(new int[]{edgeWt, adjNode});
                }
            }
        }

        return sum;
    }

    // Example usage
    public static void main(String[] args) {
        int V = 5;
        int[][] edges = {
            {0, 1, 2}, {0, 3, 6}, {1, 2, 3}, 
            {1, 3, 8}, {1, 4, 5}, {2, 4, 7}, {3, 4, 9}
        };

        Solution sol = new Solution();
        int mstWeight = sol.spanningTree(V, edges);
        System.out.println("Weight of MST = " + mstWeight);
    }
}
