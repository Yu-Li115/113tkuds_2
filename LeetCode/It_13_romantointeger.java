// 題目：Roman to Integer
// 給定一個合法的羅馬數字字串，將其轉換為整數 (範圍 [1,3999])。

import java.util.*;

public class It_13_romantointeger {

    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int value = map.get(s.charAt(i));
            if (i < n - 1 && value < map.get(s.charAt(i + 1))) {
                result -= value;
            } else {
                result += value;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        It_13_romantointeger solution = new It_13_romantointeger();
        System.out.println(solution.romanToInt("III"));      // 3
        System.out.println(solution.romanToInt("LVIII"));    // 58
        System.out.println(solution.romanToInt("MCMXCIV"));  // 1994
    }
}

/*
解題思路：
1. 使用 HashMap 建立羅馬符號與數值的對照表。
2. 從左往右掃描：
   - 若當前符號的值比右邊小，代表是減法情況（扣掉此值）。
   - 否則，加上此值。
3. 全部加總即為最終整數。
4. 時間複雜度 O(n)，字串長度最多 15，非常快。
*/
