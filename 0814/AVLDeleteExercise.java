
public class AVLDeleteExercise {

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

        Node minValueNode(Node node) {
            Node current = node;
            while (current.left != null) {
                current = current.left;
            }
            return current;
        }

        Node delete(Node root, int key) {
            if (root == null) {
                return root;
            }
            if (key < root.key) {
                root.left = delete(root.left, key); 
            }else if (key > root.key) {
                root.right = delete(root.right, key); 
            }else {
                if ((root.left == null) || (root.right == null)) {
                    Node temp = root.left != null ? root.left : root.right;
                    if (temp == null) {
                        root = null;
                    } else {
                        root = temp;
                    }
                } else {
                    Node temp = minValueNode(root.right);
                    root.key = temp.key;
                    root.right = delete(root.right, temp.key);
                }
            }
            if (root == null) {
                return root;
            }
            root.height = Math.max(height(root.left), height(root.right)) + 1;
            int balance = getBalance(root);
            if (balance > 1 && getBalance(root.left) >= 0) {
                return rotateRight(root);
            }
            if (balance > 1 && getBalance(root.left) < 0) {
                root.left = rotateLeft(root.left);
                return rotateRight(root);
            }
            if (balance < -1 && getBalance(root.right) <= 0) {
                return rotateLeft(root);
            }
            if (balance < -1 && getBalance(root.right) > 0) {
                root.right = rotateRight(root.right);
                return rotateLeft(root);
            }
            return root;
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
        tree.root = tree.insert(tree.root, 9);
        tree.root = tree.insert(tree.root, 5);
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 0);
        tree.root = tree.insert(tree.root, 6);
        tree.root = tree.insert(tree.root, 11);
        tree.root = tree.insert(tree.root, -1);
        tree.root = tree.insert(tree.root, 1);
        tree.root = tree.insert(tree.root, 2);

        tree.preorder(tree.root);
        System.out.println();

        tree.root = tree.delete(tree.root, 10);
        tree.preorder(tree.root);
        System.out.println();

        tree.root = tree.delete(tree.root, -1);
        tree.preorder(tree.root);
        System.out.println();

        tree.root = tree.delete(tree.root, 9);
        tree.preorder(tree.root);
        System.out.println();
    }
}
