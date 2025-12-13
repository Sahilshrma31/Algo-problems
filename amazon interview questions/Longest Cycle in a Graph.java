/**
 * LeetCode Problem: 2360. Longest Cycle in a Graph
 * -----------------------------------------------
 *
 * Problem Summary:
 * You are given a directed graph with `n` nodes (0 to n-1).
 * Each node has at most one outgoing edge.
 * The array `edges` represents the graph:
 *   - edges[i] = j means there is a directed edge from i → j
 *   - edges[i] = -1 means node i has no outgoing edge
 *
 * The task is to return the length of the longest cycle in the graph.
 * If there is no cycle, return -1.
 *
 * -----------------------------------------------
 * Intuition:
 * Since each node has at most one outgoing edge, the graph consists of
 * chains that may eventually form cycles.
 *
 * We perform DFS traversal:
 * - `visited[]` tracks nodes already processed
 * - `inPath[]` tracks nodes in the current DFS path (recursion stack)
 * - `dist[]` stores the depth of each node in the current DFS
 *
 * If during DFS we reach a node that is already in the current path,
 * we have found a cycle. The cycle length can be computed using
 * the difference in depths.
 *
 * -----------------------------------------------
 * Approach:
 *
 * 1. Iterate through all nodes.
 * 2. If a node is not visited, start DFS from it.
 * 3. During DFS:
 *    - Mark the node as visited and part of the current path.
 *    - Move to the next node using edges[src].
 *    - If an unvisited node is found, continue DFS.
 *    - If a node already in the current path is found, update the answer.
 * 4. Backtrack by removing the node from the current path.
 *
 * -----------------------------------------------
 * Time Complexity:
 * O(n) — Each node is visited at most once.
 *
 * -----------------------------------------------
 * Space Complexity:
 * O(n) — For visited array, recursion stack, and helper arrays.
 *
 * -----------------------------------------------
 * Author: Sahil Sharma
 */

import java.util.Arrays;

class Solution {

    int result = -1;

    public void dfs(int src, int[] edges, boolean[] visited, boolean[] inPath, int[] dist) {

        visited[src] = true;
        inPath[src] = true;

        int next = edges[src];

        if (next != -1 && !visited[next]) {
            dist[next] = dist[src] + 1;
            dfs(next, edges, visited, inPath, dist);
        }
        // Cycle detected
        else if (next != -1 && inPath[next]) {
            int cycleLength = dist[src] - dist[next] + 1;
            result = Math.max(result, cycleLength);
        }

        // Backtracking
        inPath[src] = false;
    }

    public int longestCycle(int[] edges) {

        int n = edges.length;

        boolean[] visited = new boolean[n];
        boolean[] inPath = new boolean[n];
        int[] dist = new int[n];

        // Initialize distance of each new DFS start node to 1
        Arrays.fill(dist, 1);

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, edges, visited, inPath, dist);
            }
        }

        return result;
    }
}
