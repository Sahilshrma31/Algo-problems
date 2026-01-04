/*
====================================================
📌 Problem: Number of Laser Beams in a Bank
(LeetCode 2125)
====================================================

You are given a 2D representation of a bank where:
- '1' represents a security device
- '0' represents an empty space

A laser beam is formed between two rows if:
- Both rows contain at least one security device
- All rows between them contain no security devices

The number of beams between two such rows is:
(number of devices in row1) × (number of devices in row2)

Return the total number of laser beams.

----------------------------------------------------
🧠 Intuition
----------------------------------------------------
- Only rows with at least one '1' matter
- For every valid row, multiply its device count
  with the previous valid row's device count
- Accumulate the result

----------------------------------------------------
🛠 Approach
----------------------------------------------------
1️⃣ Iterate through each row
2️⃣ Count number of '1's in the row
3️⃣ If current row has devices:
   - Add (prevRowDevices × currentRowDevices) to answer
   - Update prevRowDevices

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(m × n)
where:
- m = number of rows
- n = number of columns

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(1)

====================================================
*/

class Solution {

    public int numberOfBeams
