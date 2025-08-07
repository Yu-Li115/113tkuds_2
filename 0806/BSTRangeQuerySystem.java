
import java.util.*;

public class BSTRangeQuerySystem {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    static class BST {

        TreeNode root;

        public void insert(int val) {
            root = insertRecursive(root, val);
        }

        private TreeNode insertRecursive(TreeNode node, int val) {
            if (node == null) {
                return new TreeNode(val);
            }
            if (val < node.val) {
                node.left = insertRecursive(node.left, val); 
            }else {
                node.right = insertRecursive(node.right, val);
            }
            return node;
        }

        public List<Integer> rangeQuery(int min, int max) {
            List<Integer> result = new ArrayList<>();
            rangeQueryRecursive(root, min, max, result);
            return result;
        }

        private void rangeQueryRecursive(TreeNode node, int min, int max, List<Integer> result) {
            if (node == null) {
                return;
            }
            if (node.val > min) {
                rangeQueryRecursive(node.left, min, max, result);
            }
            if (node.val >= min && node.val <= max) {
                result.add(node.val);
            }
            if (node.val < max) {
                rangeQueryRecursive(node.right, min, max, result);
            }
        }

        public int rangeCount(int min, int max) {
            return rangeCountRecursive(root, min, max);
        }

        private int rangeCountRecursive(TreeNode node, int min, int max) {
            if (node == null) {
                return 0;
            }
            if (node.val < min) {
                return rangeCountRecursive(node.right, min, max);
            }
            if (node.val > max) {
                return rangeCountRecursive(node.left, min, max);
            }
            return 1 + rangeCountRecursive(node.left, min, max) + rangeCountRecursive(node.right, min, max);
        }

        public int rangeSum(int min, int max) {
            return rangeSumRecursive(root, min, max);
        }

        private int rangeSumRecursive(TreeNode node, int min, int max) {
            if (node == null) {
                return 0;
            }
            if (node.val < min) {
                return rangeSumRecursive(node.right, min, max);
            }
            if (node.val > max) {
                return rangeSumRecursive(node.left, min, max);
            }
            return node.val + rangeSumRecursive(node.left, min, max) + rangeSumRecursive(node.right, min, max);
        }

        public int closestValue(int target) {
            return closestValueRecursive(root, target, root.val);
        }

        private int closestValueRecursive(TreeNode node, int target, int closest) {
            if (node == null) {
                return closest;
            }
            if (Math.abs(node.val - target) < Math.abs(closest - target)) {
                closest = node.val;
            }
            if (target < node.val) {
                return closestValueRecursive(node.left, target, closest); 
            }else {
                return closestValueRecursive(node.right, target, closest);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BST bst = new BST();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            bst.insert(sc.nextInt());
        }
        int min = sc.nextInt();
        int max = sc.nextInt();
        int target = sc.nextInt();

        List<Integer> range = bst.rangeQuery(min, max);
        for (int val : range) {
            System.out.print(val + " ");
        }
        System.out.println();

        System.out.println(bst.rangeCount(min, max));
        System.out.println(bst.rangeSum(min, max));
        System.out.println(bst.closestValue(target));
    }
}
