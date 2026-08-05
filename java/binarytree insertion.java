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
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // Base case: found the correct spot to insert the new value
        if (root == null) {
            return new TreeNode(val);
        }
        
        // If the value to insert is greater, traverse to the right subtree
        if (val > root.val) {
            root.right = insertIntoBST(root.right, val);
        } 
        // If the value to insert is smaller, traverse to the left subtree
        else {
            root.left = insertIntoBST(root.left, val);
        }
        
        // Return the unchanged node pointer
        return root;
    }
}
