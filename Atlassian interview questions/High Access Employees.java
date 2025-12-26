/*
====================================================
📌 Problem: High Access Employees
====================================================

You are given a list of access logs where each entry
contains:
- Employee name
- Access time (HHMM format)

An employee is considered **high access** if they
access the system **3 or more times within any
1-hour (60 minutes) window**.

----------------------------------------------------
🧠 Approach
----------------------------------------------------
1. Convert each access time to minutes.
2. Group access times by employee using a HashMap.
3. Sort each employee’s access times.
4. Use a sliding window of size 3 to check if
   time[i+2] - time[i] < 60.
5. If yes → mark employee as high access.

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
Let N = number of access records

- Grouping: O(N)
- Sorting per employee: O(K log K)
- Overall: O(N log N)

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
- HashMap storage: O(N)
- Result list: O(N)

====================================================
*/

import java.util.*;

class Solution {

    public List<String> findHighAccessEmployees(List<List<String>> access_times) {

        // Map: Employee -> List of access times (in minutes)
        HashMap<String, List<Integer>> map = new HashMap<>();

        // Step 1: Build the map
        for (List<String> entry : access_times) {
            String name = entry.get(0);
            int time = convertTime(entry.get(1));

            map.putIfAbsent(name, new ArrayList<>());
            map.get(name).add(time);
        }

        List<String> result = new ArrayList<>();

        // Step 2: Check each employee
        for (String name : map.keySet()) {
            List<Integer> times = map.get(name);
            if (times.size() < 3) continue;

            Collections.sort(times);

            // Sliding window of size 3
            for (int i = 0; i + 2 < times.size(); i++) {
                if (times.get(i + 2) - times.get(i) < 60) {
                    result.add(name);
                    break;
                }
            }
        }

        return result;
    }

    // Convert "HHMM" → minutes
    private int convertTime(String s) {
        int hour = Integer.parseInt(s.substring(0, 2));
        int minute = Integer.parseInt(s.substring(2, 4));
        return hour * 60 + minute;
    }
}

