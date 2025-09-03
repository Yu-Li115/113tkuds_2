// 題目：Remove Duplicates from Sorted Array
// 功能：移除排序陣列中的重複元素，返回唯一元素數量 k

public class It_26_removeduplicates {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int k = 1; // 指向下一個存放唯一元素的位置
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }

    // 測試用 main
    public static void main(String[] args) {
        It_26_removeduplicates solution = new It_26_removeduplicates();

        int[] nums1 = {1,1,2};
        int k1 = solution.removeDuplicates(nums1);
        System.out.println("Unique count = " + k1); // 預期 2
        for (int i = 0; i < k1; i++) {
            System.out.print(nums1[i] + " ");
        }
        System.out.println();

        int[] nums2 = {0,0,1,1,1,2,2,3,3,4};
        int k2 = solution.removeDuplicates(nums2);
        System.out.println("Unique count = " + k2); // 預期 5
        for (int i = 0; i < k2; i++) {
            System.out.print(nums2[i] + " ");
        }
        System.out.println();
    }
}

/*
解題思路：
1. 因為陣列已排序，重複的數字會相鄰。
2. 用變數 k 追蹤唯一元素的「存放位置」。
3. 當 nums[i] 與 nums[i-1] 不同時，表示找到新元素 → 存放到 nums[k]。
4. 最後回傳 k，前 k 個數字就是唯一元素。
*/