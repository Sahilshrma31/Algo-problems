/*
🔹 Problem: Minimum Spanning Tree (MST) using Kruskal's Algorithm
-----------------------------------------------------------------
We are given a graph with `V` vertices and `E` edges, where each edge has a weight.
We want to construct a Minimum Spanning Tree (MST):
    - A subset of edges that connects all vertices with the minimum possible total weight.
    - MST has exactly V-1 edges if the graph is connected.

---

🔹 Intuition:
- MST ensures all vertices are connected with minimum cost and no cycles.
- Kruskal’s Algorithm is a greedy approach:
    1. Sort all edges by weight.
    2. Pick the smallest edge that doesn’t form a cycle.
    3. Repeat until we have V-1 edges.

- To efficiently detect cycles, we use **Disjoint Set Union (DSU)** with:
    - Path compression (optimizes `find`)
    - Union by rank (balances tree height)

---

🔹 Approach:
1. Create a list of all edges.
2. Sort edges by weight.
3. Initialize DSU for `V` nodes.
4. Iterate over sorted edges:
   - If endpoints belong to different sets, include the edge in MST.
   - Otherwise, skip (to avoid cycles).
5. Stop when we have V-1 edges.
6. Return the total MST weight.

---

🔹 Time Complexity:
- Sorting edges: O(E log E)
- DSU operations (find + union) for each edge: O(α(V)) ≈ O(1)  
  (where α is inverse Ackermann function, very small)  
- Total: **O(E log E)**

🔹 Space Complexity:
- Edge list: O(E)
- DSU arrays (parent, rank): O(V)
- Overall: **O(V + E)**

---

🔹 Example:
Input:
V = 4, Edges = [[0,1,10], [0,2,6], [0,3,5], [1,3,15], [2,3,4]]

Output:
MST weight = 19

Explanation:
Edges chosen: (2,3)=4, (0,3)=5, (0,1)=10
Total = 19

---

*/

import java.util.*;

class Solution {
    
    // Edge class for graph edges
    static class Edge implements Comparable<Edge> {
        int u, v, wt;
        
        Edge(int u, int v, int wt) {
            this.u = u;
            this.v = v;
            this.wt = wt;
        }
        
        // Sort edges by weight
        public int compareTo(Edge other) {
            return this.wt - other.wt;
        }
    }
    
    // Disjoint Set Union (DSU) for cycle detection
    static class DSU {
        int[] parent, rank;
        
        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }
        
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }
        
        boolean union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            
            if (pa == pb) return false; // already connected
            
            if (rank[pa] > rank[pb]) {
                parent[pb] = pa;
            } else if (rank[pb] > rank[pa]) {
                parent[pa] = pb;
            } else {
                parent[pb] = pa;
                rank[pa]++;
            }
            return true;
        }
    }
    
    // Function to compute MST weight using Kruskal's Algorithm
    static int kruskalsMST(int V, int[][] edges) {
        // Step 1: Build edge list
        ArrayList<Edge> edgeList = new ArrayList<>();
        for (int[] e : edges) {
            edgeList.add(new Edge(e[0], e[1], e[2]));
        }
        
        // Step 2: Sort edges by weight
        Collections.sort(edgeList);
        
        // Step 3: Initialize DSU
        DSU dsu = new DSU(V);
        
        // Step 4: Build MST
        int mstWeight = 0;
        int count = 0;
        
        for (Edge e : edgeList) {
            if (dsu.union(e.u, e.v)) {
                mstWeight += e.wt;
                count++;
                if (count == V - 1) break; // MST complete
            }
        }
        
        return mstWeight;
    }

    // Example run
    public static void main(String[] args) {
        int V = 4;
        int[][] edges = {
            {0, 1, 10}, {0, 2, 6}, {0, 3, 5}, {1, 3, 15}, {2, 3, 4}
        };
        
        System.out.println("MST Weight = " + kruskalsMST(V, edges));
    }
}
