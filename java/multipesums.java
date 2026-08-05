import java.util.PriorityQueue;

class Solution {
    public boolean isPossible(int[] target) {
        if (target.length == 1) {
            return target[0] == 1;
        }

        long totalSum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        for (int num : target) {
            totalSum += num;
            pq.add(num);
        }

        while (pq.peek() > 1) {
            int maxElement = pq.poll();
            long remainingSum = totalSum - maxElement;

            if (remainingSum == 1) {
                return true;
            }

            if (remainingSum == 0 || maxElement <= remainingSum) {
                return false;
            }

            int prevElement = (int) (maxElement % remainingSum);

            if (prevElement == 0) {
                return false;
            }

            pq.add(prevElement);
            totalSum = remainingSum + prevElement;
        }

        return true;
    }
}
