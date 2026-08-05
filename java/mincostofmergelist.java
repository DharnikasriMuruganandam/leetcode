import java.util.Arrays;

class Solution {
    public long minMergeCost(int[][] lists) {
        int n = lists.length;
        int numMasks = 1 << n;
        
        int[][] sortedLists = new int[numMasks][];
        int[] totalLen = new int[numMasks];
        int[] median = new int[numMasks];
        
        sortedLists[0] = new int[0];
        
        for (int mask = 1; mask < numMasks; mask++) {
            int i = Integer.numberOfTrailingZeros(mask);
            int prev = mask ^ (1 << i);
            
            int[] arr1 = sortedLists[prev];
            int[] arr2 = lists[i];
            int[] merged = new int[arr1.length + arr2.length];
            
            int p1 = 0, p2 = 0, idx = 0;
            while (p1 < arr1.length && p2 < arr2.length) {
                if (arr1[p1] <= arr2[p2]) {
                    merged[idx++] = arr1[p1++];
                } else {
                    merged[idx++] = arr2[p2++];
                }
            }
            while (p1 < arr1.length) {
                merged[idx++] = arr1[p1++];
            }
            while (p2 < arr2.length) {
                merged[idx++] = arr2[p2++];
            }
            
            sortedLists[mask] = merged;
            totalLen[mask] = merged.length;
            if (merged.length > 0) {
                median[mask] = merged[(merged.length - 1) / 2];
            }
        }
        
        long[] dp = new long[numMasks];
        
        for (int mask = 1; mask < numMasks; mask++) {
            if (Integer.bitCount(mask) <= 1) {
                dp[mask] = 0;
                continue;
            }
            
            dp[mask] = Long.MAX_VALUE;
            for (int submask = (mask - 1) & mask; submask > 0; submask = (submask - 1) & mask) {
                int sub2 = mask ^ submask;
                if (submask < sub2) {
                    long cost = dp[submask] + dp[sub2] + totalLen[mask] + Math.abs(median[submask] - median[sub2]);
                    dp[mask] = Math.min(dp[mask], cost);
                }
            }
        }
        
        return dp[numMasks - 1];
    }
}
