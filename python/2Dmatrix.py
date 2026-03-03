class Solution:
    def searchMatrix(self, matrix: list[list[int]], target: int) -> bool:
        if not matrix or not matrix[0]:
            return False
        
        rows, cols = len(matrix), len(matrix[0])
        left, right = 0, (rows * cols) - 1
        
        while left <= right:
            mid = (left + right) // 2
            # Map 1D index back to 2D coordinates
            mid_val = matrix[mid // cols][mid % cols]
            
            if mid_val == target:
                return True
            elif mid_val < target:
                left = mid + 1
            else:
                right = mid - 1
                
        return False
