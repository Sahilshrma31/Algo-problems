/*
====================================================
📌 Problem: Maximum Matrix Sum
(LeetCode 1975)
====================================================

You are given an n x n integer matrix.
You can perform the following operation any number of times:
- Choose any two adjacent elements and multiply both by -1.

Return the maximum possible sum of the matrix after
performing any number of operations.

----------------------------------------------------
🧠 Key Insight
----------------------------------------------------
- We can flip signs of numbers using the allowed operation
- Only the **parity (odd/even)** of negative numbers matters
- Best strategy:
  • Take absolute value of all elements
  • Count how many negative numbers exist
  • Track the smallest absolute value

If number of negatives is:
- EVEN → all can be made positive
- ODD  → one element must stay negative
         → subtract 2 × smallest absolute value

----------------------------------------------------
🛠 Approach
----------------------------------------------------
1️⃣ Traverse the matrix
2️⃣ Add absolute value of each element to totalSum
3️⃣ Count negative elements
4️⃣ Track minimum absolute value
5️⃣ Adjust result based on parity of negatives

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n²)

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(1)

====================================================
*/

class Solution {

    public long maxMatrixSum(int[][] matrix) {

        int n = matrix.length;
        long totalSum = 0;
        int negativeCount = 0;
        int minAbs = Integer.MAX_VALUE;

        // Traverse matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                int val = matrix[i][j];

                // Count negatives
                if (val < 0) {
                    negativeCount++;
                }

                // Track minimum absolute value
                minAbs = Math.min(minAbs, Math.abs(val));

                // Add absolute value
                totalSum += Math.abs(val);
            }
        }

        // If odd number of negatives, one must remain negative
        if (negativeCount % 2 != 0) {
            totalSum -= 2L * minAbs;
        }

        return totalSum;
    }
}
