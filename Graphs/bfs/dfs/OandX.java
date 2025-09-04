/*
Problem: Surrounded Regions (LeetCode 130)

Summary:
Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
A region is captured by flipping all 'O's into 'X's in that surrounded region.
An 'O' on the border or connected to a border 'O' should not be flipped.

Example:
Input:
[
 ["X","X","X","X"],
 ["X","O","O","X"],
 ["X","X","O","X"],
 ["X","O","X","X"]
]
Output:
[
 ["X","X","X","X"],
 ["X","X","X","X"],
 ["X","X","X","X"],
 ["X","O","X","X"]
]

Intuition:
- Only 'O's that are completely surrounded by 'X's should be converted to 'X'.
- Border 'O's and any 'O' connected to them should not be flipped.

Approach:
1. Create a visited matrix `vis` to mark 'O's connected to the border.
2. Run DFS from all border 'O's to mark connected 'O's as visited.
3. After DFS, flip all unvisited 'O's to 'X'.

Time Complexity: O(n * m)
- We visit each cell at most once.

Space Complexity: O(n * m)
- For visited matrix and recursion stack in worst case.

*/

class Solution {

    // DFS to mark 'O's connected to the border
    public void dfs(int r, int c, int vis[][], char board[][]) {
        int n = board.length;
        int m = board[0].length;
        vis[r][c] = 1;

        int dr[] = {-1, 0, 1, 0}; // up, right, down, left
        int dc[] = {0, 1, 0, -1};

        for(int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(nr >= 0 && nc >= 0 && nr < n && nc < m 
               && vis[nr][nc] == 0 && board[nr][nc] == 'O') {
                dfs(nr, nc, vis, board);
            }
        }
    }

    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        int vis[][] = new int[n][m];

        // Run DFS for first and last row
        for(int i = 0; i < m; i++) {
            if(board[0][i] == 'O' && vis[0][i] == 0)
                dfs(0, i, vis, board);
            if(board[n-1][i] == 'O' && vis[n-1][i] == 0)
                dfs(n-1, i, vis, board);
        }

        // Run DFS for first and last column
        for(int j = 0; j < n; j++) {
            if(board[j][0] == 'O' && vis[j][0] == 0)
                dfs(j, 0, vis, board);
            if(board[j][m-1] == 'O' && vis[j][m-1] == 0)
                dfs(j, m-1, vis, board);
        }

        // Convert unvisited 'O's to 'X'
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(board[i][j] == 'O' && vis[i][j] == 0)
                    board[i][j] = 'X';
            }
        }
    }

    // Helper function to print the board
    public void printBoard(char[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Main function to test
    public static void main(String[] args) {
        Solution sol = new Solution();

        char[][] board = {
            {'X','X','X','X'},
            {'X','O','O','X'},
            {'X','X','O','X'},
            {'X','O','X','X'}
        };

        System.out.println("Original Board:");
        sol.printBoard(board);

        sol.solve(board);

        System.out.println("\nBoard After Capturing Surrounded Regions:");
        sol.printBoard(board);
    }
}
