/*
Alien Dictionary (GFG)

-------------------------------------------------
Problem Statement:
-------------------------------------------------
Given a sorted dictionary of an alien language having N words and k starting alphabets of standard dictionary.
Find the order of characters in the alien language.

-------------------------------------------------
Example:
-------------------------------------------------
Input: 
N = 5, K = 4
dict = {"baa","abcd","abca","cab","cad"}

Output: 
bdac

Explanation:
Given words are sorted in the alien language.  
From "baa" and "abcd" → 'b' comes before 'a'.  
From "abcd" and "abca" → 'd' comes before 'a'.  
From "abca" and "cab" → 'a' comes before 'c'.  
From "cab" and "cad" → 'b' comes before 'd'.  
So order is: b d a c

-------------------------------------------------
Intuition:
-------------------------------------------------
1. The given dictionary is sorted in alien language order.  
2. Compare each pair of adjacent words:
   - Find the first differing character. That gives order (u → v).  
3. Build a directed graph of size k.  
4. Perform Topological Sort (Kahn’s Algorithm or DFS) to get the character order.  

-------------------------------------------------
Time Complexity:
-------------------------------------------------
- Building graph: O(N * L) where L is average word length.  
- Topological sort: O(K + E).  
Total: O(N * L + K + E)

Space Complexity:
-------------------------------------------------
- Graph: O(K + E)  
- Indegree array + result: O(K)  
*/

import java.util.*;

class Solution {
    // Function to return a string denoting the order of characters
    public String findOrder(String[] words, int N, int K) {
        // Step 1: Build graph
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            adj.add(new ArrayList<>());
        }

        // Step 2: Add edges based on first different character
        for (int i = 0; i < N - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            int len = Math.min(w1.length(), w2.length());

            int j = 0;
            while (j < len && w1.charAt(j) == w2.charAt(j)) {
                j++;
            }

            if (j < len) {
                int u = w1.charAt(j) - 'a';
                int v = w2.charAt(j) - 'a';
                adj.get(u).add(v);
            } else if (w1.length() > w2.length()) {
                // Invalid case (prefix problem)
                return "";
            }
        }

        // Step 3: Compute indegree
        int[] indegree = new int[K];
        for (int i = 0; i < K; i++) {
            for (int v : adj.get(i)) {
                indegree[v]++;
            }
        }

        // Step 4: Kahn’s BFS Topological Sort
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < K; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int node = q.poll();
            sb.append((char)(node + 'a'));

            for (int neigh : adj.get(node)) {
                indegree[neigh]--;
                if (indegree[neigh] == 0) {
                    q.add(neigh);
                }
            }
        }

        // Step 5: Cycle check (if not all characters included)
        if (sb.length() < K) return "";

        return sb.toString();
    }
}
