
import java.util.*;

public class MultiWayTreeAndDecisionTree {

    static class MultiWayNode {

        String val;
        List<MultiWayNode> children;

        MultiWayNode(String v) {
            val = v;
            children = new ArrayList<>();
        }
    }

    public static void dfs(MultiWayNode root, List<String> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        for (MultiWayNode child : root.children) {
            dfs(child, res);
        }
    }

    public static List<String> bfs(MultiWayNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<MultiWayNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            MultiWayNode node = q.poll();
            res.add(node.val);
            for (MultiWayNode c : node.children) {
                q.offer(c);
            }
        }
        return res;
    }

    static class DecisionNode {

        String question;
        Map<String, DecisionNode> options;
        String result;

        DecisionNode(String q) {
            question = q;
            options = new HashMap<>();
            result = null;
        }

        DecisionNode(String q, String r) {
            question = q;
            result = r;
            options = new HashMap<>();
        }

        boolean isLeaf() {
            return result != null;
        }
    }

    public static String playDecisionTree(DecisionNode root, List<String> answers) {
        DecisionNode current = root;
        int i = 0;
        while (current != null && !current.isLeaf() && i < answers.size()) {
            String ans = answers.get(i++);
            if (current.options.containsKey(ans)) {
                current = current.options.get(ans); 
            }else {
                return "Invalid answer";
            }
        }
        return current == null ? "No result" : current.result;
    }

    public static int height(MultiWayNode root) {
        if (root == null) {
            return 0;
        }
        int maxH = 0;
        for (MultiWayNode c : root.children) {
            maxH = Math.max(maxH, height(c));
        }
        return maxH + 1;
    }

    public static Map<MultiWayNode, Integer> degreeMap(MultiWayNode root) {
        Map<MultiWayNode, Integer> map = new HashMap<>();
        if (root == null) {
            return map;
        }
        Queue<MultiWayNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            MultiWayNode node = q.poll();
            map.put(node, node.children.size());
            for (MultiWayNode c : node.children) {
                q.offer(c);
            }
        }
        return map;
    }

    public static void main(String[] args) {
        MultiWayNode root = new MultiWayNode("A");
        MultiWayNode b = new MultiWayNode("B");
        MultiWayNode c = new MultiWayNode("C");
        MultiWayNode d = new MultiWayNode("D");
        root.children.add(b);
        root.children.add(c);
        b.children.add(d);

        List<String> dfsRes = new ArrayList<>();
        dfs(root, dfsRes);
        System.out.println(dfsRes);

        System.out.println(bfs(root));

        DecisionNode rootD = new DecisionNode("Is number > 50?");
        DecisionNode yes = new DecisionNode("Is number > 75?");
        DecisionNode no = new DecisionNode("Number is <= 50", "Guess is 25");
        DecisionNode yesYes = new DecisionNode(null, "Guess is 90");
        DecisionNode yesNo = new DecisionNode(null, "Guess is 60");

        rootD.options.put("yes", yes);
        rootD.options.put("no", no);
        yes.options.put("yes", yesYes);
        yes.options.put("no", yesNo);

        List<String> answers1 = Arrays.asList("yes", "no");
        List<String> answers2 = Arrays.asList("no");

        System.out.println(playDecisionTree(rootD, answers1));
        System.out.println(playDecisionTree(rootD, answers2));

        System.out.println(height(root));
        Map<MultiWayNode, Integer> degrees = degreeMap(root);
        for (Map.Entry<MultiWayNode, Integer> e : degrees.entrySet()) {
            System.out.println(e.getKey().val + " degree: " + e.getValue());
        }
    }
}
