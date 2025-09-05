
import java.util.*;

public class LC40_CombinationSum2_Procurement {

    public static void combinationSum2(int[] candidates, int target, List<List<Integer>> res, List<Integer> path, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue; // 同層去重

                        }if (candidates[i] > target) {
                break;
            }
            path.add(candidates[i]);
            combinationSum2(candidates, target - candidates[i], res, path, i + 1);
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
        Arrays.sort(candidates);

        List<List<Integer>> res = new ArrayList<>();
        combinationSum2(candidates, target, res, new ArrayList<>(), 0);

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
