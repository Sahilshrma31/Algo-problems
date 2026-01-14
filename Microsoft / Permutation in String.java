/*
====================================================
📌 Problem: Permutation in String (LeetCode 567)
====================================================

Given two strings s1 and s2, return true if s2 contains
a permutation of s1, otherwise return false.

A permutation means the characters of s1 can be rearranged
to form a substring of s2.

----------------------------------------------------
🧠 Approach: Sliding Window + Frequency Count
----------------------------------------------------
- Use two frequency arrays of size 26
- One for s1, one for the current window in s2
- Maintain a window of size |s1| in s2
- At each step, compare both frequency arrays

----------------------------------------------------
⏱ Time Complexity:
- O(26 * m) ≈ O(m) where m = length of s2

📦 Space Complexity:
- O(26) ≈ O(1)

====================================================
*/

import java.util.*;

class Solution {

    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        if (n > m) return false;

        int[] s1freq = new int[26];
        int[] s2freq = new int[26];

        // Frequency of s1
        for (int i = 0; i < n; i++) {
            s1freq[s1.charAt(i) - 'a']++;
        }

        int i = 0, j = 0;

        while (j < m) {
            // Add current character to window
            s2freq[s2.charAt(j) - 'a']++;

            // Shrink window if size exceeds s1 length
            if (j - i + 1 > n) {
                s2freq[s2.charAt(i) - 'a']--;
                i++;
            }

            // Compare frequency arrays
            if (Arrays.equals(s1freq, s2freq)) {
                return true;
            }

            j++;
        }

        return false;
    }
}
