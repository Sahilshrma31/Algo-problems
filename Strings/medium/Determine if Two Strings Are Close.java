/*
====================================================
📌 Problem: Determine if Two Strings Are Close
(LeetCode 1657)
====================================================

Two strings are considered CLOSE if you can transform
one into the other using the following operations:
1️⃣ Swap any two existing characters.
2️⃣ Transform every occurrence of one character into
   another existing character (and vice versa).

----------------------------------------------------
🧠 Key Observations
----------------------------------------------------
✔ Both strings must have the same length  
✔ Both strings must contain the SAME set of characters  
✔ The frequency distribution (multiset of counts)
  must be identical (order doesn’t matter)

----------------------------------------------------
🛠 Approach
----------------------------------------------------
1️⃣ Count frequency of each character in both strings  
2️⃣ Check character presence consistency  
3️⃣ Sort both frequency arrays  
4️⃣ Compare the sorted frequency arrays  

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n + 26 log 26) ≈ O(n)

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(26) ≈ O(1)

====================================================
*/

import java.util.*;

class Solution {

    public boolean closeStrings(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();

        // Lengths must be equal
        if (m != n) return false;

        int[] freq1 = new int[26];
        int[] freq2 = new int[26];

        // Count frequencies
        for (int i = 0; i < m; i++) {
            freq1[word1.charAt(i) - 'a']++;
            freq2[word2.charAt(i) - 'a']++;
        }

        // Check character presence consistency
        for (int i = 0; i < 26; i++) {
            if ((freq1[i] == 0 && freq2[i] != 0) ||
                (freq1[i] != 0 && freq2[i] == 0)) {
                return false;
            }
        }

        // Sort frequencies and compare
        Arrays.sort(freq1);
        Arrays.sort(freq2);

        return Arrays.equals(freq1, freq2);
    }
}
