/*
====================================================
📌 Problem: Buddy Strings
(LeetCode 859)
====================================================

You are given two strings s and goal.
You can swap exactly two characters in s.

Return true if you can make s equal to goal
after exactly one swap, otherwise return false.

----------------------------------------------------
🧠 Key Observations
----------------------------------------------------
1️⃣ If lengths differ → impossible
2️⃣ If s == goal:
   - There must be at least one duplicate character
   - So swapping duplicates keeps string same
3️⃣ If s != goal:
   - Exactly two indices must differ
   - Swapping those two characters in s
     should make it equal to goal

----------------------------------------------------
🛠 Approach
----------------------------------------------------
• Handle equal-string case using frequency check
• Track mismatched indices
• Perform one swap and compare

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n)

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(1) (26-size frequency array)

====================================================
*/

import java.util.*;

class Solution {

    // Check if string has any duplicate character
    private boolean checkFreq(String s) {
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
            if (freq[ch - 'a'] > 1) {
                return true;
            }
        }
        return false;
    }

    public boolean buddyStrings(String s, String goal) {

        if (s.length() != goal.length()) {
            return false;
        }

        // Case 1: Strings already equal
        if (s.equals(goal)) {
            return checkFreq(s);
        }

        // Case 2: Strings differ
        List<Integer> indices = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                indices.add(i);
            }
        }

        // Must differ at exactly two positions
        if (indices.size() != 2) {
            return false;
        }

        int first = indices.get(0);
        int second = indices.get(1);

        // Swap characters in s
        char[] arr = s.toCharArray();
        char temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;

        return new String(arr).equals(goal);
    }
}

