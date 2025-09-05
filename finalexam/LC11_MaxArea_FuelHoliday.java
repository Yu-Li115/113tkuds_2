
import java.util.*;

public class LC11_MaxArea_FuelHoliday {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = sc.nextInt();
        }
        System.out.println(maxArea(height));
        sc.close();
    }

    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        long max = 0; // 用 long 避免溢位，但回傳 int (題目範圍安全)
        while (left < right) {
            long h = Math.min(height[left], height[right]);
            long area = h * (right - left);
            max = Math.max(max, area);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return (int) max;
    }
}
