from collections import Counter

class Solution:
    def findSubstring(self, s: str, words: list[str]) -> list[int]:
        if not s or not words:
            return []
        
        word_len = len(words[0])
        word_count = len(words)
        total_len = word_len * word_count
        word_freq = Counter(words)
        result = []
        
        for i in range(word_len):
            left = i
            right = i
            current_freq = Counter()
            count = 0
            
            while right + word_len <= len(s):
                word = s[right : right + word_len]
                right += word_len
                
                if word in word_freq:
                    current_freq[word] += 1
                    count += 1
                    
                    while current_freq[word] > word_freq[word]:
                        left_word = s[left : left + word_len]
                        current_freq[left_word] -= 1
                        count -= 1
                        left += word_len
                    
                    if count == word_count:
                        result.append(left)
                else:
                    current_freq.clear()
                    count = 0
                    left = right
                    
        return result
