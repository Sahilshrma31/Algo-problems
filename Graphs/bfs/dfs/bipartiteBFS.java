import java.util.*;
class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n]; // 0 = uncolored, 1 / -1 = colors

        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {
                if (!bfs(graph, i, color)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean bfs(int[][] graph, int start, int[] color) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        color[start] = 1; 

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int neigh : graph[node]) {
              
                if (color[neigh] == 0) {
                    color[neigh] = -color[node]; 
                    q.add(neigh);
                }
          
                else if (color[neigh] == color[node]) {
                    return false;
                }
            }
        }
        return true;
    }
}
