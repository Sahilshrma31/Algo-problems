/*
    🎯 Problem: 1859. Sorting the Sentence
    🔗 LeetCode Link: https://leetcode.com/problems/sorting-the-sentence/

    🧩 Problem Summary:
    You are given a shuffled sentence `s` containing words followed by numbers.
    Each word has a number at its end that indicates its correct position in the sentence.

    Your task is to reorder the words based on these numbers and return the sorted sentence.

    Example:
    Input: s = "is2 sentence4 This1 a3"
    Output: "This is a sentence"

    ------------------------------------------------------------
    💡 Intuition:
    - Each word ends with a digit representing its position in the final sentence.
    - Extract that number, use it as an index, and store the word (without the number)
      in its correct place.
    - Finally, join all the words with spaces to form the sentence.

    ------------------------------------------------------------
    ⚙️ Approach:
    1️⃣ Split the string `s` into individual words using space `" "`.
    2️⃣ Create a new array `ans[]` of the same size to store words in sorted order.
    3️⃣ For each word:
        - Extract the last character (position number).
        - Convert it into an integer index.
        - Remove the number from the end of the word.
        - Store the cleaned word in the `ans` array at (index - 1).
    4️⃣ Finally, join all the words in `ans[]` with a space `" "` and return it.

    ------------------------------------------------------------
*/

class Solution {
    public String sortSentence(String s) {
        // Step 1: Split the input sentence into individual words
        String[] words = s.split(" ");
        
        // Step 2: Create an empty array to hold words in correct order
        String[] ans = new String[words.length];
        
        // Step 3: Process each word one by one
        for (String word : words) {
            // Get the last character (the position number)
            char lastChar = word.charAt(word.length() - 1);
            
            // Convert that character into an integer position
            int position = lastChar - '0';
            
            // Remove the last character (the number) from the word
            String actualWord = word.substring(0, word.length() - 1);
            
            // Place the word at its correct index (position - 1)
            ans[position - 1] = actualWord;
        }
        
        // Step 4: Join all words into a single sentence with spaces
        return String.join(" ", ans);
    }
}

/*
    ------------------------------------------------------------
    🧠 Example Dry Run:
    Input: "is2 sentence4 This1 a3"

    Split → ["is2", "sentence4", "This1", "a3"]
    ans[1] = "This"
    ans[0] = "is"
    ans[2] = "a"
    ans[3] = "sentence"

    Join → "This is a sentence"

    ------------------------------------------------------------
    ⏱️ Time Complexity:
      O(n)
      → We traverse each word once (splitting + joining = linear time).

    💾 Space Complexity:
      O(n)
      → Additional array `ans[]` stores reordered words.

    ------------------------------------------------------------
    📘 Author: Sahil Sharma
*/
