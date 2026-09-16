import java.util.*;

class Solution {
    private List<Integer>[] adj;
    private int[] ans;
    private int[] nums;
    private int[][] ancestors; 
    private boolean[][] coprime;

    public int[] getCoprimes(int[] nums, int[][] edges) {
        int n = nums.length;
        this.nums = nums;
        this.adj = new ArrayList[n];
        this.ans = new int[n];
        this.ancestors = new int[51][2];
        this.coprime = new boolean[51][51];

        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        for (int i = 1; i <= 50; i++) {
            Arrays.fill(ancestors[i], -1);
            for (int j = 1; j <= 50; j++) {
                if (gcd(i, j) == 1) {
                    coprime[i][j] = true;
                }
            }
        }

        dfs(0, -1, 0);
        return ans;
    }

    private void dfs(int node, int parent, int depth) {
        int val = nums[node];
        int maxDepth = -1;
        int closestAncestor = -1;

        for (int v = 1; v <= 50; v++) {
            if (coprime[val][v] && ancestors[v][1] > maxDepth) {
                maxDepth = ancestors[v][1];
                closestAncestor = ancestors[v][0];
            }
        }
        ans[node] = closestAncestor;

        int prevNode = ancestors[val][0];
        int prevDepth = ancestors[val][1];

        ancestors[val][0] = node;
        ancestors[val][1] = depth;

        for (int neighbor : adj[node]) {
            if (neighbor != parent) {
                dfs(neighbor, node, depth + 1);
            }
        }

        ancestors[val][0] = prevNode;
        ancestors[val][1] = prevDepth;
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
