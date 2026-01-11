/*
====================================================
📌 Problem: Largest Rectangle in Histogram
====================================================

Given an array heights[] representing the histogram's
bar heights (width of each bar = 1), find the area of
the largest rectangle in the histogram.

----------------------------------------------------
🧠 Key Observation
----------------------------------------------------
For each bar as the smallest height:
Area = height[i] * (NextSmallerIndex - PrevSmallerIndex - 1)

----------------------------------------------------
🧩 Approaches Covered
----------------------------------------------------
1️⃣ Brute Force using Precomputed PSE & NSE
2️⃣ Optimized Single-Pass Stack Approach

----------------------------------------------------
⏱ Time & Space Complexity
----------------------------------------------------

1️⃣ Brute Force (PSE + NSE):
   Time: O(n)
   Space: O(n)

2️⃣ Optimized Stack:
   Time: O(n)
   Space: O(n)

====================================================
*/

import java.util.*;

class Solution {

    /* =================================================
       1️⃣ BRUTE FORCE APPROACH (PSE + NSE)
       ================================================= */

    public int largestRectangleAreaBrute(int[] heights) {
        int n = heights.length;
        int[] pse = previousSmaller(heights);
        int[] nse = nextSmaller(heights);

        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            int width = nse[i] - pse[i] - 1;
            int area = heights[i] * width;
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    // Previous Smaller Element Index
    private int[] previousSmaller(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            res[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return res;
    }

    // Next Smaller Element Index
    private int[] nextSmaller(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            res[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
        return res;
    }

    /* =================================================
       2️⃣ OPTIMIZED SINGLE-PASS STACK APPROACH
       ================================================= */

    public int largestRectangleArea(int[] heights) {
        Stack<Integer> st = new Stack<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i < n; i++) {

            while (!st.isEmpty() && heights[st.peek()] > heights[i]) {

                int idx = st.pop();
                int nse = i;
                int pse = st.isEmpty() ? -1 : st.peek();

                int width = nse - pse - 1;
                maxArea = Math.max(maxArea, heights[idx] * width);
            }
            st.push(i);
        }

        // Remaining bars
        while (!st.isEmpty()) {
            int idx = st.pop();
            int nse = n;
            int pse = st.isEmpty() ? -1 : st.peek();

            int width = nse - pse - 1;
            maxArea = Math.max(maxArea, heights[idx] * width);
        }

        return maxArea;
    }
}
