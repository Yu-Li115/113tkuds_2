
import java.util.Scanner;

class ListNode {

    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class LC21_MergeTwoLists_Clinics {

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        // 合併剩餘節點
        if (l1 != null) {
            tail.next = l1;
        }
        if (l2 != null) {
            tail.next = l2;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        ListNode l1 = null, l2 = null, tail;

        // 建立 l1
        tail = new ListNode(0);
        l1 = tail;
        for (int i = 0; i < n; i++) {
            tail.next = new ListNode(sc.nextInt());
            tail = tail.next;
        }
        l1 = l1.next; // 去掉 dummy

        // 建立 l2
        tail = new ListNode(0);
        l2 = tail;
        for (int i = 0; i < m; i++) {
            tail.next = new ListNode(sc.nextInt());
            tail = tail.next;
        }
        l2 = l2.next; // 去掉 dummy

        ListNode merged = mergeTwoLists(l1, l2);

        // 輸出
        ListNode curr = merged;
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
