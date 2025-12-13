// File: Solution.java
// LeetCode: 839. Similar String Groups
//
// Problem Summary:
// ----------------
// We are given an array of strings `strs`, where all strings are anagrams of each other
// and have the same length. Two strings `A` and `B` are called "similar" if you can
// swap exactly two characters in `A` (possibly the same positions) and make it equal
// to `B`. In other words, they either differ in exactly 2 positions or in 0 positions.
// We say two strings belong to the same group if they are directly or indirectly
// connected by a chain of similar strings.
// The task is to return the number of connected groups formed by these strings.
//
// Intuition / Approach:
// ---------------------
// 1. Observation about similarity:
//    - Two strings `s1` and `s2` are similar if:
//        * They are exactly the same (diff == 0), OR
//        * They differ in exactly 2 positions (diff == 2).
//    - This works because one swap can fix exactly two mismatched positions.
//
// 2. Build a graph (undirected):
//    - Think of each string as a node with index i (0 to n-1).
//    - For every pair (i, j), if `strs[i]` and `strs[j]` are similar,
//      add an undirected edge between i and j.
//    - We store this graph as an adjacency list:
//          List<Integer>[] adj = new ArrayList[n];
//      where adj[i] contains all neighbors of node i.
//
// 3. Count connected components using DFS:
//    - Use a boolean array `visited` of size n.
//    - For each node i from 0 to n-1:
//         * If not visited, start a DFS from i.
//         * Mark all nodes reachable from i as visited.
//         * Increment `count` (this is one connected component / group).
//    - The final `count` is the number of similar string groups.
//
// Time Complexity:
// ----------------
// Let:
//   - n = number of strings
//   - L = length of each string
//
// 1) Checking similarity between two strings: O(L)
// 2) We compare each pair of strings in the worst case:
//      There are O(n^2) pairs.
// 3) So building the adjacency list costs: O(n^2 * L)
// 4) DFS over all nodes and edges is O(n + number_of_edges) = O(n^2) in worst case,
//    which is dominated by O(n^2 * L).
//
// => Overall Time Complexity: O(n^2 * L)
//
// Space Complexity:
// -----------------
// 1) Adjacency list `adj`: in the worst case the graph can be nearly complete,
//    so edges ~ O(n^2). The adjacency list storage is O(n^2).
// 2) `visited` array: O(n)
// 3) Recursion stack for DFS: O(n) in the worst case.
//
// => Overall Space Complexity: O(n^2) (dominated by the adjacency list).
//

import java.util.*;

class Solution {

    // Check if two strings are similar:
    // Similar if they differ in exactly 0 or 2 positions.
    public boolean isSimilar(String s1, String s2) {
        int len = s1.length();
        int diff = 0;

        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
                // Small optimization: if more than 2 mismatches, they can't be similar
                if (diff > 2) {
                    return false;
                }
            }
        }

        // Valid similarity: either identical (0 diff) or differ in exactly 2 positions
        return diff == 0 || diff == 2;
    }

    // Standard DFS on the graph
    public void dfs(List<Integer>[] adj, boolean[] visited, int src) {
        visited[src] = true;

        for (int neighbor : adj[src]) {
            if (!visited[neighbor]) {
                dfs(adj, visited, neighbor);
            }
        }
    }

    public int numSimilarGroups(String[] strs) {
        int n = strs.length;

        // Create adjacency list for n nodes (0 to n-1)
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        // Build the graph based on pairwise similarity
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isSimilar(strs[i], strs[j])) {
                    // Undirected edge between i and j
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }

        int count = 0;
        boolean[] visited = new boolean[n];

        // Count connected components using DFS
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(adj, visited, i);
                count++; // one new group found
            }
        }

        return count;
    }
}
