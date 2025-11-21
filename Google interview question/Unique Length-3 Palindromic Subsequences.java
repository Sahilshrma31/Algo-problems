
/*******************************************************************************************
 * LeetCode 1930 — Unique Length-3 Palindromic Subsequences
 *
 * ---------------- THEORY (COMMENTED FOR GITHUB CODE FILE) ----------------
 * Problem Summary:
 * - We need to count all UNIQUE palindromic subsequences of length 3.
 * - A length-3 palindrome looks like:  x _ x  (same letter at both ends)
 * - Only the middle letter can vary.
 *
 * Intuition:
 * - Choose a character 'x' as the first and last character.
 * - Find its FIRST and LAST occurrence in the string.
 * - All characters between these two positions can serve as middle.
 * - Count unique middle characters.
 * - Do this for all characters 'a' to 'z'.
 *
 * Approach:
 * 1. Collect all distinct letters in the string.
 * 2. For each letter:
 *      - Find first index where it appears.
 *      - Find last index where it appears.
 *      - Collect unique characters between them.
 * 3. Add their count to answer.
 *
 * Time Complexity:   O(26 * n) ≈ O(n)
 * Space Complexity:  O(1)  (middle set max 26 chars)
 * -------------------------------------------------------------------------
 *******************************************************************************************/

// --------------------- CODE (AS IS, CLEAN & MINIMAL) ---------------------
import java.util.*;
class Solution1 {
    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        Set<Character> uniqueLetters = new HashSet<>();

        for (char ch : s.toCharArray()) {
            uniqueLetters.add(ch);
        }

        int result = 0;

        for (char letter : uniqueLetters) {
            int firstIdx = -1;
            int lastIdx = -1;

            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == letter) {
                    if (firstIdx == -1) firstIdx = i;
                    lastIdx = i;
                }
            }

            if (firstIdx == lastIdx) continue;

            Set<Character> middleSet = new HashSet<>();
            for (int mid = firstIdx + 1; mid < lastIdx; mid++) {
                middleSet.add(s.charAt(mid));
            }

            result += middleSet.size();
        }

        return result;
    }
}


//Approach-2 (Storing first and last indices before hand
//T.C : O(n)
//S.C : O(1) - Only 26 sized always
class Solution {
    public int countPalindromicSubsequence(String s) {
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);
        
        for (int i = 0; i < s.length(); i++) {
            int curr = s.charAt(i) - 'a';
            if (first[curr] == - 1) {
                first[curr] = i;
            }
            
            last[curr] = i;
        }
        
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) {
                continue;
            }
            
            Set<Character> st = new HashSet();
            for (int middle = first[i] + 1; middle < last[i]; middle++) {
                st.add(s.charAt(middle));
            }
            
            ans += st.size();
        }
        
        return ans;
    }
}