/*
 * 🔹 LeetCode Problem: 78. Subsets
 * 
 * 🧩 Problem Summary:
 * Given an integer array `nums`, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets.
 * Return the answer in any order.
 * 
 * Example:
 * Input: nums = [1,2,3]
 * Output: [[], [1], [2], [3], [1,2], [1,3], [2,3], [1,2,3]]
 * 
 * ------------------------------------------------------------
 * 🧠 Intuition:
 * 
 * Each element has 2 choices:
 * 1️⃣ Include it in the current subset.
 * 2️⃣ Exclude it from the current subset.
 * 
 * Using recursion (backtracking), we explore both decisions for every element,
 * which builds all 2^n possible subsets.
 * 
 * ------------------------------------------------------------
 * ⏱️ Time Complexity: O(2^n * n)
 * ➤ For each element, we have 2 choices (include/exclude),
 *   and creating a copy of the subset takes up to O(n).
 * 
 * 💾 Space Complexity: O(n * 2^n)
 * ➤ Total subsets stored = 2^n, each of average length n.
 * ➤ Recursion depth = O(n)
 * 
 * ------------------------------------------------------------
 * ✅ Approach: Backtracking
 * 
 * Steps:
 * 1. Start with index = 0 and an empty temp list.
 * 2. At each step, include nums[start] and move forward.
 * 3. Backtrack by removing last element and explore excluding it.
 * 4. Once start == nums.length, store the current subset.
 */

import java.util.*;

class Solution {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> temp = new ArrayList<>();
        solve(0, nums, temp);
        return res;
    }

    public void solve(int start, int[] nums, List<Integer> temp) {
        // Base case: all elements processed
        if (start >= nums.length) {
            res.add(new ArrayList<>(temp));  // store copy of current subset
            return;
        }

        // ✅ Include current element
        temp.add(nums[start]);
        solve(start + 1, nums, temp);

        // 🔙 Backtrack (remove last added element)
        temp.remove(temp.size() - 1);

        // 🚫 Exclude current element
        solve(start + 1, nums, temp);
    }
}

