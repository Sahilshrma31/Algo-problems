package Graphs.bfs.dfs;
import java.util.*;

public class Dfs {
    static void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
        vis[node] = true;
        System.out.print(node + " ");

        for (int neigh : adj.get(node)) {
            if (!vis[neigh]) {
                dfs(neigh, adj, vis);
            }
        }
    }

    public static void main(String[] args) {
        int n = 5; // number of vertices (0 to 4)
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        // example edges
        adj.get(0).add(1);
        adj.get(0).add(2);
        adj.get(1).add(3);
        adj.get(2).add(4);

        boolean[] vis = new boolean[n];
        
        System.out.println("DFS Traversal:");
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                dfs(i, adj, vis);
            }
        }
    }
}

// Time Complexity

// Each vertex is visited once → O(V)
// Each edge is explored once → O(E)
// Total → O(V + E)

// Space Complexity

// Visited array → O(V)
// Recursion stack (in worst case graph is a chain) → O(V)
// Adjacency list storage → O(V + E)
// Total → O(V + E)