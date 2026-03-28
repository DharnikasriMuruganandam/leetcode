import java.util.*;

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return res;

        Map<String, List<String>> adj = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        
        bfs(beginWord, endWord, dict, adj, distance);
        
        if (distance.containsKey(endWord)) {
            dfs(beginWord, endWord, adj, distance, new ArrayList<>(Arrays.asList(beginWord)), res);
        }
        
        return res;
    }

    private void bfs(String begin, String end, Set<String> dict, Map<String, List<String>> adj, Map<String, Integer> distance) {
        Queue<String> q = new LinkedList<>();
        q.offer(begin);
        distance.put(begin, 0);
        
        for (String s : dict) adj.put(s, new ArrayList<>());
        adj.put(begin, new ArrayList<>());

        while (!q.isEmpty()) {
            String curr = q.poll();
            int currDist = distance.get(curr);
            
            char[] chars = curr.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char old = chars[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    chars[i] = c;
                    String next = new String(chars);
                    if (dict.contains(next) || next.equals(begin)) {
                        if (!distance.containsKey(next)) {
                            distance.put(next, currDist + 1);
                            adj.get(curr).add(next);
                            q.offer(next);
                        } else if (distance.get(next) == currDist + 1) {
                            adj.get(curr).add(next);
                        }
                    }
                }
                chars[i] = old;
            }
        }
    }

    private void dfs(String curr, String end, Map<String, List<String>> adj, Map<String, Integer> distance, List<String> path, List<List<String>> res) {
        if (curr.equals(end)) {
            res.add(new ArrayList<>(path));
            return;
        }
        
        if (!adj.containsKey(curr)) return;
        
        for (String next : adj.get(curr)) {
            if (distance.get(next) == distance.get(curr) + 1) {
                path.add(next);
                dfs(next, end, adj, distance, path, res);
                path.remove(path.size() - 1);
            }
        }
    }
}
