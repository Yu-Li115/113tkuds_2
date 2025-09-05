
import java.util.*;

public class LC04_Median_QuakeFeeds {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        double[] nums1 = new double[n];
        double[] nums2 = new double[m];

        for (int i = 0; i < n; i++) {
            nums1[i] = sc.nextDouble();
        }
        for (int j = 0; j < m; j++) {
            nums2[j] = sc.nextDouble();
        }

        double ans = findMedianSortedArrays(nums1, nums2);
        System.out.printf("%.1f\n", ans);

        sc.close();
    }

    // 核心演算法：二分切割
    public static double findMedianSortedArrays(double[] A, double[] B) {
        if (A.length > B.length) {
            return findMedianSortedArrays(B, A); // 確保 A 比較短
        }

        int n = A.length, m = B.length;
        int totalLeft = (n + m + 1) / 2;

        int left = 0, right = n; // A 的切割位置範圍
        while (left <= right) {
            int i = (left + right) / 2;      // A 左邊元素數量
            int j = totalLeft - i;           // B 左邊元素數量

            double Aleft = (i == 0) ? Double.NEGATIVE_INFINITY : A[i - 1];
            double Aright = (i == n) ? Double.POSITIVE_INFINITY : A[i];
            double Bleft = (j == 0) ? Double.NEGATIVE_INFINITY : B[j - 1];
            double Bright = (j == m) ? Double.POSITIVE_INFINITY : B[j];

            if (Aleft <= Bright && Bleft <= Aright) {
                // 找到合法切割
                if ((n + m) % 2 == 1) {
                    return Math.max(Aleft, Bleft);
                } else {
                    return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
                }
            } else if (Aleft > Bright) {
                right = i - 1; // A 太大，往左縮
            } else {
                left = i + 1;  // A 太小，往右擴
            }
        }
        return -1; // 理論上不會到這裡
    }
}

/*
解題思路：
1. 將問題轉換成：切割兩個已排序陣列，使得左半部分和右半部分大小符合中位數定義。
2. 只在短的陣列上做二分，找到合適的切割點。
3. 判斷條件：Aleft <= Bright 且 Bleft <= Aright。
4. 回傳左半最大值 / (左半最大 + 右半最小)/2。

時間複雜度：O(log(min(n, m)))
空間複雜度：O(1)
 */
