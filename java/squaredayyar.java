import java.util.Arrays;

class Solution {
    private int count = 0;

    public int numSquarefulPerms(int[] nums) {
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        backtrack(nums, used, -1, 0);
        return count;
    }

    private void backtrack(int[] nums, boolean[] used, int lastNum, int length) {
        if (length == nums.length) {
            count++;
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;

            if (lastNum != -1 && !isSquare(lastNum + nums[i])) {
                continue;
            }

            used[i] = true;
            backtrack(nums, used, nums[i], length + 1);
            used[i] = false;
        }
    }

    private boolean isSquare(int val) {
        int root = (int) Math.sqrt(val);
        return root * root == val;
    }
}
