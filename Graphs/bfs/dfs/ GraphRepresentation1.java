package Graphs;
import java.util.Scanner;

class GraphRepresentation1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // take n and m
        int n = sc.nextInt(); // number of vertices
        int m = sc.nextInt(); // number of edges

        // adjacency matrix (1-indexed, so use n+1)
        int[][] adjMatrix = new int[n + 1][m + 1];

        // take m edges
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            adjMatrix[u][v] = 1;
            adjMatrix[v][u] = 1; // because undirected
        }

        // print adjacency matrix
        System.out.println("Adjacency Matrix:");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }

        sc.close();
    }
}

//input pre defined 
class GraphRepresentation {
    public static void main(String[] args) {
        int n = 5; // number of vertices
        int m = 6; // number of edges

        // adjacency matrix (1-indexed, so use n+1)
        int[][] adjMatrix = new int[n + 1][n + 1];

        // predefined edges (u, v)
        int[][] edges = {
            {1, 2},
            {1, 5},
            {2, 3},
            {2, 4},
            {3, 4},
            {4, 5}
        };

        // build the adjacency matrix
        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            adjMatrix[u][v] = 1;
            adjMatrix[v][u] = 1; // undirected
        }

        // print adjacency matrix
        System.out.println("Adjacency Matrix:");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}


