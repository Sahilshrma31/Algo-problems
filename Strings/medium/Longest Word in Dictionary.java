/*
    🎯 Problem: 720. Longest Word in Dictionary
    🔗 LeetCode Link: https://leetcode.com/problems/longest-word-in-dictionary/

    🧩 Problem Summary:
    You are given an array of strings `words`.
    Return the longest word that can be built one character at a time
    by other words in the array.

    If there are multiple results, return the lexicographically smallest one.

    ------------------------------------------------------------
    Example:
    Input:  words = ["w","wo","wor","worl","world"]
    Output: "world"

    Explanation:
    - "world" can be built as "w" → "wo" → "wor" → "worl" → "world"
    ------------------------------------------------------------

    💡 Intuition:
    - Sort the words lexicographically.
      → ensures prefixes appear before longer words (e.g., "a", "ap", "app", "apple").
    - Maintain a HashSet `valid` of buildable words.
    - A word is valid if:
         - Its length == 1 (base case)
         - OR its prefix (w.substring(0, w.length()-1)) exists in valid set.

    ------------------------------------------------------------
    ⚙️ Approach:
    1️⃣ Sort the words array lexicographically.
    2️⃣ Initialize a HashSet to track buildable words.
    3️⃣ For each word:
          - Check if it can be built from shorter valid prefixes.
          - If yes → add to set and update result.
    4️⃣ Track the longest valid word (and lexicographically smallest if tie).

    ------------------------------------------------------------
*/

import java.util.*;

class Solution {
    public String longestWord(String[] words) {
        Arrays.sort(words);  // Sort lexicographically (ensures prefix order)

        Set<String> valid = new HashSet<>();
        String result = "";

        for (String w : words) {
            // ✅ Base Case: single-letter words are valid
            // ✅ Inductive Step: current word is valid if its prefix exists in the set
            if (w.length() == 1 || valid.contains(w.substring(0, w.length() - 1))) {
                valid.add(w);

                // Update result — longer word preferred, or lexicographically smaller if equal
                if (w.length() > result.length()) {
                    result = w;
                }
            }
        }

        return result;
    }
}


/*
    ------------------------------------------------------------
    🧠 Dry Run Example:
    Input: ["a", "banana", "app", "appl", "ap", "apply", "apple"]

    Sorted:
    ["a", "ap", "app", "appl", "apple", "apply", "banana"]

    - "a" → valid (length=1)
    - "ap" → prefix "a" exists → valid
    - "app" → prefix "ap" exists → valid
    - "appl" → prefix "app" exists → valid
    - "apple" → prefix "appl" exists → valid ✅ result="apple"
    - "apply" → prefix "appl" exists → also valid (same length, but "apple" < "apply")

    ✅ Final Answer: "apple"
    ------------------------------------------------------------

    ⏱️ Time Complexity:  O(n * log n + n * L)
         → Sorting: O(n log n)
         → Checking prefix & set insertion: O(n * L), where L = average word length

    💾 Space Complexity: O(n)
         → Storing words in HashSet

    ------------------------------------------------------------
    📘 Author: Sahil Sharma
*/
