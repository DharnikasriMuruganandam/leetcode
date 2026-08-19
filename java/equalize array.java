import java.util.Arrays;

class Solution {
    public int minCostToEqualizeArray(int[] nums, int cost1, int cost2) {
        long mod = 1_000_000_007;
        int n = nums.length;
        
        long maxNum = Integer.MIN_VALUE;
        long minNum = Integer.MAX_VALUE;
        long sum = 0;
        
        for (int num : nums) {
            if (num > maxNum) maxNum = num;
            if (num < minNum) minNum = num;
            sum += num;
        }
        
        long totalGap = maxNum * n - sum;
        
        if (cost1 * 2 <= cost2 || n <= 2) {
            return (int) ((totalGap * cost1) % mod);
        }
        
        long ans = Long.MAX_VALUE;
        long searchLimit = 2 * maxNum;
        
        for (long target = maxNum; target <= searchLimit; target++) {
            long currentTotalGap = target * n - sum;
            long maxGap = target - minNum;
            
            long pairs;
            if (maxGap > currentTotalGap - maxGap) {
                pairs = currentTotalGap - maxGap;
            } else {
                pairs = currentTotalGap / 2;
            }
            
            long singles = currentTotalGap - 2 * pairs;
            long currentCost = pairs * cost2 + singles * cost1;
            
            if (currentCost < ans) {
                ans = currentCost;
            }
        }
        
        return (int) (ans % mod);
    }
}
