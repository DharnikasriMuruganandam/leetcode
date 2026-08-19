import java.util.Arrays;

class Solution {
    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < nums1.length; i++) {
            sum1 += nums1[i];
            sum2 += nums2[i];
        }

        return Math.max(sum1 + maxSubarrayGain(nums1, nums2), sum2 + maxSubarrayGain(nums2, nums1));
    }

    private int maxSubarrayGain(int[] base, int[] target) {
        int maxGain = 0;
        int currentGain = 0;

        for (int i = 0; i < base.length; i++) {
            currentGain += target[i] - base[i];
            if (currentGain < 0) {
                currentGain = 0;
            }
            if (currentGain > maxGain) {
                maxGain = currentGain;
            }
        }

        return maxGain;
    }
}
