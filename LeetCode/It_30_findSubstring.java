// 題目：Substring with Concatenation of All Words
// 功能：找到 s 中所有由 words 中所有字串組合而成的子字串起始位置

import java.util.*;

public class It_30_findSubstring {

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || words == null || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        if (s.length() < totalLen) {
            return result;
        }

        // 建立單字頻率表
        Map<String, Integer> wordMap = new HashMap<>();
        for (String w : words) {
            wordMap.put(w, wordMap.getOrDefault(w, 0) + 1);
        }

        // 遍歷每一種可能的起始偏移
        for (int i = 0; i < wordLen; i++) {
            int left = i, count = 0;
            Map<String, Integer> seen = new HashMap<>();

            for (int j = i; j + wordLen <= s.length(); j += wordLen) {
                String sub = s.substring(j, j + wordLen);

                if (wordMap.containsKey(sub)) {
                    seen.put(sub, seen.getOrDefault(sub, 0) + 1);
                    count++;

                    // 若某字出現過多，移動左邊界
                    while (seen.get(sub) > wordMap.get(sub)) {
                        String leftWord = s.substring(left, left + wordLen);
                        seen.put(leftWord, seen.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }

                    // 如果符合 wordCount，記錄答案
                    if (count == wordCount) {
                        result.add(left);
                    }
                } else {
                    // 不符合 → 重置
                    seen.clear();
                    count = 0;
                    left = j + wordLen;
                }
            }
        }

        return result;
    }

    // 測試用 main
    public static void main(String[] args) {
        It_30_findSubstring solution = new It_30_findSubstring();

        System.out.println(solution.findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
        // 預期 [0,9]

        System.out.println(solution.findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "word"}));
        // 預期 []

        System.out.println(solution.findSubstring("barfoofoobarthefoobarman", new String[]{"bar", "foo", "the"}));
        // 預期 [6,9,12]
    }
}

/*
解題思路：
1. 每個單字長度固定，總長度 = wordLen * words.length。
2. 使用滑動視窗，每次抓 wordLen 長度的子字。
3. 用 HashMap 記錄出現次數，若超過要求 → 收縮左邊界。
4. 當 count == wordCount 時，表示找到完整組合。
5. 結果加入起始 index。

時間複雜度：O(n * wordLen)，n 為 s 長度。
空間複雜度：O(m)，m 為 words 數量。
*/
