import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public long[] countStableSubarrays(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] len = new int[n];
        long[] prefLen = new long[n + 1];
        List<Integer> inversions = new ArrayList<>();
        
        len[0] = 1;
        prefLen[1] = 1;
        
        for (int i = 1; i < n; i++) {
            if (nums[i] >= nums[i - 1]) {
                len[i] = len[i - 1] + 1;
            } else {
                len[i] = 1;
                inversions.add(i);
            }
            prefLen[i + 1] = prefLen[i] + len[i];
        }
        
        int q = queries.length;
        long[] ans = new long[q];
        
        for (int k = 0; k < q; k++) {
            int L = queries[k][0];
            int R = queries[k][1];
            
            int idx = Collections.binarySearch(inversions, L + 1);
            if (idx < 0) {
                idx = -(idx + 1);
            }
            
            if (idx == inversions.size() || inversions.get(idx) > R) {
                long rangeLen = R - L + 1;
                ans[k] = rangeLen * (rangeLen + 1) / 2;
            } else {
                int M = inversions.get(idx);
                long rangeLen = M - L;
                long firstPart = rangeLen * (rangeLen + 1) / 2;
                long secondPart = prefLen[R + 1] - prefLen[M];
                ans[k] = firstPart + secondPart;
            }
        }
        
        return ans;
    }
}
