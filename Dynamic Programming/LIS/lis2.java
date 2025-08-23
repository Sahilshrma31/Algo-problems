
/*
🔹 Problem: Longest Increasing Subsequence (LIS)
Given an integer array `arr[]`, find the longest increasing subsequence in it.
Return the actual subsequence (not just its length).

🔹 Example:
Input:  arr = {3, 10, 2, 1, 20}
Output: [3, 10, 20]

Input:  arr = {50, 3, 10, 7, 40, 80}
Output: [3, 7, 40, 80]

🔹 Approach:
1. Use Dynamic Programming (DP) to find LIS length for each index.
2. Maintain a `hash` array to reconstruct the actual sequence:
   - hash[i] stores the index of the previous element in LIS ending at i.
3. Iterate over array:
   - For each `i`, check all `prev < i`:
     - If arr[i] > arr[prev] and dp[i] < dp[prev] + 1, update dp[i] and hash[i].
4. Track the index with maximum `dp[i]` as `lastIdx`.
5. Reconstruct LIS using the `hash` array.

🔹 Time Complexity:
- O(n^2) due to two nested loops over `n` elements.

🔹 Space Complexity:
- O(n) for dp array + O(n) for hash array + O(n) for result → O(n) overall.
*/

import java.util.*;

class Solution {

    public ArrayList<Integer> getLIS(int arr[]) {
        int n = arr.length;

        // DP array: dp[i] = length of LIS ending at i
        int dp[] = new int[n];
        Arrays.fill(dp, 1);

        // hash array to reconstruct LIS
        int hash[] = new int[n];
        for (int i = 0; i < n; i++) hash[i] = i;

        int maxi = 1;        // maximum length of LIS
        int lastIdx = 0;     // index of last element of LIS

        for (int i = 0; i < n; i++) {
            for (int prev = 0; prev < i; prev++) {
                if (arr[i] > arr[prev] && dp[i] < dp[prev] + 1) {
                    dp[i] = dp[prev] + 1;
                    hash[i] = prev;
                }
            }
            if (dp[i] > maxi) {
                maxi = dp[i];
                lastIdx = i;
            }
        }

        // Reconstruct the LIS
        ArrayList<Integer> res = new ArrayList<>();
        res.add(arr[lastIdx]);
        while (hash[lastIdx] != lastIdx) {
            lastIdx = hash[lastIdx];
            res.add(arr[lastIdx]);
        }
        Collections.reverse(res);

        return res;
    }

    // Driver method to test
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] arr1 = {3, 10, 2, 1, 20};
        System.out.println("LIS: " + sol.getLIS(arr1));  // Output: [3, 10, 20]

        int[] arr2 = {50, 3, 10, 7, 40, 80};
        System.out.println("LIS: " + sol.getLIS(arr2));  // Output: [3, 7, 40, 80]
    }
}
