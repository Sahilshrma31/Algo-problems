/*
====================================================
📌 Problem: Find Removable Indices
====================================================

You are given two strings:
- str1
- str2

Task:
Find all indices in `str1` such that **removing exactly one
character at that index** makes `str1` equal to `str2`.

If no such index exists, return a list containing `-1`.

====================================================
🧠 Key Observations
====================================================

- str1 must be exactly **one character longer** than str2
- We need to efficiently check:
  - Prefix before index `i` matches
  - Suffix after index `i` matches

====================================================
🧠 Approach: Prefix & Suffix Matching
====================================================

1️⃣ If `str1.length != str2.length + 1` → impossible

2️⃣ Build:
- prefix[i]:
  true if str1[0..i-1] == str2[0..i-1]

- suffix[i]:
  true if str1[i+1..end] == str2[i..end]

3️⃣ For each index `i`:
- If prefix[i] && suffix[i] → valid removable index

====================================================
⏱ Time Complexity
====================================================
O(n)

====================================================
📦 Space Complexity
====================================================
O(n)

====================================================
*/

import java.util.*;

class Solution {

    public static List<Integer> getRemovableIndices(String str1, String str2) {

        List<Integer> result = new ArrayList<>();

        // Length check
        if (str1.length() != str2.length() + 1) {
            result.add(-1);
            return result;
        }

        int n = str1.length();
        int m = str2.length();

        boolean[] prefix = new boolean[n + 1];
        boolean[] suffix = new boolean[n + 1];

        // PREFIX MATCHING
        prefix[0] = true;
        for (int i = 1; i <= m; i++) {
            prefix[i] = prefix[i - 1] &&
                        str1.charAt(i - 1) == str2.charAt(i - 1);
        }

        // SUFFIX MATCHING
        suffix[n] = true;
        for (int i = n - 1; i >= 0; i--) {
            if (i < m) {
                suffix[i] = suffix[i + 1] &&
                            str1.charAt(i + 1) == str2.charAt(i);
            } else {
                suffix[i] = suffix[i + 1];
            }
        }

        // CHECK ALL INDICES
        for (int i = 0; i < n; i++) {
            if (prefix[i] && suffix[i]) {
                result.add(i);
            }
        }

        if (result.isEmpty()) {
            result.add(-1);
        }

        return result;
    }
}

