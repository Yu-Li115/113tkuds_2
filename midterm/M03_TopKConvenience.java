
import java.util.*;

public class M03_TopKConvenience {

    static class Item {

        String name;
        int qty;
        int idx;

        Item(String name, int qty, int idx) {
            this.name = name;
            this.qty = qty;
            this.idx = idx;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int K = sc.nextInt();
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int qty = sc.nextInt();
            items.add(new Item(name, qty, i));
        }

        PriorityQueue<Item> heap = new PriorityQueue<>(K, (a, b) -> {
            if (a.qty != b.qty) {
                return a.qty - b.qty;
            }
            return a.idx - b.idx;
        });

        for (Item item : items) {
            if (heap.size() < K) {
                heap.offer(item);
            } else if (item.qty > heap.peek().qty
                    || (item.qty == heap.peek().qty && item.idx < heap.peek().idx)) {
                heap.poll();
                heap.offer(item);
            }
        }

        List<Item> result = new ArrayList<>(heap);

        result.sort((a, b) -> {
            if (b.qty != a.qty) {
                return b.qty - a.qty;
            }
            return a.idx - b.idx;
        });

        for (Item it : result) {
            System.out.println(it.name + " " + it.qty);
        }
    }
}
/*
 * Time Complexity: O(n log K)
 * 說明：每筆商品最多進出堆一次，操作為 O(log K)，共 n 筆 ⇒ O(n log K)。
 *       輸出前再排序 K 筆資料 O(K log K)，因 K ≪ n，整體仍為 O(n log K)。
 */
