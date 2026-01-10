/*
====================================================
📌 Problem: Compare Version Numbers
(LeetCode 165)
====================================================

You are given two version numbers version1 and version2.
Each version consists of revision numbers separated by dots '.'.

Compare the two versions:
- return 1  if version1 > version2
- return -1 if version1 < version2
- return 0  if they are equal

Trailing zero revisions are ignored.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
1️⃣ Split both versions by '.'
2️⃣ Compare corresponding revision numbers one by one
3️⃣ If one version runs out of revisions, treat missing parts as 0
4️⃣ First mismatch decides the answer

----------------------------------------------------
🛠 Approach
----------------------------------------------------
- Split strings using "\\."
- Loop till max length of both arrays
- Convert revision to integer (or 0 if missing)
- Compare values

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(max(N, M))
N = number of revisions in version1
M = number of revisions in version2

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(N + M) for split arrays

====================================================
*/

class Solution {

    public int compareVersion(String version1, String version2) {

        String[] arr1 = version1.split("\\.");
        String[] arr2 = version2.split("\\.");

        int n = arr1.length;
        int m = arr2.length;

        int len = Math.max(n, m);

        for (int i = 0; i < len; i++) {

            int num1 = (i < n) ? Integer.parseInt(arr1[i]) : 0;
            int num2 = (i < m) ? Integer.parseInt(arr2[i]) : 0;

            if (num1 > num2) return 1;
            if (num1 < num2) return -1;
        }

        return 0;
    }
}
