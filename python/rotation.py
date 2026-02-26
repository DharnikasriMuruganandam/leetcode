class Solution:
    def search(self, nums: list[int], target: int) -> int:
        low, high = 0, len(nums) - 1
        
        while low <= high:
            mid = (low + high) // 2
            
            if nums[mid] == target:
                return mid
            
            # Identify which half is sorted
            # Case 1: Left half [low...mid] is sorted
            if nums[low] <= nums[mid]:
                # Check if target is within the sorted left half
                if nums[low] <= target < nums[mid]:
                    high = mid - 1
                else:
                    low = mid + 1
            
            # Case 2: Right half [mid...high] is sorted
            else:
                # Check if target is within the sorted right half
                if nums[mid] < target <= nums[high]:
                    low = mid + 1
                else:
                    high = mid - 1
                    
        return -1
