// 題目：Longest Valid Parentheses
// 功能：計算輸入字串中最長的合法括號子字串長度

import java.util.*;

public class It_32_longestValidParentheses {

    public int longestValidParentheses(String s) {
        int maxLen = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // 初始邊界

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i); // 更新邊界
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        It_32_longestValidParentheses solution = new It_32_longestValidParentheses();

        String s1 = "(()";
        System.out.println(solution.longestValidParentheses(s1)); // 預期 2

        String s2 = ")()())";
        System.out.println(solution.longestValidParentheses(s2)); // 預期 4

        String s3 = "";
        System.out.println(solution.longestValidParentheses(s3)); // 預期 0

        String s4 = "()(())";
        System.out.println(solution.longestValidParentheses(s4)); // 預期 6
    }
}

/*
解題思路：
1. 使用 stack 紀錄括號位置，初始先放入 -1 作為基準邊界。
2. 遍歷字串：
   - 若遇到 '(' → push 當前 index。
   - 若遇到 ')' → pop 一個元素。
       a. 如果 stack 空了，push 當前 index 作為新的邊界。
       b. 否則更新最長長度 = i - stack.peek()。
3. 回傳最大長度。

時間複雜度：O(n)，n 為字串長度
空間複雜度：O(n)，最差情況全部都是 '('
*/
