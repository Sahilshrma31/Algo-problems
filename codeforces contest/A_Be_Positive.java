/*
 * Problem: A. Be Positive
 *
 * You are given an array of n elements where each element is either -1, 0, or 1.
 * In one operation, you can increase an element by 1 (ai := ai + 1).
 *
 * The goal is to make the product of all elements strictly positive (> 0)
 * using the minimum number of operations.
 *
 * ------------------------------
 * Intuition:
 * - Zeros contribute nothing to the product. 
 *   To avoid product = 0, every zero must be converted to 1. 
 *   => Each zero contributes exactly 1 operation.
 *
 * - Negative numbers (-1):
 *   - If the count of negatives is even → product is already positive. 
 *   - If the count of negatives is odd → product becomes negative.
 *     To fix this, we must flip one -1 into 1.
 *     But note: -1 → 0 (1 operation), then 0 → 1 (another operation).
 *     Total = 2 operations to turn one -1 into +1.
 *
 * So:
 *   totalMoves = (zeros → ones) + (if negCount odd → +2)
 *
 * ------------------------------
 * Example:
 * Input: n=3, arr = [-1, 0, 1]
 * negCnt = 1 (odd), zeroCnt = 1
 * moves = 1 (zero→1) + 2 (fix odd neg) = 3 → Answer
 *
 * ------------------------------
 * Time Complexity: O(n) per test case (linear scan)
 * Space Complexity: O(1) (constant extra space)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A_Be_Positive {

    public static void main(String[] args) {
        FastReader input = new FastReader();
        int test = input.nextInt(); 
        while (test-- > 0) {
            solve(input);
        }
    }

    private static void solve(FastReader input) {
        int len = input.nextInt();  
        int negCnt = 0;  // count of -1
        int zeroCnt = 0; // count of 0

        for (int i = 0; i < len; i++) {
            int num = input.nextInt();
            if (num == 0) {
                zeroCnt++; 
            } else if (num == -1) {
                negCnt++;
            }
        }

        int moves = zeroCnt; // change each zero to +1

        // if odd count of negatives, flip one -1 to +1 (2 steps)
        if (negCnt % 2 != 0) {
            moves += 2;
        }

        System.out.println(moves);
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
