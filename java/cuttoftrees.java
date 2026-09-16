import java.util.*;

class Solution {
    public int cutOffTree(List<List<Integer>> forest) {
        int m = forest.size();
        int n = forest.get(0).size();
        List<int[]> trees = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int height = forest.get(i).get(j);
                if (height > 1) {
                    trees.add(new int[]{height, i, j});
                }
            }
        }
        
        Collections.sort(trees, (a, b) -> Integer.compare(a[0], b[0]));
        
        int totalSteps = 0;
        int startX = 0, startY = 0;
        
        for (int[] tree : trees) {
            int steps = bfs(forest, startX, startY, tree[1], tree[2], m, n);
            if (steps == -1) {
                return -1;
            }
            totalSteps += steps;
            startX = tree[1];
            startY = tree[2];
        }
        
        return totalSteps;
    }
    
    private int bfs(List<List<Integer>> forest, int sx, int sy, int tx, int ty, int m, int n) {
        if (sx == tx && sy == ty) {
            return 0;
        }
        
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        
        queue.add(new int[]{sx, sy});
        visited[sx][sy] = true;
        
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int steps = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                for (int[] dir : dirs) {
                    int nx = curr[0] + dir[0];
                    int ny = curr[1] + dir[1];
                    
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny] && forest.get(nx).get(ny) != 0) {
                        if (nx == tx && ny == ty) {
                            return steps;
                        }
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }
        
        return -1;
    }
}
