
import java.util.*;

public class AVLLeaderboardSystem {

    static class Node {

        String name;
        int score;
        Node left, right;
        int height, size;

        Node(String name, int score) {
            this.name = name;
            this.score = score;
            height = 1;
            size = 1;
        }
    }

    static class AVLTree {

        Node root;
        Map<String, Integer> playerScores = new HashMap<>();

        int height(Node node) {
            return node == null ? 0 : node.height;
        }

        int size(Node node) {
            return node == null ? 0 : node.size;
        }

        int getBalance(Node node) {
            return node == null ? 0 : height(node.left) - height(node.right);
        }

        void updateHeightAndSize(Node node) {
            node.height = Math.max(height(node.left), height(node.right)) + 1;
            node.size = size(node.left) + size(node.right) + 1;
        }

        int compare(Node a, String name, int score) {
            if (score != a.score) {
                return score > a.score ? -1 : 1;
            }
            return name.compareTo(a.name);
        }

        Node rotateRight(Node y) {
            Node x = y.left;
            Node T2 = x.right;
            x.right = y;
            y.left = T2;
            updateHeightAndSize(y);
            updateHeightAndSize(x);
            return x;
        }

        Node rotateLeft(Node x) {
            Node y = x.right;
            Node T2 = y.left;
            y.left = x;
            x.right = T2;
            updateHeightAndSize(x);
            updateHeightAndSize(y);
            return y;
        }

        Node insert(Node node, String name, int score) {
            if (node == null) {
                return new Node(name, score);
            }
            if (compare(node, name, score) > 0) {
                node.left = insert(node.left, name, score); 
            }else if (compare(node, name, score) < 0) {
                node.right = insert(node.right, name, score); 
            }else {
                return node;
            }
            updateHeightAndSize(node);
            int balance = getBalance(node);
            if (balance > 1 && compare(node.left, name, score) > 0) {
                return rotateRight(node);
            }
            if (balance < -1 && compare(node.right, name, score) < 0) {
                return rotateLeft(node);
            }
            if (balance > 1 && compare(node.left, name, score) < 0) {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
            if (balance < -1 && compare(node.right, name, score) > 0) {
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

        Node delete(Node root, String name, int score) {
            if (root == null) {
                return root;
            }
            if (compare(root, name, score) > 0) {
                root.left = delete(root.left, name, score); 
            }else if (compare(root, name, score) < 0) {
                root.right = delete(root.right, name, score); 
            }else {
                if ((root.left == null) || (root.right == null)) {
                    Node temp = root.left != null ? root.left : root.right;
                    root = temp;
                } else {
                    Node temp = minValueNode(root.right);
                    root.name = temp.name;
                    root.score = temp.score;
                    root.right = delete(root.right, temp.name, temp.score);
                }
            }
            if (root == null) {
                return root;
            }
            updateHeightAndSize(root);
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

        int getRank(Node node, String name, int score) {
            if (node == null) {
                return 0;
            }
            if (score > node.score || (score == node.score && name.compareTo(node.name) < 0)) {
                return getRank(node.left, name, score); 
            }else if (score < node.score || (score == node.score && name.compareTo(node.name) > 0)) {
                return size(node.left) + 1 + getRank(node.right, name, score); 
            }else {
                return size(node.left) + 1;
            }
        }

        void select(Node node, int k, List<String> result) {
            if (node == null || result.size() >= k) {
                return;
            }
            select(node.left, k, result);
            if (result.size() < k) {
                result.add(node.name + " (" + node.score + ")");
            }
            select(node.right, k, result);
        }

        void addPlayer(String name, int score) {
            if (playerScores.containsKey(name)) {
                return;
            }
            root = insert(root, name, score);
            playerScores.put(name, score);
        }

        void updateScore(String name, int newScore) {
            if (!playerScores.containsKey(name)) {
                return;
            }
            int oldScore = playerScores.get(name);
            root = delete(root, name, oldScore);
            root = insert(root, name, newScore);
            playerScores.put(name, newScore);
        }

        int getRank(String name) {
            if (!playerScores.containsKey(name)) {
                return -1;
            }
            return getRank(root, name, playerScores.get(name));
        }

        List<String> topK(int k) {
            List<String> result = new ArrayList<>();
            select(root, k, result);
            return result;
        }
    }

    public static void main(String[] args) {
        AVLTree leaderboard = new AVLTree();
        leaderboard.addPlayer("Alice", 90);
        leaderboard.addPlayer("Bob", 95);
        leaderboard.addPlayer("Charlie", 85);
        leaderboard.addPlayer("David", 92);

        System.out.println(leaderboard.topK(3));
        System.out.println(leaderboard.getRank("Charlie"));
        leaderboard.updateScore("Charlie", 97);
        System.out.println(leaderboard.topK(3));
        System.out.println(leaderboard.getRank("Charlie"));
    }
}
