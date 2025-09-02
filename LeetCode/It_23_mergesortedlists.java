// 題目：Merge k Sorted Lists
// 合併 k 條已排序的鏈表，返回一條排序好的鏈表。

import java.util.*;

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

public class It_23_mergesortedlists {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll();
            tail.next = minNode;
            tail = tail.next;
            if (minNode.next != null) {
                pq.offer(minNode.next);
            }
        }

        return dummy.next;
    }

    // 測試用
    public static void main(String[] args) {
        It_23_mergesortedlists solution = new It_23_mergesortedlists();

        // 測試輸入：lists = [[1,4,5],[1,3,4],[2,6]]
        ListNode l1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode l3 = new ListNode(2, new ListNode(6));

        ListNode[] lists = new ListNode[]{l1, l2, l3};

        ListNode result = solution.mergeKLists(lists);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        // 預期輸出: 1 1 2 3 4 4 5 6
    }
}

/*
解題思路：
1. 用 PriorityQueue 維護每個鏈表當前最小的節點。
2. 每次取出最小節點接到結果後，如果該節點有 next，放回堆中。
3. 直到所有節點都處理完。
時間複雜度：O(N log k)。
*/
