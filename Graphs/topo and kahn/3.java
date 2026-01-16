/*
====================================================
📌 Problem: Course Schedule II
(LeetCode 210)
====================================================

You are given:
- `numCourses` → total number of courses labeled from 0 to n-1
- `prerequisites[i] = [a, b]` meaning:
  to take course `a`, you must first take course `b`

Return:
- A valid order to finish all courses
- If it is impossible (cycle exists), return an empty array

====================================================
🧠 Approach: Topological Sort (Kahn’s Algorithm – BFS)
====================================================

- Model courses as a **directed graph**:
  b → a  (edge from prerequisite to course)

- Maintain:
  1️⃣ Adjacency list (graph)
  2️⃣ Indegree array (number of prerequisites for each course)

- Push all courses with indegree = 0 into a queue
- Perform BFS:
  - Remove a course from queue
  - Add it to the answer order
  - Reduce indegree of dependent courses
  - If any course’s indegree becomes 0 → push to queue

- If all courses are processed → valid order exists
- Else → cycle detected → return empty array

====================================================
⏱ Time Complexity
====================================================
O(V + E)

V = numCourses  
E = number of prerequisite relations  

====================================================
📦 Space Complexity
====================================================
O(V + E)

- Graph storage
- Indegree array
- Queue

====================================================
*/

import java.util.*;

class Solution {

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        // Step 1: Build graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[numCourses];

        for (int[] p : prerequisites) {
            int course = p[0];
            int prereq = p[1];
            graph.get(prereq).add(course);
            indegree[course]++;
        }

        // Step 2: Push all courses with indegree 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Step 3: BFS Topological Sort
        int[] order = new int[numCourses];
        int idx = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            order[idx++] = curr;

            for (int next : graph.get(curr)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        // Step 4: Check if all courses are completed
        if (idx == numCourses) {
            return order;
        }

        // Cycle exists
        return new int[0];
    }
}
