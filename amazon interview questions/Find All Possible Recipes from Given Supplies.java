/*
====================================================
📌 Problem: Find All Possible Recipes from Given Supplies"
(LeetCode 2115)
====================================================

You are given:
- `recipes[]`        → list of recipe names
- `ingredients[][]` → ingredients required for each recipe
- `supplies[]`      → items you initially have unlimited supply of

A recipe can be made **only if all its ingredients** are available
(either from supplies or from other recipes you can make).

Return **all recipes** that can be prepared.

====================================================
🧠 Approach: Topological Sort (Kahn’s Algorithm)
====================================================

- Treat this as a **dependency graph**:
  ingredient  → recipe (edge)

- Build:
  1️⃣ Graph:
     ingredient -> list of recipes that depend on it
  2️⃣ Indegree:
     number of unmet ingredients for each recipe

- Initialize a queue with all initial supplies

- BFS:
  - Take an available item (supply or cooked recipe)
  - Reduce indegree of dependent recipes
  - If a recipe’s indegree becomes 0 → it can be cooked
    → push it into queue and add to result

- Continue until queue is empty

====================================================
⏱ Time Complexity
====================================================
O(R + I)

R = number of recipes  
I = total number of ingredients across all recipes  

====================================================
📦 Space Complexity
====================================================
O(R + I)

- Graph storage
- Indegree map
- Queue

====================================================
*/

import java.util.*;

class Solution {

    public List<String> findAllRecipes(
            String[] recipes,
            List<List<String>> ingredients,
            String[] supplies) {

        // ingredient -> list of recipes that depend on it
        Map<String, List<String>> graph = new HashMap<>();

        // recipe -> number of ingredients still needed
        Map<String, Integer> indegree = new HashMap<>();

        // Initialize indegree of all recipes
        for (String recipe : recipes) {
            indegree.put(recipe, 0);
        }

        // Build graph and indegree
        for (int i = 0; i < recipes.length; i++) {
            String recipe = recipes[i];

            for (String ing : ingredients.get(i)) {

                graph.computeIfAbsent(ing, k -> new ArrayList<>())
                     .add(recipe);

                indegree.put(recipe, indegree.get(recipe) + 1);
            }
        }

        // Queue starts with all available supplies
        Queue<String> queue = new LinkedList<>();
        for (String s : supplies) {
            queue.offer(s);
        }

        List<String> result = new ArrayList<>();

        // Kahn's BFS
        while (!queue.isEmpty()) {
            String item = queue.poll();

            if (!graph.containsKey(item)) continue;

            for (String recipe : graph.get(item)) {
                indegree.put(recipe, indegree.get(recipe) - 1);

                if (indegree.get(recipe) == 0) {
                    queue.offer(recipe);
                    result.add(recipe);
                }
            }
        }

        return result;
    }
}
