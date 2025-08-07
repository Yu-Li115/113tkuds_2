
import java.util.*;

public class TreeReconstruction {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
        }
    }

    public static TreeNode buildFromPreIn(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildPreIn(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    private static TreeNode buildPreIn(int[] pre, int preStart, int preEnd,
            int[] in, int inStart, int inEnd,
            Map<Integer, Integer> inMap) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        int rootVal = pre[preStart];
        TreeNode root = new TreeNode(rootVal);
        int rootIdx = inMap.get(rootVal);
        int leftSize = rootIdx - inStart;
        root.left = buildPreIn(pre, preStart + 1, preStart + leftSize, in, inStart, rootIdx - 1, inMap);
        root.right = buildPreIn(pre, preStart + leftSize + 1, preEnd, in, rootIdx + 1, inEnd, inMap);
        return root;
    }

    public static TreeNode buildFromPostIn(int[] postorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildPostIn(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    private static TreeNode buildPostIn(int[] post, int postStart, int postEnd,
            int[] in, int inStart, int inEnd,
            Map<Integer, Integer> inMap) {
        if (postStart > postEnd || inStart > inEnd) {
            return null;
        }
        int rootVal = post[postEnd];
        TreeNode root = new TreeNode(rootVal);
        int rootIdx = inMap.get(rootVal);
        int leftSize = rootIdx - inStart;
        root.left = buildPostIn(post, postStart, postStart + leftSize - 1, in, inStart, rootIdx - 1, inMap);
        root.right = buildPostIn(post, postStart + leftSize, postEnd - 1, in, rootIdx + 1, inEnd, inMap);
        return root;
    }

    public static TreeNode buildCompleteBinaryTreeFromLevelOrder(int[] levelorder) {
        if (levelorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(levelorder[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while (i < levelorder.length) {
            TreeNode parent = q.poll();
            if (i < levelorder.length) {
                parent.left = new TreeNode(levelorder[i++]);
                q.offer(parent.left);
            }
            if (i < levelorder.length) {
                parent.right = new TreeNode(levelorder[i++]);
                q.offer(parent.right);
            }
        }
        return root;
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    private static void inorder(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        inorder(node.left, res);
        res.add(node.val);
        inorder(node.right, res);
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        int[] levelorder = {3, 9, 20, 15, 7};

        TreeNode rootFromPreIn = buildFromPreIn(preorder, inorder);
        TreeNode rootFromPostIn = buildFromPostIn(postorder, inorder);
        TreeNode rootFromLevel = buildCompleteBinaryTreeFromLevelOrder(levelorder);

        System.out.println(inorderTraversal(rootFromPreIn));
        System.out.println(inorderTraversal(rootFromPostIn));
        System.out.println(inorderTraversal(rootFromLevel));

        System.out.println(isSameTree(rootFromPreIn, rootFromPostIn));
        System.out.println(isSameTree(rootFromPreIn, rootFromLevel));
    }
}
