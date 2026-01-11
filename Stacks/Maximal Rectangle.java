/*
====================================================
📌 Problem: Maximal Rectangle (LeetCode 85)
====================================================

Given a 2D binary matrix filled with '0's and '1's,
find the largest rectangle containing only '1's
and return its area.

----------------------------------------------------
🧠 Core Idea
----------------------------------------------------
Treat each row as the base of a histogram.

For each row:
- Build heights of consecutive 1s column-wise
- Apply "Largest Rectangle in Histogram" logic
- Keep track of maximum area

This converts a 2D problem into repeated 1D problems.

----------------------------------------------------
🧩 Approaches Used
----------------------------------------------------
1️⃣ Prefix Height Matrix (Histogram per row)
2️⃣ Stack-based Largest Rectangle in Histogram

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n * m)

- Building prefix heights: O(n * m)
- Histogram calculation per row: O(m)
- For n rows → O(n * m)

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(n * m) for prefix heights
O(m) for stack

====================================================
*/

import java.util.*;

class Solution {

    /* ===============================
       MAIN FUNCTION
       =============================== */

    public int maximalRectangle(char[][] matrix) {

        if (matrix.length == 0) return 0;

        int n = matrix.length;
        int m = matrix[0].length;

        // Prefix sum matrix (heights)
        int[][] psum = new int[n][m];

        // Build histogram heights column-wise
        for (int j = 0; j < m; j++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (matrix[i][j] == '1') {
                    sum++;
                } else {
                    sum = 0;
                }
                psum[i][j] = sum;
            }
        }

        int maxArea = 0;

        // For each row, treat it as a histogram
        for (int i = 0; i < n; i++) {
            maxArea = Math.max(maxArea, largestHistogram(psum[i]));
        }

        return maxArea;
    }

    /* ===============================
       LARGEST RECTANGLE IN HISTOGRAM
       =============================== */

    private int largestHistogram(int[] heights) {

        Stack<Integer> st = new Stack<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i < n; i++) {

            while (!st.isEmpty() && heights[st.peek()] > heights[i]) {

                int idx = st.pop();
                int nse = i;
                int pse = st.isEmpty() ? -1 : st.peek();

                int width = nse - pse - 1;
                int area = heights[idx] * width;
                maxArea = Math.max(maxArea, area);
            }
            st.push(i);
        }

        // Remaining elements
        while (!st.isEmpty()) {
            int idx = st.pop();
            int nse = n;
            int pse = st.isEmpty() ? -1 : st.peek();

            int width = nse - pse - 1;
            int area = heights[idx] * width;
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}
