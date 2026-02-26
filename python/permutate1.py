class Solution:
    def permute(self, nums: list[int]) -> list[list[int]]:
        res = []
        
        def backtrack(current_perm):
            if len(current_perm) == len(nums):
                res.append(list(current_perm))
                return
            
            for n in nums:
                if n not in current_perm:
                    current_perm.append(n)
                    backtrack(current_perm)
                    current_perm.pop()
        
        backtrack([])
        return res
