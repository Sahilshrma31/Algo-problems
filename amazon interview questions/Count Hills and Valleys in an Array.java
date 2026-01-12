/*
====================================================
📌 Problem: Count Hills and Valleys in an Array
(LeetCode 2210)
====================================================

A position i is:
- a **hill** if nums[i] > nums[i-1] and nums[i] > nums[i+1]
- a **valley** if nums[i] < nums[i-1] and nums[i] < nums[i+1]

----------------------------------------------------
🧠 Approach (As Given)
----------------------------------------------------
- Use two pointers:
  i → previous significant index
  j → current index
- Traverse array and check hill/valley condition
- When found, increment count and update i

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

    public int countHillValley(int[] nums) {
        int i = 0;
        int j = 1;
        int n = nums.length;
        int cnt = 0;

        while (j + 1 < n) {
            if ((nums[i] < nums[j] && nums[j] > nums[j + 1]) ||
                (nums[i] > nums[j] && nums[j] < nums[j + 1])) {
                cnt++;
                i = j;
            }
            j++;
        }

        return cnt;
    }
}

