import java.util.*;

class Solution {
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        int n = edges1.length + 1;
        int m = edges2.length + 1;

        List<Integer>[] adj1 = new ArrayList[n];
        for (int i = 0; i < n; i++) adj1[i] = new ArrayList<>();
        for (int[] e : edges1) {
            adj1[e[0]].add(e[1]);
            adj1[e[1]].add(e[0]);
        }

        List<Integer>[] adj2 = new ArrayList[m];
        for (int i = 0; i < m; i++) adj2[i] = new ArrayList<>();
        for (int[] e : edges2) {
            adj2[e[0]].add(e[1]);
            adj2[e[1]].add(e[0]);
        }

        int maxTree2Count = 0;
        if (k > 0) {
            for (int i = 0; i < m; i++) {
                maxTree2Count = Math.max(maxTree2Count, countNodesWithinDistance(i, adj2, m, k - 1));
            }
        }

        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = countNodesWithinDistance(i, adj1, n, k) + maxTree2Count;
        }

        return answer;
    }

    private int countNodesWithinDistance(int start, List<Integer>[] adj, int size, int maxDist) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[size];
        
        queue.add(start);
        visited[start] = true;
        
        int count = 0;
        int currentDist = 0;

        while (!queue.isEmpty() && currentDist <= maxDist) {
            int levelSize = queue.size();
            count += levelSize;
            
            for (int i = 0; i < levelSize; i++) {
                int curr = queue.poll();
                for (int neighbor : adj[curr]) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.add(neighbor);
                    }
                }
            }
            currentDist++;
        }
        return count;
    }
}
