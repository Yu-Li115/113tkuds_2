// 題目：Find First and Last Position of Element in Sorted Array
// 功能：找出 target 在排序陣列中的起始與結束位置 (若不存在則回傳 [-1, -1])

import java.util.*;

public class It_34_searchRange {

    public int[] searchRange(int[] nums, int target) {
        int left = findBound(nums, target, true);   // 找最左邊的 target
        int right = findBound(nums, target, false); // 找最右邊的 target
        return new int[]{left, right};
    }

    private int findBound(int[] nums, int target, boolean isFirst) {
        int left = 0, right = nums.length - 1, result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                result = mid;
                if (isFirst) {
                    right = mid - 1; // 繼續往左找
                } else {
                    left = mid + 1;  // 繼續往右找
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    // 測試用 main
    public static void main(String[] args) {
        It_34_searchRange solution = new It_34_searchRange();

        int[] nums1 = {5, 7, 7, 8, 8, 10};
        System.out.println(Arrays.toString(solution.searchRange(nums1, 8))); // 預期 [3, 4]

        int[] nums2 = {5, 7, 7, 8, 8, 10};
        System.out.println(Arrays.toString(solution.searchRange(nums2, 6))); // 預期 [-1, -1]

        int[] nums3 = {};
        System.out.println(Arrays.toString(solution.searchRange(nums3, 0))); // 預期 [-1, -1]
    }
}

/*
解題思路：
1. 使用二分搜尋找到 target 的左邊界 (第一次出現)。
2. 使用二分搜尋找到 target 的右邊界 (最後一次出現)。
3. 若找不到 target，直接回傳 [-1, -1]。
4. 時間複雜度 O(log n)，空間複雜度 O(1)。
*/
