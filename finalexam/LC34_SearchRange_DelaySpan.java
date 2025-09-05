
import java.util.Scanner;

public class LC34_SearchRange_DelaySpan {

    public static int[] searchRange(int[] nums, int target) {
        int left = findLeft(nums, target);
        int right = findRight(nums, target);
        return new int[]{left, right};
    }

    private static int findLeft(int[] nums, int target) {
        int l = 0, r = nums.length - 1, res = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) {
                r = mid - 1; 
            }else {
                l = mid + 1;
            }
            if (nums[mid] == target) {
                res = mid;
            }
        }
        return res;
    }

    private static int findRight(int[] nums, int target) {
        int l = 0, r = nums.length - 1, res = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] <= target) {
                l = mid + 1; 
            }else {
                r = mid - 1;
            }
            if (nums[mid] == target) {
                res = mid;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int[] range = searchRange(nums, target);
        System.out.println(range[0] + " " + range[1]);
    }
}
