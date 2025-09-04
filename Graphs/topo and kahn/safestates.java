/*
LeetCode 802. Find Eventual Safe States

-------------------------------------------------
Problem Statement:
-------------------------------------------------
A directed graph of n nodes is given where each node is labeled from 0 to n-1.  
The graph is represented by a 2D integer array graph where graph[i] is a list of nodes that node i points to.

A node is a terminal node if it has no outgoing edges.  
A node is a safe node if every possible path starting from that node leads to a terminal node (not a cycle).  

Return an array containing all the eventual safe nodes sorted in increasing order.

-------------------------------------------------
Example:
-------------------------------------------------
Input:
graph = [[1,2],[2,3],[5],[0],[5],[]]

Output:
[2,4,5]

Explanation:
- Node 2 → 5 (terminal) ✅ safe  
- Node 4 → 5 (terminal) ✅ safe  
- Node 5 (terminal) ✅ safe  
- Nodes 0,1,3 are part of or lead to a cycle ❌ unsafe  

-------------------------------------------------
Intuition:
-------------------------------------------------
- Safe nodes are those that do NOT end up in a cycle.
- Terminal nodes (outdegree = 0) are always safe.
- If a node leads only to safe nodes, then it is also safe.

But it is tricky to propagate safety in the original graph because terminals are sinks.
👉 Solution: Reverse the graph so that terminals become sources (indegree = 0).

Now, we can run Kahn’s Algorithm (BFS Topological Sort):
1. Start with all nodes with indegree = 0 (terminal nodes).
2. Process them and reduce indegrees of their neighbors.
3. If a neighbor’s indegree becomes 0 → it is safe.
4. Continue until all safe nodes are found.

At the end, only nodes in cycles remain unprocessed.

-------------------------------------------------
Time Complexity:
-------------------------------------------------
- Building the reverse graph: O(V + E)
- BFS processing: O(V + E)
- Sorting result: O(V log V)
Total: O(V + E + V log V)

Space Complexity:
-------------------------------------------------
- Reverse adjacency list: O(V + E)
- Indegree array + Queue + Result list: O(V)
Total: O(V + E)
*/

import java.util.*;

class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;

        // Build reverse graph
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        int indegree[] = new int[n];
        for (int i = 0; i < n; i++) {
            for (int neigh : graph[i]) {
                adj.get(neigh).add(i); // reverse edge
                indegree[i]++;
            }
        }

        // Kahn's BFS
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        List<Integer> res = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.poll();
            res.add(node);
            for (int neigh : adj.get(node)) {
                indegree[neigh]--;
                if (indegree[neigh] == 0) {
                    q.add(neigh);
                }
            }
        }

        Collections.sort(res); // required as output should be in increasing order
        return res;
    }
}

// ----------------------------
// Testcases
// ----------------------------
class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[][] graph1 = {{1,2},{2,3},{5},{0},{5},{}};
        System.out.println(sol.eventualSafeNodes(graph1)); // [2,4,5]

        int[][] graph2 = {{1,2,3,4},{1,2},{3,4},{0,4},{}};
        System.out.println(sol.eventualSafeNodes(graph2)); // [4]
    }
}
