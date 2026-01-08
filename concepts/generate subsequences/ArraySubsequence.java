import java.util.*;

class Solution {
    static ArrayList<ArrayList<Integer>> list;
    static int n;

    public static ArrayList<ArrayList<Integer>> subsequences(int[] arr) {
        list = new ArrayList<>();
        n = arr.length;
        solve(0, arr, new ArrayList<>());
        return list;
    }

    private static void solve(int idx, int[] arr, ArrayList<Integer> curr) {
        if (idx == n) {
            if (!curr.isEmpty()) {   // skip empty subsequence
                list.add(new ArrayList<>(curr));
            }
            return;
        }

        // pick
        curr.add(arr[idx]);
        solve(idx + 1, arr, curr);
        curr.remove(curr.size() - 1);

        // not pick
        solve(idx + 1, arr, curr);
    }
}
