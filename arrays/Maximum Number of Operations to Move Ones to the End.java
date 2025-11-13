/*
    🎯 Problem: 3228. Maximum Number of Operations to Move Ones to the End
    🔗 LeetCode Link: https://leetcode.com/problems/maximum-number-of-operations-to-move-ones-to-the-end/

    🧩 Problem Summary:
    You are given a binary string `s` consisting of characters '0' and '1'.

    In one operation, you can choose a substring "01" and convert it into "10".
    You can perform this operation any number of times until all '1's are moved to the end of the string.
    Return the maximum number of such operations possible.

    Example:
    Input:  s = "1001101"
    Output: 5

    ------------------------------------------------------------
    💡 Intuition:
    - Every '0' can potentially move past all the '1's that appear before it.
    - Therefore, every time we encounter a '0', it contributes `count1` operations,
      where `count1` = number of '1's seen so far.
    - We can calculate this efficiently in one traversal.

    ------------------------------------------------------------
    ⚙️ Approach (Using While Loop – Your Version):
    1️⃣ Traverse the string using an index `i`.
    2️⃣ If the current character is '1', increment `count1`.
    3️⃣ If it's '0':
         - Add `count1` to `ops` (total operations).
         - Skip consecutive zeros until the next '1' appears to avoid redundant counting.
    4️⃣ Continue until the end of the string.
*/

class Solution {
    public int maxOperations(String s) {
        int ops = 0;
        int count1 = 0;
        int i = 0;

        while (i < s.length()) {
            if (s.charAt(i) == '1') {
                count1++;
            } else {
                ops += count1;
                // ✅ Skip all consecutive '0's to avoid redundant counting
                while (i + 1 < s.length() && s.charAt(i + 1) != '1') {
                    i++;
                }
            }
            i++;
        }
        return ops;
    }
}

/*
    ------------------------------------------------------------
    🧠 Example Dry Run:
    Input: "1001101"

    i=0 → '1' → count1 = 1
    i=1 → '0' → ops += 1 → ops = 1
    i=2 → '0' → ops += 1 → ops = 2
    i=3 → '1' → count1 = 2
    i=4 → '1' → count1 = 3
    i=5 → '0' → ops += 3 → ops = 5
    i=6 → '1' → count1 = 4

    ✅ Output = 5

    ------------------------------------------------------------
    ⏱️ Time Complexity:
       O(n)
       → Single traversal through the string.

    💾 Space Complexity:
       O(1)
       → Only uses constant variables (`count1`, `ops`, `i`).

    ------------------------------------------------------------
    🧩 Alternative Simpler Version:
    (Same logic but without inner skipping loop)
    ------------------------------------------------------------
    class Solution {
        public int maxOperations(String s) {
            int ops = 0, count1 = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '1') count1++;
                else ops += count1;
            }
            return ops;
        }
    }

    ------------------------------------------------------------
    📘 Author: Sahil Sharma
*/
