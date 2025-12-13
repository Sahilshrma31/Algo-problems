/*
====================================================
📌 Problem: Maximum Points (Ninja Training)
====================================================

You are given a 2D array arr[][] of size N x 3.
arr[i][j] represents points gained by performing
activity j on day i.

Rules:
- You must perform exactly one activity each day.
- You cannot perform the same activity on consecutive days.

Return the maximum points achievable.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
For each day, you have 3 choices.
But the choice for today depends on what you did yesterday.

State:
dp[day][last] = maximum points till 'day'
if previous day's activity was 'last'

We try all activities except 'last'.

----------------------------------------------------
🧩 Approaches Covered
----------------------------------------------------
1️⃣ Recursion (Brute Force)
2️⃣ Memoization (Top-Down DP)
3️⃣ Tabulation (Bottom-Up DP)
4️⃣ Space Optimized DP

----------------------------------------------------
⏱️ Time & Space Complexity
----------------------------------------------------

Let N = number of days

1️⃣ Recursion
Time: O(3^N)
Space: O(N) (recursion stack)

2️⃣ Memoization
Time: O(N * 4 * 3) ≈ O(N)
Space: O(N * 4) + O(N)

3️⃣ Tabulation
Time: O(N * 4 * 3) ≈ O(N)
Space: O(N * 4)

4️⃣ Space Optimized
Time: O(N * 4 * 3) ≈ O(N)
Space: O(4)

====================================================
*/

import java.util.*;

class Solution {

    /*------------------------------------------------
      1️⃣ RECURSION (BrUTE FORCE)
    ------------------------------------------------*/
    public int maximumPointsRecursion(int[][] arr) {
        int n = arr.length;
        return helperRec(arr, n - 1, 3);
    }

    private int helperRec(int[][] arr, int day, int last) {
        if (day == 0) {
            int maxi = 0;
            for (int task = 0; task < 3; task++) {
                if (task != last) {
                    maxi = Math.max(maxi, arr[0][task]);
                }
            }
            return maxi;
        }

        int maxi = 0;
        for (int task = 0; task < 3; task++) {
            if (task != last) {
                int points = arr[day][task] + helperRec(arr, day - 1, task);
                maxi = Math.max(maxi, points);
            }
        }
        return maxi;
    }

    /*------------------------------------------------
      2️⃣ MEMOIZATION (TOP-DOWN DP)
    ------------------------------------------------*/
    public int maximumPointsMemo(int[][] arr) {
        int n = arr.length;
        int[][] dp = new int[n][4];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return helperMemo(arr, n - 1, 3, dp);
    }

    private int helperMemo(int[][] arr, int day, int last, int[][] dp) {
        if (dp[day][last] != -1) {
            return dp[day][last];
        }

        if (day == 0) {
            int maxi = 0;
            for (int task = 0; task < 3; task++) {
                if (task != last) {
                    maxi = Math.max(maxi, arr[0][task]);
                }
            }
            return dp[day][last] = maxi;
        }

        int maxi = 0;
        for (int task = 0; task < 3; task++) {
            if (task != last) {
                int points = arr[day][task] + helperMemo(arr, day - 1, task, dp);
                maxi = Math.max(maxi, points);
            }
        }

        return dp[day][last] = maxi;
    }

    /*------------------------------------------------
      3️⃣ TABULATION (BOTTOM-UP DP)
    ------------------------------------------------*/
    public int maximumPointsTabulation(int[][] arr) {
        int n = arr.length;
        int[][] dp = new int[n][4];

        // Base Case (day 0)
        dp[0][0] = Math.max(arr[0][1], arr[0][2]);
        dp[0][1] = Math.max(arr[0][0], arr[0][2]);
        dp[0][2] = Math.max(arr[0][0], arr[0][1]);
        dp[0][3] = Math.max(arr[0][0], Math.max(arr[0][1], arr[0][2]));

        for (int day = 1; day < n; day++) {
            for (int last = 0; last < 4; last++) {
                dp[day][last] = 0;
                for (int task = 0; task < 3; task++) {
                    if (task != last) {
                        int points = arr[day][task] + dp[day - 1][task];
                        dp[day][last] = Math.max(dp[day][last], points);
                    }
                }
            }
        }

        return dp[n - 1][3];
    }

    /*------------------------------------------------
      4️⃣ SPACE OPTIMIZED DP
    ------------------------------------------------*/
    public int maximumPointsSpaceOptimized(int[][] arr) {
        int n = arr.length;
        int[] prev = new int[4];

        prev[0] = Math.max(arr[0][1], arr[0][2]);
        prev[1] = Math.max(arr[0][0], arr[0][2]);
        prev[2] = Math.max(arr[0][0], arr[0][1]);
        prev[3] = Math.max(arr[0][0], Math.max(arr[0][1], arr[0][2]));

        for (int day = 1; day < n; day++) {
            int[] curr = new int[4];
            for (int last = 0; last < 4; last++) {
                curr[last] = 0;
                for (int task = 0; task < 3; task++) {
                    if (task != last) {
                        curr[last] = Math.max(curr[last],
                                arr[day][task] + prev[task]);
                    }
                }
            }
            prev = curr;
        }

        return prev[3];
    }

    /*------------------------------------------------
      ✅ GFG Required Function
    ------------------------------------------------*/
    public int maximumPoints(int[][] arr) {
        return maximumPointsSpaceOptimized(arr);
    }
}
