import java.util.*;

/*
 * Problem Summary:
 * ----------------
 * Given N courses and a list of prerequisites, determine if it's possible
 * to finish all courses. This is equivalent to checking if the directed graph
 * formed by prerequisites has a cycle.
 *
 * Input Format:
 * -------------
 * - N: number of courses (0 to N-1)
 * - P: number of prerequisite pairs
 * - prerequisites: a list of pairs [a, b] meaning you must complete course b before course a.
 *   So, there is a directed edge b -> a.
 *
 * Output:
 * -------
 * - Return true if it's possible to complete all courses (no cycle exists).
 * - Return false if there is a cycle (not all courses can be finished).
 *
 * Intuition:
 * ----------
 * - Model the courses as a directed graph.
 * - Use Kahn's Algorithm (BFS Topological Sort):
 *   1. Compute indegree of all nodes.
 *   2. Add all nodes with indegree = 0 into a queue.
 *   3. Remove nodes from queue one by one, reducing indegree of their neighbors.
 *   4. If a neighbor's indegree becomes 0, push it into the queue.
 * - If we can process all N nodes, no cycle exists and it is possible to finish
 *   all courses. Otherwise, a cycle exists.
 *
 * Time Complexity: O(V + E)
 * - V = number of courses, E = number of prerequisites.
 * - Each node and edge is processed once.
 *
 * Space Complexity: O(V + E)
 * - For adjacency list, indegree array, and queue.
 *
 * Example 1:
 * ----------
 * N = 3, P = 2
 * prerequisites = [[1,0], [2,1]]
 * Graph: 0 -> 1 -> 2
 * Topological order = [0,1,2]
 * cnt = 3 = N → All courses can be finished → true
 *
 * Example 2:
 * ----------
 * N = 2, P = 2
 * prerequisites = [[0,1], [1,0]]
 * Graph: 0 <-> 1 (cycle)
 * No node will remain with indegree 0 after some steps.
 * cnt < N → Cycle exists → false
 */

class Solution {
    public boolean isPossible(int N, int P, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        // Build adjacency list: edge[1] -> edge[0]
        for (int edge[] : prerequisites) {
            adj.get(edge[1]).add(edge[0]);
        }

        // Filling indegree array
        int indegree[] = new int[N];
        for (int i = 0; i < N; i++) {
            for (int neigh : adj.get(i)) {
                indegree[neigh]++;
            }
        }

        // Add nodes with indegree 0 to queue
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int cnt = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            cnt++;
            for (int neigh : adj.get(node)) {
                indegree[neigh]--;
                if (indegree[neigh] == 0) {
                    q.add(neigh);
                }
            }
        }

        return cnt == N;
    }

    // Example usage with testcases
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1: No cycle
        int N1 = 3, P1 = 2;
        int[][] prerequisites1 = {{1, 0}, {2, 1}}; // 0 -> 1 -> 2
        System.out.println(sol.isPossible(N1, P1, prerequisites1)); // true

        // Example 2: Cycle exists
        int N2 = 2, P2 = 2;
        int[][] prerequisites2 = {{0, 1}, {1, 0}}; // 0 <-> 1
        System.out.println(sol.isPossible(N2, P2, prerequisites2)); // false
    }
}
