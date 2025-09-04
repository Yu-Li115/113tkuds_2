// 題目：Combination Sum II
// 功能：找出所有組合，使其和為 target，每個數字只能用一次，避免重複組合

import java.util.*;

public class It_40_combinationSum2 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates); // 排序，便於去重
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (target < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // 避免同層重複解
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            path.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i + 1, path, res); // i+1 → 每個數只能用一次
            path.remove(path.size() - 1);
        }
    }

    // 測試用 main
    public static void main(String[] args) {
        It_40_combinationSum2 solution = new It_40_combinationSum2();

        int[] candidates1 = {10, 1, 2, 7, 6, 1, 5};
        System.out.println(solution.combinationSum2(candidates1, 8));
        // 預期 [[1,1,6], [1,2,5], [1,7], [2,6]]

        int[] candidates2 = {2, 5, 2, 1, 2};
        System.out.println(solution.combinationSum2(candidates2, 5));
        // 預期 [[1,2,2], [5]]
    }
}

/*
解題思路：
1. 先排序，方便處理重複數字。
2. 回溯過程中，每層選擇時跳過相同數字 (i > start && nums[i] == nums[i-1])。
3. 每個數字只能用一次 → 下一次遞迴從 i+1 開始。
4. 當 target == 0 時，存入解答。

時間複雜度：O(2^n)，最壞情況需遍歷所有子集。
空間複雜度：O(n)，遞迴深度。
*/
