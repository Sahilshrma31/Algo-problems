/*
    🎯 Problem: 2654. Minimum Number of Operations to Make All Array Elements Equal to 1
    🔗 LeetCode Link: https://leetcode.com/problems/minimum-number-of-operations-to-make-all-array-elements-equal-to-1/

    🧩 Problem Summary:
    You are given an integer array nums. 
    In one operation, you can select any adjacent pair of elements and replace one of them with their GCD.
    Return the minimum number of operations required to make all elements in the array equal to 1.
    If it is impossible, return -1.

    Example:
    Input: nums = [2,6,3,4]
    Output: 4
    Explanation:
        - GCD(2,6) = 2
        - GCD(6,3) = 3
        - GCD(3,4) = 1  ✅ one '1' formed after 2 internal operations.
        Then replace all others with 1 → 4 total operations.

    ------------------------------------------------------------
    💡 Intuition:
    - If the array already contains some '1's, 
      each non-1 element can be made 1 in a single operation with any '1' next to it.
      ⇒ Answer = n - count(1s)
    
    - If no 1 exists:
      - We need to find the *shortest subarray* whose GCD = 1.
      - Because combining elements in that subarray will eventually produce a 1.
      - The cost of forming that 1 = (length_of_subarray - 1)
      - Once we have one '1', we need (n - 1) more operations to make all 1.

      ⇒ Final Answer = minStepsToMakeOne + (n - 1)
      If no subarray has GCD 1 → return -1.

    ------------------------------------------------------------
*/

class Solution {

    public int minOperations(int[] nums) {
        int n = nums.length;
        int count = 0;

        // Step 1️⃣: Count number of 1s
        for (int num : nums) {
            if (num == 1) count++;
        }

        // Step 2️⃣: If we already have some 1s
        if (count > 0) {
            return n - count;
        }

        // Step 3️⃣: Find shortest subarray whose GCD = 1
        int minStepsToOne = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
            int currGcd = nums[i];
            for (int j = i + 1; j < n; j++) {
                currGcd = gcd(currGcd, nums[j]);
                if (currGcd == 1) {
                    minStepsToOne = Math.min(minStepsToOne, j - i);
                    break;
                }
            }
        }

        // Step 4️⃣: If impossible, return -1
        if (minStepsToOne == Integer.MAX_VALUE) return -1;

        // Step 5️⃣: Total operations = steps to make one '1' + (n - 1)
        return minStepsToOne + (n - 1);
    }

    // Helper function to calculate GCD
    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}

/*
    ------------------------------------------------------------
    🧠 Example Dry Run:
    nums = [2, 6, 3, 4]
    count(1) = 0

    Subarray GCDs:
    (2,6)=2
    (2,6,3)=GCD(2,3)=1 → minStepsToOne = (2 indices apart) = 2

    Final = 2 + (4-1) = 5 operations total

    ------------------------------------------------------------
    ⏱️ Time Complexity:
      - O(n² * log(max(nums)))
        → nested loop for subarray pairs, GCD takes log(max_num)
    💾 Space Complexity:
      - O(1) (only variables used)
    ------------------------------------------------------------
    📘 Author: Sahil Sharma
*/
