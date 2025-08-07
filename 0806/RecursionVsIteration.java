
public class RecursionVsIteration {

    public static int combR(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        return combR(n - 1, k - 1) + combR(n - 1, k);
    }

    public static int combI(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                if (j == 0 || j == i) {
                    dp[i][j] = 1; 
                }else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
            }
        }
        return dp[n][k];
    }

    public static int prodR(int[] arr, int i) {
        if (i == arr.length) {
            return 1;
        }
        return arr[i] * prodR(arr, i + 1);
    }

    public static int prodI(int[] arr) {
        int res = 1;
        for (int n : arr) {
            res *= n;
        }
        return res;
    }

    public static int vowelR(String s, int i) {
        if (i == s.length()) {
            return 0;
        }
        return (isVowel(s.charAt(i)) ? 1 : 0) + vowelR(s, i + 1);
    }

    public static int vowelI(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (isVowel(c)) {
                count++;
            }
        }
        return count;
    }

    public static boolean isVowel(char c) {
        return "aeiouAEIOU".indexOf(c) >= 0;
    }

    public static boolean validR(String s, int i, int open) {
        if (i == s.length()) {
            return open == 0;
        }
        if (s.charAt(i) == '(') {
            return validR(s, i + 1, open + 1);
        }
        if (s.charAt(i) == ')') {
            return open > 0 && validR(s, i + 1, open - 1);
        }
        return validR(s, i + 1, open);
    }

    public static boolean validI(String s) {
        int open = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++; 
            }else if (c == ')') {
                if (open == 0) {
                    return false;
                }
                open--;
            }
        }
        return open == 0;
    }

    public static void main(String[] args) {
        System.out.println(combR(5, 2));
        System.out.println(combI(5, 2));
        System.out.println(prodR(new int[]{2, 3, 4}, 0));
        System.out.println(prodI(new int[]{2, 3, 4}));
        System.out.println(vowelR("hello", 0));
        System.out.println(vowelI("hello"));
        System.out.println(validR("(())()", 0, 0));
        System.out.println(validI("(())()"));
    }
}
