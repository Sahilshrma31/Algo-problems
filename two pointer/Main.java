// Count Occurrences of Anagrams
// Problem: Given a word pat and a text txt. Return the count of the occurrences of anagrams of the word in the text.

// ------------------------------------------------------------
// Brute Force Approach (Frequency Map)
// -----------------------------------------------------------
// Time Complexity: O((n-m+1) * 26) ≈ O(n * 26)
// Space Complexity: O(26) = O(1)

class BruteForceSolution {

    // Helper: check if all counts are zero
    private boolean allZero(int[] freq) {
        for (int val : freq) {
            if (val != 0) return false;
        }
        return true;
    }

    int search(String pat, String txt) {
        int n = txt.length();
        int m = pat.length();

        int[] patFreq = new int[26];
        for (int i = 0; i < m; i++) {
            patFreq[pat.charAt(i) - 'a']++;
        }

        int count = 0;
        // For every substring of length m
        for (int i = 0; i <= n - m; i++) {
            int[] temp = patFreq.clone(); // fresh copy for each substring

            for (int j = i; j < i + m; j++) {
                temp[txt.charAt(j) - 'a']--;
            }

            if (allZero(temp)) {
                count++;
            }
        }
        return count;
    }
}



// ------------------------------------------------------------
// Optimized Sliding Window Approach
// ------------------------------------------------------------
// Idea:
// - Maintain a frequency counter for pat.
// - Use a sliding window of size m on txt.
// - Update the counter as chars enter/leave the window.
// - If counter becomes all zeros, it's a valid anagram.
//
// Time Complexity: O(n * 26) ≈ O(n)
// Space Complexity: O(26) = O(1)

class Solution {

    private boolean isValid(int[] counter) {
        for (int num : counter) {
            if (num != 0) return false;
        }
        return true;
    }

    int search(String pat, String txt) {
        int n = txt.length();
        int m = pat.length();

        int[] counter = new int[26];

        // Step 1: Count frequency of chars in pat
        for (int i = 0; i < m; i++) {
            counter[pat.charAt(i) - 'a']++;
        }

        int i = 0, j = 0, cnt = 0;

        // Step 2: Sliding window
        while (j < n) {
            counter[txt.charAt(j) - 'a']--;

            if (j - i + 1 < m) {
                j++;
            } else if (j - i + 1 == m) {
                if (isValid(counter)) {
                    cnt++;
                }
                // Slide window
                counter[txt.charAt(i) - 'a']++;
                i++;
                j++;
            }
        }
        return cnt;
    }
}

// ------------------------------------------------------------
// Driver Code
// ------------------------------------------------------------
public class Main {
    public static void main(String[] args) {
        String txt = "forxxorfxdofr";
        String pat = "for";

        BruteForceSolution brute = new BruteForceSolution();
        System.out.println("Brute Force Result: " + brute.search(pat, txt)); // Output: 3

        Solution optimized = new Solution();
        System.out.println("Optimized Result: " + optimized.search(pat, txt)); // Output: 3
    }
}
