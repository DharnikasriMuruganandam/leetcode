class Solution {
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (root == null) {
            return false;
        }
        return checkPath(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean checkPath(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (head.val != root.val) {
            return false;
        }
        return checkPath(head.next, root.left) || checkPath(head.next, root.right);
    }
}
