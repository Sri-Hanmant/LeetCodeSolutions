import java.util.HashMap;
import java.util.Map;

public class WordPattern {

    public static boolean wordPattern(String pattern, String s) {

        Map<Character, String> map = new HashMap();

        String[] splitString = s.trim().split(" ");

        if (pattern.length() != splitString.length) {
            return false;
        }

        for (int i = 0; i < pattern.length(); i++) {

            char k = pattern.charAt(i);
            String v = splitString[i];

            if (map.containsKey(k)) {
                if (!map.get(k).equals(v))
                    return false;
            } else if (map.containsValue(v)) {
                return false;
            } else {
                map.put(k, v);
            }
        }

        return true;
    }

    public static void main(String args[]) {
        wordPattern("abba", "dog cat cat fish");
    }
}