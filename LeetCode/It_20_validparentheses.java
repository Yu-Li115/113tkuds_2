// 題目：Valid Parentheses
// 判斷字串中的括號是否有效。

import java.util.*;

public class It_20_validparentheses {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();
                if ((c == ')' && top != '(')
                        || (c == ']' && top != '[')
                        || (c == '}' && top != '{')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    // 測試用
    public static void main(String[] args) {
        It_20_validparentheses solution = new It_20_validparentheses();

        System.out.println(solution.isValid("()"));      // true
        System.out.println(solution.isValid("()[]{}"));  // true
        System.out.println(solution.isValid("(]"));      // false
        System.out.println(solution.isValid("([])"));    // true
        System.out.println(solution.isValid("([)]"));    // false
    }
}

/*
解題思路：
1. 使用 stack 模擬。
2. 遇到左括號 push，遇到右括號檢查是否與 stack.top() 配對。
3. 若 stack 為空或不匹配 -> false。
4. 結束後 stack 必須為空才算有效。
*/
