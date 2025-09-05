
import java.util.Scanner;

class ListNode {

    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class LC24_SwapPairs_Shifts {

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;
            ListNode b = a.next;

            // 交換 a,b
            prev.next = b;
            a.next = b.next;
            b.next = a;

            // prev 移到下一對前
            prev = a;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (sc.hasNextInt()) {
            tail.next = new ListNode(sc.nextInt());
            tail = tail.next;
        }

        ListNode swapped = swapPairs(dummy.next);

        ListNode curr = swapped;
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
