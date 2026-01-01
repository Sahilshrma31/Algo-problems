/*
====================================================
📌 Problem: Word Pattern
====================================================

Given a pattern and a string s, check whether s follows
the same pattern.

A bijection must exist between characters in pattern
and words in s.

----------------------------------------------------
🧠 Approach
----------------------------------------------------
- Split the string into words
- Use:
  1) HashMap<Character, String> → pattern → word mapping
  2) HashSet<String> → ensure no two characters map to same word
- Validate mapping consistency

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n) — n = number of words

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(n) — HashMap + HashSet

====================================================
*/

import java.util.*;

class Solution {
    public boolean wordPattern(String pattern, String s) {

        String[] words = s.split(" ");
        int n = words.length;

        if (pattern.length() != n) return false;

        HashMap<Character, String> map = new HashMap<>();
        HashSet<String> usedWords = new HashSet<>();

        for (int i = 0; i < n; i++) {
            char ch = pattern.charAt(i);
            String currWord = words[i];

            if (map.containsKey(ch)) {
                if (!map.get(ch).equals(currWord)) {
                    return false;
                }
            } else {
                if (usedWords.contains(currWord)) {
                    return false;
                }
                map.put(ch, currWord);
                usedWords.add(currWord);
            }
        }

        return true;
    }
}
