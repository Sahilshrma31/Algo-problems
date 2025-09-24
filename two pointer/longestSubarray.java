/*
================================================================================
Problem: Longest Subarray of 1's After Deleting One Element (LeetCode 1493)
================================================================================

📝 Problem Summary:
-------------------
Given a binary array nums, return the length of the longest subarray of 1's 
after deleting exactly one element. 
- You must delete one element (it can be either 0 or 1).
- A subarray is a contiguous non-empty sequence of elements.

Example 1:
Input: nums = [1,1,0,1]
Output: 3
Explanation: Delete the zero, the longest subarray of 1's is [1,1,1] with length 3.

Example 2:
Input: nums = [0,1,1,1,0,1,1,0,1]
Output: 5
Explanation: Delete one zero (either at index 4 or 7), longest subarray = [1,1,1,1,1].

Example 3:
Input: nums = [1,1,1]
Output: 2
Explanation: Must delete one element, so answer = 2.

================================================================================
Intuition:
----------
- We need the longest run of 1's **allowing one zero to be skipped**.
- Essentially, we are allowed at most one zero inside the window. 
- If there are no zeros in the array, we still must delete one element, 
  so answer = n-1.

================================================================================
Approach 1: Brute Force (O(n^2))
--------------------------------
1. Try deleting each index (0..n-1).
2. After deleting, scan the array and find the longest streak of 1's.
3. Keep track of the maximum length found.

This is too slow for large n (n ≤ 10^5), but works conceptually.

Code:
*/
class BruteForceSolution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int max = 0;
        for (int del = 0; del < n; del++) {
            int count = 0, best = 0;
            for (int i = 0; i < n; i++) {
                if (i == del) continue; // skip deleted element
                if (nums[i] == 1) {
                    count++;
                    best = Math.max(best, count);
                } else {
                    count = 0;
                }
            }
            max = Math.max(max, best);
        }
        return max;
    }
}

/*
Time Complexity: O(n^2)
Space Complexity: O(1)
Too slow for large input.

================================================================================
Approach 2: Optimized Two-Pointer / Sliding Window (O(n))
---------------------------------------------------------
- Maintain a sliding window [i..j].
- Allow at most one zero in the window.
- If more than one zero appears, move i just past the previous zero.
- Track the maximum length (j - i) as we slide.

Key Detail:
- We use (j - i), not (j - i + 1), because we must delete exactly one element.

Code:
*/
class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int i = 0;
        int j = 0;
        int max = 0;
        int lastZeroIdx = -1; // last seen zero

        while (j < n) {
            if (nums[j] == 0) {
                // Shift left pointer right after last zero
                i = lastZeroIdx + 1;
                lastZeroIdx = j;
            }
            max = Math.max(max, j - i); // length after deleting one
            j++;
        }
        return max;
    }
}

/*
Time Complexity: O(n)
Space Complexity: O(1)
Efficient for large input.
*/
