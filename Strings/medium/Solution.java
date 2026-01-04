/*
====================================================
📌 Problem: Number of Ways to Divide a Long Corridor
(LeetCode 2147)
====================================================

You are given a corridor represented by a string:
- 'S' → Seat
- 'P' → Plant

You must divide the corridor into sections such that:
- Each section contains exactly 2 seats
- Dividers can only be placed between characters

Return the number of ways to divide the corridor.
Answer should be returned modulo 1e9 + 7.

----------------------------------------------------
🧠 Key Observation
----------------------------------------------------
- Total number of seats must be even and non-zero
- Every pair of seats forms a section
- Between consecutive sections, the number of valid
  divider positions equals the distance between:
  second seat of previous section and
  first seat of next section

----------------------------------------------------
🛠 Approach
----------------------------------------------------
1️⃣ Store indices of all seats
2️⃣ If seat count is odd or zero → return 0
3️⃣ For every gap between seat-pairs:
   - Multiply number of possible divider positions
4️⃣ Return result modulo 1e9+7

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n)

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(n) (to store seat positions)

====================================================
*/

import java.util.*;

public class Solution {

    private static final int MOD = 1_000_000_007;

    public int numberOfWays(String corridor) {

        // Store positions of all seats
        List<Integer> posSeats = new ArrayList<>();

        for (int i = 0; i < corridor.length(); i++) {
            if (corridor.charAt(i) == 'S') {
                posSeats.add(i);
            }
        }

        // Invalid cases
        if (posSeats.size() == 0 || posSeats.size() % 2 != 0) {
            return 0;
        }

        long result = 1;

        // End position of the first section (2nd seat)
        int prev = posSeats.get(1);

        // Process remaining seat pairs
        for (int i = 2; i < posSeats.size(); i += 2) {
            int gap = posSeats.get(i) - prev;
            result = (result * gap) % MOD;

            // Update prev to end of current section
            prev = posSeats.get(i + 1);
        }

        return (int) result;
    }
}
