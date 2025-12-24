/*
====================================================
📌 Problem: Spiral Matrix II (Generate n x n Matrix)
LeetCode: 59
====================================================

Given an integer n, generate an n x n matrix filled with
elements from 1 to n^2 in spiral order.

----------------------------------------------------
🧠 Approach
----------------------------------------------------
We maintain four boundaries:
- top
- bottom
- left
- right

We fill the matrix layer by layer in spiral order:
1) left → right
2) top → bottom
3) right → left
4) bottom → top

After each traversal, we shrink the corresponding boundary.

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n²)

----------------------------------------------------
🧠 Space Complexity
----------------------------------------------------
O(n²) (output matrix)
====================================================
*/

class Solution {
    public int[][] generateMatrix(int n) {

        int[][] ans = new int[n][n];

        int top = 0, bottom = n - 1;
        int left = 0, right = n - 1;
        int num = 1;

        while (top <= bottom && left <= right) {

            // left → right
            for (int i = left; i <= right; i++) {
                ans[top][i] = num++;
            }
            top++;

            // top → bottom
            for (int i = top; i <= bottom; i++) {
                ans[i][right] = num++;
            }
            right--;

            // right → left
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    ans[bottom][i] = num++;
                }
                bottom--;
            }

            // bottom → top
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    ans[i][left] = num++;
                }
                left++;
            }
        }

        return ans;
    }
}
