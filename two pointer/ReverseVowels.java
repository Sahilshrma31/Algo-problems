/*
 * Problem: Reverse Vowels of a String
 * LeetCode Link: https://leetcode.com/problems/reverse-vowels-of-a-string/
 * Company Tags: Google, Zoho, Flipkart
 *
 * Summary:
 * Given a string, reverse only the vowels in the string while keeping other characters in place.
 *
 * Approach:
 * - Use two pointers: one starting at the beginning (i) and one at the end (j) of the char array.
 * - Move the pointers until both point to vowels.
 * - Swap the vowels.
 * - Repeat until pointers meet.
 *
 * Time Complexity: O(n) - traverse each character at most once
 * Space Complexity: O(n) - for converting string to char array
 */

import java.util.*;

public class ReverseVowels {

    // Helper function to check if a character is a vowel
    private boolean isVowel(char ch){
        return ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u' ||
               ch=='A' || ch=='E' || ch=='I' || ch=='O' || ch=='U';
    }

    // Main function to reverse vowels in a string
    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        int i = 0, j = arr.length - 1;

        while (i < j) {
            if (!isVowel(arr[i])) i++;
            else if (!isVowel(arr[j])) j--;
            else {
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        return new String(arr);
    }

    // Driver code for testing
    public static void main(String[] args) {
        ReverseVowels obj = new ReverseVowels();
        String input = "leetcode";
        String output = obj.reverseVowels(input);
        System.out.println("Input: " + input);
        System.out.println("Output: " + output); // Expected: "leotcede"
    }
}
