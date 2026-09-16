import java.util.*;

class Solution {
    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        int d1 = getDiameter(edges1);
        int d2 = getDiameter(edges2);
        
        int r1 = (d1 + 1) / 2;
        int r2 = (d2 + 1) / 2;
        
        return Math.max(Math.max(d1, d2), r1 + r2 + 1);
    }
    
    private int getDiameter(int[][] edges) {
        int n = edges.length + 1;
        if (n == 1) return 0;
        
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        
        int[] farNodeData = bfs(0, adj, n);
        int[] diameterData = bfs(farNodeData[0], adj, n);
        
        return diameterData[1];
    }
    
    private int[] bfs(int start, List<Integer>[] adj, int n) {
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(start);
        dist[start] = 0;
        
        int farNode = start;
        int maxDist = 0;
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int neighbor : adj[curr]) {
                if (dist[neighbor] == -1) {
                    dist[neighbor] = dist[curr] + 1;
                    queue.add(neighbor);
                    if (dist[neighbor] > maxDist) {
                        maxDist = dist[neighbor];
                        farNode = neighbor;
                    }
                }
            }
        }
        return new int[]{farNode, maxDist};
    }
}
