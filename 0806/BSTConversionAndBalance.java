
public class BSTConversionAndBalance {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
        }
    }

    static TreeNode prev, head;

    public static TreeNode bstToDoublyLinkedList(TreeNode root) {
        prev = null;
        head = null;
        inorderConvert(root);
        return head;
    }

    private static void inorderConvert(TreeNode node) {
        if (node == null) {
            return;
        }
        inorderConvert(node.left);
        if (prev == null) {
            head = node;
        } else {
            prev.right = node;
            node.left = prev;
        }
        prev = node;
        inorderConvert(node.right);
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return buildBSTFromSortedArray(nums, 0, nums.length - 1);
    }

    private static TreeNode buildBSTFromSortedArray(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildBSTFromSortedArray(nums, left, mid - 1);
        root.right = buildBSTFromSortedArray(nums, mid + 1, right);
        return root;
    }

    static class BalanceStatus {

        boolean balanced;
        int height;
        int balanceFactor;

        BalanceStatus(boolean b, int h, int bf) {
            balanced = b;
            height = h;
            balanceFactor = bf;
        }
    }

    public static BalanceStatus checkBalanced(TreeNode root) {
        if (root == null) {
            return new BalanceStatus(true, 0, 0);
        }
        BalanceStatus left = checkBalanced(root.left);
        BalanceStatus right = checkBalanced(root.right);
        boolean balanced = left.balanced && right.balanced && Math.abs(left.height - right.height) <= 1;
        int height = 1 + Math.max(left.height, right.height);
        int balanceFactor = left.height - right.height;
        return new BalanceStatus(balanced, height, balanceFactor);
    }

    static int sum = 0;

    public static void convertBST(TreeNode root) {
        sum = 0;
        reverseInorder(root);
    }

    private static void reverseInorder(TreeNode node) {
        if (node == null) {
            return;
        }
        reverseInorder(node.right);
        sum += node.val;
        node.val = sum;
        reverseInorder(node.left);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(10);

        TreeNode head = bstToDoublyLinkedList(root);
        TreeNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.right;
        }
        System.out.println();

        int[] sorted = {1, 2, 3, 4, 5, 6, 7};
        TreeNode balancedRoot = sortedArrayToBST(sorted);

        BalanceStatus status = checkBalanced(balancedRoot);
        System.out.println(status.balanced + " " + status.balanceFactor);

        convertBST(balancedRoot);
        printInorder(balancedRoot);
    }

    private static void printInorder(TreeNode root) {
        if (root == null) {
            return;
        }
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }
}
