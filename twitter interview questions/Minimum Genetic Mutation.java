import java.util.*;

/**
 * Problem: Minimum Genetic Mutation (LeetCode 433)
 *
 * You are given a start gene, an end gene, and a bank of valid genes.
 * Each gene mutation changes exactly one character and the result
 * must exist in the bank.
 *
 * The goal is to find the minimum number of mutations needed
 * to convert startGene into endGene.
 *
 * If it is not possible, return -1.
 *
 * ------------------------------------------------------------
 * Intuition:
 * ------------------------------------------------------------
 * Each gene string can be treated as a node in a graph.
 * An edge exists between two nodes if they differ by exactly one character.
 *
 * We use Breadth First Search (BFS) because:
 * - Each mutation has equal cost (1 step)
 * - BFS always finds the shortest path first
 *
 * ------------------------------------------------------------
 * Approach:
 * ------------------------------------------------------------
 * 1. Store all valid genes from the bank in a HashSet for fast lookup.
 * 2. Use BFS starting from startGene.
 * 3. At each level, mutate one character using 'A', 'C', 'G', 'T'.
 * 4. Push unvisited valid mutations into the queue.
 * 5. Each BFS level represents one mutation (step).
 *
 * ------------------------------------------------------------
 * Time Complexity:
 * ------------------------------------------------------------
 * O(N * L * 4)
 * where:
 *   N = number of genes in the bank
 *   L = length of each gene (usually 8)
 *
 * ------------------------------------------------------------
 * Space Complexity:
 * ------------------------------------------------------------
 * O(N)
 * for the queue and visited set.
 */

class Solution {

    public int minMutation(String startGene, String endGene, String[] bank) {

        char[] genes = {'A', 'C', 'G', 'T'};

        HashSet<String> set = new HashSet<>();
        for (String el : bank) {
            set.add(el);
        }

        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();

        int steps = 0;

        queue.add(startGene);
        visited.add(startGene);

        while (!queue.isEmpty()) {
            int count = queue.size();

            for (int i = 0; i < count; i++) {
                String curr = queue.poll();

                if (curr.equals(endGene)) {
                    return steps;
                }

                for (int j = 0; j < curr.length(); j++) {
                    for (char ch : genes) {

                        char[] arr = curr.toCharArray();
                        arr[j] = ch;
                        String next = new String(arr);

                        if (!visited.contains(next) && set.contains(next)) {
                            visited.add(next);
                            queue.add(next);
                        }
                    }
                }
            }
            steps++;
        }

        return -1;
    }
}
