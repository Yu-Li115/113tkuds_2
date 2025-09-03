// 題目：Remove Element
// 功能：移除陣列中所有等於 val 的元素，返回剩餘元素數量 k

public class It_27_removeelement {

    public int removeElement(int[] nums, int val) {
        int k = 0; // 指向下一個存放非 val 元素的位置
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }

    // 測試用 main
    public static void main(String[] args) {
        It_27_removeelement solution = new It_27_removeelement();

        int[] nums1 = {3, 2, 2, 3};
        int k1 = solution.removeElement(nums1, 3);
        System.out.println("Remaining count = " + k1); // 預期 2
        for (int i = 0; i < k1; i++) {
            System.out.print(nums1[i] + " ");
        }
        System.out.println();

        int[] nums2 = {0, 1, 2, 2, 3, 0, 4, 2};
        int k2 = solution.removeElement(nums2, 2);
        System.out.println("Remaining count = " + k2); // 預期 5
        for (int i = 0; i < k2; i++) {
            System.out.print(nums2[i] + " ");
        }
        System.out.println();
    }
}

/*
解題思路：
1. 設一個指標 k 來存放非 val 的元素。
2. 掃描陣列，若 nums[i] != val，就存放到 nums[k]。
3. 最後回傳 k，表示前 k 個元素為處理後的陣列。
*/
