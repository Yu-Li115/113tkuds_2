
import java.util.*;

public class LC20_ValidParentheses_AlertFormat {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();
        System.out.println(isValid(s));
        sc.close();
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) { // c 是閉括號
                if (stack.isEmpty() || stack.pop() != map.get(c)) {
                    return false;
                }
            } else { // 開括號
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
