class Solution:
    def divide(self, dividend: int, divisor: int) -> int:
        MAX = 2147483647
        MIN = -2147483648

        if dividend == MIN and divisor == -1:
            return MAX

        negative = (dividend < 0) != (divisor < 0)
        a, b = abs(dividend), abs(divisor)
        res = 0

        while a >= b:
            temp, multiple = b, 1
            while a >= (temp << 1):
                temp <<= 1
                multiple <<= 1
            a -= temp
            res += multiple

        return -res if negative else res
