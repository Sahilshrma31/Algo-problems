/*
====================================================
📌 Problem: Sort Vowels in a String
====================================================

Given a string s, sort only the vowels in the string
in non-decreasing order while keeping the positions
of consonants unchanged.

Vowels include both lowercase and uppercase:
a, e, i, o, u, A, E, I, O, U

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
We only care about vowels:
1) Identify all vowels
2) Sort them
3) Place them back in the original string positions

----------------------------------------------------
🧩 Approaches Covered
----------------------------------------------------
1️⃣ Frequency Map + Fixed Order (Counting Sort style)
2️⃣ Extract → Sort → Reinsert (Simpler approach)

----------------------------------------------------
⏱ Time & Space Complexity
----------------------------------------------------
Let N = length of string

Approach 1:
Time:  O(N)
Space: O(1)  (fixed 10 vowels)

Approach 2:
Time:  O(N log N)
Space: O(N)

====================================================
*/

import java.util.*;

class Solution {

    /* ============================================
       Utility function to check vowel
       ============================================ */
    private boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        return ch == 'a' || ch == 'e' || ch == 'i'
            || ch == 'o' || ch == 'u';
    }

    /* ==================================================
       1️⃣ APPROACH 1: Frequency Map + Fixed Order
       ==================================================
       Idea:
       - Count frequency of each vowel
       - Place vowels back in sorted order
    */
    public String sortVowelsUsingFrequency(String s) {

        // Count frequency of vowels
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            if (isVowel(ch)) {
                map.put(ch, map.getOrDefault(ch, 0) + 1);
            }
        }

        // Fixed vowel order (already sorted)
        String vowels = "AEIOUaeiou";
        int j = 0;

        char[] result = s.toCharArray();

        // Replace vowels in sorted order
        for (int i = 0; i < result.length; i++) {
            if (isVowel(result[i])) {

                while (map.getOrDefault(vowels.charAt(j), 0) == 0) {
                    j++;
                }

                result[i] = vowels.charAt(j);
                map.put(vowels.charAt(j),
                        map.get(vowels.charAt(j)) - 1);
            }
        }

        return new String(result);
    }

    /* ==================================================
       2️⃣ APPROACH 2: Extract → Sort → Reinsert
       ==================================================
       Idea:
       - Extract all vowels
       - Sort them
       - Put them back in original positions
    */
    public String sortVowelsUsingSorting(String s) {

        // Extract vowels
        StringBuilder temp = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (isVowel(ch)) {
                temp.append(ch);
            }
        }

        // Sort vowels
        char[] vowels = temp.toString().toCharArray();
        Arrays.sort(vowels);

        // Reinsert vowels
        int j = 0;
        char[] result = s.toCharArray();

        for (int i = 0; i < result.length; i++) {
            if (isVowel(result[i])) {
                result[i] = vowels[j++];
            }
        }

        return new String(result);
    }
}
