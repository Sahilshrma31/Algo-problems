/*
 * Problem: Daily Temperatures
 * ---------------------------------
 * You are given an array 'temperatures' where temperatures[i] is the temperature on day i.
 * For each day, return how many days you would have to wait until a warmer temperature.
 * If there is no future day with a warmer temperature, return 0 for that day.
 *
 * Example:
 * Input:  [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 *
 * ---------------------------------
 * Brute Force Approach:
 * - For each day i, scan forward until a warmer temperature is found.
 * - Time Complexity: O(n^2) (worst case check every future day for each i).
 * - Space Complexity: O(1).
 *
 * Optimized Approach (Monotonic Stack):
 * - Use a stack to keep indices of days with decreasing temperatures.
 * - Traverse the array once and maintain the next warmer day info.
 * - Each index is pushed/popped at most once.
 * - Time Complexity: O(n).
 * - Space Complexity: O(n).
 *
 * Intuition:
 * - We are essentially looking for the "Next Greater Element" problem variant.
 * - Brute force: look ahead until you find a greater temperature.
 * - Optimized: maintain a decreasing stack so when a warmer day arrives,
 *   it resolves multiple previous colder days at once.
 */

// ---------------------------------
// Brute Force Solution
// ---------------------------------
import java.util.*;
class BruteForceSolution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (temperatures[j] > temperatures[i]) {
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
    }
}

// ---------------------------------
// Optimized Solution (Monotonic Stack, Right-to-Left)
// ---------------------------------


class OptimizedSolution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>(); // stores indices

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                res[i] = stack.peek() - i;
            } else {
                res[i] = 0;
            }
            stack.push(i);
        }
        return res;
    }
}

// ---------------------------------
// Example Usage
// ---------------------------------
class Main {
    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};

        BruteForceSolution bf = new BruteForceSolution();
        System.out.println("Brute Force Result: " + Arrays.toString(bf.dailyTemperatures(temperatures)));

        OptimizedSolution opt = new OptimizedSolution();
        System.out.println("Optimized Result:   " + Arrays.toString(opt.dailyTemperatures(temperatures)));
    }
}
