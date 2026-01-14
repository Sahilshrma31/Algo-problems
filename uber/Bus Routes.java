/*
====================================================
📌 Problem: Bus Routes
(LeetCode 815)
====================================================

You are given bus routes where each route is a circular
list of stops. You can transfer between buses at common
stops.

Task:
Return the minimum number of buses required to travel
from source to target.

If impossible, return -1.

====================================================
🧠 Approach: BFS on Routes (Graph on Buses)
====================================================

- Treat each **bus route as a node**
- Two routes are connected if they share a stop
- Build a mapping:
    stop → list of routes passing through that stop
- Start BFS from all routes that include `source`
- Each BFS level represents taking one more bus

====================================================
⏱ Time Complexity
====================================================
Let:
R = number of routes
S = total number of stops across all routes

Building map: O(S)
BFS traversal: O(S)

➡️ Overall: O(S)

====================================================
📦 Space Complexity
====================================================
Adjacency map + queue + visited array

➡️ O(S + R)

====================================================
*/

import java.util.*;

class Solution {

    public int numBusesToDestination(int[][] routes, int source, int target) {

        // If source and target are same
        if (source == target) return 0;

        // stop -> list of routes passing through it
        HashMap<Integer, ArrayList<Integer>> adj = new HashMap<>();

        for (int route = 0; route < routes.length; route++) {
            for (int stop : routes[route]) {
                adj.putIfAbsent(stop, new ArrayList<>());
                adj.get(stop).add(route);
            }
        }

        // If source stop doesn't exist
        if (!adj.containsKey(source)) return -1;

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[routes.length];

        // Initialize BFS with routes containing source
        for (int route : adj.get(source)) {
            queue.offer(route);
            visited[route] = true;
        }

        int busCount = 1;

        // BFS on routes
        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int currRoute = queue.poll();

                for (int stop : routes[currRoute]) {

                    // Target reached
                    if (stop == target) {
                        return busCount;
                    }

                    // Visit all connected routes via this stop
                    for (int nextRoute : adj.get(stop)) {
                        if (!visited[nextRoute]) {
                            visited[nextRoute] = true;
                            queue.offer(nextRoute);
                        }
                    }
                }
            }
            busCount++;
        }

        return -1;
    }
}
