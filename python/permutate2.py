from collections import Counter

class Solution:
    def permuteUnique(self, nums: list[int]) -> list[list[int]]:
        res = []
        perm = []
        count = Counter(nums)

        def backtrack():
            if len(perm) == len(nums):
                res.append(list(perm))
                return

            for n in count:
                if count[n] > 0:
                    perm.append(n)
                    count[n] -= 1
                    
                    backtrack()
                    
                    count[n] += 1
                    perm.pop()

        backtrack()
        return res
