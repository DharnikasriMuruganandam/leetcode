import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean splitArraySameAverage(int[] nums) {
        int n = nums.length;
        if (n == 1) return false;
        
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        
        boolean possible = false;
        for (int i = 1; i <= n / 2; i++) {
            if ((totalSum * i) % n == 0) {
                possible = true;
                break;
            }
        }
        if (!possible) return false;
        
        int m = n / 2;
        Set<Integer>[] leftSums = new Set[m + 1];
        for (int i = 0; i <= m; i++) {
            leftSums[i] = new HashSet<>();
        }
        leftSums[0].add(0);
        
        for (int i = 0; i < m; i++) {
            for (int j = i; j >= 0; j--) {
                for (int sum : leftSums[j]) {
                    leftSums[j + 1].add(sum + nums[i]);
                }
            }
        }
        
        Set<Integer>[] rightSums = new Set[n - m + 1];
        for (int i = 0; i <= n - m; i++) {
            rightSums[i] = new HashSet<>();
        }
        rightSums[0].add(0);
        
        for (int i = m; i < n; i++) {
            for (int j = i - m; j >= 0; j--) {
                for (int sum : rightSums[j]) {
                    rightSums[j + 1].add(sum + nums[i]);
                }
            }
        }
        
        for (int len = 1; len <= n / 2; len++) {
            if ((totalSum * len) % n == 0) {
                int targetSum = (totalSum * len) / n;
                for (int k = 0; k <= len; k++) {
                    if (k <= m && (len - k) <= (n - m)) {
                        for (int leftSum : leftSums[k]) {
                            if (rightSums[len - k].contains(targetSum - leftSum)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        
        return false;
    }
}
