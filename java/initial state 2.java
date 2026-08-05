class Solution {
    public int minimumTimeToInitialState(String word, int k) {
        int n = word.length();
        int[] z = new int[n];
        int l = 0, r = 0;
        
        for (int i = 1; i < n; i++) {
            if (i <= r) {
                z[i] = Math.min(r - i + 1, z[i - l]);
            }
            while (i + z[i] < n && word.charAt(z[i]) == word.charAt(i + z[i])) {
                z[i]++;
            }
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        
        int ans = 1;
        for (int i = k; i < n; i += k) {
            if (z[i] == n - i) {
                return ans;
            }
            ans++;
        }
        
        return ans;
    }
}
