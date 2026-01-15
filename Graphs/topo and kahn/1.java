/*
------------------------------------------------------
📝 Problem: Topological Sort of a Directed Acyclic Graph (DAG)
------------------------------------------------------
Given a Directed Acyclic Graph (DAG) with V vertices and E edges, 
return a valid topological ordering of the graph.

------------------------------------------------------
💡 Intuition:
------------------------------------------------------
Topological sort gives a linear ordering of nodes such that 
for every directed edge u → v, u appears before v in the ordering.

Think of it as a **task scheduler**:
- You must finish prerequisites before the dependent task.
- Example: Study "Math 101" before "Math 201".

There are two main approaches:

1️⃣ DFS Based:
- Do a DFS traversal.
- A node is pushed into the ordering only **after exploring all its neighbors**.
- Reverse the order at the end → gives valid topo sort.

2️⃣ Kahn’s Algorithm (BFS Based):
- Count in-degrees (number of prerequisites) for each node.
- Start with nodes having in-degree = 0 (no prerequisites).
- Remove them from the graph, reduce in-degrees of neighbors.
- Continue until all nodes are processed.
- If some nodes remain (cycle exists) → topo sort not possible.

------------------------------------------------------
⚙️ Approach (DFS Method here):
------------------------------------------------------
1. Build adjacency list.
2. Maintain a visited array.
3. For each unvisited node, run DFS:
   - Visit all neighbors.
   - After finishing neighbors, add the node to a stack.
4. Reverse stack → gives topological order.

------------------------------------------------------
⏱️ Time Complexity:
------------------------------------------------------
O(V + E) 
- Each node and edge is processed once.

------------------------------------------------------
💾 Space Complexity:
------------------------------------------------------
O(V)
- For recursion stack (DFS) or queue (BFS).
- O(V) for result list.

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

class TopologicalSort {
    // Function to perform Topological Sort using DFS
    public int[] topoSort(int V, int[][] edges) {
        // Step 1: Build adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
        }

        // Step 2: Visited array and stack
        boolean[] vis = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        // Step 3: Run DFS for each unvisited node
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfs(i, vis, adj, stack);
            }
        }

        // Step 4: Pop from stack to get topo order
        int[] topo = new int[V];
        int idx = 0;
        while (!stack.isEmpty()) {
            topo[idx++] = stack.pop();
        }
        return topo;
    }

    // DFS helper
    private void dfs(int node, boolean[] vis, ArrayList<ArrayList<Integer>> adj, Stack<Integer> stack) {
        vis[node] = true;
        for (int neigh : adj.get(node)) {
            if (!vis[neigh]) {
                dfs(neigh, vis, adj, stack);
            }
        }
        stack.push(node); // add after visiting neighbors
    }

    // Driver code to test
    public static void main(String[] args) {
        TopologicalSort ts = new TopologicalSort();

        int V = 6;
        int[][] edges = {
            {5, 2}, {5, 0}, {4, 0}, {4, 1}, {2, 3}, {3, 1}
        };

        int[] result = ts.topoSort(V, edges);
        System.out.println("Topological Sort: " + Arrays.toString(result));
    }
}
