// Problem: Detect cycle in an undirected graph
// Source: GFG
// Given V vertices and E edges, check whether the graph contains a cycle.
// Graph may be disconnected, so check all components.
//
// Intuition:
// In DFS traversal of an undirected graph, a cycle exists if
// we find a neighbor that is already visited and is NOT the parent of the current node.
//
// Why "not parent"? 
// Because in an undirected graph, every edge appears twice.
// Example: Edge u-v means u has v as neighbor AND v has u as neighbor.
// When we go back to parent, it should not be mistaken as a cycle.
//
// Approach (DFS):
// 1. Build adjacency list from edge list.
// 2. Traverse each component using DFS.
// 3. If during DFS we visit an already visited node that is not parent → cycle exists.
// 4. If all DFS traversals end safely → no cycle.
//
// Time Complexity: O(V + E)
// Space Complexity: O(V + E) adjacency list + visited array + recursion stack

import java.util.*;

public class CycleDetectionDFS {
    
    public boolean isCycle(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        // Build adjacency list
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] vis = new boolean[V];

        // Check all components
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                if (dfs(i, -1, adj, vis)) return true;
            }
        }
        return false;
    }

    private boolean dfs(int node, int parent, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
        vis[node] = true;

        for (int neigh : adj.get(node)) {
            if (!vis[neigh]) {
                if (dfs(neigh, node, adj, vis)) return true;
            } else if (neigh != parent) {
                // If neighbor is visited and not parent → cycle found
                return true;
            }
        }
        return false;
    }

    // Driver code for quick test
    public static void main(String[] args) {
        CycleDetectionDFS obj = new CycleDetectionDFS();
        int V = 5;
        int[][] edges = {
            {0,1}, {1,2}, {2,3}, {3,4}, {4,1}
        };
        System.out.println(obj.isCycle(V, edges)); // true (cycle exists)
    }
}
