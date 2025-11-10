/*
    🧩 Problem: A. Souvlaki VS Kalamaki
    📚 Platform: Codeforces
    ⚙️ Difficulty: Easy
    🧠 Concept: Game Theory + Sorting + Parity Observation

    ---------------------------------------------------------------------------
    📜 Problem Summary:
    ---------------------------------------------------------------------------
    Two players, Souvlaki and Kalamaki, are given an array of n integers.

    - The game has (n - 1) rounds.
    - In each round i:
        - The player can either swap a[i] and a[i+1] or skip.
    - Souvlaki plays on odd-numbered rounds.
    - Kalamaki plays on even-numbered rounds.
    - After all rounds, if the array is sorted (non-decreasing), Souvlaki wins.
      Otherwise, Kalamaki wins.

    But before the game starts, Souvlaki can rearrange (shuffle) the array
    however he wants. The question:
        👉 Can Souvlaki rearrange the array so that he always wins,
           no matter how Kalamaki plays?

    ---------------------------------------------------------------------------
    💡 Intuition (in simple Hinglish):
    ---------------------------------------------------------------------------
    - Souvlaki pehle array ko kisi bhi order mein rakh sakta hai (rearrange kar sakta hai).
      Iska matlab woh best starting position choose karega.

    - Har round ek pair pe control deta hai:
        Round 1 → (a1, a2) → Souvlaki
        Round 2 → (a2, a3) → Kalamaki
        Round 3 → (a3, a4) → Souvlaki
        Round 4 → (a4, a5) → Kalamaki
        and so on...

    - So, Kalamaki controls pairs starting from even rounds
      (0-based odd indices): (a[1], a[2]), (a[3], a[4]), etc.

    - If in any of these pairs, the elements are different,
      Kalamaki can swap them and break sorting.
      Example: [1, 2, 3, 4] → Kalamaki swaps (2, 3) → [1, 3, 2, 4] ❌ Not sorted.

    ✅ Therefore, to guarantee a win for Souvlaki:
        Every Kalamaki-controlled pair must have equal elements.

    - Since Souvlaki can rearrange array freely,
      we first sort the array (best possible arrangement),
      then check if every Kalamaki pair (1,2), (3,4), ... have equal elements.

    ---------------------------------------------------------------------------
    🧮 Approach:
    ---------------------------------------------------------------------------
    1. Sort the array.
    2. Traverse with step 2 from index 1:
        - If nums[i] != nums[i+1], Souvlaki can’t guarantee win → "NO"
    3. Otherwise → "YES"

    ---------------------------------------------------------------------------
    ⏱️ Time Complexity:
        O(n log n) → for sorting
    💾 Space Complexity:
        O(1) → no extra significant space used

    ---------------------------------------------------------------------------
    🧠 Example:
    ---------------------------------------------------------------------------
    Input:
        4
        4 2 2 1

    Sorted: [1, 2, 2, 4]
    Kalamaki pairs:
        (a[1], a[2]) = (2, 2) ✅ equal → he can’t break sorting
    Output: YES
*/

import java.util.*;
import java.io.*;

public class A_Souvlaki_VS_Kalamaki {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();

        while (testCases-- > 0) {
            int size = sc.nextInt();
            int[] nums = new int[size];

            for (int i = 0; i < size; i++) {
                nums[i] = sc.nextInt();
            }

            Arrays.sort(nums);
            boolean possible = true;

            for (int i = 1; i < size - 1; i += 2) {
                if (nums[i] != nums[i + 1]) {
                    possible = false;
                    break;
                }
            }

            System.out.println(possible ? "YES" : "NO");
        }

        sc.close();
    }
}
