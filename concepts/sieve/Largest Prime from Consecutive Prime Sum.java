/*
====================================================
📌 Problem: 3770. Largest Prime from Consecutive Prime Sum
====================================================

Given an integer n, find the largest prime number that can be
represented as a sum of consecutive prime numbers such that
the sum does not exceed n.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
1. First, generate all prime numbers up to n using
   the Sieve of Eratosthenes.
2. Store all primes in a list.
3. Keep adding consecutive primes from the list and
   track the cumulative sum.
4. If at any point:
   - sum > n → stop
   - sum itself is prime → update the answer

----------------------------------------------------
🧩 Approach
----------------------------------------------------
1️⃣ Use Sieve of Eratosthenes to mark primes up to n  
2️⃣ Store all prime numbers in a list  
3️⃣ Iterate over the list and maintain a running sum  
4️⃣ If running sum is prime and ≤ n, update maximum answer  

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
Sieve: O(n log log n)  
Summation loop: O(number of primes) ≈ O(n / log n)  

Overall: **O(n log log n)**

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
Boolean prime array: O(n)  
Prime list storage: O(n / log n)  

Overall: **O(n)**

====================================================
*/

import java.util.*;

class Solution {

    // Sieve of Eratosthenes
    public void sieve(boolean[] isPrime, int n) {
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    public int largestPrime(int n) {
        if (n < 2) return 0;

        // Step 1: Generate primes
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        sieve(isPrime, n);

        // Step 2: Store primes
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        // Step 3: Consecutive prime sum
        int sum = 0;
        int maxPrimeSum = 0;

        for (int prime : primes) {
            sum += prime;
            if (sum > n) break;

            if (isPrime[sum]) {
                maxPrimeSum = Math.max(maxPrimeSum, sum);
            }
        }

        return maxPrimeSum;
    }
}

