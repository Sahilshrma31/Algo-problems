// LeetCode Problem: Longest Substring Without Repeating Characters
// Problem Statement:
// Given a string s, find the length of the longest substring without repeating characters.
// Example: s = "abcabcbb" -> Output = 3 (substring "abc")

import java.util.*;

class SolutionHashMap {
    // Approach 1: Using HashMap
    // Intuition:
    // We use a sliding window [l, r] and a HashMap to store the last index of each character.
    // When we see a duplicate character, we move the left pointer 'l' to avoid repetition.
    // This ensures we always maintain a valid window of unique characters.
    
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int l = 0, maxlen = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for (int r = 0; r < n; r++) {
            char c = s.charAt(r);

            if (map.containsKey(c)) {
                l = Math.max(l, map.get(c) + 1);
            }

            map.put(c, r);
            maxlen = Math.max(maxlen, r - l + 1);
        }

        return maxlen;
    }
}

class SolutionArray {
    // Approach 2: Using Array (Optimized)
    // Intuition:
    // Instead of HashMap, use an array of fixed size (256 for ASCII) to store last seen index of each character.
    // This avoids HashMap overhead and makes lookups faster.
    
    public int lengthOfLongestSubstring(String s) {
        int[] lastSeen = new int[256];
        Arrays.fill(lastSeen, -1);

        int l = 0, maxlen = 0;

        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);

            if (lastSeen[c] != -1) {
                l = Math.max(l, lastSeen[c] + 1);
            }

            lastSeen[c] = r;
            maxlen = Math.max(maxlen, r - l + 1);
        }

        return maxlen;
    }
}

/*
--------------------
Complexity Analysis:
--------------------
1. HashMap Approach:
   - Time Complexity: O(n)  (each character processed once)
   - Space Complexity: O(min(n, charset)) (HashMap stores characters, up to all unique ones)

2. Array Approach:
   - Time Complexity: O(n)
   - Space Complexity: O(256) = O(1) if ASCII (or O(26) if lowercase only)

Both approaches are optimal in time. The array approach is faster in practice due to constant-time lookups.
*/
