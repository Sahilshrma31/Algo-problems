/*
====================================================
📌 Problem: Detonate the Maximum Bombs (LeetCode 2101)
====================================================

You are given n bombs, where each bomb is represented
by:
bombs[i] = [xi, yi, ri]

- (xi, yi) is the position of the bomb
- ri is its blast radius

If bomb i explodes, it can trigger bomb j if:
distance(i, j) <= ri

The explosion is directional:
i → j does NOT imply j → i.

Your task:
Find the maximum number of bombs that can be detonated
by choosing exactly ONE bomb to start the chain reaction.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
Each bomb can trigger some other bombs.
This naturally forms a **directed graph**:

- Node = bomb
- Edge i → j if bomb i can detonate bomb j

Now the problem becomes:
👉 From which node does DFS/BFS reach the maximum
number of nodes?

So:
1) Build a directed graph
2) Run DFS from every node
3) Track maximum reachable nodes

----------------------------------------------------
🛠️ Approach
----------------------------------------------------
1️⃣ Build Graph:
   - For every pair (i, j), check:
     (xi - xj)² + (yi - yj)² <= ri²
   - If true → add directed edge i → j

2️⃣ DFS from every node:
   - Maintain a visited array
   - Count how many bombs are detonated

3️⃣ Take maximum over all DFS runs

----------------------------------------------------
/*
 * Time Complexity: O(N^3)
 * - We run a DFS starting from every bomb (N times).
 * - A single DFS takes O(V + E). Here V = N, and in the worst case (dense graph), E = N^2.
 * - Calculation: N * (N + N^2) ≈ O(N^3).
 *
 * Space Complexity: O(N^2)
 * - Dominated by the adjacency list, which stores up to N^2 edges if every bomb reaches every other bomb.
 * - The recursion stack and visited array only contribute O(N).
 

----------------------------------------------------
✅ Key Observations
----------------------------------------------------
✔ Use long for distance calculation (avoid overflow)
✔ Graph is directed
✔ DFS from each node is required (no single source)

====================================================
*/

import java.util.*;

class Solution {

    // DFS to count reachable bombs
    private void dfs(int u, boolean[] visited, ArrayList<ArrayList<Integer>> adj) {
        visited[u] = true;

        for (int v : adj.get(u)) {
            if (!visited[v]) {
                dfs(v, visited, adj);
            }
        }
    }

    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;

        // Step 1: Create adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Step 2: Build directed graph
        for (int i = 0; i < n; i++) {
            long x1 = bombs[i][0];
            long y1 = bombs[i][1];
            long r1 = bombs[i][2];

            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                long x2 = bombs[j][0];
                long y2 = bombs[j][1];

                long dx = x2 - x1;
                long dy = y2 - y1;

                long distanceSquared = dx * dx + dy * dy;

                if (r1 * r1 >= distanceSquared) {
                    adj.get(i).add(j);
                }
            }
        }

        // Step 3: Try DFS from each bomb
        int maxBombs = 0;

        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            dfs(i, visited, adj);

            int count = 0;
            for (boolean v : visited) {
                if (v) count++;
            }

            maxBombs = Math.max(maxBombs, count);
        }

        return maxBombs;
    }
}
