
public class RecursiveMathCalculator {

    public static int combination(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    public static int catalan(int n) {
        if (n <= 1) {
            return 1;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += catalan(i) * catalan(n - 1 - i);
        }
        return res;
    }

    public static int hanoiMoves(int n) {
        if (n == 1) {
            return 1;
        }
        return 2 * hanoiMoves(n - 1) + 1;
    }

    public static boolean isPalindrome(int n) {
        return n == reverse(n, 0);
    }

    private static int reverse(int n, int res) {
        if (n == 0) {
            return res;
        }
        return reverse(n / 10, res * 10 + n % 10);
    }

    public static void main(String[] args) {
        System.out.println(combination(5, 2));
        System.out.println(catalan(4));
        System.out.println(hanoiMoves(3));
        System.out.println(isPalindrome(12321));
    }
}
