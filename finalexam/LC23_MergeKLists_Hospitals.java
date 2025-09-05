
import java.util.*;

class ListNode {

    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class LC23_MergeKLists_Hospitals {

    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode node : lists) {
            if (node != null) {
                pq.add(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode curr = pq.poll();
            tail.next = curr;
            tail = tail.next;
            if (curr.next != null) {
                pq.add(curr.next);
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        sc.nextLine();

        ListNode[] lists = new ListNode[k];

        for (int i = 0; i < k; i++) {
            ListNode dummy = new ListNode(0);
            ListNode tail = dummy;

            while (true) {
                int val = sc.nextInt();
                if (val == -1) {
                    break;
                }
                tail.next = new ListNode(val);
                tail = tail.next;
            }
            lists[i] = dummy.next;
        }

        ListNode merged = mergeKLists(lists);

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
