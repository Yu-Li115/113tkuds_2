
import java.util.*;

public class SelectionSortImplementation {

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        int comp = 0, swap = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                comp++;
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (i != min) {
                int t = arr[i];
                arr[i] = arr[min];
                arr[min] = t;
                swap++;
            }
            System.out.println("第" + (i + 1) + "輪：" + Arrays.toString(arr));
        }
        System.out.println("比較次數：" + comp);
        System.out.println("交換次數：" + swap);
    }
}
