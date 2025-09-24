import java.util.*;

// Problem: Student with Highest Average Grade
// Given a list of strings in the format "Name: grade",
// compute the average for each student and return the student with the highest average.

class Solution {
    public String highestAverageStudent(String[] records) {
        // Map to store student -> [totalScore, count]
        Map<String, int[]> map = new HashMap<>();

        // Step 1: Parse input and update map
        for (String rec : records) {
            String[] parts = rec.split(":");
            String name = parts[0].trim();
            int grade = Integer.parseInt(parts[1].trim());

            map.putIfAbsent(name, new int[2]);
            int[] arr = map.get(name);
            arr[0] += grade; // total score
            arr[1] += 1;     // count of grades
        }

        // Step 2: Find student with max average
        String best = "";
        double maxAvg = -1;
        for (String name : map.keySet()) {
            int[] arr = map.get(name);
            double avg = (double) arr[0] / arr[1];
            if (avg > maxAvg) {
                maxAvg = avg;
                best = name;
            }
        }

        return best;
    }

    // Driver code with sample testcases
    public static void main(String[] args) {
        Solution sol = new Solution();

        String[] records1 = {"Alice: 90", "Bob: 80", "Alice: 100", "Charlie: 70", "Bob: 70", "Charlie: 100", "Alice: 95", "Bob: 85", "Charlie: 80"};
        System.out.println("Testcase 1: " + sol.highestAverageStudent(records1));
        // Expected: Alice (average = 95)

        String[] records2 = {"John: 50", "Mary: 100", "John: 100", "Mary: 100"};
        System.out.println("Testcase 2: " + sol.highestAverageStudent(records2));
        // Expected: Mary (average = 100)

        String[] records3 = {"Sam: 70", "Sam: 80", "Lily: 85", "Lily: 75"};
        System.out.println("Testcase 3: " + sol.highestAverageStudent(records3));
        // Expected: Lily (average = 80)
    }
}

/*
Time Complexity: O(N), where N = number of records (we loop once + iterate map)
Space Complexity: O(M), where M = number of unique students
*/