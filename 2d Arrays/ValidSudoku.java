/*
 * 🧩 LeetCode 36: Valid Sudoku
 *
 * 📌 Problem:
 * Given a 9x9 Sudoku board, determine if it is valid. 
 * - Each row must contain digits 1–9 without repetition.
 * - Each column must contain digits 1–9 without repetition.
 * - Each 3x3 sub-box must contain digits 1–9 without repetition.
 * Empty cells are denoted by '.'.
 *
 * ----------------------------------------------------------
 * 💡 Approach 1: Brute Force (Check Rows, Cols, and Boxes separately)
 *
 * Intuition:
 * - Check each row → ensure no duplicate digits.
 * - Check each column → ensure no duplicate digits.
 * - Check each 3x3 box → ensure no duplicate digits.
 *
 * Time Complexity: O(81) = O(1) since board size is fixed
 * Space Complexity: O(9) per check = O(1)
 *
 * ----------------------------------------------------------
 * 💡 Approach 2: Optimized HashSet (Single Pass)
 *
 * Intuition:
 * - Use one HashSet<String> to track constraints.
 * - For each cell (i, j) with digit `num`, store:
 *   - "num in row i"
 *   - "num in col j"
 *   - "num in box (i/3)-(j/3)"
 * - If any already exists → invalid.
 *
 * Time Complexity: O(81 × 3) = O(1) since board size is fixed
 * Space Complexity: O(243) max entries in HashSet = O(1)
 *
 * ----------------------------------------------------------
 * ✅ Conclusion:
 * - Brute force → simple and clear.
 * - Optimized → elegant, single pass.
 * - Both run in constant time for 9x9 Sudoku.
 */

import java.util.HashSet;

public class ValidSudoku {

    // 🔹 Approach 1: Brute Force
    static class SolutionBruteForce {
        public boolean isValidSudoku(char[][] board) {
            // Check rows
            for (int i = 0; i < 9; i++) {
                HashSet<Character> set = new HashSet<>();
                for (int j = 0; j < 9; j++) {
                    char num = board[i][j];
                    if (num == '.') continue;
                    if (!set.add(num)) return false;
                }
            }

            // Check cols
            for (int j = 0; j < 9; j++) {
                HashSet<Character> set = new HashSet<>();
                for (int i = 0; i < 9; i++) {
                    char num = board[i][j];
                    if (num == '.') continue;
                    if (!set.add(num)) return false;
                }
            }

            // Check 3x3 boxes
            for (int boxRow = 0; boxRow < 9; boxRow += 3) {
                for (int boxCol = 0; boxCol < 9; boxCol += 3) {
                    HashSet<Character> set = new HashSet<>();
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            char num = board[boxRow + i][boxCol + j];
                            if (num == '.') continue;
                            if (!set.add(num)) return false;
                        }
                    }
                }
            }

            return true;
        }
    }

    // 🔹 Approach 2: Optimized HashSet
    static class SolutionOptimized {
        public boolean isValidSudoku(char[][] board) {
            HashSet<String> st = new HashSet<>();

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] == '.') continue;

                    char num = board[i][j];

                    String row = num + " in row " + i;
                    String col = num + " in col " + j;
                    String box = num + " in box " + (i / 3) + "-" + (j / 3);

                    if (st.contains(row) || st.contains(col) || st.contains(box)) {
                        return false;
                    }

                    st.add(row);
                    st.add(col);
                    st.add(box);
                }
            }

            return true;
        }
    }

    // 🔹 Example usage
    public static void main(String[] args) {
        char[][] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };

        SolutionBruteForce brute = new SolutionBruteForce();
        SolutionOptimized opt = new SolutionOptimized();

        System.out.println("Brute Force Result: " + brute.isValidSudoku(board));
        System.out.println("Optimized Result: " + opt.isValidSudoku(board));
    }
}
