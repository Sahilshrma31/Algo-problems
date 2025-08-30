/*
Problem Summary (LeetCode 3235 - Flower Game):
---------------------------------------------
Alice and Bob are playing a game with two flower beds:
 - Alice chooses a number of flowers from the first bed (1 to n)
 - Bob chooses a number of flowers from the second bed (1 to m)
They want to know: in how many ways can they pick flowers such that 
the total number of flowers picked is ODD?

So, we are asked to return the count of pairs (i, j) where:
    1 <= i <= n
    1 <= j <= m
    (i + j) is odd.

-------------------------------------------------------
Intuition:
----------
- A sum (i + j) is odd only if one is even and the other is odd.
- So there are 2 valid cases:
    1) Even i with Odd j
    2) Odd i with Even j
- Count odds and evens separately:
    * Evens in [1..n] = n/2 (floor)
    * Odds in [1..n]  = (n+1)/2 (ceil)
    * Evens in [1..m] = m/2
    * Odds in [1..m]  = (m+1)/2
- Multiply the counts for both cases and add them.

-------------------------------------------------------
Time Complexity:
----------------
O(1) → Just arithmetic.

Space Complexity:
-----------------
O(1) → Constant space.
*/

class Solution {
    public long flowerGame(int n, int m) {
        // Case 1: even i with odd j
        long part1 = (long)(n/2) * ((m+1)/2);

        // Case 2: odd i with even j
        long part2 = (long)((n+1)/2) * (m/2);

        // Total odd-sum pairs
        return part1 + part2;
    }
}
