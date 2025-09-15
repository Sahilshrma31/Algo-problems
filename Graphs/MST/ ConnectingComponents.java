/*
🔹 Problem: Connecting Components with Extra Edges
-------------------------------------------------
Given a graph with `n` nodes labeled 0 to n-1 and a list of edges, some edges may be redundant.
We want to connect all components of the graph using the given edges and possibly extra edges.
Return the minimum number of operations required to connect all nodes. If it is impossible, return -1.

---

🔹 Intuition:
- Each connected component can be merged using edges.
- If an edge connects two nodes already in the same component, it is "extra".
- To connect all components, we need at least (number of components - 1) edges.
- If extra edges >= required edges, we can connect all components.

---

🔹 Approach:
1. Initialize DSU (Disjoint Set Union) for `n` nodes.
2. Count extra edges while iterating over the given edges:
    - If two nodes belong to the same set → extra edge.
    - Else → union the two sets.
3. Count connected components by checking nodes which are their own parent.
4. Required edges = connectedComponents - 1
5. If extra >= required edges → return required edges, else return -1.

---

🔹 Time Complexity:
- DSU operations (find + union) with path compression & rank: O(α(n)) ≈ O(1)
- Iterating all edges: O(E)
- Counting connected components: O(n)
- Overall: **O(n + E)**

🔹 Space Complexity:
- DSU arrays: O(n)
- Overall: **O(n)**

*/

import java.util.*;

class Solution {

    // Disjoint Set Union (DSU) class
    public class DSU {
        int[] parent, rank;

        public DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        public int find(int node) {
            if (parent[node] != node)
                parent[node] = find(parent[node]);  // Path compression
            return parent[node];
        }

        public void union(int u, int v) {
            int parentU = find(u);
            int parentV = find(v);

            if (parentU == parentV) return;

            if (rank[parentU] < rank[parentV]) {
                parent[parentU] = parentV;
            } else if (rank[parentU] > rank[parentV]) {
                parent[parentV] = parentU;
            } else {
                parent[parentV] = parentU;
                rank[parentU]++;
            }
        }
    }

    // Main function to solve the problem
    public int Solve(int n, int[][] edges) {
        DSU dsu = new DSU(n);
        int extra = 0;

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];

            if (dsu.find(u) == dsu.find(v)) { // Edge is redundant
                extra++;
            } else {
                dsu.union(u, v);
            }
        }

        // Count connected components
        int cc = 0;
        for (int i = 0; i < n; i++) {
            if (dsu.find(i) == i) {
                cc++;
            }
        }

        int requiredEdges = cc - 1;
        if (extra >= requiredEdges) {
            return requiredEdges;
        }
        return -1;
    }

    // Example Run
    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 6;
        int[][] edges = {{0,1},{0,2},{0,3},{1,2},{1,3}};
        System.out.println("Minimum operations to connect all components: " + sol.Solve(n, edges));
    }
}
