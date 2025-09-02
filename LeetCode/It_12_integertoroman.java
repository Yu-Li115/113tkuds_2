// 題目：Integer to Roman
// 給定一個整數 num (1 <= num <= 3999)，請將其轉換為羅馬數字。

public class It_12_integertoroman {

    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        It_12_integertoroman solution = new It_12_integertoroman();
        System.out.println(solution.intToRoman(3749)); // MMMDCCXLIX
        System.out.println(solution.intToRoman(58));   // LVIII
        System.out.println(solution.intToRoman(1994)); // MCMXCIV
    }
}

/*
解題思路：
1. 羅馬數字由大到小拼接，遇到 4、9 特殊情況要用減法表示。
2. 建立 values 與 symbols 對照表。
3. 從大到小遍歷，扣掉並加入符號直到 num = 0。
4. 時間複雜度 O(1)，因為數值範圍固定。
 */
