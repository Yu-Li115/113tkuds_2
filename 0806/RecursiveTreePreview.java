
import java.util.*;

public class RecursiveTreePreview {

    static class Folder {

        String name;
        List<Object> content;

        Folder(String name) {
            this.name = name;
            this.content = new ArrayList<>();
        }

        void add(Object o) {
            content.add(o);
        }
    }

    public static int countFiles(Object node) {
        if (node instanceof String) {
            return 1;
        }
        int count = 0;
        for (Object o : ((Folder) node).content) {
            count += countFiles(o);
        }
        return count;
    }

    public static void printMenu(String[] menu, int level) {
        for (String item : menu) {
            System.out.println("  ".repeat(level) + item);
        }
    }

    public static List<Object> flatten(Object[] arr) {
        List<Object> res = new ArrayList<>();
        for (Object o : arr) {
            if (o instanceof Object[]) {
                res.addAll(flatten((Object[]) o)); 
            }else {
                res.add(o);
            }
        }
        return res;
    }

    public static int maxDepth(Object o) {
        if (!(o instanceof Object[])) {
            return 0;
        }
        int max = 0;
        for (Object item : (Object[]) o) {
            max = Math.max(max, maxDepth(item));
        }
        return max + 1;
    }

    public static void main(String[] args) {
        Folder root = new Folder("root");
        root.add("a.txt");
        Folder sub = new Folder("sub");
        sub.add("b.txt");
        root.add(sub);
        System.out.println(countFiles(root));
        printMenu(new String[]{"檔案", "編輯", "檢視"}, 0);
        Object[] nested = {1, new Object[]{2, new Object[]{3}}, 4};
        System.out.println(flatten(nested));
        System.out.println(maxDepth(nested));
    }
}
