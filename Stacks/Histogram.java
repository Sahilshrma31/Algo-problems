/*
📌 Problem: Largest Rectangle in Histogram (LeetCode 84)
--------------------------------------------------------
Given an array heights representing the histogram's bar height where the width of each bar is 1, 
return the area of the largest rectangle in the histogram.

🔑 Intuition:
Two standard approaches:

1️⃣ Stack-Based Single Pass:
   - Use a monotonic increasing stack to store indices of bars.
   - When a smaller bar is found, pop bars and calculate area using width = current index - previous index - 1.
   - Push the current index.
   - Add a sentinel (0 height) at the end to pop all remaining bars.
   - Time: O(n), Space: O(n)

2️⃣ Next Smaller Element (Left & Right) Approach:
   - For each bar, precompute:
       - Next Smaller Element to Right (NSR)
       - Next Smaller Element to Left (NSL)
   - Width of rectangle = NSR[i] - NSL[i] - 1
   - Area = heights[i] * width
   - Time: O(n), Space: O(n)
*/

import java.util.*;

class Histogram {

    // -----------------------------
    // Approach 1: Stack-Based Single Pass
    // -----------------------------
    public int largestRectangleAreaStack(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int h = (i == n) ? 0 : heights[i]; // Sentinel at the end
            while (!stack.isEmpty() && heights[stack.peek()] > h) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        return maxArea;
    }

    // -----------------------------
    // Approach 2: Next Smaller Element (Left & Right)
    // -----------------------------
    public int largestRectangleAreaNSE(int[] heights) {
        int n = heights.length;
        int[] nextSmallerRight = new int[n];
        int[] nextSmallerLeft = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Next Smaller Element to Right
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            nextSmallerRight[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        stack.clear();

        // Next Smaller Element to Left
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            nextSmallerLeft[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        // Calculate max area
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = nextSmallerRight[i] - nextSmallerLeft[i] - 1;
            int area = heights[i] * width;
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    // -----------------------------
    // Runner
    // -----------------------------
    public static void main(String[] args) {
        LargestRectangleHistogram obj = new LargestRectangleHistogram();
        int[] heights = {2, 1, 5, 6, 2, 3};

        System.out.println("Largest Rectangle Area (Stack Approach): " + obj.largestRectangleAreaStack(heights));
        System.out.println("Largest Rectangle Area (NSE Approach): " + obj.largestRectangleAreaNSE(heights));
    }
}
