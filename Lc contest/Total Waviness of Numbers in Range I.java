/*******************************************************************************************
 * LeetCode 3751 — Total Waviness of Numbers in Range I
 *
 * Problem:
 * For each number in the range [num1, num2], count how many digits are
 * "peaks" (greater than both neighbors) or "valleys" (smaller than both neighbors).
 * Only digits with left and right neighbors can be considered, so numbers with fewer
 * than 3 digits automatically have a waviness of 0.
 *
 * Intuition:
 *  - Convert each number into a string to easily examine its digits.
 *  - Traverse from index 1 to length-2, checking for peak/valley properties.
 *  - Sum these counts over the entire range.
 *
 * Approach:
 * 1. Iterate from num1 to num2.
 * 2. Convert each number to string.
 * 3. Skip if length < 3.
 * 4. Count digits that satisfy:
 *        Peak  : b > a && b > c
 *        Valley: b < a && b < c
 * 5. Accumulate total waviness across all numbers.
 *
 * Time Complexity : O((num2 - num1 + 1) * number_of_digits)  ≈ O(n)
 * Space Complexity: O(1)
 *******************************************************************************************/

class Solution {
    public int totalWaviness(int num1, int num2) {
        int total = 0;

        for (int num = num1; num <= num2; num++) {

            // Convert number to string for digit-by-digit processing
            String s = String.valueOf(num);

            // Numbers with fewer than 3 digits can't have peaks or valleys
            if (s.length() < 3) continue;

            int waviness = 0;

            // Check only middle digits (1 to len-2)
            for (int i = 1; i < s.length() - 1; i++) {
                int left  = s.charAt(i - 1) - '0';
                int mid   = s.charAt(i)     - '0';
                int right = s.charAt(i + 1) - '0';

                // Check peak or valley condition
                if ((mid > left && mid > right) || (mid < left && mid < right)) {
                    waviness++;
                }
            }

            total += waviness;
        }

        return total;
    }
}
