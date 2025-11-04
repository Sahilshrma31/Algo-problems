/**
 * 🧩 Problem: Find X-Sum of Subarrays of Length K
 * 
 * You are given an integer array `nums` and two integers `k` and `x`.
 * For every contiguous subarray of size `k`, you must:
 *   → Count the frequency of each element.
 *   → Pick the top `x` elements based on frequency 
 *     (if frequencies are equal, choose larger element value).
 *   → Calculate sum = Σ(value * frequency) for these top `x` elements.
 *   → Return the result for every such window.
 *
 * Example:
 * Input: nums = [1, 2, 2, 3, 3, 3], k = 3, x = 2
 * Output: [5, 7, 9, 12]
 *
 * ---------------------------------------------------
 * 🔹 Topics: Sliding Window, HashMap, PriorityQueue (Heap)
 */

import java.util.*;

/* ==========================================================
   🥉 Approach 1: Brute Force (Recompute Each Window)
   ========================================================== */
class FindXSumBruteForce {

    /**
     * For each subarray of length k:
     *   → Count frequencies using HashMap.
     *   → Push into a max heap sorted by (freq desc, val desc).
     *   → Take top x elements and sum up value * freq.
     */
    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] result = new int[n - k + 1];

        for (int i = 0; i <= n - k; i++) {
            Map<Integer, Integer> freq = new HashMap<>();

            // Count frequencies for this window
            for (int j = i; j < i + k; j++) {
                freq.put(nums[j], freq.getOrDefault(nums[j], 0) + 1);
            }

            // Create max heap based on (freq desc, val desc)
            PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> b[1] == a[1] ? b[0] - a[0] : b[1] - a[1]
            );

            for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
                pq.offer(new int[]{e.getKey(), e.getValue()});
            }

            int sum = 0, count = 0;
            while (!pq.isEmpty() && count < x) {
                int[] top = pq.poll();
                sum += top[0] * top[1];
                count++;
            }

            result[i] = sum;
        }

        return result;
    }

    // Main test for Brute Force
    public static void main(String[] args) {
        FindXSumBruteForce brute = new FindXSumBruteForce();
        int[] nums = {1, 2, 2, 3, 3, 3};
        int k = 3, x = 2;

        System.out.println("🔹 Brute Force Output:");
        System.out.println(Arrays.toString(brute.findXSum(nums, k, x)));
    }
}

/* ==========================================================
   🥇 Approach 2: Optimized Sliding Window
   ========================================================== */
class FindXSumOptimized {

    /**
     * We maintain a frequency map while sliding the window.
     * For each step:
     *   → Remove the outgoing element.
     *   → Add the new incoming element.
     *   → Compute the top x frequent elements efficiently.
     */
    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        Map<Integer, Integer> freq = new HashMap<>();

        // Initialize the first window
        for (int i = 0; i < k; i++) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        }

        ans[0] = computeXSum(freq, x);

        // Slide the window
        for (int i = k; i < n; i++) {
            int out = nums[i - k];
            int in = nums[i];

            // Decrement outgoing element
            freq.put(out, freq.get(out) - 1);
            if (freq.get(out) == 0) freq.remove(out);

            // Increment incoming element
            freq.put(in, freq.getOrDefault(in, 0) + 1);

            ans[i - k + 1] = computeXSum(freq, x);
        }

        return ans;
    }

    private int computeXSum(Map<Integer, Integer> freq, int x) {
        // Max heap sorted by (frequency desc, value desc)
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> b[1] == a[1] ? b[0] - a[0] : b[1] - a[1]
        );

        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            pq.offer(new int[]{e.getKey(), e.getValue()});
        }

        int sum = 0, count = 0;
        while (!pq.isEmpty() && count < x) {
            int[] curr = pq.poll();
            sum += curr[0] * curr[1];
            count++;
        }
        return sum;
    }

    // Main test for Optimized Solution
    public static void main(String[] args) {
        FindXSumOptimized opt = new FindXSumOptimized();
        int[] nums = {1, 2, 2, 3, 3, 3};
        int k = 3, x = 2;

        System.out.println("🔹 Optimized Sliding Window Output:");
        System.out.println(Arrays.toString(opt.findXSum(nums, k, x)));
    }
}

/* ==========================================================
   🧮 Complexity Summary
   ==========================================================
   🥉 Brute Force:
       Time:  O(n * k * log k)
       Space: O(k)
   
   🥇 Optimized:
       Time:  O(n * log m)     (m = unique elements per window)
       Space: O(m)
   ==========================================================
*/

// 🧩 Brute Force vs Optimized (Sliding Window) — Short Notes
// 🔹 Brute Force Approach

// Rebuild frequency map and heap for every window of size k.

// No reuse of previous work.

// Time Complexity: O(n × k log k)

// Space Complexity: O(k)

// 🔸 Logic: For each window → count freq → sort or heapify → take top x.

// 🔹 Optimized Sliding Window Approach

// Maintain a running frequency map.

// When window slides:

// Remove outgoing element → O(1)

// Add incoming element → O(1)

// Compute sum using heap of only unique elements (m ≤ k).

// Time Complexity: O(n × log m)

// Space Complexity: O(k)

// 🔸 Logic: Incremental update instead of full recomputation → less redundant work.