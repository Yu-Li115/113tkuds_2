// 題目：Swap Nodes in Pairs
// 給定一個鏈表，將每兩個相鄰的節點交換。

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

public class It_24_swapnodesinpairs {

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        while (head != null && head.next != null) {
            ListNode first = head;
            ListNode second = head.next;

            // 交換
            prev.next = second;
            first.next = second.next;
            second.next = first;

            // 移動指針
            prev = first;
            head = first.next;
        }

        return dummy.next;
    }

    // 測試
    public static void main(String[] args) {
        It_24_swapnodesinpairs solution = new It_24_swapnodesinpairs();

        // 測試用例 head = [1,2,3,4]
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode result = solution.swapPairs(head);

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        // 預期輸出: 2 1 4 3
    }
}

/*
解題思路：
- dummy 節點簡化交換操作。
- 每次交換 head 和 head.next。
- 使用 prev 指針連接已交換好的部分。
時間複雜度：O(n)，空間複雜度：O(1)。
*/
