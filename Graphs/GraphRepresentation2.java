package Graphs;
import java.util.*;

class GraphRepresentation {
    // Pair class to store (node, weight) for weighted graphs
    static class Pair {
        int node, weight;
        Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        int n = 5; // number of vertices
        int m = 6; // number of edges

        // predefined edges (u, v)
        int[][] edges = {
            {1, 2},
            {1, 5},
            {2, 3},
            {2, 4},
            {3, 4},
            {4, 5}
        };

        // predefined weighted edges (u, v, w)
        int[][] weightedEdges = {
            {1, 2, 4},
            {1, 5, 2},
            {2, 3, 7},
            {2, 4, 1},
            {3, 4, 3},
            {4, 5, 5}
        };

        // ---------------- Unweighted Undirected ----------------
        ArrayList<ArrayList<Integer>> undirectedAdj = new ArrayList<>();
        for (int i = 0; i <= n; i++) undirectedAdj.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int u = edges[i][0], v = edges[i][1];
            undirectedAdj.get(u).add(v);
            undirectedAdj.get(v).add(u); // both sides
        }

        System.out.println("Adjacency List for Unweighted Undirected Graph:");
        for (int i = 1; i <= n; i++) {
            System.out.print(i + " -> ");
            for (int node : undirectedAdj.get(i)) System.out.print(node + " ");
            System.out.println();
        }

        // ---------------- Unweighted Directed ----------------
        ArrayList<ArrayList<Integer>> directedAdj = new ArrayList<>();
        for (int i = 0; i <= n; i++) directedAdj.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int u = edges[i][0], v = edges[i][1];
            directedAdj.get(u).add(v); // only one direction
        }

        System.out.println("\nAdjacency List for Unweighted Directed Graph:");
        for (int i = 1; i <= n; i++) {
            System.out.print(i + " -> ");
            for (int node : directedAdj.get(i)) System.out.print(node + " ");
            System.out.println();
        }

        // ---------------- Weighted Undirected ----------------
        ArrayList<ArrayList<Pair>> weightedUndirectedAdj = new ArrayList<>();
        for (int i = 0; i <= n; i++) weightedUndirectedAdj.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int u = weightedEdges[i][0], v = weightedEdges[i][1], w = weightedEdges[i][2];
            weightedUndirectedAdj.get(u).add(new Pair(v, w));
            weightedUndirectedAdj.get(v).add(new Pair(u, w)); // both sides
        }

        System.out.println("\nAdjacency List for Weighted Undirected Graph:");
        for (int i = 1; i <= n; i++) {
            System.out.print(i + " -> ");
            for (Pair p : weightedUndirectedAdj.get(i)) System.out.print("(" + p.node + "," + p.weight + ") ");
            System.out.println();
        }

        // ---------------- Weighted Directed ----------------
        ArrayList<ArrayList<Pair>> weightedDirectedAdj = new ArrayList<>();
        for (int i = 0; i <= n; i++) weightedDirectedAdj.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int u = weightedEdges[i][0], v = weightedEdges[i][1], w = weightedEdges[i][2];
            weightedDirectedAdj.get(u).add(new Pair(v, w)); // only one direction
        }

        System.out.println("\nAdjacency List for Weighted Directed Graph:");
        for (int i = 1; i <= n; i++) {
            System.out.print(i + " -> ");
            for (Pair p : weightedDirectedAdj.get(i)) System.out.print("(" + p.node + "," + p.weight + ") ");
            System.out.println();
        }
    }
}
