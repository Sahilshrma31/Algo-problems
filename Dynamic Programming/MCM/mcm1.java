/*
------------------------------------------------------------
📌 Problem: Matrix Chain Multiplication
------------------------------------------------------------
You are given an array `arr[]` of size `n` which represents 
the dimensions of matrices. 
The i-th matrix has dimension arr[i-1] x arr[i].

➡️ Task: Find the minimum number of multiplications 
         needed to multiply the chain of matrices.

------------------------------------------------------------
💡 Intuition:
We want to decide where to place brackets while multiplying 
matrices to minimize the total number of scalar multiplications.

At every step, we try to split the chain into two parts and 
recursively calculate the cost.

------------------------------------------------------------
Approaches:
1. Pure Recursion
2. Recursion + Memoization (Top-Down DP)
3. Tabulation (Bottom-Up DP)
------------------------------------------------------------
*/

import java.util.*;

class Solution {

    // ------------------------------------------------------------
    // 1️⃣ Recursive Approach
    // ------------------------------------------------------------
    static int matrixMultiplicationRecursive(int arr[]) {
        int n = arr.length;
        return helperRecursive(1, n - 1, arr);
    }

    static int helperRecursive(int i, int j, int arr[]) {
        if (i >= j) return 0; // only one matrix or invalid
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j - 1; k++) {
            int cost = helperRecursive(i, k, arr) +
                       helperRecursive(k + 1, j, arr) +
                       arr[i - 1] * arr[k] * arr[j];
            min = Math.min(min, cost);
        }
        return min;
    }
    // ⏱️ TC: Exponential (O(2^n))
    // 💾 SC: O(n) (recursion stack)


    // ------------------------------------------------------------
    // 2️⃣ Memoization (Top-Down DP)
    // ------------------------------------------------------------
    static int matrixMultiplicationMemo(int arr[]) {
        int n = arr.length;
        int dp[][] = new int[n][n];
        for (int[] row : dp) Arrays.fill(row, -1);
        return helperMemo(1, n - 1, arr, dp);
    }

    static int helperMemo(int i, int j, int arr[], int dp[][]) {
        if (i >= j) return 0;
        if (dp[i][j] != -1) return dp[i][j];
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j - 1; k++) {
            int cost = helperMemo(i, k, arr, dp) +
                       helperMemo(k + 1, j, arr, dp) +
                       arr[i - 1] * arr[k] * arr[j];
            min = Math.min(min, cost);
        }
        return dp[i][j] = min;
    }
    // ⏱️ TC: O(n^3) (n^2 states * n loop for k)
    // 💾 SC: O(n^2) (dp table) + O(n) recursion stack


    // ------------------------------------------------------------
    // 3️⃣ Tabulation (Bottom-Up DP) [Striver’s approach]
    // ------------------------------------------------------------
    static int matrixMultiplicationTabulation(int arr[]) {
        int n = arr.length;
        int[][] dp = new int[n][n];

        // Base case: cost of multiplying one matrix = 0
        for (int i = 1; i < n; i++) dp[i][i] = 0;

        // Fill table in bottom-up manner
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j < n; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = arr[i - 1] * arr[k] * arr[j] + dp[i][k] + dp[k + 1][j];
                    min = Math.min(min, cost);
                }
                dp[i][j] = min;
            }
        }
        return dp[1][n - 1];
    }
    // ⏱️ TC: O(n^3)
    // 💾 SC: O(n^2) (dp table)

    
    // ------------------------------------------------------------
    // Main method for testing
    // ------------------------------------------------------------
    public static void main(String[] args) {
        int arr[] = {40, 20, 30, 10, 30};

        System.out.println("Recursive: " + matrixMultiplicationRecursive(arr));
        System.out.println("Memoization: " + matrixMultiplicationMemo(arr));
        System.out.println("Tabulation: " + matrixMultiplicationTabulation(arr));
    }
}
