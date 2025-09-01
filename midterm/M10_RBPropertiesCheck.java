
import java.util.*;

public class M10_RBPropertiesCheck {

    static class Node {

        int val;
        char color;
        Node left, right;

        Node(int v, char c) {
            val = v;
            color = c;
        }
    }

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Node[] nodes = new Node[n];

        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            String col = sc.next();
            char color = val == -1 ? 'B' : col.charAt(0);
            if (val != -1) {
                nodes[i] = new Node(val, color);
            }
        }

        for (int i = 0; i < n; i++) {
            if (nodes[i] == null) {
                continue;
            }
            int li = 2 * i + 1, ri = 2 * i + 2;
            if (li < n) {
                nodes[i].left = nodes[li];
            }
            if (ri < n) {
                nodes[i].right = nodes[ri];
            }
        }

        Node root = nodes[0];

        if (root == null || root.color != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        int bh = checkRB(root);
        if (bh == -1) {
            return;
        }
        System.out.println("RB Valid");
    }

    static int checkRB(Node node) {
        if (node == null) {
            return 1;
        }

        if (node.color == 'R') {
            if ((node.left != null && node.left.color == 'R')
                    || (node.right != null && node.right.color == 'R')) {
                System.out.println("RedRedViolation");
                return -1;
            }
        }

        int lh = checkRB(node.left);
        if (lh == -1) {
            return -1;
        }
        int rh = checkRB(node.right);
        if (rh == -1) {
            return -1;
        }

        if (lh != rh) {
            System.out.println("BlackHeightMismatch");
            return -1;
        }

        return lh + (node.color == 'B' ? 1 : 0);
    }
}
