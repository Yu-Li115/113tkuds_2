
import java.util.*;

public class LC01_TwoSum_THSRHoliday {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long target = sc.nextLong(); // target 可能很大，所以用 long
        long[] seats = new long[n];
        for (int i = 0; i < n; i++) {
            seats[i] = sc.nextLong();
        }

        int[] result = twoSum(seats, target);

        System.out.println(result[0] + " " + result[1]);

        sc.close();
    }

    public static int[] twoSum(long[] nums, long target) {
        Map<Long, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long x = nums[i];
            if (map.containsKey(x)) {
                return new int[]{map.get(x), i};
            }
            map.put(target - x, i);
        }
        return new int[]{-1, -1};
    }
}

/*
解題思路：
1. 使用 HashMap<需要的數, 索引>。
2. 遍歷陣列，每讀到 nums[i]，檢查 map 是否已存在這個數：
   - 若存在，表示前面有人在等它，回傳 (map.get(nums[i]), i)。
   - 若不存在，將「需要 target-nums[i]」存入 map。
3. 遍歷完仍無解，輸出 -1 -1。

時間複雜度：O(n)，每個元素查詢與插入 HashMap 都是 O(1)。
空間複雜度：O(n)，最壞情況要存所有元素。
 */
