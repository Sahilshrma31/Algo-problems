/*
------------------------------------------------------
📝 Problem: Topological Sort of a Directed Acyclic Graph (DAG)
------------------------------------------------------
Given a Directed Acyclic Graph (DAG) with V vertices and E edges, 
return a valid topological ordering of the graph.

------------------------------------------------------
💡 Intuition:
------------------------------------------------------
Kahn’s Algorithm uses **in-degree** (number of incoming edges) 
to decide the order:
- Nodes with in-degree = 0 (no prerequisites) can be placed first.
- Once placed, remove them from the graph (reduce in-degree of neighbors).
- Repeat until all nodes are placed.
- If some nodes remain with nonzero in-degree → graph contains a cycle 
  (so topo sort is not possible).

This is just like scheduling tasks:  
Do tasks without dependencies first, then unlock dependent tasks.

------------------------------------------------------
⚙️ Approach (Kahn’s Algorithm - BFS):
------------------------------------------------------
1. Build adjacency list.
2. Compute in-degree for each node.
3. Push all nodes with in-degree = 0 into a queue.
4. While queue not empty:
   - Pop a node, add it to topo order.
   - Decrease in-degree of its neighbors.
   - If neighbor’s in-degree becomes 0, push it to queue.
5. At the end:
   - If topo order has all V nodes → valid topo sort.
   - Otherwise → cycle exists.

------------------------------------------------------
⏱️ Time Complexity:
------------------------------------------------------
O(V + E)  
- Each node visited once, each edge processed once.

------------------------------------------------------
💾 Space Complexity:
------------------------------------------------------
O(V + E)  
- Adjacency list + in-degree array + queue.

------------------------------------------------------
✅ Example:
------------------------------------------------------
Input:
V = 6, Edges = {{5,2}, {5,0}, {4,0}, {4,1}, {2,3}, {3,1}}

Graph:
5 → 2 → 3 → 1
↓         ↑
0         4 → 1

Output (one valid order):
5 4 2 3 1 0

------------------------------------------------------
*/

import java.util.*;

class TopologicalSortKahn {
    public int[] topoSort(int V, int[][] edges) {
        // Step 1: Build adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
        }

        // Step 2: Compute in-degrees
        int[] indegree = new int[V];
        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) {
                indegree[v]++;
            }
        }

        // Step 3: Initialize queue with all in-degree = 0 nodes
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        // Step 4: Process nodes
        int[] topo = new int[V];
        int idx = 0;

        while (!q.isEmpty()) {
            int node = q.poll();
            topo[idx++] = node;

            for (int neigh : adj.get(node)) {
                indegree[neigh]--;
                if (indegree[neigh] == 0) {
                    q.add(neigh);
                }
            }
        }

        // If graph has a cycle, topo sort not possible
        if (idx != V) {
            throw new RuntimeException("Graph contains a cycle, topological sort not possible");
        }

        return topo;
    }

    // Driver code
    public static void main(String[] args) {
        TopologicalSortKahn ts = new TopologicalSortKahn();

        int V = 6;
        int[][] edges = {
            {5, 2}, {5, 0}, {4, 0}, {4, 1}, {2, 3}, {3, 1}
        };

        int[] result = ts.topoSort(V, edges);
        System.out.println("Topological Sort (Kahn's Algorithm): " + Arrays.toString(result));
    }
}
