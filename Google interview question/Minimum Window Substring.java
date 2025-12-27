/*
====================================================
📌 Problem: Minimum Window Substring
LeetCode 76
====================================================

Given two strings s and t, return the minimum window
substring of s such that every character in t
(including duplicates) is included in the window.

If no such substring exists, return an empty string.

----------------------------------------------------
🧠 Approach: Sliding Window + HashMap
----------------------------------------------------
- Store frequency of characters of t in a map
- Use two pointers (i, j) to maintain a window
- Expand window with j until all characters are matched
- Shrink window from i to get the minimum valid window

----------------------------------------------------
⏱ Time Complexity: O(n)
📦 Space Complexity: O(1) / O(52)
----------------------------------------------------
*/

import java.util.*;

class Solution {
    public String minWindow(String s, String t) {
        int n = s.length();
        if (t.length() > n) return "";

        Map<Character, Integer> map = new HashMap<>();
        for (char ch : t.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int i = 0, j = 0;
        int count = t.length();
        int minWindowSize = Integer.MAX_VALUE;
        int startIndex = 0;

        while (j < n) {
            char ch = s.charAt(j);
            if (map.containsKey(ch) && map.get(ch) > 0) {
                count--;
            }
            map.put(ch, map.getOrDefault(ch, 0) - 1);

            while (count == 0) {
                if (j - i + 1 < minWindowSize) {
                    minWindowSize = j - i + 1;
                    startIndex = i;
                }

                char leftChar = s.charAt(i);
                map.put(leftChar, map.getOrDefault(leftChar, 0) + 1);
                if (map.containsKey(leftChar) && map.get(leftChar) > 0) {
                    count++;
                }
                i++;
            }
            j++;
        }

        return minWindowSize == Integer.MAX_VALUE
                ? ""
                : s.substring(startIndex, startIndex + minWindowSize);
    }
}
