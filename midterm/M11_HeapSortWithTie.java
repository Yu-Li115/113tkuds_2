
import java.util.*;

public class M11_HeapSortWithTie {

    static class Pair implements Comparable<Pair> {

        int score, index;

        Pair(int s, int i) {
            score = s;
            index = i;
        }

        public int compareTo(Pair o) {
            if (this.score != o.score) {
                return o.score - this.score; // Max-Heap

                        }return this.index - o.index; // 平手用 index
        }
    }

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Pair[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(sc.nextInt(), i);
        }

        heapSort(arr);

        for (int i = 0; i < n; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(arr[i].score);
        }
        System.out.println();
    }

    static void heapSort(Pair[] arr) {
        int n = arr.length;
        // Build Heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        // Sort
        for (int i = n - 1; i > 0; i--) {
            Pair tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            heapify(arr, i, 0);
        }
    }

    static void heapify(Pair[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1, r = 2 * i + 2;
        if (l < n && arr[l].compareTo(arr[largest]) < 0) {
            largest = l;
        }
        if (r < n && arr[r].compareTo(arr[largest]) < 0) {
            largest = r;
        }
        if (largest != i) {
            Pair tmp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = tmp;
            heapify(arr, n, largest);
        }
    }
}

/*
 * Time Complexity: O(n log n)
 * 說明：建堆自底向上 O(n)，每次取出元素 heapify O(log n)，共 n 次，總共 O(n log n)。
 */
