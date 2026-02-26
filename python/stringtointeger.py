class Solution:
    def myAtoi(self, s: str) -> int:
        s = s.lstrip()
        if not s:
            return 0
        
        sign = 1
        i = 0
        
        if s[i] == '-':
            sign = -1
            i += 1
        elif s[i] == '+':
            i += 1
        
        res = 0
        while i < len(s) and s[i].isdigit():
            res = res * 10 + int(s[i])
            i += 1
        
        res *= sign
        
        MIN_INT, MAX_INT = -2**31, 2**31 - 1
        
        if res < MIN_INT:
            return MIN_INT
        if res > MAX_INT:
            return MAX_INT
            
        return res
