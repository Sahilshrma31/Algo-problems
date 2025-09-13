// **Disjoint Set Union (DSU)**, also known as **Union-Find**, is a data structure that keeps track of elements partitioned into multiple disjoint (non-overlapping) sets.

// It supports two main operations:

// - **Find(x)** → Find representative (parent) of the set `x` belongs to.
// - **Union(x, y)** → Merge sets that contain `x` and `y`.


class DSU {
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

