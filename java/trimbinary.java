/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        // Base case: if the node is empty
        if (root == null) {
            return null;
        }
        
        // If current value is smaller than low, the valid nodes are on the right
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        
        // If current value is larger than high, the valid nodes are on the left
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }
        
        // If current value is in range, recursively trim both subtrees
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        
        return root;
    }
}
