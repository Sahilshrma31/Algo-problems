/*
💡 Problem: 1578. Minimum Time to Make Rope Colorful
---------------------------------------------------
You are given:
- A string `colors` where each character represents the color of a balloon.
- An integer array `neededTime` where `neededTime[i]` is the time needed to remove the i-th balloon.

Goal:
Remove some balloons so that no two adjacent balloons have the same color.
Return the **minimum total time** needed to achieve this.

---------------------------------------------------
🧠 Intuition:
If two adjacent balloons have the same color, we can only keep one.
We should **remove the one with smaller time**, keeping the max time balloon intact.

So we iterate through the string:
- If adjacent colors differ → reset group.
- If same → add the min of current and previous times to total cost, 
  and keep track of the max time (since that balloon remains).

---------------------------------------------------
⚙️ Approach:
1. Initialize `time = 0` and `prevMax = 0`.
2. Traverse the string:
   - If current color ≠ previous → reset `prevMax = 0`.
   - Add `min(prevMax, curr)` to total time.
   - Update `prevMax = max(prevMax, curr)`.
3. Return `time`.

---------------------------------------------------
⏱ Time Complexity: O(N)
💾 Space Complexity: O(1)
*/

class Solution {
    public int minCost(String colors, int[] neededTime) {
        int time = 0;
        int prevMax = 0;

        for (int i = 0; i < colors.length(); i++) {
            int curr = neededTime[i];

            // Reset if color changes
            if (i > 0 && colors.charAt(i) != colors.charAt(i - 1)) {
                prevMax = 0;
            }

            // Add the minimum removal time between duplicates
            time += Math.min(prevMax, curr);

            // Keep the balloon with higher time
            prevMax = Math.max(prevMax, curr);
        }

        return time;
    }
}
