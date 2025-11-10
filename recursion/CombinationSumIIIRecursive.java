/*
    🎯 Problem: 216. Combination Sum III
    🔗 LeetCode Link: https://leetcode.com/problems/combination-sum-iii/

    🧩 Problem Summary:
    Find all valid combinations of k numbers that sum up to n such that only numbers from 1 to 9 are used,
    and each number is used at most once.

    Example:
    Input: k = 3, n = 7
    Output: [[1,2,4]]

    💡 Intuition:
    - We explore numbers from 1 to 9, deciding at each step whether to include or exclude a number.
    - If we include a number, we reduce both `k` and `n` accordingly.
    - If we exclude it, we move to the next number.
    - We stop when `k == 0` and `n == 0`, meaning we found a valid combination.

    🔁 Approach (Backtracking — Include/Exclude Recursion):
    1️⃣ Start from 1.
    2️⃣ At each number:
         - Include it (decrease both n and k).
         - Exclude it (keep n and k same, move to next number).
    3️⃣ Stop when:
         - (k == 0 && n == 0) → store the combination.
         - (start > 9 || n < 0 || k < 0) → stop exploring.

    ⏱️ Time Complexity: O(2^9)
        - Each number (1–9) has two choices: include or exclude.

    🧮 Space Complexity: O(k)
        - Recursive stack depth and temporary list.

    📘 Author: Sahil Sharma
*/

import java.util.*;

public class CombinationSumIIIRecursive {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> temp = new ArrayList<>();
        solve(1, n, k, temp);
        return res;
    }

    public void solve(int start, int n, int k, List<Integer> temp) {
        // ✅ Base case: valid combination found
        if (k == 0 && n == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }

        // 🚫 Invalid case: out of range or sum/length exceeded
        if (start > 9 || n < 0 || k < 0) {
            return;
        }

        // ✅ Include current number
        temp.add(start);
        solve(start + 1, n - start, k - 1, temp);

        // 🚫 Exclude current number
        temp.remove(temp.size() - 1);
        solve(start + 1, n, k, temp);
    }

    // 🧪 Driver code for testing
    public static void main(String[] args) {
        CombinationSumIIIRecursive obj = new CombinationSumIIIRecursive();
        System.out.println(obj.combinationSum3(3, 7)); // [[1,2,4]]
        System.out.println(obj.combinationSum3(3, 9)); // [[1,2,6], [1,3,5], [2,3,4]]
    }
}