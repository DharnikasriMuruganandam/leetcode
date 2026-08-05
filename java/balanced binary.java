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
import java.util.ArrayList;
import java.util.List;

class Solution {
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> sortedNodes = new ArrayList<>();
        // Step 1: Store nodes in sorted order using inorder traversal
        inorder(root, sortedNodes);
        
        // Step 2: Build a balanced BST from the sorted list
        return buildBalancedTree(sortedNodes, 0, sortedNodes.size() - 1);
    }
    
    private void inorder(TreeNode root, List<Integer> sortedNodes) {
        if (root == null) {
            return;
        }
        inorder(root.left, sortedNodes);
        sortedNodes.add(root.val);
        inorder(root.right, sortedNodes);
    }
    
    private TreeNode buildBalancedTree(List<Integer> sortedNodes, int start, int end) {
        if (start > end) {
            return null;
        }
        
        // Find the middle element to make it the root
        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(sortedNodes.get(mid));
        
        // Recursively build the left and right subtrees
        node.left = buildBalancedTree(sortedNodes, start, mid - 1);
        node.right = buildBalancedTree(sortedNodes, mid + 1, end);
        
        return node;
    }
}
