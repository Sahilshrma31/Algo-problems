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
import java.util.*;
class Solution {

    private boolean dfs(int node, int col, int[] color,
                        ArrayList<ArrayList<Integer>> adj) {

        color[node] = col;

        for (int it : adj.get(node)) {

            // not colored yet
            if (color[it] == 0) {
                if (!dfs(it, -col, color, adj)) { //  toggle
                    return false;
                }
            }
            // conflict found
            else if (color[it] == col) {
                return false;
            }
        }
        return true;
    }

    public boolean isBipartite(int V,
                               ArrayList<ArrayList<Integer>> adj) {

        int[] color = new int[V]; // 0 = unvisited, 1 / -1 = colors

        // check all components
        for (int i = 0; i < V; i++) {
            if (color[i] == 0) {
                if (!dfs(i, 1, color, adj)) {
                    return false;
                }
            }
        }
        return true;
    }
}

