
import java.util.*;

public class M01_BuildHeap {

    static boolean isMax;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String type = sc.next();
        isMax = type.equals("max");
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + (i == n - 1 ? "" : " "));
        }
    }

    static void heapify(int[] arr, int n, int i) {
        int target = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && compare(arr[left], arr[target])) {
            target = left;
        }
        if (right < n && compare(arr[right], arr[target])) {
            target = right;
        }

        if (target != i) {
            int tmp = arr[i];
            arr[i] = arr[target];
            arr[target] = tmp;
            heapify(arr, n, target);
        }
    }

    static boolean compare(int a, int b) {
        return isMax ? a > b : a < b;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：自底向上建堆對每個節點做 heapify。高度為 h 的節點數大約為 n/2^(h+1)，
 *       每次 heapify 最多走 h 步。總計 Σ (n/2^(h+1) * h) = O(n)。
 */
