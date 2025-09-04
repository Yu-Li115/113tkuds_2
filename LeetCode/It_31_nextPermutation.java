// 題目：Next Permutation
// 功能：將輸入陣列 nums 重新排列成字典序中「下一個更大的排列」

import java.util.*;

public class It_31_nextPermutation {

    public void nextPermutation(int[] nums) {
        int n = nums.length;

        // 1. 從右往左找到第一個下降點
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // 2. 若找到，則從右往左找到第一個比 nums[i] 大的數字，交換
        if (i >= 0) {
            int j = n - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        // 3. 將 i+1 ~ n-1 反轉，讓後半部分變成升序
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left++, right--);
        }
    }

    // 測試用 main
    public static void main(String[] args) {
        It_31_nextPermutation solution = new It_31_nextPermutation();

        int[] nums1 = {1, 2, 3};
        solution.nextPermutation(nums1);
        System.out.println(Arrays.toString(nums1)); // 預期 [1, 3, 2]

        int[] nums2 = {3, 2, 1};
        solution.nextPermutation(nums2);
        System.out.println(Arrays.toString(nums2)); // 預期 [1, 2, 3]

        int[] nums3 = {1, 1, 5};
        solution.nextPermutation(nums3);
        System.out.println(Arrays.toString(nums3)); // 預期 [1, 5, 1]
    }
}

/*
解題思路：
1. 找下降點：從右往左，找到第一個 nums[i] < nums[i+1]。
2. 若存在：從右往左找第一個 > nums[i] 的元素 nums[j]，交換 i 和 j。
3. 反轉 i+1 ~ 結尾，讓後半部分最小化。
4. 若沒找到下降點 → 直接反轉整個陣列 (最大排列 → 最小排列)。

時間複雜度：O(n)
空間複雜度：O(1)
*/
