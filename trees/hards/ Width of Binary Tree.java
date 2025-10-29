import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {
        this.val = val;
    }
}

/**
 * Problem: Width of Binary Tree
 * ----------------------------------------
 * Given a binary tree, return the maximum width of the given tree.
 * The width of one level is defined as the length between the end-nodes
 * (the leftmost and rightmost non-null nodes in the level),
 * where the null nodes between the end-nodes are also counted.
 * 
 * Example:
 * Input: [1,3,2,5,3,null,9]
 * Output: 4
 * Explanation:
 * The maximum width exists in the third level with length 4 (5,3,null,9).
 */
public class Solution {
    
    // Helper class to store the node and its "index" in the level
    class Pair {
        TreeNode node;
        int num;
        Pair(TreeNode _node, int _num) {
            node = _node;
            num = _num;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        int ans = 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        // Level-order traversal (BFS)
        while (!queue.isEmpty()) {
            int size = queue.size();
            int mmin = queue.peek().num; // Minimum index at current level
            int first = 0, last = 0;

            for (int i = 0; i < size; i++) {
                int curId = queue.peek().num - mmin; // Prevent integer overflow
                TreeNode node = queue.peek().node;
                queue.poll();

                if (i == 0) first = curId;          // Leftmost node index
                if (i == size - 1) last = curId;    // Rightmost node index

                // Assign new indices for left and right children
                if (node.left != null)
                    queue.offer(new Pair(node.left, 2 * curId + 1));

                if (node.right != null)
                    queue.offer(new Pair(node.right, 2 * curId + 2));
            }

            ans = Math.max(ans, last - first + 1);
        }

        return ans;
    }

    // Optional: main method for testing
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example Tree:
        //        1
        //      /   \
        //     3     2
        //    / \     \
        //   5   3     9

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);

        System.out.println("Maximum Width: " + sol.widthOfBinaryTree(root));
    }
}
