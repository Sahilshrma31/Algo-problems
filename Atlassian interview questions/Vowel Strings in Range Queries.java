/*
====================================================
📌 Problem: Vowel Strings in Range Queries
====================================================

You are given:
- An array of strings `words`
- A list of queries [l, r]

For each query, count how many strings in the range [l, r]
(start index l to r inclusive) start AND end with a vowel.

----------------------------------------------------
🧠 Approach: Prefix Sum
----------------------------------------------------
1) Convert each word into 1 or 0:
   - 1 → if it starts & ends with a vowel
   - 0 → otherwise

2) Build prefix sum array over these values.

3) Each query can be answered in O(1) using:
   prefix[r] - prefix[l-1]

----------------------------------------------------
⏱ Time Complexity:
- Preprocessing: O(n)
- Each Query: O(1)

🧠 Space Complexity:
- O(n) for prefix array

====================================================
*/

class Solution {

    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length;
        int[] prefix = new int[n];

        // Build prefix sum array
        for (int i = 0; i < n; i++) {
            if (isValid(words[i])) {
                prefix[i] = 1;
            }
            if (i > 0) {
                prefix[i] += prefix[i - 1];
            }
        }

        // Answer queries
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            if (l == 0) {
                res[i] = prefix[r];
            } else {
                res[i] = prefix[r] - prefix[l - 1];
            }
        }

        return res;
    }

    // Check if word starts and ends with a vowel
    private boolean isValid(String s) {
        int n = s.length();
        return isVowel(s.charAt(0)) && isVowel(s.charAt(n - 1));
    }

    // Helper to check vowel
    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i'
            || ch == 'o' || ch == 'u';
    }
}
