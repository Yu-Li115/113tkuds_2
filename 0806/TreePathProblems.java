
import java.util.*;

public class TreePathProblems {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
        }
    }

    // 找出所有從根到葉的路徑
    public static List<List<Integer>> rootToLeafPaths(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        dfs(root, new ArrayList<>(), res);
        return res;
    }

    private static void dfs(TreeNode node, List<Integer> path, List<List<Integer>> res) {
        if (node == null) {
            return;
        }
        path.add(node.val);
        if (node.left == null && node.right == null) {
            res.add(new ArrayList<>(path));
        } else {
            dfs(node.left, path, res);
            dfs(node.right, path, res);
        }
        path.remove(path.size() - 1);
    }

    // 判斷是否有根到葉路徑和為target
    public static boolean hasPathSum(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == target;
        }
        return hasPathSum(root.left, target - root.val) || hasPathSum(root.right, target - root.val);
    }

    // 找出和最大的根到葉路徑（回傳該路徑和）
    public static int maxRootToLeafSum(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        return root.val + Math.max(maxRootToLeafSum(root.left), maxRootToLeafSum(root.right));
    }

    // 計算樹的最大路徑和（任意兩節點間，樹的直徑和）
    static int maxPathSumResult = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        maxPathSumResult = Integer.MIN_VALUE;
        maxGain(root);
        return maxPathSumResult;
    }

    private static int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);
        int priceNewPath = node.val + leftGain + rightGain;
        maxPathSumResult = Math.max(maxPathSumResult, priceNewPath);
        return node.val + Math.max(leftGain, rightGain);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);

        System.out.println(rootToLeafPaths(root));
        System.out.println(hasPathSum(root, 18));
        System.out.println(maxRootToLeafSum(root));
        System.out.println(maxPathSum(root));
    }
}
