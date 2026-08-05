import java.util.Arrays;

class Solution {
    public int maxValueAfterReverse(int[] nums) {
        int n = nums.length;
        int originalValue = 0;
        for (int i = 0; i < n - 1; i++) {
            originalValue += Math.abs(nums[i] - nums[i + 1]);
        }

        int maxGain = 0;
        for (int i = 1; i < n - 1; i++) {
            maxGain = Math.max(maxGain, Math.abs(nums[0] - nums[i + 1]) - Math.abs(nums[i] - nums[i + 1]));
            maxGain = Math.max(maxGain, Math.abs(nums[n - 1] - nums[i - 1]) - Math.abs(nums[i] - nums[i - 1]));
        }

        int maxMin = Integer.MIN_VALUE;
        int minMax = Integer.MAX_VALUE;

        for (int i = 0; i < n - 1; i++) {
            int a = nums[i];
            int b = nums[i + 1];
            maxMin = Math.max(maxMin, Math.min(a, b));
            minMax = Math.min(minMax, Math.max(a, b));
        }

        maxGain = Math.max(maxGain, 2 * (maxMin - minMax));

        return originalValue + maxGain;
    }
}
