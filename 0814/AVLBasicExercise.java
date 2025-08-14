
public class AVLBasicExercise {

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

        Node root;

        int height(Node node) {
            return node == null ? 0 : node.height;
        }

        int getBalance(Node node) {
            return node == null ? 0 : height(node.left) - height(node.right);
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

        Node insert(Node node, int key) {
            if (node == null) {
                return new Node(key);
            }
            if (key < node.key) {
                node.left = insert(node.left, key); 
            }else if (key > node.key) {
                node.right = insert(node.right, key); 
            }else {
                return node;
            }
            node.height = Math.max(height(node.left), height(node.right)) + 1;
            int balance = getBalance(node);
            if (balance > 1 && key < node.left.key) {
                return rotateRight(node);
            }
            if (balance < -1 && key > node.right.key) {
                return rotateLeft(node);
            }
            if (balance > 1 && key > node.left.key) {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
            if (balance < -1 && key < node.right.key) {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
            return node;
        }

        boolean search(Node node, int key) {
            if (node == null) {
                return false;
            }
            if (key == node.key) {
                return true;
            }
            if (key < node.key) {
                return search(node.left, key);
            }
            return search(node.right, key);
        }

        boolean isAVL(Node node) {
            if (node == null) {
                return true;
            }
            int balance = getBalance(node);
            if (balance < -1 || balance > 1) {
                return false;
            }
            return isAVL(node.left) && isAVL(node.right);
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 40);
        tree.root = tree.insert(tree.root, 50);
        tree.root = tree.insert(tree.root, 25);
        System.out.println(tree.search(tree.root, 30));
        System.out.println(tree.search(tree.root, 99));
        System.out.println(tree.height(tree.root));
        System.out.println(tree.isAVL(tree.root));
    }
}
