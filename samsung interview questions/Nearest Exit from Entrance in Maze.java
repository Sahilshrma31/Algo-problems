import java.util.*;

/**
 * LeetCode 1926 - Nearest Exit from Entrance in Maze
 *
 * Problem Summary:
 * You are given a maze represented as a 2D grid of characters:
 *   '.' represents an empty cell
 *   '+' represents a wall
 *
 * You are also given an entrance cell.
 * An exit is any empty cell on the boundary of the maze, except the entrance.
 *
 * Return the minimum number of steps required to reach the nearest exit.
 * If no exit is reachable, return -1.
 *
 * ------------------------------------------------------------
 * Intuition:
 * ------------------------------------------------------------
 * Each cell in the maze can be treated as a node in a graph.
 * From each cell, you can move in 4 directions (up, down, left, right).
 *
 * Since every move has the same cost, Breadth First Search (BFS)
 * is the best choice to find the shortest path to an exit.
 *
 * The first time we reach an exit during BFS guarantees the
 * minimum number of steps.
 *
 * ------------------------------------------------------------
 * Approach:
 * ------------------------------------------------------------
 * 1. Use a queue to perform BFS starting from the entrance.
 * 2. Mark the entrance as visited to avoid revisiting.
 * 3. Process the maze level by level; each level represents one step.
 * 4. For each cell, check its 4 neighbors and add valid ones to the queue.
 * 5. If a boundary cell (not the entrance) is reached, return steps.
 *
 * ------------------------------------------------------------
 * Time Complexity:
 * ------------------------------------------------------------
 * O(m * n)
 * where:
 *   m = number of rows in the maze
 *   n = number of columns in the maze
 *
 * Each cell is visited at most once.
 *
 * ------------------------------------------------------------
 * Space Complexity:
 * ------------------------------------------------------------
 * O(m * n)
 * for the BFS queue in the worst case.
 */

class Solution {

    int[][] directions = {
        {0, 1},   // right
        {1, 0},   // down
        {-1, 0},  // up
        {0, -1}   // left
    };

    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;

        Queue<int[]> queue = new LinkedList<>();

        // Start BFS from entrance
        queue.add(new int[]{entrance[0], entrance[1]});
        maze[entrance[0]][entrance[1]] = '+'; // mark entrance as visited

        int steps = 0;

        while (!queue.isEmpty()) {
            int count = queue.size();

            for (int i = 0; i < count; i++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];

                // Check if current cell is an exit
                if (!(r == entrance[0] && c == entrance[1]) &&
                        (r == 0 || r == m - 1 || c == 0 || c == n - 1)) {
                    return steps;
                }

                // Explore 4 directions
                for (int[] dir : directions) {
                    int newr = r + dir[0];
                    int newc = c + dir[1];

                    if (newr >= 0 && newr < m &&
                            newc >= 0 && newc < n &&
                            maze[newr][newc] != '+') {

                        queue.add(new int[]{newr, newc});
                        maze[newr][newc] = '+'; // mark as visited
                    }
                }
            }
            steps++;
        }

        return -1;
    }
}
