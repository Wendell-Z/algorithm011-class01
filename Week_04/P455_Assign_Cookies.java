package week04;

import java.util.Arrays;

public class P455_Assign_Cookies {

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = s.length - 1;
        int j = g.length - 1;
        while (i >= 0 && j >= 0) {
            if (s[i] >= g[j]) {
                i--;
                j--;
            } else {
                j--;
            }
        }
        return s.length - 1 - i;
    }


}
