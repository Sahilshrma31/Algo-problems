/*
====================================================
📌 Problem: Rotate Image (90° Clockwise)
LeetCode: 48
====================================================

📝 Problem Summary:
You are given an n x n 2D matrix representing an image.
Rotate the image by 90 degrees clockwise in-place.

----------------------------------------------------
🧠 Intuition:
A 90° clockwise rotation can be achieved in 2 steps:
1) Transpose the matrix
2) Reverse each row

----------------------------------------------------
🛠️ Approach:
- First transpose the matrix (swap matrix[i][j] with matrix[j][i])
- Then reverse every row of the matrix

----------------------------------------------------
⏱️ Time Complexity:
O(n²)

📦 Space Complexity:
O(1) (in-place)
====================================================
*/

class Solution {

    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // Step 1: Transpose the matrix
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row
        for (int i = 0; i < n; i++) {
            reverseRow(matrix[i]);
        }
    }

    private void reverseRow(int[] row) {
        int start = 0, end = row.length - 1;
        while (start < end) {
            int temp = row[start];
            row[start] = row[end];
            row[end] = temp;
            start++;
            end--;
        }
    }
}
