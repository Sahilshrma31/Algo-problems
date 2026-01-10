/*
====================================================
📌 Problem: Group the People Given the Group Size They Belong To
(LeetCode 1282)
====================================================

You are given an integer array groupSizes where
groupSizes[i] is the size of the group that person i belongs to.

Each person must be placed in exactly one group,
and every group must have exactly the size specified.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
- People with the same required group size should be grouped together
- Once we collect `size` number of people for a group,
  we finalize that group and start a new one

----------------------------------------------------
🛠 Approach
----------------------------------------------------
1️⃣ Use a HashMap:
   key   → group size
   value → list of people indices waiting for that group

2️⃣ Iterate over people:
   - Add person to their group-size list
   - If list size == group size:
       • add it to result
       • reset list for that size

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n)
Each person is processed once

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(n)
For HashMap and result storage

====================================================
*/

import java.util.*;

class Solution {

    public List<List<Integer>> groupThePeople(int[] groupSizes) {

        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < groupSizes.length; i++) {

            int size = groupSizes[i];

            // Create list if not present
            map.putIfAbsent(size, new ArrayList<>());

            // Add person to their group-size bucket
            map.get(size).add(i);

            // If group is complete, add to result
            if (map.get(size).size() == size) {
                result.add(map.get(size));
                map.put(size, new ArrayList<>()); // start fresh group
            }
        }

        return result;
    }
}
