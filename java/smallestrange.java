import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.size(); i++) {
            int val = nums.get(i).get(0);
            minHeap.offer(new int[]{val, i, 0});
            max = Math.max(max, val);
        }

        int rangeStart = 0;
        int rangeEnd = Integer.MAX_VALUE;

        while (minHeap.size() == nums.size()) {
            int[] current = minHeap.poll();
            int minVal = current[0];
            int listIdx = current[1];
            int elementIdx = current[2];

            if ((long) max - minVal < (long) rangeEnd - rangeStart) {
                rangeStart = minVal;
                rangeEnd = max;
            }

            if (elementIdx + 1 < nums.get(listIdx).size()) {
                int nextVal = nums.get(listIdx).get(elementIdx + 1);
                minHeap.offer(new int[]{nextVal, listIdx, elementIdx + 1});
                max = Math.max(max, nextVal);
            }
        }

        return new int[]{rangeStart, rangeEnd};
    }
}
