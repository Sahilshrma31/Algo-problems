/*
====================================================
📌 Problem: Simplify Path (LeetCode 71)
====================================================

Given an absolute Unix-style file path, simplify it.

Rules:
- "." means current directory → ignore
- ".." means go to parent directory → pop stack if possible
- Multiple "/" should be treated as single "/"
- Result must start with "/"
- No trailing "/" (unless root)

====================================================
⏱ Time Complexity: O(N)
📦 Space Complexity: O(N)
====================================================
*/

import java.util.*;

class Solution {
    public String simplifyPath(String path) {

        Stack<String> stack = new Stack<>();
        String[] parts = path.split("/");

        for (String part : parts) {
            if (part.equals("") || part.equals(".")) {
                continue;
            } else if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(part);
            }
        }

        StringBuilder result = new StringBuilder();
        for (String dir : stack) {
            result.append("/").append(dir);
        }

        return result.length() == 0 ? "/" : result.toString();
    }
}
