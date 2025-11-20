/*
    🎯 Problem: 757. Set Intersection Size At Least Two
    🔗 LeetCode Link: https://leetcode.com/problems/set-intersection-size-at-least-two/

    🧩 Problem Summary:
    You are given a list of intervals.  
    You must choose the *minimum number of integers* such that:
    Every interval has **at least 2 chosen integers** inside it.

    Return the minimum size of such a set.

    ------------------------------------------------------------
    💡 Intuition:
    Classic greedy strategy based on interval sorting.

    If we sort intervals:
        - by ending point ASC
        - ties: by starting point DESC  
    Then, to satisfy an interval [l, r]:
        - If we already have 2 chosen integers inside, great.
        - If we have 0, we must add the last two possible numbers: (r-1, r).
        - If we have 1, add the largest possible number r.

    Why greedy works?
    ✔ Picking numbers near the *right end* of an interval helps 
      maximize overlap with future intervals.
    ✔ Sorting ensures we handle tighter intervals earlier.

    ------------------------------------------------------------
    ⚙️ Approach:
    1️⃣ Sort intervals by (end ASC, start DESC).  
    2️⃣ Maintain the last two chosen numbers: `first`, `second`.  
    3️⃣ For each interval:
         - If both chosen numbers fit → skip.
         - If none fit → add two new numbers near `r`.
         - If one fits → add one more.

    ------------------------------------------------------------
*/

import java.util.*;

class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        int n = intervals.length;

        // Sort by end ASC, start DESC
        Arrays.sort(intervals, (a, b) -> {
            if (a[1] != b[1]) return a[1] - b[1];
            return b[0] - a[0];
        });

        int result = 0;
        int first = -1;   // smaller chosen number
        int second = -1;  // larger chosen number

        for (int[] interval : intervals) {
            int l = interval[0];
            int r = interval[1];

            // Case 1: both chosen points already inside
            if (first >= l) continue;

            // Case 2: no chosen points inside
            if (second < l) {
                result += 2;
                first = r - 1;
                second = r;
            }
            // Case 3: exactly one point inside
            else {
                result += 1;
                first = second;
                second = r;
            }
        }

        return result;
    }
}

/*
    ------------------------------------------------------------
    🧪 Example Dry Run:

    Input: [[1,3],[1,4],[2,5],[3,5]]

    Sorted: [[1,3],[2,5],[3,5],[1,4]]

    Following greedy rule, final answer = 3

    ------------------------------------------------------------
    ⏱️ Time Complexity:
        O(n log n) — due to sorting
    💾 Space Complexity:
        O(1)

    ------------------------------------------------------------
    📘 Author: Sahil Sharma
*/
