// 題目：Find the Index of the First Occurrence in a String
// 功能：找到 needle 在 haystack 中第一次出現的位置，若不存在則回傳 -1

public class It_28_strstr {

    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    // 測試用 main
    public static void main(String[] args) {
        It_28_strstr solution = new It_28_strstr();

        String haystack1 = "sadbutsad";
        String needle1 = "sad";
        System.out.println(solution.strStr(haystack1, needle1)); // 預期 0

        String haystack2 = "leetcode";
        String needle2 = "leeto";
        System.out.println(solution.strStr(haystack2, needle2)); // 預期 -1
    }
}

/*
解題思路：
1. 使用 Java 內建的 indexOf 方法。
2. 若 needle 存在於 haystack，回傳第一次出現的索引；
   否則回傳 -1。
*/
