
import java.util.*;

public class AVLRangeQueryExercise {

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

        List<Integer> rangeQuery(int min, int max) {
            List<Integer> result = new ArrayList<>();
            rangeQueryHelper(root, min, max, result);
            return result;
        }

        void rangeQueryHelper(Node node, int min, int max, List<Integer> result) {
            if (node == null) {
                return;
            }
            if (node.key > min) {
                rangeQueryHelper(node.left, min, max, result);
            }
            if (node.key >= min && node.key <= max) {
                result.add(node.key);
            }
            if (node.key < max) {
                rangeQueryHelper(node.right, min, max, result);
            }
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        int[] nums = {20, 10, 30, 5, 15, 25, 35, 2, 7, 13, 17};
        for (int n : nums) {
            tree.root = tree.insert(tree.root, n);
        }

        System.out.println(tree.rangeQuery(10, 25));
        System.out.println(tree.rangeQuery(5, 17));
        System.out.println(tree.rangeQuery(1, 40));
    }
}
