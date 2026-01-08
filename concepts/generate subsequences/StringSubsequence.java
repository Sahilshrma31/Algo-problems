import java.util.*;

class Solution {
    static ArrayList<String> list;
    static int n;

    public static ArrayList<String> subsequences(String str) {
        list = new ArrayList<>();
        n = str.length();
        solve(0, str, new StringBuilder());
        return list;
    }

    public static void solve(int idx, String str, StringBuilder sb) {
        if (idx == n) {
            if (sb.length() > 0) {   // skip empty subsequence
                list.add(sb.toString());
            }
            return;  // ✅ THIS WAS MISSING
        }

        // pick
        sb.append(str.charAt(idx));
        solve(idx + 1, str, sb);
        sb.deleteCharAt(sb.length() - 1);

        // not pick
        solve(idx + 1, str, sb);
    }
}
