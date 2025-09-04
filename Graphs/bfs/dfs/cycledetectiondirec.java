/*
------------------------------------------------------
📝 Problem: Detect Cycle in a Directed Graph
------------------------------------------------------
Given a directed graph with V vertices and E edges, 
check whether the graph contains a cycle.

------------------------------------------------------
💡 Intuition:
------------------------------------------------------
A cycle exists in a directed graph if we can start from 
a node and come back to it while following the direction 
of edges. 

To detect cycles, we use **DFS with a recursion stack**:
- `vis[]`: keeps track of whether a node has been visited globally.
- `pathVis[]`: keeps track of whether a node is in the current DFS path (recursion stack).
- If during DFS we reach a node that is already in the current path (`pathVis[node] == 1`), 
  then we found a cycle.

------------------------------------------------------
⚙️ Approach:
------------------------------------------------------
1. Build adjacency list from the edges.
2. For each unvisited node, run DFS.
3. In DFS:
   - Mark node as visited (`vis[node] = 1`) and put it in the recursion stack (`pathVis[node] = 1`).
   - Traverse all neighbors:
       - If neighbor not visited → DFS on neighbor.
       - If neighbor already in recursion stack → cycle found.
   - After exploring neighbors, remove the node from recursion stack (`pathVis[node] = 0`).
4. If any DFS returns true → cycle exists.
5. Otherwise, no cycle.

------------------------------------------------------
⏱️ Time Complexity:
------------------------------------------------------
O(V + E)
- Each node is visited once.
- Each edge is traversed once in DFS.

------------------------------------------------------
💾 Space Complexity:
------------------------------------------------------
O(V) 
- For `vis[]` and `pathVis[]` arrays.
- O(V) recursion stack in the worst case.

------------------------------------------------------
✅ Example:
------------------------------------------------------
Input:
V = 4, Edges = {{0,1}, {1,2}, {2,3}, {3,1}}

Graph:
0 → 1 → 2 → 3
      ↑     |
      └─────┘

Output:
true (cycle exists)

------------------------------------------------------
*/

import java.util.*;

class Solution {
    public boolean isCyclic(int V, int[][] edges) {
        // Step 1: Build adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            adj.get(edges[i][0]).add(edges[i][1]);
        }

        // Step 2: Initialize visited arrays
        int vis[] = new int[V];
        int pathVis[] = new int[V];

        // Step 3: Run DFS for each unvisited node
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                if (dfs(i, vis, pathVis, adj) == true) {
                    return true;
                }
            }
        }

        return false; // No cycle found
    }

    // DFS helper function
    public boolean dfs(int node, int vis[], int pathVis[], ArrayList<ArrayList<Integer>> adj) {
        vis[node] = 1;
        pathVis[node] = 1;

        for (int neigh : adj.get(node)) {
            if (vis[neigh] == 0) {
                if (dfs(neigh, vis, pathVis, adj) == true) {
                    return true;
                }
            } else if (pathVis[neigh] == 1) {
                return true; // cycle detected
            }
        }

        pathVis[node] = 0; // backtrack
        return false;
    }

    // Driver code to test the solution
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        int V = 4;
        int[][] edges = {
            {0, 1}, {1, 2}, {2, 3}, {3, 1}
        };

        System.out.println(sol.isCyclic(V, edges)); // Output: true
    }
}
