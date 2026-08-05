import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<>();
        HashSet<String> set = new HashSet<>(Arrays.asList(words));

        for (String word : words) {
            set.remove(word);
            if (canForm(word, set)) {
                result.add(word);
            }
            set.add(word);
        }

        return result;
    }

    private boolean canForm(String word, HashSet<String> set) {
        if (word.isEmpty()) {
            return false;
        }

        int n = word.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}
