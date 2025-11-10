/*
💥 3542. Minimum Operations to Convert All Elements to Zero 💥
🔗 LeetCode Link: https://leetcode.com/problems/minimum-operations-to-convert-all-elements-to-zero/

🧩 Problem Summary:
You're given an array nums[] consisting of non-negative integers.
In one operation, you can select a subarray [i, j] and set all occurrences
of the **minimum non-negative integer** in that subarray to 0.

Your task ➤ Return the **minimum number of operations** required to make all elements 0.

🧠 Example:
Input:  nums = [3,1,2,1]
Output: 3

Explanation:
- Choose [1,3] → min = 1 → [3,0,2,0]
- Choose [2,2] → min = 2 → [3,0,0,0]
- Choose [0,0] → min = 3 → [0,0,0,0]
✅ Total ops = 3
---------------------------------------------------------------
*/

import java.util.*;

public class MinimumOperationsToConvertAllElementsToZero {

    /* 🧾---------------------------------------------------------
       🐢 BRUTE FORCE (Conceptual / Understanding Version)
       ---------------------------------------------------------
       ⚙️ Logic:
       - For each unique positive value v in nums:
           → Scan through the array.
           → Count how many continuous segments of elements ≥ v
             contain v itself (each such segment needs one operation).
       - Skip 0 because those are already “done”.
       
       ⏱️ Time Complexity: O(n * u) where u = number of unique elements (worst O(n²))
       💾 Space Complexity: O(u) for storing unique elements.
    ------------------------------------------------------------*/
    public static int minOperationsBrute(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);

        List<Integer> unique = new ArrayList<>(set);
        Collections.sort(unique);

        int ops = 0;
        for (int val : unique) {
            if (val == 0) continue; // 🚫 already zero
            boolean inSegment = false;

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] >= val) {
                    if (nums[i] == val && !inSegment) {
                        ops++;           // 🌟 new segment found
                        inSegment = true;
                    }
                } else {
                    inSegment = false;   // 🔚 break segment when value < val
                }
            }
        }
        return ops;
    }

    /* 🚀---------------------------------------------------------
       ⚡ OPTIMAL SOLUTION (Using Monotonic Stack)
       ---------------------------------------------------------
       🧠 Intuition:
       - Think in terms of “layers” of values.
         Each new higher value starts a new operation layer.
       - Use a monotonic increasing stack to track current layers.
       - Whenever we encounter a smaller value → pop larger ones.
       - When we see a bigger number than stack top → new layer ⇒ ops++.
       - Skip 0 because:
         ✅ It’s already the smallest value.
         ✅ It breaks the segment (acts like reset).

       💡 Dry Run Example: nums = [1,3,2,0,2,3]
       Stepwise (stack changes):
       1️⃣ [1] → ops=1
       2️⃣ [1,3] → ops=2
       3️⃣ [1,2] (3 popped) → ops=2
       4️⃣ skip 0
       5️⃣ [1,2] same → ops=2
       6️⃣ [1,2,3] → ops=3
       ✅ Final Answer = 3

       ⏱️ Time Complexity: O(n)
       💾 Space Complexity: O(n)
    ------------------------------------------------------------*/
    public static int minOperationsOptimal(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int ops = 0;

        for (int i = 0; i < nums.length; i++) {
            // ⚙️ Remove all greater elements to maintain monotonic order
            while (!st.isEmpty() && st.peek() > nums[i]) {
                st.pop();
            }

            // 🚫 Skip zeros — already converted, no new operation needed
            if (nums[i] == 0) continue;

            // 💡 New layer begins
            if (st.isEmpty() || st.peek() < nums[i]) {
                ops++;
                st.push(nums[i]);
            }
        }
        return ops;
    }

    /* 🧪---------------------------------------------------------
       🧭 MAIN DRIVER — Quick Test Examples
    ------------------------------------------------------------*/
    public static void main(String[] args) {
        int[][] tests = {
            {0,2},
            {3,1,2,1},
            {1,2,1,2,1,2},
            {1,2,3,4},
            {0,0,0}
        };

        for (int[] nums : tests) {
            System.out.println("\n🧮 Input: " + Arrays.toString(nums));
            System.out.println("🐢 Brute Force Output:  " + minOperationsBrute(nums));
            System.out.println("⚡ Optimal Output:      " + minOperationsOptimal(nums));
        }
    }
}

/*
✨ Summary:
- Brute Force → Checks every unique value & counts segments.
- Optimal → Counts new increasing layers using monotonic stack.
- Skipping 0 is crucial because it resets the layer chain.
*/
