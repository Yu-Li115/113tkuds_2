
import java.util.*;

public class LC18_4Sum_Procurement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long target = sc.nextLong();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        List<List<Integer>> ans = fourSum(nums, target);
        for (List<Integer> quad : ans) {
            System.out.println(quad.get(0) + " " + quad.get(1) + " " + quad.get(2) + " " + quad.get(3));
        }
        sc.close();
    }

    public static List<List<Integer>> fourSum(int[] nums, long target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 去重 i

                        }for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue; // 去重 j

                                }int left = j + 1, right = n - 1;
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;   // 去重 L

                                                }while (left < right && nums[right] == nums[right + 1]) {
                            right--; // 去重 R

                                            }} else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return res;
    }
}
