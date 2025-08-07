
import java.util.*;

public class BSTKthElement {

    static class TreeNode {

        int val;
        TreeNode left, right;
        int count; // 子樹節點數(包含自己)

        TreeNode(int val) {
            this.val = val;
            this.count = 1;
        }
    }

    static class BST {

        TreeNode root;

        private int size(TreeNode node) {
            return node == null ? 0 : node.count;
        }

        public void insert(int val) {
            root = insert(root, val);
        }

        private TreeNode insert(TreeNode node, int val) {
            if (node == null) {
                return new TreeNode(val);
            }
            if (val < node.val) {
                node.left = insert(node.left, val); 
            }else {
                node.right = insert(node.right, val);
            }
            node.count = 1 + size(node.left) + size(node.right);
            return node;
        }

        public void delete(int val) {
            root = delete(root, val);
        }

        private TreeNode delete(TreeNode node, int val) {
            if (node == null) {
                return null;
            }
            if (val < node.val) {
                node.left = delete(node.left, val); 
            }else if (val > node.val) {
                node.right = delete(node.right, val); 
            }else {
                if (node.left == null) {
                    return node.right;
                }
                if (node.right == null) {
                    return node.left;
                }
                TreeNode minNode = findMin(node.right);
                node.val = minNode.val;
                node.right = delete(node.right, minNode.val);
            }
            node.count = 1 + size(node.left) + size(node.right);
            return node;
        }

        private TreeNode findMin(TreeNode node) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        public int kthSmallest(int k) {
            return kthSmallest(root, k);
        }

        private int kthSmallest(TreeNode node, int k) {
            int leftCount = size(node.left);
            if (k <= leftCount) {
                return kthSmallest(node.left, k); 
            }else if (k == leftCount + 1) {
                return node.val; 
            }else {
                return kthSmallest(node.right, k - leftCount - 1);
            }
        }

        public int kthLargest(int k) {
            return kthLargest(root, k);
        }

        private int kthLargest(TreeNode node, int k) {
            int rightCount = size(node.right);
            if (k <= rightCount) {
                return kthLargest(node.right, k); 
            }else if (k == rightCount + 1) {
                return node.val; 
            }else {
                return kthLargest(node.left, k - rightCount - 1);
            }
        }

        public List<Integer> kthSmallestRange(int k, int j) {
            List<Integer> res = new ArrayList<>();
            inorderRange(root, new int[]{k, j}, res, new int[]{0});
            return res;
        }

        private void inorderRange(TreeNode node, int[] range, List<Integer> res, int[] count) {
            if (node == null) {
                return;
            }
            inorderRange(node.left, range, res, count);
            count[0]++;
            if (count[0] >= range[0] && count[0] <= range[1]) {
                res.add(node.val);
            }
            if (count[0] > range[1]) {
                return;
            }
            inorderRange(node.right, range, res, count);
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        int[] vals = {5, 3, 7, 2, 4, 6, 8};
        for (int v : vals) {
            bst.insert(v);
        }

        System.out.println(bst.kthSmallest(3));
        System.out.println(bst.kthLargest(2));
        List<Integer> range = bst.kthSmallestRange(2, 5);
        for (int val : range) {
            System.out.print(val + " ");
        }
        System.out.println();

        bst.delete(4);
        System.out.println(bst.kthSmallest(3));
    }
}
