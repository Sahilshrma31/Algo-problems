package Graphs;
import java.util.*;

class BFS {
    public static void main(String[] args) {
        int n = 5; // number of vertices
        int m = 6; // number of edges

        // predefined edges (undirected graph)
        int[][] edges = {
            {1, 2},
            {1, 5},
            {2, 3},
            {2, 4},
            {3, 4},
            {4, 5}
        };

        // adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());

        // build graph
        for (int i = 0; i < m; i++) {
            int u = edges[i][0], v = edges[i][1];
            adj.get(u).add(v);
            adj.get(v).add(u); // undirected
        }

        // BFS starting from node 1
        bfs(adj, n, 1);
    }

    public static void bfs(ArrayList<ArrayList<Integer>> adj, int n, int start) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> q = new LinkedList<>();

        visited[start] = true;
        q.add(start);

        System.out.print("BFS Traversal: ");
        while (!q.isEmpty()) {
            int node = q.poll();
            System.out.print(node + " ");

            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.add(neighbor);
                }
            }
        }
    }
}

// Time Complexity
// We visit each vertex once → O(V)
// For each vertex, we traverse all its neighbors → sum of degrees = 2E (for undirected graph) or E (for directed graph).
// So overall:
// TC = O(V + E)

// Space Complexity
// Visited array → O(V)
// Queue → in worst case, can hold all vertices → O(V)
// Adjacency list storage → O(V + E)
// So total:
// SC = O(V + E)