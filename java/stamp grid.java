class Solution {
    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] prefix = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prefix[i + 1][j + 1] = grid[i][j] + prefix[i][j + 1] + prefix[i + 1][j] - prefix[i][j];
            }
        }
        
        int[][] diff = new int[m + 2][n + 2];
        for (int i = 0; i <= m - stampHeight; i++) {
            for (int j = 0; j <= n - stampWidth; j++) {
                int r = i + stampHeight;
                int c = j + stampWidth;
                int occupiedCount = prefix[r][c] - prefix[i][c] - prefix[r][j] + prefix[i][j];
                
                if (occupiedCount == 0) {
                    diff[i + 1][j + 1] += 1;
                    diff[i + 1][c + 1] -= 1;
                    diff[r + 1][j + 1] -= 1;
                    diff[r + 1][c + 1] += 1;
                }
            }
        }
        
        int[][] stampCount = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                stampCount[i + 1][j + 1] = diff[i + 1][j + 1] + stampCount[i][j + 1] + stampCount[i + 1][j] - stampCount[i][j];
                if (grid[i][j] == 0 && stampCount[i + 1][j + 1] == 0) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
