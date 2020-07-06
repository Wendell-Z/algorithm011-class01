package week02;

import java.util.*;

public class P49_GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();
        Map<String, List> map = new HashMap<>();
        for (String s : strs) {
            byte[] b = s.getBytes();
            Arrays.sort(b);
            String ss = new String(b, 0, b.length);
            if (!map.containsKey(ss)) {
                map.put(ss, new ArrayList<String>());
            }
            map.get(ss).add(s);
        }

        return new ArrayList(map.values());
    }

    // worst
    public List<List<String>> _groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();

        for (int i = 0; i < strs.length; i++) {
            if (strs[i].equals("-")) continue;
            List<String> list = new ArrayList<>();
            list.add(strs[i]);
            for (int j = i + 1; j < strs.length; j++) {
                if (isAnagram(strs[i], strs[j])) {
                    list.add(strs[j]);
                    strs[j] = "-";
                }
            }
            result.add(list);
        }
        return result;
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        byte[] a = s.getBytes();
        byte[] b = t.getBytes();
        Arrays.sort(a);
        Arrays.sort(b);
        return new String(a, 0, a.length).equals(new String(b, 0, b.length));
    }
}
