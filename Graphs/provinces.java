/*
LeetCode 547. Number of Provinces (Medium)

=====================
📌 Problem Summary:
We are given an n x n adjacency matrix `isConnected`, where:
- isConnected[i][j] = 1 if city i and city j are directly connected
- isConnected[i][j] = 0 otherwise
We need to count the number of provinces. 
(A province = a connected component in the graph)

=====================
💡 Intuition:
- The problem is essentially asking for the number of connected components 
  in an undirected graph.
- Each city is a node, and isConnected[i][j] = 1 means an edge exists.
- We can use **DFS** (or BFS) to traverse all nodes in one component 
  and mark them visited. 
- Every time we start a new DFS from an unvisited node, it means 
  we found a new province.

=====================
🛠️ Approach:
1. Build an adjacency list from the adjacency matrix (optional, 
   we can also DFS directly on matrix).
2. Maintain a visited array to track visited nodes.
3. For each unvisited city, start DFS → mark all connected cities visited.
4. Increment province count each time a DFS is started.

=====================
⏱️ Time Complexity:
- O(n^2), because adjacency matrix has n*n entries to check.
- Alternatively, if using adjacency list: O(V + E).
  (Here V = n, E = number of connections)

💾 Space Complexity:
- O(n) for visited array.
- O(n) recursion stack in worst case.
- If adjacency list built: O(V + E).

=====================
✅ Example:
Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2
Explanation:
- City 0 and City 1 form one province
- City 2 is isolated → another province
Total = 2
*/

import java.util.*;

class Solution {
    public void dfs(int curr, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
        vis[curr] = true;
        for (int neigh : adj.get(curr)) {
            if (!vis[neigh]) {
                dfs(neigh, adj, vis);
            }
        }
    }

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        // Initialize adjacency list
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Build adjacency list from matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1 && i != j) {
                    adj.get(i).add(j);
                }
            }
        }

        boolean[] vis = new boolean[n];
        int provinces = 0;

        // Count connected components
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                provinces++;
                dfs(i, adj, vis);
            }
        }

        return provinces;
    }
}


/*
=====================
🛠️ Approach (Optimized - Directly on Matrix):
1. We do NOT build an adjacency list (saves O(n²) space).
2. Maintain a visited[] array of size n.
3. For every city (0..n-1):
   - If it's not visited, start a DFS from that city.
   - DFS marks all cities in the same province as visited.
   - Increment province count by 1.
4. Return the total count.

=====================
⏱️ Time Complexity:
- O(n²) → For each DFS, we may scan an entire row of the adjacency matrix.
- Since there are n rows of size n → O(n²).

💾 Space Complexity:
- O(n) → visited array
- O(n) → recursion stack in the worst case (if the graph is fully connected)
- Total = O(n)

*/


class Solution {

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] vis = new boolean[n];
        int cnt = 0;

        // iterate over all cities
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                cnt++;                  // new province found
                dfs(i, isConnected, vis);
            }
        }
        return cnt;
    }

    // DFS to mark all cities connected to current city
    private void dfs(int i, int[][] isConnected, boolean[] vis) {
        vis[i] = true;
        for (int j = 0; j < isConnected.length; j++) {
            if (isConnected[i][j] == 1 && !vis[j]) {
                dfs(j, isConnected, vis);
            }
        }
    }
}
