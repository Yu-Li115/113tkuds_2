
import java.util.*;

public class M12_MergeKTimeTables {

    static class Entry implements Comparable<Entry> {

        int time, listIndex, idx;

        Entry(int t, int l, int i) {
            time = t;
            listIndex = l;
            idx = i;
        }

        public int compareTo(Entry o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        List<int[]> lists = new ArrayList<>();

        for (int k = 0; k < K; k++) {
            int len = sc.nextInt();
            int[] arr = new int[len];
            for (int i = 0; i < len; i++) {
                arr[i] = sc.nextInt();
            }
            lists.add(arr);
        }

        PriorityQueue<Entry> pq = new PriorityQueue<>();
        for (int i = 0; i < K; i++) {
            int[] arr = lists.get(i);
            if (arr.length > 0) {
                pq.add(new Entry(arr[0], i, 0));
            }
        }

        List<Integer> merged = new ArrayList<>();
        while (!pq.isEmpty()) {
            Entry e = pq.poll();
            merged.add(e.time);
            int[] arr = lists.get(e.listIndex);
            int nextIdx = e.idx + 1;
            if (nextIdx < arr.length) {
                pq.add(new Entry(arr[nextIdx], e.listIndex, nextIdx));
            }
        }

        for (int i = 0; i < merged.size(); i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(merged.get(i));
        }
        System.out.println();
    }
}
