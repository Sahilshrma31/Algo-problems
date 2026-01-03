/*
====================================================
📌 Problem: Smallest Equivalent String (LeetCode 1061)
====================================================

You are given two strings s1 and s2 of the same length.
Each character s1[i] is equivalent to s2[i].

Equivalence is transitive:
a ~ b and b ~ c ⇒ a ~ c

Given another string baseStr, replace each character
with the lexicographically smallest equivalent character.

----------------------------------------------------
🧠 Approach: Graph + DFS (Striver Style)
----------------------------------------------------
• Treat characters 'a'–'z' as nodes (0–25)
• Build an undirected graph using s1 and s2
• For each character in baseStr:
  - Run DFS to find the smallest reachable character
• Append it to the answer

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
Let:
n = length of s1 (and s2)
m = length of baseStr

Graph creation: O(n)
DFS per character: O(26)
Total: O(n + 26 * m) ≈ O(n + m)

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
Adjacency list: O(26)
Visited array: O(26)
DFS stack: O(26)

====================================================
*/

import java.util.*;

class Solution {

    // Adjacency list for characters a-z
    List<Integer>[] adj = new ArrayList[26];

    public String smallestEquivalentString(String s1, String s2, String baseStr) {

        // Initialize graph
        for (int i = 0; i < 26; i++) {
            adj[i] = new ArrayList<>();
        }

        // Build undirected graph from s1 and s2
        for (int i = 0; i < s1.length(); i++) {
            int u = s1.charAt(i) - 'a';
            int v = s2.charAt(i) - 'a';
            adj[u].add(v);
            adj[v].add(u);
        }

        StringBuilder result = new StringBuilder();

        // For each character in baseStr, find smallest equivalent
        for (char ch : baseStr.toCharArray()) {
            boolean[] visited = new boolean[26];
            char minChar = dfs(ch - 'a', ch, visited);
            result.append(minChar);
        }

        return result.toString();
    }

    // DFS to find lexicographically smallest character in component
    private char dfs(int node, char minChar, boolean[] visited) {
        visited[node] = true;
        minChar = (char) Math.min(minChar, node + 'a');

        for (int neighbor : adj[node]) {
            if (!visited[neighbor]) {
                minChar = dfs(neighbor, minChar, visited);
            }
        }

        return minChar;
    }
}
