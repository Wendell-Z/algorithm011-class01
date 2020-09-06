package week09;

public class P541_Reverse_String_II {

    public String reverseStr(String s, int k) {
        char cs[] = s.toCharArray();
        if (cs.length <= k) return new String(reverse(cs, 0, cs.length - 1));
        int i = 0;
        while (i < cs.length) {
            if (i + k - 1 < cs.length) cs = reverse(cs, i, i + k - 1);
            else cs = reverse(cs, i, cs.length - 1);
            i = i + 2 * k;
        }
        return new String(cs);
    }

    public char[] reverse(char[] cs, int start, int end) {
        while (start < end) {
            char temp = cs[start];
            cs[start++] = cs[end];
            cs[end--] = temp;
        }
        return cs;
    }


}
