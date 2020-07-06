package week02;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P242_Valid_Anagram {

    // 排序 优秀
    public boolean isAnagram(String s, String t) {
        byte[] a = s.getBytes();
        byte[] b = t.getBytes();
        Arrays.sort(a);
        Arrays.sort(b);
        return new String(a, 0, a.length).equals(new String(b, 0, b.length));
    }

    // hash遍历 极差
    public boolean _isAnagram(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.get(c) == null) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (map.get(c) == null) {
                return false;
            } else {
                map.put(c, map.get(c) - 1);
            }
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() != 0) {
                return false;
            }
        }
        return true;
    }
}
