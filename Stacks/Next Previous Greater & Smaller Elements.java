/*
====================================================
📌 Problem: Next Previous Greater & Smaller Elements
====================================================

Given an integer array arr[], find for each element:

1️⃣ Next Greater Element (NGE)
2️⃣ Next Smaller Element (NSE)
3️⃣ Previous Greater Element (PGE)
4️⃣ Previous Smaller Element (PSE)

----------------------------------------------------
🧠 Core Idea (Monotonic Stack)
----------------------------------------------------
We use a stack to maintain elements in a specific order:
- Monotonic decreasing stack → for Greater elements
- Monotonic increasing stack → for Smaller elements

Each element is pushed and popped at most once.

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n) for each operation

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(n) for stack + result arrays

====================================================
*/

import java.util.*;

class Solution {

    /* =================================================
       1️⃣ NEXT GREATER ELEMENT (Right side)
       ================================================= */
    public static int[] nextGreaterElement(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {

            while (!st.isEmpty() && st.peek() <= arr[i]) {
                st.pop();
            }

            res[i] = st.isEmpty() ? -1 : st.peek();
            st.push(arr[i]);
        }
        return res;
    }

    /* =================================================
       2️⃣ NEXT SMALLER ELEMENT (Right side)
       ================================================= */
    public static int[] nextSmallerElement(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {

            while (!st.isEmpty() && st.peek() >= arr[i]) {
                st.pop();
            }

            res[i] = st.isEmpty() ? -1 : st.peek();
            st.push(arr[i]);
        }
        return res;
    }

    /* =================================================
       3️⃣ PREVIOUS GREATER ELEMENT (Left side)
       ================================================= */
    public static int[] previousGreaterElement(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {

            while (!st.isEmpty() && st.peek() <= arr[i]) {
                st.pop();
            }

            res[i] = st.isEmpty() ? -1 : st.peek();
            st.push(arr[i]);
        }
        return res;
    }

    /* =================================================
       4️⃣ PREVIOUS SMALLER ELEMENT (Left side)
       ================================================= */
    public static int[] previousSmallerElement(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {

            while (!st.isEmpty() && st.peek() >= arr[i]) {
                st.pop();
            }

            res[i] = st.isEmpty() ? -1 : st.peek();
            st.push(arr[i]);
        }
        return res;
    }

    /* =================================================
       Utility Method for Testing
       ================================================= */
    public static void main(String[] args) {
        int[] arr = {4, 5, 2, 10, 8};

        System.out.println("Next Greater: " + Arrays.toString(nextGreaterElement(arr)));
        System.out.println("Next Smaller: " + Arrays.toString(nextSmallerElement(arr)));
        System.out.println("Prev Greater: " + Arrays.toString(previousGreaterElement(arr)));
        System.out.println("Prev Smaller: " + Arrays.toString(previousSmallerElement(arr)));
    }
}

