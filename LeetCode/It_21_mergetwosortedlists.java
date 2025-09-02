// 題目：Merge Two Sorted Lists
// 合併兩個已排序的鏈結串列。

public class It_21_mergetwosortedlists {

    static class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        if (list1 != null) {
            current.next = list1;
        }
        if (list2 != null) {
            current.next = list2;
        }

        return dummy.next;
    }

    // 測試用
    public static void main(String[] args) {
        It_21_mergetwosortedlists solution = new It_21_mergetwosortedlists();

        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));

        ListNode merged = solution.mergeTwoLists(list1, list2);

        // 輸出合併結果
        while (merged != null) {
            System.out.print(merged.val + " ");
            merged = merged.next;
        }
        // 預期輸出：1 1 2 3 4 4
    }
}

/*
解題思路：
1. 建立 dummy 節點，避免處理頭節點時的特例。
2. 使用指標逐步比較 list1 和 list2 的節點值。
3. 小的節點接到結果串列，並移動該串列指標。
4. 最後將剩餘的節點直接接到尾端。
*/
