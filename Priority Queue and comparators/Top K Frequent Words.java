/*
====================================================
📌 Problem: Top K Frequent Words
(LeetCode 692)
====================================================

Given an array of strings `words` and an integer `k`,
return the `k` most frequent strings.

Sorting Rules:
1️⃣ Higher frequency comes first
2️⃣ If frequencies are equal → lexicographically smaller word comes first

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
We first count frequency of each word using a HashMap.

Then we have two ways:
1️⃣ Sort all words using a custom comparator (Brute Force)
2️⃣ Use a Min Heap of size `k` to keep only top k elements (Optimized)

----------------------------------------------------
🧩 Approaches Implemented
----------------------------------------------------
1️⃣ Brute Force (Sorting using ArrayList)
2️⃣ Optimized (PriorityQueue / Min Heap)

----------------------------------------------------
⏱ Time & Space Complexity
----------------------------------------------------

Let:
N = number of words
U = number of unique words

1️⃣ Brute Force:
- Time: O(U log U)
- Space: O(U)

2️⃣ Optimized Heap:
- Time: O(N log K)
- Space: O(U + K)

====================================================
*/

import java.util.*;

class Solution {

    /* =================================================
       1️⃣ BRUTE FORCE APPROACH (Sorting)
       ================================================= */
    public List<String> topKFrequentBrute(String[] words, int k) {

        // Step 1: Frequency map
        Map<String, Integer> map = new HashMap<>();
        for (String w : words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }

        // Step 2: Put all unique words into a list
        List<String> list = new ArrayList<>(map.keySet());

        // Step 3: Sort using custom comparator
        Collections.sort(list, (a, b) -> {
            if (!map.get(a).equals(map.get(b))) {
                return map.get(b) - map.get(a); // frequency desc
            }
            return a.compareTo(b); // lexicographical asc
        });

        // Step 4: Take first k elements
        return list.subList(0, k);
    }

    /* =================================================
       2️⃣ OPTIMIZED APPROACH (Min Heap)
       ================================================= */
    public List<String> topKFrequent(String[] words, int k) {

        // Step 1: Frequency map
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        // Step 2: Min Heap
        PriorityQueue<String> pq = new PriorityQueue<>(
            (a, b) -> {
                if (!map.get(a).equals(map.get(b))) {
                    return map.get(a) - map.get(b); // min freq first
                }
                return b.compareTo(a); // reverse lex order
            }
        );

        // Step 3: Keep heap size <= k
        for (String word : map.keySet()) {
            pq.offer(word);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        // Step 4: Extract result
        List<String> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll());
        }

        result.add(0, pq.poll()); 
        return result;
    }
}
