/*
====================================================
📌 Problem: Longest Repeating Character Replacement
(LeetCode 424)
====================================================

You are given a string s consisting of uppercase English letters
and an integer k.

You can replace at most k characters in the string so that the
resulting substring contains only one repeating character.

Return the length of the longest such substring.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
We want the longest window where:
(window length - frequency of most frequent character) ≤ k

Why?
Because the remaining characters can be replaced (at most k).

----------------------------------------------------
🧩 Approaches Covered
----------------------------------------------------
1️⃣ Brute Force (Check all substrings)
2️⃣ Optimized Sliding Window (Best / Optimal)

----------------------------------------------------
⏱ Time & Space Complexity
----------------------------------------------------

1️⃣ Brute Force:
   Time: O(N³)  (generate substring + count frequency)
   Space: O(26)

2️⃣ Sliding Window (Optimal):
   Time: O(N)
   Space: O(26)

====================================================
*/

import java.util.*;

class Solution {

    /* ===========================
       1️⃣ BRUTE FORCE APPROACH
       =========================== */

    public int characterReplacementBrute(String s, int k) {
        int n = s.length();
        int ans = 0;

        for (int i = 0; i < n; i++) {
            int[] freq = new int[26];
            int maxFreq = 0;

            for (int j = i; j < n; j++) {
                freq[s.charAt(j) - 'A']++;
                maxFreq = Math.max(maxFreq, freq[s.charAt(j) - 'A']);

                int windowLen = j - i + 1;
                int replacementsNeeded = windowLen - maxFreq;

                if (replacementsNeeded <= k) {
                    ans = Math.max(ans, windowLen);
                }
            }
        }
        return ans;
    }

    /* ==================================
       2️⃣ SLIDING WINDOW (OPTIMAL)
       ================================== */

    public int characterReplacement(String s, int k) {

        int[] freq = new int[26];
        int i = 0;
        int maxFreq = 0;
        int ans = 0;

        for (int j = 0; j < s.length(); j++) {

            freq[s.charAt(j) - 'A']++;
            maxFreq = Math.max(maxFreq, freq[s.charAt(j) - 'A']);

            // shrink window if replacements exceed k
            while ((j - i + 1) - maxFreq > k) {
                freq[s.charAt(i) - 'A']--;
                i++;
            }

            ans = Math.max(ans, j - i + 1);
        }

        return ans;
    }
}
