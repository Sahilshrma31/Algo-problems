import java.util.*;

/*
 * Problem Summary:
 * ----------------
 * We need to detect if a Directed Graph contains a cycle using Topological Sort.
 * A Topological Sort (TopoSort) is only possible for Directed Acyclic Graphs (DAGs).
 * So, if during TopoSort we cannot include all nodes, then a cycle must exist.
 *
 * Intuition:
 * ----------
 * - In Kahn's Algorithm, we start with nodes having indegree = 0 (no incoming edges).
 * - If the graph has a cycle, then no such node will be left to process at some point,
 *   leaving unvisited nodes.
 * - Hence, if the number of nodes we process < total nodes, then a cycle exists.
 *
 * Time Complexity: O(V + E)
 * - We process every vertex and edge once.
 *
 * Space Complexity: O(V + E)
 * - For adjacency list + indegree array + queue.
 */

public class CycleDetectionTopoSort {

    // Function to detect cycle using Kahn's Algorithm
    public static boolean hasCycle(int n, List<List<Integer>> adj) {
        int[] indegree = new int[n];

        // Step 1: Calculate indegree of all nodes
        for (int u = 0; u < n; u++) {
            for (int v : adj.get(u)) {
                indegree[v]++;
            }
        }

        // Step 2: Add all nodes with indegree 0 into queue
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) q.add(i);
        }

        // Step 3: Process nodes
        int count = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            count++;

            for (int neighbor : adj.get(node)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) q.add(neighbor);
            }
        }

        // Step 4: If count != number of nodes, cycle exists
        return count != n;
    }

    public static void main(String[] args) {
        // Example Usage:
        int n = 4;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        // Graph: 0 -> 1 -> 2 -> 3 -> 1 (cycle)
        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(1);

        boolean cycle = hasCycle(n, adj);
        System.out.println(cycle ? "Cycle Exists" : "No Cycle");
    }
}
