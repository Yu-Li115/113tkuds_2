// 題目：4Sum
// 找出所有唯一的四元組，使其和等於 target。

import java.util.*;

public class It_18_4sum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return res;
        }

        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 跳過重複的 i
            }
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue; // 跳過重複的 j
                }
                long newTarget = (long) target - nums[i] - nums[j];
                int left = j + 1, right = n - 1;

                while (left < right) {
                    long sum = (long) nums[left] + nums[right];
                    if (sum == newTarget) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        left++;
                        right--;
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;   // 跳過重複 left

                                                }while (left < right && nums[right] == nums[right + 1]) {
                            right--; // 跳過重複 right

                                            }} else if (sum < newTarget) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        It_18_4sum solution = new It_18_4sum();

        int[] nums1 = {1, 0, -1, 0, -2, 2};
        int target1 = 0;
        System.out.println(solution.fourSum(nums1, target1));
        // 輸出 [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

        int[] nums2 = {2, 2, 2, 2, 2};
        int target2 = 8;
        System.out.println(solution.fourSum(nums2, target2));
        // 輸出 [[2,2,2,2]]
    }
}

/*
解題思路：
1. 先排序陣列。
2. 固定前兩個數 i 和 j，再用雙指針(left, right)找另外兩個數。
3. 為避免重複，i、j、left、right 需要跳過相同值。
4. 使用 long 防止整數加法溢位。
時間複雜度：O(n^3)，適合 n <= 200 的限制。
*/
