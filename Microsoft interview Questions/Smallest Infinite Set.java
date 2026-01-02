/*
====================================================
📌 Problem: Smallest Infinite Set
(LeetCode 2336)
====================================================

Design a data structure that supports the following operations
on an infinite set of positive integers {1, 2, 3, ...}:

1️⃣ popSmallest() → Removes and returns the smallest integer
2️⃣ addBack(num)  → Adds num back into the set if it was removed

----------------------------------------------------
🧠 Approaches Covered (as requested)
----------------------------------------------------
1️⃣ Brute Force Approach (Boolean Array)
2️⃣ Optimized Approach (Min-Heap + HashSet)

----------------------------------------------------
⏱ Time & Space Complexity
----------------------------------------------------

🔹 Brute Force:
- popSmallest(): O(N)
- addBack(): O(1)
- Space: O(N)

🔹 Optimized:
- popSmallest(): O(log N)
- addBack(): O(log N)
- Space: O(N)

====================================================
*/

import java.util.*;

/* ==================================================
   1️⃣ BRUTE FORCE APPROACH
   ================================================== */
class SmallestInfiniteSetBrute {

    boolean[] nums;
    int i;

    public SmallestInfiniteSetBrute() {
        nums = new boolean[1001];
        Arrays.fill(nums, true); // initially all numbers are available
        i = 1;                  // smallest available number
    }

    public int popSmallest() {
        int result = i;
        nums[i] = false;

        // find next smallest available
        for (int j = i + 1; j < 1001; j++) {
            if (nums[j]) {
                i = j;
                break;
            }
        }
        return result;
    }

    public void addBack(int num) {
        nums[num] = true;
        if (num < i) {
            i = num;
        }
    }
}

/* ==================================================
   2️⃣ OPTIMIZED APPROACH
   (Min Heap + HashSet)
   ================================================== */
class SmallestInfiniteSet {

    private PriorityQueue<Integer> pq;
    private HashSet<Integer> set;
    private int curr;

    public SmallestInfiniteSet() {
        pq = new PriorityQueue<>();
        set = new HashSet<>();
        curr = 1;
    }

    public int popSmallest() {
        if (!pq.isEmpty()) {
            int val = pq.poll();
            set.remove(val);
            return val;
        }
        return curr++;
    }

    public void addBack(int num) {
        if (num < curr && !set.contains(num)) {
            pq.offer(num);
            set.add(num);
        }
    }
}
