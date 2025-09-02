// 題目：Longest Common Prefix
// 給定字串陣列，回傳它們的最長共同前綴字串。
// 若沒有共同前綴，回傳 ""。

public class It_14_longestcommonprefix {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        It_14_longestcommonprefix solution = new It_14_longestcommonprefix();

        String[] strs1 = {"flower", "flow", "flight"};
        System.out.println(solution.longestCommonPrefix(strs1)); // fl

        String[] strs2 = {"dog", "racecar", "car"};
        System.out.println(solution.longestCommonPrefix(strs2)); // ""
    }
}

/*
解題思路：
1. 以第一個字串作為初始共同前綴。
2. 依序檢查其他字串是否以該前綴開頭：
   - 若不是，縮短 prefix 一個字元。
   - 重複直到符合或 prefix 變空。
3. 全部檢查完後，prefix 即為最長共同前綴。
4. 時間複雜度 O(n * m)，其中 n 是字串數量，m 是字串長度。
*/
