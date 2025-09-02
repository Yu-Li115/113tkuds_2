// 題目：Reverse Nodes in k-Group

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

public class It_25_reversekgroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prevGroup = dummy;

        while (true) {
            ListNode kth = getKth(prevGroup, k);
            if (kth == null) {
                break;
            }

            ListNode groupNext = kth.next;
            ListNode prev = kth.next;
            ListNode curr = prevGroup.next;

            while (curr != groupNext) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }

            ListNode tmp = prevGroup.next;
            prevGroup.next = kth;
            prevGroup = tmp;
        }

        return dummy.next;
    }

    private ListNode getKth(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }
        return curr;
    }

    // 測試
    public static void main(String[] args) {
        It_25_reversekgroup solution = new It_25_reversekgroup();

        // 測試用例 head = [1,2,3,4,5], k = 2
        ListNode head = new ListNode(1,
                new ListNode(2,
                        new ListNode(3,
                                new ListNode(4,
                                        new ListNode(5)))));
        ListNode result = solution.reverseKGroup(head, 2);

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        // 預期輸出: 2 1 4 3 5
    }
}

/*
測試案例：
Input: [1,2,3,4,5], k = 2  → Output: [2,1,4,3,5]
Input: [1,2,3,4,5], k = 3  → Output: [3,2,1,4,5]
Input: [1,2,3,4,5], k = 1  → Output: [1,2,3,4,5]
Input: [1,2,3,4,5], k = 5  → Output: [5,4,3,2,1]
*/
