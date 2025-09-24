/*
Problem: A. Equal Occurrences (Codeforces)

We call an array balanced if and only if the numbers of occurrences of any of its elements are the same. 
For example:
- [1,1,3,3,6,6] is balanced
- [2,2,2,2] is balanced
- [1,2,3,3] is NOT balanced

You are given a non-decreasing array `a` of size `n`.
Find the length of its longest balanced subsequence.

---
Intuition:
1. Count frequencies of all elements.
2. Suppose max frequency of any element = `maxFreq`.
3. For each possible frequency `f` from 1 to maxFreq:
   - Count how many elements have frequency >= f.
   - Those elements can each contribute `f` times.
   - So subsequence length = count * f.
   - Take maximum across all f.
4. This ensures we try every possible "balanced frequency".

---
Example:
a = [1,1,4,4,4]
freq = {1:2, 4:3}
- f=1 → count=2 → length=2
- f=2 → count=2 → length=4
- f=3 → count=1 → length=3
Answer = 4

---
Time Complexity:
- Building frequency map: O(n)
- Checking all possible frequencies: O(maxFreq * distinctElements) ≤ O(n^2) worst case
But since n ≤ 100 (from constraints), this is efficient.

Space Complexity:
- O(distinct elements) ≤ O(n)

---
*/

import java.util.*;

public class A_Equal_Occurrences {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            HashMap<Integer, Integer> map = new HashMap<>();
            
            // Build frequency map
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
                map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            }

            // Find max frequency
            int maxfreq = 0;
            for (int values : map.values()) {
                maxfreq = Math.max(maxfreq, values);
            }

            int ans = 0;
            // Try all possible frequencies
            for (int f = 1; f <= maxfreq; f++) {
                int cnt = 0;
                for (int value : map.values()) {
                    if (value >= f) {
                        cnt++;
                    }
                }
                ans = Math.max(ans, cnt * f);
            }

            System.out.println(ans);
        }

        sc.close();
    }
}
