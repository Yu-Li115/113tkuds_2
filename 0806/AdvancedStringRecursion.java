
public class AdvancedStringRecursion {

    public static void permutations(String s) {
        permute("", s);
    }

    private static void permute(String prefix, String s) {
        if (s.isEmpty()) {
            System.out.println(prefix); 
        }else {
            for (int i = 0; i < s.length(); i++) {
                permute(prefix + s.charAt(i), s.substring(0, i) + s.substring(i + 1));
            }
        }
    }

    public static boolean match(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        boolean first = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        if (p.length() >= 2 && p.charAt(1) == '*') {
            return match(s, p.substring(2)) || (first && match(s.substring(1), p)); 
        }else {
            return first && match(s.substring(1), p.substring(1));
        }
    }

    public static String removeDuplicates(String s) {
        return removeDupHelper(s, "");
    }

    private static String removeDupHelper(String s, String seen) {
        if (s.isEmpty()) {
            return "";
        }
        char c = s.charAt(0);
        if (seen.indexOf(c) >= 0) {
            return removeDupHelper(s.substring(1), seen);
        }
        return c + removeDupHelper(s.substring(1), seen + c);
    }

    public static void substrings(String s) {
        subHelper("", s);
    }

    private static void subHelper(String prefix, String s) {
        if (!prefix.isEmpty()) {
            System.out.println(prefix);
        }
        for (int i = 0; i < s.length(); i++) {
            subHelper(prefix + s.charAt(i), s.substring(i + 1));
        }
    }

    public static void main(String[] args) {
        permutations("abc");
        System.out.println(match("aab", "c*a*b"));
        System.out.println(removeDuplicates("banana"));
        substrings("abc");
    }
}
