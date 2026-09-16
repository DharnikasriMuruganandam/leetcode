import java.util.*;

class Solution {
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        int n = edges1.length + 1;
        int m = edges2.length + 1;

        List<Integer>[] adj1 = new ArrayList[n];
        for (int i = 0; i < n; i++) adj1[i] = new ArrayList<>();
        for (int[] e : edges1) {
            adj1[e].add(e);
            adj1[e].add(e);
        }

        List<Integer>[] adj2 = new ArrayList[m];
        for (int i = 0; i < m; i++) adj2[i] = new ArrayList<>();
        for (int[] e : edges2) {
            adj2[e].add(e);
            adj2[e].add(e);
        }

        int[] colors1 = new int[n];
        int[] counts1 = new int[2];
        dfs(0, -1, 0, adj1, colors1, counts1);

        int[] colors2 = new int[m];
        int[] counts2 = new int[2];
        dfs(0, -1, 0, adj2, colors2, counts2);

        int maxTree2Contribution = Math.max(counts2[0], counts2[1]);

        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = counts1[colors1[i]] + maxTree2Contribution;
        }

        return answer;
    }

    private void dfs(int node, int parent, int color, List<Integer>[] adj, int[] colors, int[] counts) {
        colors[node] = color;
        counts[color]++;
        for (int neighbor : adj[node]) {
            if (neighbor != parent) {
                dfs(neighbor, node, 1 - color, adj, colors, counts);
            }
        }
    }
}
