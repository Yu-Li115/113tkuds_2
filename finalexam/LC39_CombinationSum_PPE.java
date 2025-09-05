
import java.util.*;

public class LC39_CombinationSum_PPE {

    public static void combinationSum(int[] candidates, int target, List<List<Integer>> res, List<Integer> path, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) {
                continue;
            }
            path.add(candidates[i]);
            combinationSum(candidates, target - candidates[i], res, path, i); // 可以重複使用同一元素
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) {
            candidates[i] = sc.nextInt();
        }
        Arrays.sort(candidates); // 剪枝需排序

        List<List<Integer>> res = new ArrayList<>();
        combinationSum(candidates, target, res, new ArrayList<>(), 0);

        for (List<Integer> comb : res) {
            for (int i = 0; i < comb.size(); i++) {
                System.out.print(comb.get(i));
                if (i != comb.size() - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
