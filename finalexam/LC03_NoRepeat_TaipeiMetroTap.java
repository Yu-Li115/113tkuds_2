
import java.util.*;

public class LC03_NoRepeat_TaipeiMetroTap {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(lengthOfLongestSubstring(s));
        sc.close();
    }

    // 核心解法：滑動視窗 + HashMap
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // 如果重複，移動左指針
            if (map.containsKey(c) && map.get(c) >= left) {
                left = map.get(c) + 1;
            }

            map.put(c, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}

/*
解題思路：
1. 使用滑動視窗（left, right 指標）。
2. 用 HashMap<char, lastIndex> 紀錄字元最後出現的位置。
3. 每次右指針遇到重複字元，就將左指針移到「該字元上次位置+1」。
4. 計算視窗長度，更新最大值。

時間複雜度：O(n)，每個字元只處理一次。
空間複雜度：O(k)，k 為不同字元數。
 */
