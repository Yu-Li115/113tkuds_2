// 題目：3Sum Closest
// 給定整數陣列 nums 和整數 target，找到三個整數，使得三數之和最接近 target。
// 回傳這個三數和。

import java.util.*;

public class It_16_threesumclosest {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int closest = nums[0] + nums[1] + nums[2]; // 初始化為前三個的和

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
                }

                if (sum == target) {
                    return sum; // 完全相等，直接回傳
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return closest;
    }

    public static void main(String[] args) {
        It_16_threesumclosest solution = new It_16_threesumclosest();

        int[] nums1 = {-1, 2, 1, -4};
        int target1 = 1;
        System.out.println(solution.threeSumClosest(nums1, target1)); // 輸出 2

        int[] nums2 = {0, 0, 0};
        int target2 = 1;
        System.out.println(solution.threeSumClosest(nums2, target2)); // 輸出 0
    }
}

/*
解題思路：
1. 排序後，外層固定一個數 nums[i]。
2. 內層用雙指針掃描，計算 nums[i] + nums[left] + nums[right]。
3. 若差距更小則更新 closest。
4. 若 sum == target，直接回傳。
5. 否則調整指針，直到找到最接近的三數和。
*/
