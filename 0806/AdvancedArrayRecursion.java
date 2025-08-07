
import java.util.*;

public class AdvancedArrayRecursion {

    public static void quickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = partition(arr, l, r);
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);
    }

    private static int partition(int[] arr, int l, int r) {
        int pivot = arr[r], i = l;
        for (int j = l; j < r; j++) {
            if (arr[j] < pivot) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
                i++;
            }
        }
        int t = arr[i];
        arr[i] = arr[r];
        arr[r] = t;
        return i;
    }

    public static int[] mergeSorted(int[] a, int[] b) {
        return mergeHelper(a, b, 0, 0);
    }

    private static int[] mergeHelper(int[] a, int[] b, int i, int j) {
        if (i == a.length) {
            return Arrays.copyOfRange(b, j, b.length);
        }
        if (j == b.length) {
            return Arrays.copyOfRange(a, i, a.length);
        }
        if (a[i] < b[j]) {
            return concat(a[i], mergeHelper(a, b, i + 1, j)); 
        }else {
            return concat(b[j], mergeHelper(a, b, i, j + 1));
        }
    }

    private static int[] concat(int val, int[] arr) {
        int[] res = new int[arr.length + 1];
        res[0] = val;
        System.arraycopy(arr, 0, res, 1, arr.length);
        return res;
    }

    public static int kthSmallest(int[] arr, int k) {
        return kthHelper(arr, 0, arr.length - 1, k);
    }

    private static int kthHelper(int[] arr, int l, int r, int k) {
        if (l == r) {
            return arr[l];
        }
        int p = partition(arr, l, r);
        int count = p - l + 1;
        if (k == count) {
            return arr[p]; 
        }else if (k < count) {
            return kthHelper(arr, l, p - 1, k); 
        }else {
            return kthHelper(arr, p + 1, r, k - count);
        }
    }

    public static boolean hasSubsetSum(int[] arr, int target) {
        return subsetHelper(arr, 0, target);
    }

    private static boolean subsetHelper(int[] arr, int i, int target) {
        if (target == 0) {
            return true;
        }
        if (i == arr.length) {
            return false;
        }
        return subsetHelper(arr, i + 1, target) || subsetHelper(arr, i + 1, target - arr[i]);
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 7, 1, 3};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(mergeSorted(new int[]{1, 4}, new int[]{2, 3, 5})));
        System.out.println(kthSmallest(new int[]{7, 4, 6, 3, 9}, 3));
        System.out.println(hasSubsetSum(new int[]{1, 2, 3}, 5));
    }
}
