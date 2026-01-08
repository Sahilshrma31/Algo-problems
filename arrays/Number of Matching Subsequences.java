/*
====================================================
📌 Problem: Number of Matching Subsequences
(LeetCode 792)
====================================================

Given a string s and an array of strings words,
return the number of words that are subsequences of s.

----------------------------------------------------
🧠 Core Idea
----------------------------------------------------
Preprocess string s:
- For each character 'a' to 'z', store all indices
  where it appears in s.

For each word:
- Try to match characters one by one in s
- Use binary search (upper bound) to find the next
  valid index greater than the previous matched index

----------------------------------------------------
🛠 Approach
----------------------------------------------------
1️⃣ Build an array of lists `pos[26]`
   pos[c] stores indices where character c occurs in s

2️⃣ For each word:
   - Maintain `prev` = last matched index in s
   - For every character, binary search in pos[c]
     to find index > prev
   - If not found → word is NOT a subsequence

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
Let:
- N = length of s
- W = number of words
- L = average length of words

Preprocessing: O(N)  
Each word check: O(L * log N)

Total: O(N + W * L * log N)

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(N) for index lists

====================================================
*/

import java.util.*;

class Solution {

    public int numMatchingSubseq(String s, String[] words) {

        // Store indices of each character in s
        List<Integer>[] pos = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            pos[i] = new ArrayList<>();
        }

        for (int i = 0; i < s.length(); i++) {
            pos[s.charAt(i) - 'a'].add(i);
        }

        int count = 0;

        // Check each word
        for (String word : words) {
            boolean isSubsequence = true;
            int prev = -1;

            for (char ch : word.toCharArray()) {
                List<Integer> list = pos[ch - 'a'];
                int idx = upperBound(list, prev);

                if (idx == list.size()) {
                    isSubsequence = false;
                    break;
                }
                prev = list.get(idx);
            }

            if (isSubsequence) count++;
        }

        return count;
    }

    // Binary search to find first element > prev
    private int upperBound(List<Integer> list, int prev) {
        int l = 0, r = list.size();

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (list.get(mid) <= prev) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
