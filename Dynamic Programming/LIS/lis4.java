// File: LongestStringChain.java

/*
🔹 Problem: Longest String Chain
Given an array of words, find the longest chain of words such that each word in the chain is formed by adding exactly one character to the previous word.  

🔹 Example:
Input: words = ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: One of the longest chains is ["a","ba","bda","bdca"]

🔹 Approach (DP + Hash array / LIS-style):
1. Sort words by length.
2. Initialize:
   - dp[i] = length of longest chain ending at words[i]
   - hash[i] = previous index in chain (for reconstruction)
3. For each word i, check all previous words j < i:
   - If words[j] is a predecessor of words[i] (one char added), update:
     dp[i] = max(dp[i], dp[j] + 1)
     hash[i] = j
4. Track maximum dp[i] as chain length.
5. Reconstruct chain using hash[] if needed.

🔹 Time Complexity: O(n^2 * L), L = max word length
🔹 Space Complexity: O(n)
*/

import java.util.*;

class Solution {

    // Check if s1 is predecessor of s2 (s2 formed by adding exactly one char to s1)
    private boolean isPredecessor(String s1, String s2) {
        if (s2.length() - s1.length() != 1) return false;

        int i = 0, j = 0;
        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) == s2.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s1.length();
    }

    // Returns length of longest string chain
    public int longestStrChain(String[] words) {
        int n = words.length;
        Arrays.sort(words, (a, b) -> a.length() - b.length()); // sort by length

        int dp[] = new int[n];
        int hash[] = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) hash[i] = i;

        int maxi = 1;
        int lastIdx = 0;

        for (int i = 0; i < n; i++) {
            for (int prev = 0; prev < i; prev++) {
                if (isPredecessor(words[prev], words[i]) && dp[i] < 1 + dp[prev]) {
                    dp[i] = 1 + dp[prev];
                    hash[i] = prev;
                }
            }
            if (dp[i] > maxi) {
                maxi = dp[i];
                lastIdx = i;
            }
        }

        // Optional: reconstruct actual chain
        List<String> chain = new ArrayList<>();
        int idx = lastIdx;
        chain.add(words[idx]);
        while (hash[idx] != idx) {
            idx = hash[idx];
            chain.add(words[idx]);
        }
        Collections.reverse(chain);
        System.out.println("Longest String Chain: " + chain);

        return maxi;
    }

    // Driver
    public static void main(String[] args) {
        Solution sol = new Solution();

        String[] words1 = {"a","b","ba","bca","bda","bdca"};
        System.out.println("Longest String Chain Length: " + sol.longestStrChain(words1));
        // Output: 4

        String[] words2 = {"xbc","pcxbcf","xb","cxbc","pcxbc"};
        System.out.println("Longest String Chain Length: " + sol.longestStrChain(words2));
        // Output: 5
    }
}
