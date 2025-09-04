// 題目：Search in Rotated Sorted Array
// 功能：在旋轉過的遞增陣列中搜尋 target，若存在回傳索引，否則回傳 -1

public class It_33_searchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // 左半邊有序
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // target 在左半邊
                } else {
                    left = mid + 1;  // target 在右半邊
                }
            } // 右半邊有序
            else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;  // target 在右半邊
                } else {
                    right = mid - 1; // target 在左半邊
                }
            }
        }

        return -1; // 沒找到
    }

    // 測試用 main
    public static void main(String[] args) {
        It_33_searchInRotatedSortedArray solution = new It_33_searchInRotatedSortedArray();

        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(solution.search(nums1, 0)); // 預期 4

        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(solution.search(nums2, 3)); // 預期 -1

        int[] nums3 = {1};
        System.out.println(solution.search(nums3, 0)); // 預期 -1

        int[] nums4 = {6, 7, 1, 2, 3, 4, 5};
        System.out.println(solution.search(nums4, 3)); // 預期 4
    }
}

/*
解題思路：
1. 使用二分搜尋 (Binary Search)，每次檢查中間元素。
2. 判斷哪一半是有序的：
   - 若 nums[left] <= nums[mid]，左半部分有序。
   - 否則右半部分有序。
3. 檢查 target 是否落在有序區間內，縮小搜尋範圍。
4. 重複直到找到或區間縮小為空。

時間複雜度：O(log n)
空間複雜度：O(1)
*/
