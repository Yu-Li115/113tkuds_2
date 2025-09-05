
import java.util.Scanner;

class ListNode {

    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class LC25_ReverseKGroup_Shifts {

    // 反轉從 start 到 end (不含 end)
    private static ListNode reverse(ListNode start, ListNode end) {
        ListNode prev = null;
        ListNode curr = start;

        while (curr != end) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }

        return prev;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupEnd = dummy;

        while (true) {
            ListNode kth = prevGroupEnd;
            for (int i = 0; i < k; i++) {
                kth = kth.next;
                if (kth == null) {
                    return dummy.next; // 不足 k，保留原樣

                            }}

            ListNode groupStart = prevGroupEnd.next;
            ListNode nextGroup = kth.next;

            // 反轉該區段
            ListNode newStart = reverse(groupStart, kth.next);

            prevGroupEnd.next = newStart;
            groupStart.next = nextGroup;

            prevGroupEnd = groupStart;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (sc.hasNextInt()) {
            tail.next = new ListNode(sc.nextInt());
            tail = tail.next;
        }

        ListNode reversed = reverseKGroup(dummy.next, k);

        ListNode curr = reversed;
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
