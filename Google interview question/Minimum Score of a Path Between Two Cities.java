/**
 * LeetCode Problem: 2492. Minimum Score of a Path Between Two Cities
 * -----------------------------------------------------------------
 *
 * Problem Summary:
 * There are `n` cities numbered from 1 to n and a list of bidirectional roads.
 * Each road is represented as [u, v, distance].
 *
 * The score of a path is defined as the minimum distance of any road
 * along that path.
 *
 * The goal is to find the minimum possible score of a path
 * from city 1 to city n.
 *
 * It is guaranteed that there is at least one path between city 1 and city n.
 *
 * -----------------------------------------------------------------
 * Intuition:
 *
 * Any path from city 1 to city n must stay inside the connected component
 * that contains city 1.
 *
 * The minimum score possible is simply the minimum edge weight
 * present anywhere in that connected component.
 *
 * Why?
 * Because we can always choose a path that goes through the smallest edge
 * as long as it lies in the same connected component.
 *
 * So the task reduces to:
 * - Traverse all cities reachable from city 1
 * - Track the minimum edge weight encountered
 *
 * -----------------------------------------------------------------
 * Approach:
 *
 * 1. Build an adjacency list for the graph
 * 2. Perform a DFS starting from city 1
 * 3. During traversal:
 *    - Mark cities as visited
 *    - For every edge encountered, update the minimum distance
 * 4. After DFS completes, the stored minimum edge value is the answer
 *
 * -----------------------------------------------------------------
 * Time Complexity:
 *
 * - O(n + m)
 *   where:
 *   - n = number of cities
 *   - m = number of roads
 *
 * Each city and road is visited at most once during DFS.
 *
 * -----------------------------------------------------------------
 * Space Complexity:
 *
 * - O(n + m) for adjacency list
 * - O(n) for visited array and recursion stack
 *
 * -----------------------------------------------------------------
 * Author: Sahil Sharma
 */

import java.util.ArrayList;
import java.util.List;

class Solution {

    public int minScore(int n, int[][] roads) {

        // Build adjacency list: graph[city] -> {neighbor, distance}
        List<int[]>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Add bidirectional roads
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int dist = road[2];

            graph[u].add(new int[]{v, dist});
            graph[v].add(new int[]{u, dist});
        }

        boolean[] visited = new boolean[n + 1];

        // Using array to allow updates inside DFS
        int[] minEdge = new int[]{Integer.MAX_VALUE};

        // Start DFS from city 1
        dfs(1, graph, visited, minEdge);

        return minEdge[0];
    }

    private void dfs(int node, List<int[]>[] graph, boolean[] visited, int[] minEdge) {
        visited[node] = true;

        for (int[] edge : graph[node]) {
            int neighbor = edge[0];
            int distance = edge[1];

            // Update minimum edge seen so far
            minEdge[0] = Math.min(minEdge[0], distance);

            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited, minEdge);
            }
        }
    }
}

