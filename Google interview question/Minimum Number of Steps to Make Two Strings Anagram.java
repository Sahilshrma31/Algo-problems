/*
====================================================
📌 Problem: Minimum Number of Steps to Make Two Strings Anagram
(LeetCode 1347)
====================================================

You are given two strings s and t of equal length.
In one step, you can replace any character in t
with another lowercase English letter.

Return the minimum number of steps required
to make t an anagram of s.

----------------------------------------------------
🧠 Key Idea
----------------------------------------------------
- Count frequency difference between s and t
- Extra characters in t must be replaced
- Sum of negative frequency differences gives the answer

----------------------------------------------------
🛠 Approach
----------------------------------------------------
1️⃣ Count characters in s
2️⃣ Subtract characters of t
3️⃣ Sum absolute value of negative counts

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n)

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(1) (26-length frequency array)

====================================================
*/

class Solution {

    public int minSteps(String s, String t) {

        int[] freq = new int[26];

        // Count frequency of characters in s
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Subtract frequency of characters in t
        for (char c : t.toCharArray()) {
            freq[c - 'a']--;
        }

        // Count steps needed
        int steps = 0;
        for (int count : freq) {
            if (count < 0) {
                steps += -count;
            }
        }

        return steps;
    }
}
