// Problem: Detect cycle in an undirected graph
// Source: GFG
// Given V vertices and E edges, check whether the graph contains a cycle.
// Graph may be disconnected, so check all components.
//
// Intuition:
// In an undirected graph, a cycle exists if during BFS traversal
// we encounter a visited node that is NOT the parent of the current node.
// We keep track of each node's parent in the BFS queue to avoid
// mistaking the bidirectional edge for a cycle.
//
// Approach (BFS):
// 1. Build adjacency list from edge list.
// 2. Traverse each component using BFS.
// 3. For each node, push (node, parent) into queue.
// 4. If a visited neighbor is not the parent → cycle detected.
// 5. If all BFS traversals end without such a case → no cycle.
//
// Time Complexity: O(V + E) → visiting all vertices and edges once.
// Space Complexity: O(V + E) → adjacency list + visited array + queue.

import java.util.*;

public class CycleDetectionBFS {
    
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
                if (bfs(i, adj, vis)) return true;
            }
        }
        return false;
    }

    private boolean bfs(int src, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{src, -1});
        vis[src] = true;

        while (!q.isEmpty()) {
            int[] pair = q.poll();
            int node = pair[0], parent = pair[1];

            for (int neigh : adj.get(node)) {
                if (!vis[neigh]) {
                    vis[neigh] = true;
                    q.add(new int[]{neigh, node});
                } else if (neigh != parent) {
                    // Found a visited neighbor that is not parent → cycle exists
                    return true;
                }
            }
        }
        return false;
    }

    // Driver code for quick test
    public static void main(String[] args) {
        CycleDetectionBFS obj = new CycleDetectionBFS();
        int V = 5;
        int[][] edges = {
            {0,1}, {1,2}, {2,3}, {3,4}, {4,1}
        };
        System.out.println(obj.isCycle(V, edges)); // true (cycle exists)
    }
}
