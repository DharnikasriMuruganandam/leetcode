class Solution {
    public boolean isGoodArray(int[] nums) {
        int gcdResult = nums[0];
        for (int i = 1; i < nums.length; i++) {
            gcdResult = gcd(gcdResult, nums[i]);
            if (gcdResult == 1) {
                return true;
            }
        }
        return gcdResult == 1;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
