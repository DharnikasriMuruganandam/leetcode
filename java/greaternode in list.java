import java.util.ArrayList;
import java.util.Stack;

class Solution {
    public int[] nextLargerNodes(ListNode head) {
        ArrayList<Integer> values = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            values.add(current.val);
            current = current.next;
        }

        int[] result = new int[values.size()];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < values.size(); i++) {
            while (!stack.isEmpty() && values.get(stack.peek()) < values.get(i)) {
                result[stack.pop()] = values.get(i);
            }
            stack.push(i);
        }

        return result;
    }
}
