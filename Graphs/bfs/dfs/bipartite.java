/*
Problem: Check if a Graph is Bipartite (LeetCode #785)

Problem Summary:
Given an undirected graph, determine if it is bipartite. 
A graph is bipartite if you can split its set of nodes into two sets such that every edge connects a node in one set to a node in the other set.

Input:
- graph: int[][] adjacency list, where graph[i] contains all neighbors of node i.

Output:
- boolean: true if graph is bipartite, false otherwise.

Example:
Input: graph = [[1,3],[0,2],[1,3],[0,2]]
Output: true
Explanation: We can split nodes into two sets: {0,2} and {1,3}.

Intuition:
- Bipartite graph means we can color the graph using 2 colors without conflict.
- If two adjacent nodes ever have the same color, the graph is NOT bipartite.

Approach:
1. Use DFS to traverse the graph.
2. Maintain a color array: 0 = unvisited, 1 = color1, -1 = color2.
3. For each unvisited node, start DFS and assign a color.
4. For each neighbor:
   - If uncolored, assign opposite color and DFS recursively.
   - If already colored and same as current node, return false.
5. If DFS completes without conflicts, graph is bipartite.

Time Complexity:
- O(V + E) where V = number of nodes, E = number of edges.

Space Complexity:
- O(V) for color array + recursion stack.

*/

class Solution {

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n]; // 0 = unvisited, 1 or -1 = color

        // Check each component
        for (int i = 0; i < n; i++) {
            if (color[i] == 0 && !dfs(graph, color, i, 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int[][] graph, int[] color, int node, int c) {
        color[node] = c;

        for (int neighbor : graph[node]) {
            if (color[neighbor] == 0) {
                if (!dfs(graph, color, neighbor, -c)) return false;
            } else if (color[neighbor] == c) {
                return false; // conflict detected
            }
        }
        return true;
    }

    // Example usage
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] graph1 = {{1,3},{0,2},{1,3},{0,2}};
        System.out.println("Graph1 is bipartite? " + sol.isBipartite(graph1)); // true

        int[][] graph2 = {{1,2,3},{0,2},{0,1,3},{0,2}};
        System.out.println("Graph2 is bipartite? " + sol.isBipartite(graph2)); // false
    }
}
