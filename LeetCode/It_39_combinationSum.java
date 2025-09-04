// 題目：Combination Sum
// 功能：找出所有組合，使其和為 target，每個數字可使用多次

import java.util.*;

public class It_39_combinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
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
            path.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i, path, res); // i 不變 → 可重複使用
            path.remove(path.size() - 1);
        }
    }

    // 測試用 main
    public static void main(String[] args) {
        It_39_combinationSum solution = new It_39_combinationSum();

        int[] candidates1 = {2, 3, 6, 7};
        System.out.println(solution.combinationSum(candidates1, 7));
        // 預期 [[2, 2, 3], [7]]

        int[] candidates2 = {2, 3, 5};
        System.out.println(solution.combinationSum(candidates2, 8));
        // 預期 [[2, 2, 2, 2], [2, 3, 3], [3, 5]]

        int[] candidates3 = {2};
        System.out.println(solution.combinationSum(candidates3, 1));
        // 預期 []
    }
}

/*
解題思路：
1. 使用回溯法，遞迴嘗試每個候選數字。
2. 每次選擇 candidates[i]，並遞迴呼叫剩餘 target - candidates[i]。
3. 允許重複 → 下一次遞迴仍然從 i 開始。
4. 當 target == 0 → 找到一組合法解。

時間複雜度：指數級，最壞情況 O(T^(N/M)) (T=target, N=長度, M=最小候選值)。
空間複雜度：O(target)，取決於遞迴深度。
*/
