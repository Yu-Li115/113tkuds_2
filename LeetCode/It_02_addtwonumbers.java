
public class It_02_addtwonumbers {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
        }
        return dummy.next;
    }

    static ListNode createList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int n : arr) {
            curr.next = new ListNode(n);
            curr = curr.next;
        }
        return dummy.next;
    }

    static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) {
                System.out.print(" -> ");
            }
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode l1 = createList(new int[]{2, 4, 3});
        ListNode l2 = createList(new int[]{5, 6, 4});
        printList(addTwoNumbers(l1, l2));

        l1 = createList(new int[]{0});
        l2 = createList(new int[]{0});
        printList(addTwoNumbers(l1, l2));

        l1 = createList(new int[]{9, 9, 9, 9, 9, 9, 9});
        l2 = createList(new int[]{9, 9, 9, 9});
        printList(addTwoNumbers(l1, l2));
    }
}
