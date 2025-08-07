
import java.util.*;

public class BinaryTreeBasicOperations {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static int sum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return root.val + sum(root.left) + sum(root.right);
    }

    public static int count(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + count(root.left) + count(root.right);
    }

    public static int findMax(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        int left = findMax(root.left);
        int right = findMax(root.right);
        return Math.max(root.val, Math.max(left, right));
    }

    public static int findMin(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        int left = findMin(root.left);
        int right = findMin(root.right);
        return Math.min(root.val, Math.min(left, right));
    }

    public static int getMaxWidth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int maxWidth = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            maxWidth = Math.max(maxWidth, size);
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
        }
        return maxWidth;
    }

    public static boolean isComplete(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean seenNull = false;
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                seenNull = true;
            } else {
                if (seenNull) {
                    return false;
                }
                q.offer(node.left);
                q.offer(node.right);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(7);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(8);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(20);

        int total = sum(root);
        int nodeCount = count(root);
        int max = findMax(root);
        int min = findMin(root);
        int width = getMaxWidth(root);
        boolean complete = isComplete(root);

        System.out.println("總和: " + total);
        System.out.println("平均: " + (total / (double) nodeCount));
        System.out.println("最大值: " + max);
        System.out.println("最小值: " + min);
        System.out.println("最大寬度: " + width);
        System.out.println("是否為完全二元樹: " + (complete ? "是" : "否"));
    }
}
