/*
🔹 Problem: 743. Network Delay Time (LeetCode)
----------------------------------------------
We are given a network of `n` nodes, labeled from 1 to n. 
Times[i] = (u, v, w) means there is a directed edge from u → v with weight w (time).

We want to send a signal from node `k`. 
Return the time it takes for all nodes to receive the signal. 
If some nodes cannot be reached, return -1.

---

🔹 Intuition:
- This is a classic **shortest path problem** in a weighted directed graph.
- We want the **longest shortest path** from the source `k` to any node.
- Use **Dijkstra's Algorithm**:
    - Maintain min-heap (priority queue) for exploring shortest distances first.
    - Update distances for neighbors if we find a shorter path.

---

🔹 Approach:
1. Build adjacency list from the `times` input.
2. Use a distance array, initialized to ∞ (1e9).
3. Push `(k,0)` into min-heap (source with distance 0).
4. While PQ not empty:
   - Pop the node with the smallest distance.
   - Relax all its neighbors (update distance if shorter).
5. After Dijkstra finishes:
   - If any node is unreachable → return -1.
   - Otherwise, return the maximum distance (longest shortest path).

---

🔹 Time Complexity:
- Building adjacency list: O(E)
- Dijkstra: O((V + E) log V)
    - Each edge can be processed once in log V PQ.
- Overall: **O(E log V)**

🔹 Space Complexity:
- Adjacency list: O(V + E)
- Distance array: O(V)
- Priority queue: O(V)
- Overall: **O(V + E)**

---

🔹 Code:
*/

import java.util.*;

class Solution {
    class Pair {
        int node;
        int time;
        Pair(int n, int t) {
            this.node = n;
            this.time = t;
        }
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        // Step 1: Build adjacency list
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : times) {
            int u = edge[0], v = edge[1], w = edge[2];
            adj.get(u).add(new Pair(v, w));
        }

        // Step 2: Initialize distance array
        int[] dist = new int[n + 1];
        Arrays.fill(dist, (int) 1e9);
        dist[k] = 0;

        // Step 3: Min-heap for Dijkstra
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.time - b.time);
        pq.add(new Pair(k, 0));

        // Step 4: Dijkstra’s Algorithm
        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int node = curr.node;
            int currTime = curr.time;

            for (Pair neigh : adj.get(node)) {
                int nextNode = neigh.node;
                int wt = neigh.time;

                if (currTime + wt < dist[nextNode]) {
                    dist[nextNode] = currTime + wt;
                    pq.add(new Pair(nextNode, dist[nextNode]));
                }
            }
        }

        // Step 5: Find max distance
        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == (int) 1e9) return -1; // unreachable
            maxTime = Math.max(maxTime, dist[i]);
        }

        return maxTime;
    }
}

/*
🔹 Example Testcase:
Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
Output: 2

Explanation:
- From node 2 → node 1 = 1
- From node 2 → node 3 = 1
- From node 2 → node 3 → node 4 = 2
Maximum = 2
*/
