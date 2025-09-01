
import java.util.*;

public class M04_TieredTaxSimple {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long total = 0;

        for (int i = 0; i < n; i++) {
            long income = sc.nextLong();
            long tax = computeTax(income);
            total += tax;
            System.out.println("Tax: " + tax);
        }

        System.out.println("Average: " + (total / n));
    }

    static long computeTax(long income) {
        long tax = 0;
        if (income > 1000000) {
            tax += (income - 1000000) * 30 / 100;
            income = 1000000;
        }
        if (income > 500000) {
            tax += (income - 500000) * 20 / 100;
            income = 500000;
        }
        if (income > 120000) {
            tax += (income - 120000) * 12 / 100;
            income = 120000;
        }
        tax += income * 5 / 100;
        return tax;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：對每一筆收入計算稅額需經過固定 4 個區間判斷，為 O(1)。
 *       總共 n 筆輸入，因此總時間複雜度為 O(n)。
 */
