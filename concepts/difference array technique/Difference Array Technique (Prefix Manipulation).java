/*
    🎯 Topic: Difference Array Technique (Prefix Manipulation)
    🔗 Related LeetCode Problems:
        - 370. Range Addition
        - 1109. Corporate Flight Bookings
        - 1094. Car Pooling
        - 2381. Shifting Letters II

    🧩 Concept Summary:
    The Difference Array Technique allows fast **range updates** in O(1) time
    using boundary marking, and final reconstruction using prefix sum.

    ------------------------------------------------------------
    💡 Intuition:
    Instead of updating every index in a range [l, r],
    we simply mark:
        diff[l] += x
        diff[r + 1] -= x
    Then, the prefix sum of `diff` gives the final updated array.

    This drastically improves performance for large repetitive range updates.
    ------------------------------------------------------------
*/


// ✅ Problem 1 — LeetCode 370. Range Addition
// Add `val` to elements in range [l, r] efficiently using difference array
class RangeAddition {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] diff = new int[length + 1];

        for (int[] u : updates) {
            int l = u[0], r = u[1], val = u[2];
            diff[l] += val;
            if (r + 1 < diff.length) diff[r + 1] -= val;
        }

        int[] ans = new int[length];
        int curr = 0;
        for (int i = 0; i < length; i++) {
            curr += diff[i];
            ans[i] = curr;
        }

        return ans;
    }
}


/*
    ------------------------------------------------------------
    ✅ Problem 2 — LeetCode 1109. Corporate Flight Bookings

    🧩 Summary:
    Each booking adds `seats` to flights in range [first, last].
    Use difference array to handle range additions efficiently.
*/
class CorporateFlightBookings {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] diff = new int[n];

        for (int[] b : bookings) {
            int start = b[0] - 1;
            int end = b[1] - 1;
            int seats = b[2];

            diff[start] += seats;
            if (end + 1 < n) diff[end + 1] -= seats;
        }

        for (int i = 1; i < n; i++) {
            diff[i] += diff[i - 1];
        }

        return diff;
    }
}


/*
    ------------------------------------------------------------
    ✅ Problem 3 — LeetCode 1094. Car Pooling

    🧩 Summary:
    Keep track of passenger pickup and drop-off events using diff array.
    At each stop:
      diff[from] += passengers
      diff[to]   -= passengers

    Traverse prefix sum and ensure car capacity is never exceeded.
*/
class CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] diff = new int[1001]; // max stop index constraint

        for (int[] t : trips) {
            int passengers = t[0];
            int from = t[1];
            int to = t[2];

            diff[from] += passengers;
            diff[to] -= passengers;
        }

        int curr = 0;
        for (int i = 0; i < 1001; i++) {
            curr += diff[i];
            if (curr > capacity) return false;
        }

        return true;
    }
}


/*
    ------------------------------------------------------------
    ✅ Problem 4 — LeetCode 2381. Shifting Letters II

    🧩 Summary:
    Each operation shifts substring [l, r]:
        dir = 1 → shift forward
        dir = 0 → shift backward

    We mark these changes in a diff array, 
    then apply prefix sum to get the net shift per character.
*/
class ShiftingLettersII {
    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        int[] diff = new int[n + 1];

        for (int[] sh : shifts) {
            int l = sh[0], r = sh[1];
            int delta = (sh[2] == 1) ? 1 : -1;

            diff[l] += delta;
            diff[r + 1] -= delta;
        }

        StringBuilder sb = new StringBuilder();
        int shift = 0;

        for (int i = 0; i < n; i++) {
            shift = (shift + diff[i]) % 26;
            if (shift < 0) shift += 26;

            int base = s.charAt(i) - 'a';
            int newChar = (base + shift) % 26;
            sb.append((char) ('a' + newChar));
        }

        return sb.toString();
    }
}


/*
    ------------------------------------------------------------
    🧠 Master Summary:
    Difference Array = Efficient Range Update Technique

    ✅ Used for:
       - Range increment/decrement queries
       - Batch updates on arrays
       - Efficient simulation of continuous effects

    ✅ Core Idea:
       diff[l] += x
       diff[r + 1] -= x
       Then take prefix sum to reconstruct the final array.

    ------------------------------------------------------------
    ⏱️ Time Complexity:
       - Range Update: O(1)
       - Final Reconstruction: O(n)

    💾 Space Complexity:
       O(n)

    ------------------------------------------------------------
    📘 Author: Sahil Sharma
*/
