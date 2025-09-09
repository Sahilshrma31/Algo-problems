/*
🔹 Problem: Minimum Multiplications to reach End (GFG)
------------------------------------------------------
We are given:
- An array arr of n numbers.
- A start number and an end number.

At each step:
- Multiply the current number with any number in arr,
- Take result % 100000 (mod).

We want the minimum number of steps to reach 'end' starting from 'start'.
If not possible, return -1.

---

🔹 Intuition:
This is a **shortest path problem** on an implicit graph:
- Nodes = numbers [0 ... 99999]
- Edge from u → v exists if v = (u * arr[i]) % 100000
- Each edge cost = 1 step.

👉 Since all edges have equal weight (1), we can use **BFS** to find the shortest path.

---

🔹 Approach:
1. Maintain a distance array `dist[100000]` initialized to INF.
2. Start BFS from `start` with distance = 0.
3. For each node:
   - Generate all possible next states = `(node * arr[i]) % 100000`
   - If we can reach this next state with fewer steps, update distance and push into queue.
4. If we reach `end`, return steps.
5. If BFS ends and `end` is not reached, return -1.

---

🔹 Time Complexity:
- Each node ∈ [0, 100000)
- Each node has up to `n` edges (from multiplication with arr[i])
- Worst-case: O(n * 100000)
- Given constraints, this is efficient.

🔹 Space Complexity:
- Distance array: O(100000)
- Queue: O(100000)
- Overall: **O(100000)**

---

🔹 Code:
*/

import java.util.*;

class Solution {
    class Pair {
        int f; // distance (steps taken so far)
        int s; // current node value
        Pair(int f, int s) {
            this.f = f;
            this.s = s;
        }
    }

    int minimumMultiplications(int[] arr, int start, int end) {
        int mod = 100000;
        int[] dist = new int[mod];
        Arrays.fill(dist, (int) 1e9);
        
        Queue<Pair> q = new LinkedList<>();
        dist[start] = 0;
        q.add(new Pair(0, start)); // (steps, node)

        while (!q.isEmpty()) {
            Pair curr = q.poll();
            int distn = curr.f;
            int node = curr.s;

            if (node == end) {
                return distn;
            }

            for (int num : arr) {
                int newNode = (int)((node * 1L * num) % mod);

                if (distn + 1 < dist[newNode]) {
                    dist[newNode] = distn + 1;
                    q.add(new Pair(dist[newNode], newNode));
                }
            }
        }
        return -1;
    }
}

/*
🔹 Example:
arr = {2, 5, 7}, start = 3, end = 30

Step 1: 3 * 2 % 100000 = 6
Step 2: 6 * 5 % 100000 = 30 ✅

Answer = 2 steps
*/
