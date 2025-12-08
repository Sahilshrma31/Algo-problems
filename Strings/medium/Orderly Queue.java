/**
 * LeetCode Problem: 899. Orderly Queue
 * -----------------------------------
 *
 * Problem Summary:
 * You are given a string `s` and an integer `k`.
 * In one move, you can choose one of the first `k` characters of the string
 * and append it to the end of the string.
 *
 * Your task is to return the lexicographically smallest string
 * that can be achieved after performing any number of moves.
 *
 * -----------------------------------
 * Intuition:
 *
 * Case 1: k > 1
 * ----------
 * If k > 1, we can effectively rearrange characters freely.
 * This is because having at least two choices allows us to simulate
 * swapping characters, which means we can generate any permutation.
 *
 * Therefore, the smallest lexicographical string possible
 * is simply the sorted version of the string.
 *
 * Case 2: k == 1
 * ----------
 * If k == 1, we have a strict restriction:
 * - We can only move the first character to the end.
 *
 * This means the only strings we can form are rotations of the original string.
 * So, we generate all rotations and return the smallest one lexicographically.
 *
 * -----------------------------------
 * Approach:
 *
 * 1. If k > 1:
 *    - Convert the string to a character array
 *    - Sort it
 *    - Return the sorted string
 *
 * 2. If k == 1:
 *    - Initialize result as the original string
 *    - Generate all possible rotations of the string
 *    - Compare each rotation lexicographically
 *    - Keep updating the smallest string found
 *
 * -----------------------------------
 * Time Complexity:
 *
 * Case k > 1:
 * - O(n log n) due to sorting
 *
 * Case k == 1:
 * - There are n rotations
 * - Each rotation takes O(n) time to construct and compare
 * - Total = O(n²)
 *
 * Overall Time Complexity:
 * - O(n log n) when k > 1
 * - O(n²) when k == 1
 *
 * -----------------------------------
 * Space Complexity:
 *
 * Case k > 1:
 * - O(n) for character array
 *
 * Case k == 1:
 * - O(n) for substring creation
 *
 * -----------------------------------
 * Author: Sahil Sharma
 */

import java.util.Arrays;

class Solution {

    public String orderlyQueue(String s, int k) {

        // Case 1: k > 1 → We can sort the string
        if (k > 1) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        }

        // Case 2: k == 1 → Only rotations are possible
        String result = s;
        int n = s.length();

        // Generate all rotations and find the smallest one
        for (int i = 1; i < n; i++) {
            String current = s.substring(i) + s.substring(0, i);

            if (current.compareTo(result) < 0) {
                result = current;
            }
        }

        return result;
    }
}
