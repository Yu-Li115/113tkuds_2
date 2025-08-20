
import java.util.*;

public class M02_YouBikeNextArrival {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            times[i] = toMinutes(sc.nextLine().trim());
        }
        int query = toMinutes(sc.nextLine().trim());

        int ansIdx = binarySearch(times, query);
        if (ansIdx == -1) {
            System.out.println("No bike");
        } else {
            System.out.println(toHHMM(times[ansIdx]));
        }
    }

    static int binarySearch(int[] arr, int query) {
        int l = 0, r = arr.length - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] > query) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    static int toMinutes(String t) {
        String[] parts = t.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    static String toHHMM(int m) {
        int h = m / 60, min = m % 60;
        return String.format("%02d:%02d", h, min);
    }
}
