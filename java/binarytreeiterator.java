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
import java.util.Stack;

class BSTIterator {
    // Stack to keep track of the path to the next smallest node
    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        // Initialize the stack by pushing all leftmost nodes
        pushLeftNodes(root);
    }
    
    public int next() {
        // The top of the stack contains the next smallest node
        TreeNode node = stack.pop();
        
        // If the node has a right child, process its leftmost branch
        if (node.right != null) {
            pushLeftNodes(node.right);
        }
        
        return node.val;
    }
    
    public boolean hasNext() {
        // If the stack is not empty, there are more numbers to visit
        return !stack.isEmpty();
    }

    // Helper method to push a node and all of its left descendants
    private void pushLeftNodes(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
