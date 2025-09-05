
import java.util.Scanner;

class ListNode {

    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class LC19_RemoveNth_Node_Clinic {

    // 刪除倒數第 k 節點
    public static ListNode removeNthFromEnd(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        // fast 先走 k 步
        for (int i = 0; i < k; i++) {
            if (fast.next != null) {
                fast = fast.next;
            }
        }

        // fast 和 slow 同步移動，直到 fast 到尾
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // slow.next 就是要刪除的節點
        slow.next = slow.next.next;

        return dummy.next;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        if (n == 0) {
            return; // 空鏈結，直接結束
        }
        // 建立鏈結串
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int i = 0; i < n; i++) {
            tail.next = new ListNode(sc.nextInt());
            tail = tail.next;
        }

        int k = sc.nextInt();

        ListNode newHead = removeNthFromEnd(dummy.next, k);

        // 輸出刪除後序列
        ListNode curr = newHead;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) {
                System.out.print(" ");
            }
            curr = curr.next;
        }
        System.out.println();
    }
}
