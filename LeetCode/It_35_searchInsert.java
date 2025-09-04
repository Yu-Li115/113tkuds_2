// 題目：Search Insert Position
// 功能：回傳 target 在排序陣列中的索引；若不存在則回傳應插入的位置

public class It_35_searchInsert {

    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid; // 找到
            } else if (nums[mid] < target) {
                left = mid + 1; // 往右
            } else {
                right = mid - 1; // 往左
            }
        }

        // 沒找到 → left 就是插入位置
        return left;
    }

    // 測試用 main
    public static void main(String[] args) {
        It_35_searchInsert solution = new It_35_searchInsert();

        int[] nums1 = {1, 3, 5, 6};
        System.out.println(solution.searchInsert(nums1, 5)); // 預期 2

        int[] nums2 = {1, 3, 5, 6};
        System.out.println(solution.searchInsert(nums2, 2)); // 預期 1

        int[] nums3 = {1, 3, 5, 6};
        System.out.println(solution.searchInsert(nums3, 7)); // 預期 4

        int[] nums4 = {1, 3, 5, 6};
        System.out.println(solution.searchInsert(nums4, 0)); // 預期 0
    }
}

/*
解題思路：
1. 用二分搜尋尋找 target。
2. 找到就直接回傳 index。
3. 沒找到時，left 會停在應插入的位置，直接回傳 left。
4. 時間複雜度：O(log n)，空間複雜度：O(1)。
*/
