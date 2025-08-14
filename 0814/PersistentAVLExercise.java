
import java.util.*;

public class PersistentAVLExercise {

    static class Node {

        final int key;
        final Node left, right;
        final int height;

        Node(int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = Math.max(height(left), height(right)) + 1;
        }
    }

    static int height(Node n) {
        return n == null ? 0 : n.height;
    }

    static int balanceFactor(Node n) {
        return n == null ? 0 : height(n.left) - height(n.right);
    }

    static Node rotateRight(Node y) {
        Node x = y.left;
        Node t2 = x.right;
        return new Node(x.key, x.left, new Node(y.key, t2, y.right));
    }

    static Node rotateLeft(Node x) {
        Node y = x.right;
        Node t2 = y.left;
        return new Node(y.key, new Node(x.key, x.left, t2), y.right);
    }

    static Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key, null, null);
        }
        if (key < node.key) {
            node = new Node(node.key, insert(node.left, key), node.right); 
        }else if (key > node.key) {
            node = new Node(node.key, node.left, insert(node.right, key)); 
        }else {
            return node;
        }
        int balance = balanceFactor(node);
        if (balance > 1 && key < node.left.key) {
            return rotateRight(node);
        }
        if (balance < -1 && key > node.right.key) {
            return rotateLeft(node);
        }
        if (balance > 1 && key > node.left.key) {
            return rotateRight(new Node(node.key, rotateLeft(node.left), node.right));
        }
        if (balance < -1 && key < node.right.key) {
            return rotateLeft(new Node(node.key, node.left, rotateRight(node.right)));
        }
        return node;
    }

    static boolean search(Node node, int key) {
        if (node == null) {
            return false;
        }
        if (key < node.key) {
            return search(node.left, key);
        }
        if (key > node.key) {
            return search(node.right, key);
        }
        return true;
    }

    static void inorder(Node node, List<Integer> res) {
        if (node == null) {
            return;
        }
        inorder(node.left, res);
        res.add(node.key);
        inorder(node.right, res);
    }

    public static void main(String[] args) {
        List<Node> versions = new ArrayList<>();
        Node root = null;
        root = insert(root, 20);
        versions.add(root);
        root = insert(root, 10);
        versions.add(root);
        root = insert(root, 30);
        versions.add(root);
        root = insert(root, 25);
        versions.add(root);
        root = insert(root, 40);
        versions.add(root);

        for (int i = 0; i < versions.size(); i++) {
            List<Integer> res = new ArrayList<>();
            inorder(versions.get(i), res);
            System.out.println("Version " + i + ": " + res);
        }

        System.out.println("Search 25 in version 2: " + search(versions.get(2), 25));
        System.out.println("Search 25 in version 4: " + search(versions.get(4), 25));
    }
}
