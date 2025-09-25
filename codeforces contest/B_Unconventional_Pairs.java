/*
 * Problem: B. Unconventional Pairs
 * 
 * Petya has an array of n integers (n is even). 
 * He must divide the array into exactly n/2 pairs. 
 * Each element can only belong to one pair. 
 *
 * The difference of a pair (x, y) = |x - y|.
 * Petya wants to minimize the maximum difference among all pairs.
 *
 * ------------------------------
 * Intuition:
 * To minimize the maximum difference:
 *   - Sort the array first.
 *   - Pair adjacent elements together: (a[0], a[1]), (a[2], a[3]), ...
 *   - Why? Because in a sorted array, the closest numbers are next to each other. 
 *     Pairing them ensures that large numbers don’t get paired with small numbers,
 *     which would increase the difference.
 *
 * After forming pairs, compute the difference of each pair. 
 * The answer is the maximum of these differences.
 *
 * ------------------------------
 * Example:
 * n = 4, arr = [10, 1, 2, 9]
 * Sorted: [1, 2, 9, 10]
 * Pairs: (1,2), (9,10)
 * Differences = [1, 1], maximum = 1 → Answer = 1
 *
 * ------------------------------
 * Time Complexity: O(n log n) (because of sorting)
 * Space Complexity: O(1) extra space
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_Unconventional_Pairs {

    public static void main(String[] args) {
        FastReader reader = new FastReader();
        int testCases = reader.nextInt(); // total test cases
        while (testCases-- > 0) {
            solve(reader);
        }
    }

    private static void solve(FastReader reader) {
        int size = reader.nextInt();   // array size (even)
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = reader.nextInt();
        }

        Arrays.sort(arr); // sort the array

        int maxDiff = 0;  // to store the largest difference among pairs

        // form pairs and calculate differences
        for (int i = 0; i < size; i += 2) {
            int diff = arr[i + 1] - arr[i];
            if (diff > maxDiff) {
                maxDiff = diff;  // update if a larger difference is found
            }
        }

        System.out.println(maxDiff);
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

