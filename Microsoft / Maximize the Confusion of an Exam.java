/*
====================================================
📌 Problem: Maximize the Confusion of an Exam
(LeetCode 2024)
====================================================

You are given a string answerKey consisting of 'T' and 'F',
and an integer k.

You can change at most k characters.
Return the maximum length of a consecutive sequence of
the same character after performing at most k changes.

----------------------------------------------------
🧠 Key Idea
----------------------------------------------------
Try making the longest window of:
1) All 'T' by flipping at most k 'F'
2) All 'F' by flipping at most k 'T'

Answer = max(of both cases)

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n)

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(1)

====================================================
*/

class Solution {

    public int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(
            maxLen(answerKey, k, 'T'),
            maxLen(answerKey, k, 'F')
        );
    }

    private int maxLen(String s, int k, char target) {
        int i = 0;
        int bad = 0;
        int maxLen = 0;

        for (int j = 0; j < s.length(); j++) {

            if (s.charAt(j) != target) {
                bad++;
            }

            while (bad > k) {
                if (s.charAt(i) != target) {
                    bad--;
                }
                i++;
            }

            maxLen = Math.max(maxLen, j - i + 1);
        }

        return maxLen;
    }
}
