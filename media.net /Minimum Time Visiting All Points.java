/*
====================================================
📌 Problem: Minimum Time Visiting All Points
(LeetCode 1266)
====================================================

On a 2D plane, you are given points[i] = [xi, yi].
Starting from points[0], you must visit all points
in the given order.

In one second, you can:
- Move vertically
- Move horizontally
- Move diagonally

Return the minimum time needed to visit all points.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
To move from point A(x1, y1) to B(x2, y2):

- Horizontal moves needed = |x2 - x1|
- Vertical moves needed   = |y2 - y1|

Since diagonal moves can cover both directions
simultaneously, the minimum time required is:

👉 max(|x2 - x1|, |y2 - y1|)

----------------------------------------------------
🧩 Approach
----------------------------------------------------
1️⃣ Iterate through consecutive points
2️⃣ For each pair, compute dx and dy
3️⃣ Add max(dx, dy) to the answer

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n)

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(1)

====================================================
*/

class Solution {

    public int minTimeToVisitAllPoints(int[][] points) {
        int n = points.length;
        int time = 0;

        for (int i = 1; i < n; i++) {
            int dx = Math.abs(points[i][0] - points[i - 1][0]);
            int dy = Math.abs(points[i][1] - points[i - 1][1]);

            time += Math.max(dx, dy);
        }

        return time;
    }
}
