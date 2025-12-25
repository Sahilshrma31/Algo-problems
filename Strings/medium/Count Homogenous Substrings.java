/*
====================================================
📌 Problem: Count Homogenous Substrings
====================================================

Given a string s, return the number of homogenous substrings.
A substring is homogenous if all characters in it are the same.

Since the answer can be very large, return it modulo 10^9 + 7.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
If we have a consecutive block of the same character of length L,
then the number of homogenous substrings formed from it is:

    L * (L + 1) / 2

Example:
For "aaa" (L = 3)
Substrings = "a", "a", "a", "aa", "aa", "aaa" → 6

So the idea is:
- Traverse the string
- Group consecutive same characters
- For each group, add L*(L+1)/2 to the answer

----------------------------------------------------
🧩 Approach
----------------------------------------------------
1️⃣ Traverse the string using index i
2️⃣ For each character, expand while the next character is same
3️⃣ Calculate the length of the block
4️⃣ Add number of homogenous substrings from that block
5️⃣ Take modulo at every step

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n)
- Each character is visited once

----------------------------------------------------
🧠 Space Complexity
----------------------------------------------------
O(1)
- Only constant extra variables used

====================================================
*/

class Solution {

    public static final int MOD = 1000000007;

    public int countHomogenous(String s) {
        long count = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char curr = s.charAt(i);
            int start = i;

            // Extend while same characters continue
            while (i + 1 < n && s.charAt(i + 1) == curr) {
                i++;
            }

            long len = i - start + 1;

            // Number of homogenous substrings in this block
            count = (count + (len * (len + 1) / 2) % MOD) % MOD;
        }

        return (int) count;
    }
}
