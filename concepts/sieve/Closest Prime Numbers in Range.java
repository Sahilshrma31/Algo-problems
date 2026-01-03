/*
====================================================
📌 Problem: Closest Prime Numbers in Range
(LeetCode: Closest Primes in Range)
====================================================

You are given two integers `left` and `right`.
Find two prime numbers `p1` and `p2` such that:
- left ≤ p1 < p2 ≤ right
- p2 - p1 is minimized

If no such pair exists, return {-1, -1}.

----------------------------------------------------
🧠 Approach Used
----------------------------------------------------
1️⃣ Use **Sieve of Eratosthenes** to precompute primes up to `right`.
2️⃣ Collect all primes in range [left, right].
3️⃣ Traverse the list and find the minimum difference between
   consecutive primes.

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
Sieve: O(N log log N)
Scanning range: O(right - left)

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(N) for prime array and list

====================================================
*/

import java.util.*;

class Solution {

    public int[] closestPrimes(int left, int right) {

        int n = right;

        // Step 1: Sieve of Eratosthenes
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);

        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // Step 2: Collect primes in range [left, right]
        List<Integer> primes = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        // Step 3: Edge case
        if (primes.size() <= 1) {
            return new int[]{-1, -1};
        }

        // Step 4: Find closest pair
        int minDiff = Integer.MAX_VALUE;
        int[] result = new int[2];

        for (int i = 1; i < primes.size(); i++) {
            int diff = primes.get(i) - primes.get(i - 1);
            if (diff < minDiff) {
                minDiff = diff;
                result[0] = primes.get(i - 1);
                result[1] = primes.get(i);
            }
        }

        return result;
    }
}
