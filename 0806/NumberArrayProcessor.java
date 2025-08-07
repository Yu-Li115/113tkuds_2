
import java.util.*;

public class NumberArrayProcessor {

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 2, 3, 4, 1, 5};
        int[] arr2 = {2, 4, 6, 8};
        Set<Integer> set = new LinkedHashSet<>();
        for (int n : arr1) {
            set.add(n);
        }
        System.out.println("去重：" + set);
        int[] merged = mergeSorted(new int[]{1, 3, 5}, new int[]{2, 4, 6});
        System.out.println("合併：" + Arrays.toString(merged));
        int most = mostFrequent(arr1);
        System.out.println("最常出現：" + most);
        int[] split = splitEqual(arr1);
        System.out.println("分割點：" + Arrays.toString(split));
    }

    static int[] mergeSorted(int[] a, int[] b) {
        int[] res = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            res[k++] = (a[i] < b[j]) ? a[i++] : b[j++];
        }
        while (i < a.length) {
            res[k++] = a[i++];
        }
        while (j < b.length) {
            res[k++] = b[j++];
        }
        return res;
    }

    static int mostFrequent(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : arr) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        int max = 0, res = arr[0];
        for (int n : map.keySet()) {
            if (map.get(n) > max) {
                max = map.get(n);
                res = n;
            }
        }
        return res;
    }

    static int[] splitEqual(int[] arr) {
        int total = 0, left = 0;
        for (int n : arr) {
            total += n;
        }
        for (int i = 0; i < arr.length; i++) {
            left += arr[i];
            if (left >= total - left) {
                return new int[]{i + 1, arr.length - (i + 1)};
            }
        }
        return new int[]{arr.length, 0};
    }
}
