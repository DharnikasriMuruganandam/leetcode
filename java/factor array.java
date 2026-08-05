import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int minStable(int[] nums, int maxC) {
        int n = nums.length;
        int[] minStableStart = new int[n];
        Queue<int[]> queue = new ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            queue.offer(new int[]{nums[i], i});
            int size = queue.size();
            int lastFactor = -1;
            
            for (int j = 0; j < size; j++) {
                int[] pair = queue.poll();
                int currFactor = gcd(pair[0], nums[i]);
                if (currFactor != lastFactor) {
                    pair[0] = currFactor;
                    queue.offer(pair);
                    lastFactor = currFactor;
                }
            }
            
            while (!queue.isEmpty() && queue.peek()[0] < 2) {
                queue.poll();
            }
            
            if (!queue.isEmpty()) {
                minStableStart[i] = queue.peek()[1];
            } else {
                minStableStart[i] = i + 1;
            }
        }
        
        int low = 0, high = n;
        int ans = n;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (check(minStableStart, mid, maxC, n)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return ans;
    }
    
    private boolean check(int[] minStableStart, int stabilityFactor, int maxC, int n) {
        int modifications = 0;
        int index = stabilityFactor;
        
        while (index < n && modifications <= maxC) {
            if (index - minStableStart[index] + 1 > stabilityFactor) {
                modifications++;
                index += stabilityFactor + 1;
            } else {
                index++;
            }
        }
        
        return modifications <= maxC;
    }
    
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
