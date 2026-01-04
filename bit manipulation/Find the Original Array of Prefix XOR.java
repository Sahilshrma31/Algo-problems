/*
====================================================
📌 Problem: Find the Original Array of Prefix XOR
(LeetCode 2433)
====================================================

You are given an integer array pref where:
pref[i] = arr[0] ^ arr[1] ^ ... ^ arr[i]

Your task is to reconstruct and return the original
array arr.

----------------------------------------------------
🧠 Key Insight
----------------------------------------------------
Using XOR properties:

pref[i] = pref[i - 1] ^ arr[i]
⇒ arr[i] = pref[i] ^ pref[i - 1]

We can reconstruct the array by iterating backward
(or forward using a new array).

----------------------------------------------------
🛠 Approach
----------------------------------------------------
- Modify the given pref array in-place
- Start from the end and compute each element
  using XOR with the previous prefix

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n)

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(1) (in-place modification)

====================================================
*/

class Solution {

    public int[] findArray(int[] pref) {
        int n = pref.length;

        // Start from the end and compute original values
        for (int i = n - 1; i >= 1; i--) {
            pref[i] = pref[i] ^ pref[i - 1];
        }

        return pref;
    }
}
