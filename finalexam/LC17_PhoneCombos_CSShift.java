
import java.util.*;

public class LC17_PhoneCombos_CSShift {

    private static final String[] MAP = {
        "abc", // 2
        "def", // 3
        "ghi", // 4
        "jkl", // 5
        "mno", // 6
        "pqrs", // 7
        "tuv", // 8
        "wxyz" // 9
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = sc.hasNextLine() ? sc.nextLine().trim() : "";
        List<String> combos = letterCombinations(digits);
        for (String s : combos) {
            System.out.println(s);
        }
        sc.close();
    }

    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }

        StringBuilder path = new StringBuilder();
        dfs(digits, 0, path, res);
        return res;
    }

    private static void dfs(String digits, int idx, StringBuilder path, List<String> res) {
        if (idx == digits.length()) {
            res.add(path.toString());
            return;
        }
        char d = digits.charAt(idx);
        // 題目保證僅含 '2'~'9'
        String letters = MAP[d - '2'];
        for (int i = 0; i < letters.length(); i++) {
            path.append(letters.charAt(i));
            dfs(digits, idx + 1, path, res);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
