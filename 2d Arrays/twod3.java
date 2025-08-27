/*
Problem Summary:
---------------
You are given a grid with values {0,1,2}. 
We want to find the maximum length of a diagonal zigzag path starting from any cell
containing 1. A valid path alternates values (1 → 2 → 0 → 2 → 0 …) along diagonals. 
We are allowed to move diagonally in 4 directions:
   ↖ top-left, ↗ top-right, ↘ bottom-right, ↙ bottom-left
Additionally, we may take at most one turn during the path (changing diagonal direction once).

We need to return the maximum length of such a path.

---

Intuition:
----------
- Start from every cell that has value 1 (since path always begins with 1).
- Try all 4 diagonal directions from there.
- Recursively move in the same direction if the next cell matches the required alternating value.
- Keep track of alternating values: after 2 comes 0, after 0 comes 2.
- Allow at most one "turn". If turn is used, continue in the new diagonal direction.
- Take the maximum path length across all possibilities.

This is essentially a DFS with branching when we decide to "turn".

---

Time Complexity:
----------------
- In the worst case, from each cell we try 4 directions.
- Each DFS may explore O(n*m) states in the worst case.
- So upper bound: O(n * m * 4) = O(n*m).

Space Complexity:
-----------------
- Recursive stack in DFS can go as deep as O(n+m) in worst case.
- So space = O(n*m) for recursion depth (in practice much less).

---
*/

class Solution {
    // ✅ Correct clockwise diagonal directions
    int directions[][] = {
        {-1,-1}, // top-left
        {-1, 1}, // top-right
        { 1, 1}, // bottom-right
        { 1,-1}  // bottom-left
    };

    int n; // rows
    int m; // cols

    public int lenOfVDiagonal(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        int result = 0;

        for (int i = 0; i < n; i++) {     
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    for (int d = 0; d < 4; d++) {
                        result = Math.max(result, 1 + solve(i, j, d, 1, 2, grid));
                    }
                }
            }
        }
        return result;
    }

    public int solve(int i, int j, int d, int canturn, int val, int grid[][]) {
        int ni = i + directions[d][0];
        int nj = j + directions[d][1];

        // boundary + value check
        if (ni < 0 || ni >= n || nj < 0 || nj >= m || grid[ni][nj] != val) {
            return 0;
        }

        int newval = (val == 2 ? 0 : 2);
        int result = 0;

        // keep moving straight
        int keepmoving = 1 + solve(ni, nj, d, canturn, newval, grid);
        result = Math.max(result, keepmoving);

        // try turning once
        if (canturn == 1) {
            int turnandmove = 1 + solve(ni, nj, (d + 1) % 4, 0, newval, grid);
            result = Math.max(result, turnandmove);
        }

        return result;
    }
}
