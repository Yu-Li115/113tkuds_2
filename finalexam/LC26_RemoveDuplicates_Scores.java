
import java.util.Scanner;

public class LC26_RemoveDuplicates_Scores {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 0) {
            System.out.println(0);
            return;
        }

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int write = 1; // 可寫位置
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[write - 1]) {
                nums[write] = nums[i];
                write++;
            }
        }

        // 輸出
        System.out.println(write);
        for (int i = 0; i < write; i++) {
            System.out.print(nums[i]);
            if (i != write - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
