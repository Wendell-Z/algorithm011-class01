package week09;

public class P151_Reverse_Words_In_A_String {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        String[] words = s.split(" ");
        for (int i = words.length - 1; i >= 0; i--) {
            if (!words[i].equals("")) {
                if (i != words.length - 1) {
                    sb.append(" ");
                }
                sb.append(words[i]);
            }
        }
        return sb.toString();
    }
}
