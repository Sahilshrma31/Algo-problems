// # LeetCode 787: Cheapest Flights Within K Stops ✈️

// ## Problem Summary
// We are given:
// - `n` cities (numbered `0` to `n-1`).
// - An array `flights` where each entry is `[from, to, price]`.
// - A source city `src`, a destination city `dst`, and a maximum number of stops `k`.

// We need to find the **cheapest price** to travel from `src` to `dst` with at most `k` stops.  
// If there is no such route, return `-1`.

// ---

// ## Intuition
// This problem is a variation of the **shortest path problem in a weighted directed graph**, but with a **constraint on the number of stops**.  

// - Dijkstra’s algorithm alone doesn’t work directly because it minimizes distance, not stops.  
// - Bellman-Ford works (since it handles edge relaxations up to `k+1` times), but can be slower.  
// - A **BFS-like traversal with (node, cost, stops)** works well here:  
//   - We process level by level (like BFS), but keep track of both **cost** and **stops used**.  
//   - If we reach the destination within `k` stops with the minimum cost, we return that.  

// ---

// ## Approach
// 1. **Build adjacency list** from `flights` to represent the graph.  
//    Each edge stores `(neighbor, price)`.  

// 2. **Use a queue (BFS style)** to traverse:
//    - Store `(stops, node, cost)` as a tuple.
//    - Start with `(0, src, 0)` → 0 stops, source city, cost 0.  

// 3. **Track minimum cost to each city** (`minDist[]`):
//    - Initialize with infinity (`1e9`), except `src = 0`.  
//    - For each neighbor, if `newCost < minDist[neighbor]`, update and push into queue.  

// 4. **Stop condition**:
//    - If `stops > k`, skip further exploration.  
//    - After BFS ends, check `minDist[dst]`. If unchanged, return `-1`.  

// ---

// ## Time Complexity
// - Building adjacency list: **O(E)** where `E` is number of flights.  
// - BFS traversal: Each edge can be relaxed at most once per stop limit → **O(E * K)**.  
// - Overall: **O(E * K)**  

// ## Space Complexity
// - Adjacency list: **O(E)**  
// - Distance array: **O(N)**  
// - Queue: at most **O(N * K)** in worst case.  
// - Overall: **O(N + E)**  

// ---

// ## Code (Java)
// ```java
import java.util.*;

class Solution {
    class Pair {
        int node;
        int cost;
        Pair(int n, int c) {
            this.node = n;
            this.cost = c;
        }
    }

    class Tuple {
        int stops;
        int node;
        int dist;
        Tuple(int s, int n, int d) {
            this.stops = s;
            this.node = n;
            this.dist = d;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Step 1: Build adjacency list
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] flight : flights) {
            adj.get(flight[0]).add(new Pair(flight[1], flight[2]));
        }

        // Step 2: Distance array
        int[] minDist = new int[n];
        Arrays.fill(minDist, (int) 1e9);
        minDist[src] = 0;

        // Step 3: BFS queue -> (stops, node, cost)
        Queue<Tuple> q = new LinkedList<>();
        q.add(new Tuple(0, src, 0));

        while (!q.isEmpty()) {
            Tuple curr = q.poll();
            int stops = curr.stops;
            int node = curr.node;
            int dist = curr.dist;

            if (stops > k) continue;

            for (Pair neigh : adj.get(node)) {
                int nextNode = neigh.node;
                int edgeCost = neigh.cost;

                if (dist + edgeCost < minDist[nextNode]) {
                    minDist[nextNode] = dist + edgeCost;
                    q.add(new Tuple(stops + 1, nextNode, dist + edgeCost));
                }
            }
        }

        return minDist[dst] == (int) 1e9 ? -1 : minDist[dst];
    }
}
