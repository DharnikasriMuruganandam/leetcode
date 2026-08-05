class Solution {
    public int largestPalindrome(int n) {
        if (n == 1) {
            return 9;
        }
        
        long maxNum = (long) Math.pow(10, n) - 1;
        long minNum = (long) Math.pow(10, n - 1);
        
        for (long left = maxNum; left >= minNum; left--) {
            long palindrome = left;
            long temp = left;
            while (temp > 0) {
                palindrome = palindrome * 10 + (temp % 10);
                temp /= 10;
            }
            
            for (long i = maxNum; i * i >= palindrome; i--) {
                if (palindrome % i == 0 && palindrome / i >= minNum) {
                    return (int) (palindrome % 1337);
                }
            }
        }
        
        return 0;
    }
}
