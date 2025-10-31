/**
 * Problem: Minimum Operations to Make Arrays Equal
 *
 * Given two integer arrays nums and target of equal length n, 
 * you can perform operations to make nums equal to target.
 * In one operation, you can increase or decrease any contiguous subarray 
 * by 1. The task is to find the minimum number of operations required.
 *
 * ------------------------------------------------------------
 * Intuition:
 * ------------------------------------------------------------
 * The key observation is that only the difference between nums[i] and target[i] matters.
 * Let's compute an array diff[] where diff[i] = target[i] - nums[i].
 * We then track how the difference changes as we move from one element to the next.
 *
 * Whenever the sign of diff changes (positive to negative or vice versa), it means
 * we need new operations to start adjusting in the opposite direction.
 * Also, when the absolute difference increases compared to the previous value,
 * we need additional operations to match that new magnitude.
 *
 * ------------------------------------------------------------
 * Approach:
 * ------------------------------------------------------------
 * 1. Compute diff[i] = target[i] - nums[i].
 * 2. Traverse the diff array.
 * 3. For each position, compare current and previous diff values:
 *      - If signs differ, add |curr| to the count.
 *      - If magnitude increases, add |curr - prev|.
 * 4. Return the total number of operations.
 *
 * ------------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

class Solution {
    public long minimumOperations(int[] nums, int[] target) {
        long oprns = 0;
        int n = nums.length;
        int[] diff = new int[n];

        // Step 1: Calculate the difference array
        for (int i = 0; i < n; i++) {
            diff[i] = target[i] - nums[i];
        }

        // Step 2: Traverse and count operations
        int prev = 0;
        for (int i = 0; i < n; i++) {
            int curr = diff[i];

            if ((prev > 0 && curr < 0) || (prev < 0 && curr > 0)) {
                oprns += Math.abs(curr); // Sign change => new operation
            } else if (Math.abs(curr) > Math.abs(prev)) {
                oprns += Math.abs(curr - prev); // Magnitude increased
            }

            prev = curr;
        }

        return oprns;
    }
}