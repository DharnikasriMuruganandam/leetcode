import java.util.*;

class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            List<Integer> res = new ArrayList<>();
            res.add(0);
            return res;
        }

        List<Integer>[] adj = new List[n];
        int[] degree = new int[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
            degree[edge[0]]++;
            degree[edge[1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.add(i);
            }
        }

        int remainingNodes = n;
        while (remainingNodes > 2) {
            int size = queue.size();
            remainingNodes -= size;
            for (int i = 0; i < size; i++) {
                int leaf = queue.poll();
                for (int neighbor : adj[leaf]) {
                    degree[neighbor]--;
                    if (degree[neighbor] == 1) {
                        queue.add(neighbor);
                    }
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }
        return result;
    }
}
