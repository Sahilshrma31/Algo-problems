/*
 * Problem: C. MEX Rose
 * 
 * You are given an array a of length n and a number k (0 ≤ k ≤ n).
 * In one operation, you can choose any index i (1 ≤ i ≤ n) 
 * and set a[i] to any integer value x in the range [0, n].
 *
 * Task: Find the minimum number of operations required to make MEX(a) = k.
 * (MEX = Minimum Excluded Number = the smallest non-negative integer 
 * that does not appear in the array).
 *
 * ------------------------------
 * Intuition:
 * To have MEX = k:
 *   1. All numbers from 0 to k-1 must appear at least once in the array.
 *   2. The number k itself must NOT appear.
 *
 * So:
 *   - Count how many numbers from 0..k-1 are missing → missingCount.
 *   - Count how many times k appears → kFreq.
 *   - To fix the array:
 *        * We must add all missing numbers (missingCount operations).
 *        * We must remove all occurrences of k (kFreq operations).
 *   - The minimum number of operations = max(missingCount, kFreq).
 *
 * ------------------------------
 * Example:
 * n = 6, k = 2
 * a = [0, 3, 4, 2, 6, 2]
 * Missing in [0..1]: "1" → missingCount = 1
 * Frequency of k=2: kFreq = 2
 * Answer = max(1, 2) = 2
 *
 * ------------------------------
 * Time Complexity: O(n) per test case
 * Space Complexity: O(k)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class C_MEX_Rose {

    public static void main(String[] args) {
        FastReader in = new FastReader();
        int t = in.nextInt();
        while (t-- > 0) {
            solve(in);
        }
    }

    private static void solve(FastReader in) {
        int n = in.nextInt();
        int k = in.nextInt();

        int[] freq = new int[k + 1];
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            if (x <= k) {
                freq[x]++;
            }
        }

        int missingCount = 0;
        for (int i = 0; i < k; i++) {
            if (freq[i] == 0) {
                missingCount++;
            }
        }

        int kFreq = freq[k];
        System.out.println(Math.max(missingCount, kFreq));
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
