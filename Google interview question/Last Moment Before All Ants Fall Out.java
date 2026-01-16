/*
====================================================
📌 Problem: Last Moment Before All Ants Fall Out
(LeetCode 1503)
====================================================

You are given:
- An integer `n` → length of a plank (0 to n)
- `left[]`  → positions of ants moving left
- `right[]` → positions of ants moving right

All ants move at the same speed.
When two ants meet, they **swap directions**, which is equivalent
to them **passing through each other**.

Return the **last moment** when any ant is still on the plank.

====================================================
🧠 Key Observation
====================================================

- Direction swapping does NOT affect the final answer
- Each ant can be treated as moving independently

So:
- Ant moving left at position `x` falls off in `x` time
- Ant moving right at position `x` falls off in `n - x` time

The answer is simply the **maximum** time among all ants.

====================================================
🧠 Approach
====================================================

1. Initialize `maxTime = 0`
2. For every ant in `left[]`:
   - Time = position
   - Update maxTime
3. For every ant in `right[]`:
   - Time = n - position
   - Update maxTime
4. Return maxTime

====================================================
⏱ Time Complexity
====================================================
O(L + R)

L = left.length  
R = right.length  

====================================================
📦 Space Complexity
====================================================
O(1)

====================================================
*/

class Solution {

    public int getLastMoment(int n, int[] left, int[] right) {

        int maxTime = 0;

        // Ants moving left
        for (int pos : left) {
            maxTime = Math.max(maxTime, pos);
        }

        // Ants moving right
        for (int pos : right) {
            maxTime = Math.max(maxTime, n - pos);
        }

        return maxTime;
    }
}

