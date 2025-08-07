
import java.util.*;

public class BSTValidationAndRepair {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
        }
    }

    // 驗證是否為有效BST
    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private static boolean isValidBST(TreeNode node, Integer low, Integer high) {
        if (node == null) {
            return true;
        }
        int v = node.val;
        if (low != null && v <= low) {
            return false;
        }
        if (high != null && v >= high) {
            return false;
        }
        return isValidBST(node.left, low, v) && isValidBST(node.right, v, high);
    }

    // 找出不符合規則的節點（即錯誤節點值）
    public static List<Integer> findInvalidNodes(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        List<TreeNode> wrongNodes = new ArrayList<>();
        TreeNode[] prev = new TreeNode[1];
        inorderFindWrongNodes(root, prev, wrongNodes);
        for (TreeNode n : wrongNodes) {
            res.add(n.val);
        }
        return res;
    }

    private static void inorderFindWrongNodes(TreeNode root, TreeNode[] prev, List<TreeNode> wrongNodes) {
        if (root == null) {
            return;
        }
        inorderFindWrongNodes(root.left, prev, wrongNodes);
        if (prev[0] != null && prev[0].val > root.val) {
            if (wrongNodes.isEmpty()) {
                wrongNodes.add(prev[0]);
                wrongNodes.add(root);
            } else {
                wrongNodes.set(1, root);
            }
        }
        prev[0] = root;
        inorderFindWrongNodes(root.right, prev, wrongNodes);
    }

    // 修復錯誤的BST（兩節點交換）
    public static void recoverBST(TreeNode root) {
        TreeNode[] prev = new TreeNode[1];
        TreeNode[] first = new TreeNode[1];
        TreeNode[] second = new TreeNode[1];
        inorderFindTwoSwapped(root, prev, first, second);
        if (first[0] != null && second[0] != null) {
            int t = first[0].val;
            first[0].val = second[0].val;
            second[0].val = t;
        }
    }

    private static void inorderFindTwoSwapped(TreeNode root, TreeNode[] prev, TreeNode[] first, TreeNode[] second) {
        if (root == null) {
            return;
        }
        inorderFindTwoSwapped(root.left, prev, first, second);
        if (prev[0] != null && prev[0].val > root.val) {
            if (first[0] == null) {
                first[0] = prev[0];
                second[0] = root;
            } else {
                second[0] = root;
            }
        }
        prev[0] = root;
        inorderFindTwoSwapped(root.right, prev, first, second);
    }

    // 計算最少移除節點數使樹成為有效BST
    public static int minRemoveToMakeBST(TreeNode root) {
        return root == null ? 0 : rootCount(root) - longestBSTSubtree(root);
    }

    private static int rootCount(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + rootCount(root.left) + rootCount(root.right);
    }

    private static int longestBSTSubtree(TreeNode root) {
        int[] max = new int[1];
        helper(root, max);
        return max[0];
    }

    // 回傳子樹資訊: [isBST(0/1), min, max, size]
    private static int[] helper(TreeNode node, int[] max) {
        if (node == null) {
            return new int[]{1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }
        int[] left = helper(node.left, max);
        int[] right = helper(node.right, max);
        if (left[0] == 1 && right[0] == 1 && node.val > left[2] && node.val < right[1]) {
            int size = left[3] + right[3] + 1;
            max[0] = Math.max(max[0], size);
            int min = Math.min(left[1], node.val);
            int maxv = Math.max(right[2], node.val);
            return new int[]{1, min, maxv, size};
        } else {
            return new int[]{0, 0, 0, 0};
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);

        System.out.println(isValidBST(root));
        System.out.println(findInvalidNodes(root));
        recoverBST(root);
        System.out.println(isValidBST(root));
        System.out.println(minRemoveToMakeBST(root));
    }
}
