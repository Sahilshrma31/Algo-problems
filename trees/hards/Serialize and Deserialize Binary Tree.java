/**
 * 🔹 Problem: Serialize and Deserialize Binary Tree
 * LeetCode #297
 *
 * 🧩 Problem Summary:
 * We need to design an algorithm to encode a binary tree to a string 
 * (serialize), and then decode the string back to the same tree structure (deserialize).
 *
 * The serialization should preserve the exact structure of the tree.
 *
 * Example:
 * Input Tree:     1
 *                / \
 *               2   3
 *                  / \
 *                 4   5
 * Serialized: "1 2 3 n n 4 5 n n n n"
 * Deserialized → same structure restored.
 *
 * --------------------------------------------------
 * 💡 Intuition:
 * - For serialization, perform Level Order Traversal (BFS).
 *   Append "n" for null nodes to preserve structure.
 * - For deserialization, rebuild the tree using the serialized order.
 *   Use a queue to assign left and right children level-wise.
 *
 * --------------------------------------------------
 * 🧠 Approach:
 * 1️⃣ **Serialize**:
 *    - Perform BFS using a queue.
 *    - Add "n" for null nodes.
 *    - Append node values separated by spaces.
 *
 * 2️⃣ **Deserialize**:
 *    - Split string by spaces.
 *    - Create root from first element.
 *    - Use queue to assign left and right children.
 *    - Skip "n" nodes.
 *
 * --------------------------------------------------
 * ⏱️ Time Complexity:
 * - Serialization: O(N)
 * - Deserialization: O(N)
 *   (Each node is processed exactly once)
 *
 * 💾 Space Complexity:
 * - O(N) for queue and output string.
 *
 * --------------------------------------------------
 * ✅ Works for:
 * - Empty tree
 * - Skewed trees
 * - Complete trees
 */

import java.util.*;

public class Codec {

    // 🔸 Serialize: Convert tree → string (BFS)
    public String serialize(TreeNode root) {
        if (root == null) {
            return "n ";
        }

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                sb.append("n ");
                continue;
            }
            sb.append(curr.val).append(" ");
            queue.offer(curr.left);
            queue.offer(curr.right);
        }

        return sb.toString();
    }

    // 🔸 Deserialize: Convert string → tree (BFS)
    public TreeNode deserialize(String data) {
        if (data.equals("") || data.startsWith("n")) {
            return null;
        }

        String[] values = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        for (int i = 1; i < values.length; i++) {
            TreeNode curr = queue.poll();

            if (!values[i].equals("n")) {
                TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                curr.left = left;
                queue.add(left);
            }

            if (++i < values.length && !values[i].equals("n")) {
                TreeNode right = new TreeNode(Integer.parseInt(values[i]));
                curr.right = right;
                queue.add(right);
            }
        }
        return root;
    }
}

/**
 * Usage:
 * Codec ser = new Codec();
 * Codec deser = new Codec();
 * TreeNode ans = deser.deserialize(ser.serialize(root));
 */
