/*
====================================================
📌 Problem: Minimum Number of Arrows to Burst Balloons
====================================================

You are given a 2D array `points` where:
points[i] = [start, end] represents a balloon
that spans from x = start to x = end on the x-axis.

An arrow shot at position x will burst all balloons
for which start <= x <= end.

Your task is to find the minimum number of arrows
required to burst all balloons.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
This is a classic **interval overlap / greedy** problem.

If multiple balloons overlap, we can burst them using
a single arrow by shooting within their overlapping range.

If a balloon does NOT overlap with the previous group,
we need a new arrow.

----------------------------------------------------
🧩 Greedy Strategy
----------------------------------------------------
1️⃣ Sort balloons by their start coordinate.
2️⃣ Keep track of the current overlapping interval.
3️⃣ If the next balloon overlaps with the current one:
      → shrink the overlap window.
4️⃣ If it does NOT overlap:
      → increment arrow count and start a new window.

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
Sorting balloons: O(N log N)
Single traversal: O(N)

➡ Total Time: O(N log N)

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(1) extra space (sorting excluded)

====================================================
*/

import java.util.*;

class Solution {

    public int findMinArrowShots(int[][] points) {

        // Edge case
        if (points == null || points.length == 0) {
            return 0;
        }

        int n = points.length;
        int arrows = 1;

        // Sort balloons by start point
        Arrays.sort(points, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        // Initialize first overlapping interval
        int[] prev = points[0];

        for (int i = 1; i < n; i++) {

            int[] curr = points[i];

            int currStart = curr[0];
            int currEnd = curr[1];

            int prevStart = prev[0];
            int prevEnd = prev[1];

            // Case 1: No overlap → need a new arrow
            if (currStart > prevEnd) {
                arrows++;
                prev = curr;
            }
            // Case 2: Overlap → shrink the overlap window
            else {
                prev[0] = Math.max(prevStart, currStart);
                prev[1] = Math.min(prevEnd, currEnd);
            }
        }

        return arrows;
    }
}
