import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Count Special Triplets (LeetCode 3583)
 *
 * A special triplet is defined as indices (i, j, k) such that:
 *   0 <= i < j < k < n
 *   nums[i] == nums[j] * 2
 *   nums[k] == nums[j] * 2
 *
 * Return the total number of such triplets modulo (1e9 + 7).
 *
 * ------------------------------------------------------------
 * Intuition:
 * ------------------------------------------------------------
 * Fix the middle index j.
 *
 * For a value x = nums[j]:
 *   - We need nums[i] = 2 * x on the LEFT side (i < j)
 *   - We need nums[k] = 2 * x on the RIGHT side (k > j)
 *
 * If:
 *   leftCount  = number of (2 * x) on the left
 *   rightCount = number of (2 * x) on the right
 *
 * Then total triplets with j as the middle element:
 *   leftCount * rightCount
 *
 * ------------------------------------------------------------
 * Approach (Two-Pass with Frequency Maps):
 * ------------------------------------------------------------
 * 1. Build a frequency map (rMap) for all elements — represents the right side.
 * 2. Traverse the array again, treating each element as the middle (j):
 *    - Remove current element from rMap (so it represents elements strictly after j).
 *    - Use lMap to count how many (nums[j] * 2) exist on the left.
 *    - Use rMap to count how many (nums[j] * 2) exist on the right.
 *    - Multiply leftCount * rightCount and add to the result.
 *    - Add the current element to lMap.
 *
 * ------------------------------------------------------------
 * Time Complexity:
 * ------------------------------------------------------------
 * O(n)
 * - One pass to build rMap
 * - One pass to count triplets
 *
 * ------------------------------------------------------------
 * Space Complexity:
 * ------------------------------------------------------------
 * O(n)
 * - Two HashMaps storing frequency counts
 */

public class Solution {

    // Modulo constant
    static final int M = (int) 1e9 + 7;

    public int specialTriplets(int[] nums) {
        Map<Integer, Integer> lMap = new HashMap<>(); // Left frequency map
        Map<Integer, Integer> rMap = new HashMap<>(); // Right frequency map

        int result = 0;

        // Fill right map with frequencies of all elements
        for (int num : nums) {
            rMap.put(num, rMap.getOrDefault(num, 0) + 1);
        }

        // Iterate through array treating each element as middle index j
        for (int num : nums) {
            // Remove current element from right side
            rMap.put(num, rMap.get(num) - 1);

            // Count valid left and right elements (must be double of num)
            int left = lMap.getOrDefault(num * 2, 0);
            int right = rMap.getOrDefault(num * 2, 0);

            // Add number of valid triplets for this middle index
            long add = (1L * left * right) % M;
            result = (int) ((result + add) % M);

            // Add current element to left map
            lMap.put(num, lMap.getOrDefault(num, 0) + 1);
        }

        return result;
    }
}
