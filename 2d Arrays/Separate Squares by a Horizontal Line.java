/*
====================================================
📌 Problem: Separate Squares by a Horizontal Line
====================================================

You are given multiple axis-aligned squares.
Each square is represented as:
    [x, y, sideLength]
(where x is irrelevant for area calculation here)

Your task is to find a horizontal line (y = resultY)
such that:
- The total area of squares **below** the line
  is equal to the total area **above** the line.

----------------------------------------------------
🧠 Key Idea
----------------------------------------------------
This is a classic **binary search on answer** problem.

Why binary search?
- As Y increases, the area below the line increases monotonically.
- We search for the Y where bottom area = total area / 2.

----------------------------------------------------
🧩 Approach
----------------------------------------------------
1️⃣ Compute:
   - total area of all squares
   - minimum Y (low bound)
   - maximum Y (high bound)

2️⃣ Binary search on Y:
   - midY = candidate horizontal line
   - compute area below midY
   - if bottomArea >= total/2 → move search upward/downward

3️⃣ Stop when precision is good enough (1e-5)

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
Let N = number of squares

- check() runs in O(N)
- Binary search runs ~log(range / precision)

Total:
O(N * log(1 / precision))

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(1) extra space

====================================================
*/

class Solution {

    /*
     * Helper function to check if area below midY
     * is at least half of total area.
     */
    private boolean check(int[][] squares, double midY, double total) {
        double bottomArea = 0.0;

        for (int[] square : squares) {
            double y = square[1];
            double l = square[2];

            double bottomY = y;
            double topY = y + l;

            if (midY >= topY) {
                // Entire square lies below midY
                bottomArea += l * l;
            } else if (midY > bottomY) {
                // Partial square lies below midY
                bottomArea += (midY - bottomY) * l;
            }
        }

        return bottomArea >= total / 2.0;
    }

    /*
     * Main function to find the Y-coordinate
     * of the separating horizontal line.
     */
    public double separateSquares(int[][] squares) {

        double low = Double.MAX_VALUE;
        double high = -Double.MAX_VALUE;
        double total = 0.0;

        // Compute total area and search bounds
        for (int[] square : squares) {
            double y = square[1];
            double l = square[2];

            total += l * l;
            low = Math.min(low, y);
            high = Math.max(high, y + l);
        }

        double resultY = 0.0;

        // Binary search on Y-coordinate
        while (high - low > 1e-5) {
            double midY = low + (high - low) / 2.0;
            resultY = midY;

            if (check(squares, midY, total)) {
                // Bottom area too large → move line down
                high = midY;
            } else {
                // Bottom area too small → move line up
                low = midY;
            }
        }

        return resultY;
    }
}
