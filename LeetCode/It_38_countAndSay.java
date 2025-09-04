// 題目：Count and Say
// 功能：輸出 count-and-say 數列的第 n 項

public class It_38_countAndSay {

    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String prev = countAndSay(n - 1);
        StringBuilder sb = new StringBuilder();

        int count = 1;
        for (int i = 1; i <= prev.length(); i++) {
            if (i < prev.length() && prev.charAt(i) == prev.charAt(i - 1)) {
                count++;
            } else {
                sb.append(count).append(prev.charAt(i - 1));
                count = 1;
            }
        }
        return sb.toString();
    }

    // 測試用 main
    public static void main(String[] args) {
        It_38_countAndSay solution = new It_38_countAndSay();

        System.out.println(solution.countAndSay(1)); // 預期 "1"
        System.out.println(solution.countAndSay(2)); // 預期 "11"
        System.out.println(solution.countAndSay(3)); // 預期 "21"
        System.out.println(solution.countAndSay(4)); // 預期 "1211"
        System.out.println(solution.countAndSay(5)); // 預期 "111221"
    }
}

/*
解題思路：
1. 基本案例：countAndSay(1) = "1"。
2. 遞迴：countAndSay(n) = 對 countAndSay(n-1) 進行 RLE 壓縮。
3. 壓縮方式：統計連續相同字元的個數 → "次數 + 字元"。
   例如 "21" → "1211" (一個 '2'，一個 '1')。

時間複雜度：O(m * n)，其中 m 為字串長度 (最大到約 2^n)。
空間複雜度：O(m)。
*/
