// 題目：3Sum
// 給定整數陣列 nums，找出所有不重複的三元組 [nums[i], nums[j], nums[k]] 使得三數和為 0。

import java.util.*;

public class It_15_threesum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 避免重複
            }
            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        It_15_threesum solution = new It_15_threesum();

        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println(solution.threeSum(nums1)); // [[-1, -1, 2], [-1, 0, 1]]

        int[] nums2 = {0, 1, 1};
        System.out.println(solution.threeSum(nums2)); // []

        int[] nums3 = {0, 0, 0};
        System.out.println(solution.threeSum(nums3)); // [[0, 0, 0]]
    }
}

/*
解題思路：
1. 先排序陣列，讓相同數字聚在一起，方便去重。
2. 外層迴圈固定一個數 nums[i]。
3. 內層用雙指針 (left, right) 掃描剩餘數字：
   - 若三數和 = 0，存入結果並跳過重複數字。
   - 若和 < 0，左指針右移。
   - 若和 > 0，右指針左移。
4. 保證所有三元組唯一且和為 0。
*/
