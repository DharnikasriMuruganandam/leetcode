import java.util.Arrays;

class Solution {
    public boolean gcdSort(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }

        int[] parent = new int[max + 1];
        for (int i = 0; i <= max; i++) {
            parent[i] = i;
        }

        boolean[] hasNum = new boolean[max + 1];
        for (int num : nums) {
            hasNum[num] = true;
        }

        boolean[] visited = new boolean[max + 1];
        for (int i = 2; i <= max; i++) {
            if (!visited[i]) {
                for (int j = i; j <= max; j += i) {
                    visited[j] = true;
                    if (hasNum[j]) {
                        union(parent, i, j);
                    }
                }
            }
        }

        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);

        for (int i = 0; i < nums.length; i++) {
            if (find(parent, nums[i]) != find(parent, sortedNums[i])) {
                return false;
            }
        }

        return true;
    }

    private int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent, parent[i]);
    }

    private void union(int[] parent, int i, int j) {
        int rootI = find(parent, i);
        int rootJ = find(parent, j);
        if (rootI != rootJ) {
            parent[rootI] = rootJ;
        }
    }
}
