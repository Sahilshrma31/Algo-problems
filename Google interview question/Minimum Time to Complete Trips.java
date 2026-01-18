/*
====================================================
📌 Problem: Minimum Time to Complete Trips
(LeetCode 2187)
====================================================

You are given:
- `time[i]` → time taken by the i-th bus to complete **one trip**
- `totalTrips` → total number of trips required

Each bus can make multiple trips **independently**.

Return the **minimum time** required so that the total number
of trips completed by all buses is **at least `totalTrips`**.

====================================================
🧠 Key Insight
====================================================

- If we fix a time `T`, we can easily check:
  how many trips can be completed in `T` time.

- Trips by one bus in time T = T / time[i]

- As time increases, total trips **monotonically increase**
  ➜ This makes it a perfect **Binary Search on Answer** problem.

====================================================
🧠 Approach: Binary Search on Time
====================================================

1️⃣ Search space:
- Minimum time = 1
- Maximum time = (minimum bus time) × totalTrips

2️⃣ For a guessed time `mid`:
- Calculate total trips possible
- If trips ≥ totalTrips → try smaller time
- Else → increase time

3️⃣ Keep shrinking the search space to find the minimum valid time

====================================================
⏱ Time Complexity
====================================================
O(n · log(maxTime))

====================================================
📦 Space Complexity
====================================================
O(1)

====================================================
*/

class Solution {

    int n;

    public long minimumTime(int[] time, int totalTrips) {

        n = time.length;

        // Find minimum bus time
        int minTime = Integer.MAX_VALUE;
        for (int t : time) {
            minTime = Math.min(minTime, t);
        }

        long left = 1;
        long right = 1L * minTime * totalTrips;
        long answer = right;

        // Binary Search
        while (left <= right) {

            long mid = left + (right - left) / 2;

            if (isPossible(time, mid, totalTrips)) {
                answer = mid;
                right = mid - 1;   // try smaller time
            } else {
                left = mid + 1;    // need more time
            }
        }

        return answer;
    }

    // Check if we can complete totalTrips within given time
    private boolean isPossible(int[] time, long givenTime, int totalTrips) {

        long trips = 0;

        for (int i = 0; i < n; i++) {
            trips += givenTime / time[i];   
            if (trips >= totalTrips) {
                return true;
            }
        }

        return false;
    }
}
