// 題目：Letter Combinations of a Phone Number
// 給定一個只包含 2-9 的字串，輸出所有可能的字母組合。

import java.util.*;

public class It_17_lettercombinations {

    private static final String[] KEYS = {
        "", // 0
        "", // 1
        "abc", // 2
        "def", // 3
        "ghi", // 4
        "jkl", // 5
        "mno", // 6
        "pqrs",// 7
        "tuv", // 8
        "wxyz" // 9
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder path, String digits, int index) {
        if (index == digits.length()) {
            result.add(path.toString());
            return;
        }
        String letters = KEYS[digits.charAt(index) - '0'];
        for (char c : letters.toCharArray()) {
            path.append(c);                  // 選擇
            backtrack(result, path, digits, index + 1); // 遞歸
            path.deleteCharAt(path.length() - 1);       // 回溯
        }
    }

    public static void main(String[] args) {
        It_17_lettercombinations solution = new It_17_lettercombinations();

        String digits1 = "23";
        System.out.println(solution.letterCombinations(digits1));
        // 輸出 ["ad","ae","af","bd","be","bf","cd","ce","cf"]

        String digits2 = "";
        System.out.println(solution.letterCombinations(digits2));
        // 輸出 []

        String digits3 = "2";
        System.out.println(solution.letterCombinations(digits3));
        // 輸出 ["a","b","c"]
    }
}

/*
解題思路：
1. 建立數字到字母的映射 (KEYS)。
2. 用回溯法遍歷 digits 的每一位，依次拼接字母。
3. 當 index == digits.length()，把當前字串放入結果。
4. 時間複雜度：最多 4^n，n 為 digits 的長度 (最大 4)，所以非常快。
*/
