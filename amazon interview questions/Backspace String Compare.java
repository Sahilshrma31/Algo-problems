/*
====================================================
📌 Problem: Backspace String Compare
LeetCode: 844. Backspace String Compare
====================================================

Given two strings s and t, return true if they are equal
when both are typed into empty text editors.

'#' means a backspace character.

----------------------------------------------------
🧠 Approach
----------------------------------------------------
- Simulate typing using StringBuilder
- Append normal characters
- On '#', remove last character if present
- Compare final processed strings

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n + m)
n = length of s
m = length of t

----------------------------------------------------
🧠 Space Complexity
----------------------------------------------------
O(n + m)
for building processed strings

====================================================
*/

class Solution {

    public boolean backspaceCompare(String s, String t) {
        return backSpace(s).equals(backSpace(t));
    }

    private String backSpace(String s) {
        StringBuilder temp = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (ch != '#') {
                temp.append(ch);
            } else if (temp.length() > 0) {
                temp.deleteCharAt(temp.length() - 1);
            }
        }
        return temp.toString();
    }
}
