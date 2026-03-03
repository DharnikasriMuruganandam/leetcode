class Solution:
    def totalNQueens(self, n: int) -> int:
        cols = set()
        posDiag = set() # (r + c)
        negDiag = set() # (r - c)
        
        res = 0
        def backtrack(r):
            if r == n:
                nonlocal res
                res += 1
                return
            
            for c in range(n):
                if c in cols or (r + c) in posDiag or (r - c) in negDiag:
                    continue
                
                cols.add(c)
                posDiag.add(r + c)
                negDiag.add(r - c)
                
                backtrack(r + 1)
                
                cols.remove(c)
                posDiag.remove(r + c)
                negDiag.remove(r - c)
        
        backtrack(0)
        return res
