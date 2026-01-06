/*
====================================================
📌 Problem: Find Maximum Possible Value with Restrictions
====================================================

You are given:
- An integer n (size of array)
- A list of restrictions where restrictions[i] = [index, maxValue]
- An array diff[] where diff[i] represents the maximum
  allowed difference between arr[i] and arr[i+1]

You must construct an array arr of size n such that:
1️⃣ arr[0] = 0
2️⃣ arr[index] ≤ maxValue for each restriction
3️⃣ |arr[i+1] - arr[i]| ≤ diff[i]
4️⃣ The maximum value in arr is as large as possible

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
- Start with all values as +∞
- Apply direct restrictions
- Do a forward pass to enforce increasing constraints
- Do a backward pass to enforce decreasing constraints
- The answer is the maximum value in the final array

----------------------------------------------------
🛠 Approach
----------------------------------------------------
1️⃣ Initialize array with infinity
2️⃣ Apply restrictions
3️⃣ Forward pass (left → right)
4️⃣ Backward pass (right → left)
5️⃣ Find maximum value in array

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n + r)
where r = number of restrictions

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(n)

====================================================
*/

import java.util.*;

class Solution {

    public int findMaxVal(int n, int[][] restrictions, int[] diff) {

        long[] arr = new long[n];
        Arrays.fill(arr, Long.MAX_VALUE);

        // Base condition
        arr[0] = 0;

        // Apply given restrictions
        for (int[] r : restrictions) {
            int idx = r[0];
            int maxVal = r[1];
            arr[idx] = Math.min(arr[idx], maxVal);
        }

        // Forward pass: enforce diff constraint
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] != Long.MAX_VALUE) {
                arr[i + 1] = Math.min(arr[i + 1], arr[i] + diff[i]);
            }
        }

        // Backward pass: enforce diff constraint
        for (int i = n - 2; i >= 0; i--) {
            arr[i] = Math.min(arr[i], arr[i + 1] + diff[i]);
        }

        // Find maximum value
        long ans = 0;
        for (long v : arr) {
            ans = Math.max(ans, v);
        }

        return (int) ans;
    }
}
