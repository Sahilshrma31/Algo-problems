/*
 * 🔹 Problem: Accounts Merge (LeetCode 721)
 *
 * You are given a list of accounts. Each account has:
 *   - A name (string)
 *   - One or more email addresses
 *
 * If two accounts share at least one email, they belong to the same person
 * and should be merged into a single account with:
 *   - The person’s name
 *   - All unique emails (sorted)
 *
 * -----------------------------------------------------
 * Example:
 * Input:
 * [
 *   ["John","johnsmith@mail.com","john_newyork@mail.com"],
 *   ["John","johnsmith@mail.com","john00@mail.com"],
 *   ["Mary","mary@mail.com"],
 *   ["John","johnnybravo@mail.com"]
 * ]
 *
 * Output:
 * [
 *   ["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],
 *   ["Mary","mary@mail.com"],
 *   ["John","johnnybravo@mail.com"]
 * ]
 *
 * -----------------------------------------------------
 * 🧠 Intuition:
 * - Think of each email as a "node" in a graph.
 * - If two emails appear in the same account, connect them.
 * - Then, all connected emails form one merged account.
 * - Use Disjoint Set Union (DSU / Union-Find) to merge accounts efficiently.
 *
 * -----------------------------------------------------
 * ⚡ Approach:
 * 1. Map each email to the account index it belongs to.
 * 2. If an email is already mapped, union the current account with the earlier one.
 * 3. After union operations, group all emails by their "parent" account.
 * 4. Sort each group’s emails, prepend the account name, and build the final answer.
 *
 * -----------------------------------------------------
 * ⏱️ Time Complexity:
 * - Let N = total number of accounts, M = total number of emails.
 * - Union-Find operations ≈ O(M * α(N))   [α(N) is inverse Ackermann, very small]
 * - Sorting emails per group ≈ O(M log M)
 * - Overall: O(M log M)
 *
 * 💾 Space Complexity:
 * - Storing mappings & groups ≈ O(M + N)
 *
 */

import java.util.*;

class Solution {
    public static List<List<String>> accountsMerge(List<List<String>> details) {
        int n = details.size();
        DisjointSet ds = new DisjointSet(n);
        HashMap<String, Integer> mapMailNode = new HashMap<>();

        // Step 1: Build Union connections
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < details.get(i).size(); j++) {
                String mail = details.get(i).get(j);
                if (!mapMailNode.containsKey(mail)) {
                    mapMailNode.put(mail, i);
                } else {
                    ds.unionBySize(i, mapMailNode.get(mail));
                }
            }
        }

        // Step 2: Group emails by their root parent
        ArrayList<String>[] mergedMail = new ArrayList[n];
        for (int i = 0; i < n; i++) mergedMail[i] = new ArrayList<>();

        for (Map.Entry<String, Integer> it : mapMailNode.entrySet()) {
            String mail = it.getKey();
            int node = ds.findUPar(it.getValue());
            mergedMail[node].add(mail);
        }

        // Step 3: Build the final answer
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (mergedMail[i].size() == 0) continue;
            Collections.sort(mergedMail[i]);
            List<String> temp = new ArrayList<>();
            temp.add(details.get(i).get(0)); // account name
            temp.addAll(mergedMail[i]);      // sorted emails
            ans.add(temp);
        }
        return ans;
    }
}

/*
 * 🔹 Disjoint Set Union (Union-Find) with Union by Size & Path Compression
 */
class DisjointSet {
    int[] parent, size;

    DisjointSet(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    int findUPar(int node) {
        if (parent[node] == node) return node;
        return parent[node] = findUPar(parent[node]); // path compression
    }

    void unionBySize(int u, int v) {
        int pu = findUPar(u);
        int pv = findUPar(v);
        if (pu == pv) return;
        if (size[pu] < size[pv]) {
            parent[pu] = pv;
            size[pv] += size[pu];
        } else {
            parent[pv] = pu;
            size[pu] += size[pv];
        }
    }
}
