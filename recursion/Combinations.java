/*
    🎯 Problem: 77. Combinations
    🔗 LeetCode Link: https://leetcode.com/problems/combinations/

    🧩 Problem Summary:
    Given two integers n and k, return all possible combinations of k numbers
    chosen from the range [1, n].

    Example:
    Input: n = 4, k = 2
    Output: [[1,2], [1,3], [1,4], [2,3], [2,4], [3,4]]

    ------------------------------------------------------------
    💡 Intuition:
    We need to generate all possible subsets of size `k` using numbers from 1 to n.
    Think of it as building combinations step by step — at each step we decide
    whether to include or skip a number.
    ------------------------------------------------------------
*/

import java.util.*;

public class Combinations {

    // =====================================================================
    // 🧠 Approach 1: Recursive (Include / Exclude method)
    // =====================================================================
    /*
        🔁 Approach:
        1️⃣ Start from 1, go up to n.
        2️⃣ For each number:
            - Include it (reduce k by 1)
            - Exclude it (keep k same)
        3️⃣ Stop when:
            - k == 0 → add combination
            - start > n → no numbers left
    */

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        List<Integer> temp = new ArrayList<>();
        solve(1, n, k, temp);
        return res;
    }

    private void solve(int start, int n, int k, List<Integer> temp) {
        // ✅ Base Case
        if (k == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }

        // 🚫 Out of range
        if (start > n) return;

        // ✅ Include current number
        temp.add(start);
        solve(start + 1, n, k - 1, temp);
        temp.remove(temp.size() - 1);

        // 🚫 Exclude current number
        solve(start + 1, n, k, temp);
    }

    // =====================================================================
    // 🧠 Approach 2: Iterative Backtracking (Using for loop)
    // =====================================================================
    /*
        💡 Intuition:
        - Instead of binary include/exclude, we iterate through each possible number
          at each level and recursively build combinations.
        - This avoids redundant recursive calls and is a bit cleaner.

        🔁 Steps:
        1️⃣ Use a for loop from 'start' to 'n'.
        2️⃣ Include each number i → recursively call for i+1.
        3️⃣ Backtrack (remove last element).
    */

    public List<List<Integer>> combineIterative(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, n, k, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int n, int k, List<Integer> temp, List<List<Integer>> result) {
        if (k == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = start; i <= n; i++) {
            temp.add(i);
            backtrack(i + 1, n, k - 1, temp, result);
            temp.remove(temp.size() - 1);
        }
    }

    // =====================================================================
    // 🧮 Complexity Analysis
    // =====================================================================
    /*
        ⏱️ Time Complexity:
        - O(C(n, k)) → number of valid combinations generated.
        - Each combination takes O(k) time to build.
        - So overall ≈ O(C(n, k) * k)

        📦 Space Complexity:
        - O(k) recursion stack + O(C(n, k)) for storing results.
    */

    // =====================================================================
    // 🧪 Driver code for testing both methods
    // =====================================================================
    public static void main(String[] args) {
        Combinations obj = new Combinations();
        int n = 4, k = 2;

        System.out.println("🔹 Recursive (Include/Exclude) Output:");
        List<List<Integer>> ans1 = obj.combine(n, k);
        for (List<Integer> list : ans1) System.out.println(list);

        System.out.println("\n🔹 Iterative (For-loop Backtracking) Output:");
        List<List<Integer>> ans2 = obj.combineIterative(n, k);
        for (List<Integer> list : ans2) System.out.println(list);
    }
}
