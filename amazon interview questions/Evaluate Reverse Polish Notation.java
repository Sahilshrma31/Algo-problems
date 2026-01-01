/*
====================================================
📌 Problem: Evaluate Reverse Polish Notation
(LeetCode 150)
====================================================

Given an array of strings tokens representing an
arithmetic expression in Reverse Polish Notation (RPN),
evaluate the expression and return the result.

Valid operators: +, -, *, /
Each operand may be an integer or another expression.

----------------------------------------------------
🧠 Approach (Stack Based)
----------------------------------------------------
- Use a stack to store operands
- Traverse tokens:
  - If token is a number → push to stack
  - If token is an operator:
      - Pop two elements (a, b)
      - Apply operation: a (op) b
      - Push result back to stack
- Final stack top is the answer

----------------------------------------------------
⏱ Time Complexity
----------------------------------------------------
O(n), where n = number of tokens

----------------------------------------------------
📦 Space Complexity
----------------------------------------------------
O(n) for stack

====================================================
*/

import java.util.*;

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {

            // If operator
            if (token.equals("+") || token.equals("-") ||
                token.equals("*") || token.equals("/")) {

                int b = stack.pop(); // second operand
                int a = stack.pop(); // first operand

                if (token.equals("+")) stack.push(a + b);
                else if (token.equals("-")) stack.push(a - b);
                else if (token.equals("*")) stack.push(a * b);
                else stack.push(a / b);
            }
            // If operand
            else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }
}
