// 題目：Generate Parentheses
// 輸入 n，生成所有合法的括號組合。

import java.util.*;

public class It_22_generateparentheses {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, String current, int open, int close, int max) {
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }

        if (open < max) {
            backtrack(result, current + "(", open + 1, close, max);
        }
        if (close < open) {
            backtrack(result, current + ")", open, close + 1, max);
        }
    }

    // 測試用
    public static void main(String[] args) {
        It_22_generateparentheses solution = new It_22_generateparentheses();

        System.out.println(solution.generateParenthesis(3));
        // 預期輸出: ["((()))","(()())","(())()","()(())","()()()"]

        System.out.println(solution.generateParenthesis(1));
        // 預期輸出: ["()"]
    }
}

/*
解題思路：
1. 回溯法，維護 open (已使用左括號數量)、close (已使用右括號數量)。
2. 選擇：
   - 如果 open < n，加 '('。
   - 如果 close < open，加 ')'。
3. 當字串長度 == 2n，加入答案。
*/
