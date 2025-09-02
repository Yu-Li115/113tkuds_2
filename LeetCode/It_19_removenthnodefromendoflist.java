// 題目：Remove Nth Node From End of List
// 給定一個鏈結串列，刪除倒數第 n 個節點，並返回新的頭節點。

class ListNode {

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

public class It_19_removenthnodefromendoflist {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = dummy;
        ListNode second = dummy;

        for (int i = 0; i <= n; i++) {
            first = first.next;
        }

        while (first != null) {
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next;

        return dummy.next;
    }

    // 測試用
    public static void main(String[] args) {
        It_19_removenthnodefromendoflist solution = new It_19_removenthnodefromendoflist();

        // 建立測資 [1,2,3,4,5], n=2
        ListNode head = new ListNode(1,
                new ListNode(2,
                        new ListNode(3,
                                new ListNode(4,
                                        new ListNode(5)))));
        ListNode result = solution.removeNthFromEnd(head, 2);
        printList(result); // 輸出: 1 2 3 5

        // 測資 [1], n=1
        head = new ListNode(1);
        result = solution.removeNthFromEnd(head, 1);
        printList(result); // 輸出: (空)

        // 測資 [1,2], n=1
        head = new ListNode(1, new ListNode(2));
        result = solution.removeNthFromEnd(head, 1);
        printList(result); // 輸出: 1
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}

/*
解題思路：
1. 使用 dummy 節點，避免刪除頭節點時的特判。
2. first 先走 n+1 步，然後 first、second 一起走。
3. 當 first 到尾時，second 停在倒數第 n 個節點的前一個，直接刪除即可。
時間複雜度：O(sz)，空間複雜度：O(1)。
*/
