
public class AVLRotationExercise {

    static class Node {

        int key;
        Node left, right;
        int height;

        Node(int key) {
            this.key = key;
            height = 1;
        }
    }

    static class AVLTree {

        int height(Node node) {
            return node == null ? 0 : node.height;
        }

        Node rotateRight(Node y) {
            Node x = y.left;
            Node T2 = x.right;
            x.right = y;
            y.left = T2;
            y.height = Math.max(height(y.left), height(y.right)) + 1;
            x.height = Math.max(height(x.left), height(x.right)) + 1;
            return x;
        }

        Node rotateLeft(Node x) {
            Node y = x.right;
            Node T2 = y.left;
            y.left = x;
            x.right = T2;
            x.height = Math.max(height(x.left), height(x.right)) + 1;
            y.height = Math.max(height(y.left), height(y.right)) + 1;
            return y;
        }

        Node rotateLeftRight(Node node) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        Node rotateRightLeft(Node node) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        void preorder(Node node) {
            if (node != null) {
                System.out.print(node.key + " ");
                preorder(node.left);
                preorder(node.right);
            }
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        Node ll = new Node(30);
        ll.left = new Node(20);
        ll.left.left = new Node(10);
        ll.height = 3;
        ll = tree.rotateRight(ll);
        tree.preorder(ll);
        System.out.println();

        Node rr = new Node(10);
        rr.right = new Node(20);
        rr.right.right = new Node(30);
        rr.height = 3;
        rr = tree.rotateLeft(rr);
        tree.preorder(rr);
        System.out.println();

        Node lr = new Node(30);
        lr.left = new Node(10);
        lr.left.right = new Node(20);
        lr.height = 3;
        lr = tree.rotateLeftRight(lr);
        tree.preorder(lr);
        System.out.println();

        Node rl = new Node(10);
        rl.right = new Node(30);
        rl.right.left = new Node(20);
        rl.height = 3;
        rl = tree.rotateRightLeft(rl);
        tree.preorder(rl);
        System.out.println();
    }
}
