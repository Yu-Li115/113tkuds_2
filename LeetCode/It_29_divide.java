// 題目：Divide Two Integers
// 功能：實作不使用 *, /, % 的整數除法，並處理溢位

public class It_29_divide {

    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE; // 避免溢位
        }

        boolean negative = (dividend < 0) ^ (divisor < 0);
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);
        int result = 0;

        while (a >= b) {
            long temp = b, multiple = 1;
            while (a >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            a -= temp;
            result += multiple;
        }

        return negative ? -result : result;
    }

    // 測試用 main
    public static void main(String[] args) {
        It_29_divide solution = new It_29_divide();

        System.out.println(solution.divide(10, 3));   // 預期 3
        System.out.println(solution.divide(7, -3));   // 預期 -2
        System.out.println(solution.divide(-2147483648, -1)); // 預期 2147483647 (避免溢位)
    }
}

/*
解題思路：
1. 特殊情況：最小整數 / -1 → 超出範圍，回傳 Integer.MAX_VALUE。
2. 轉 long → 避免 Math.abs(Integer.MIN_VALUE) 溢位。
3. 透過位移 (<<) 模擬快速減法，每次倍增 divisor。
4. 判斷正負號，最後輸出結果。
*/
