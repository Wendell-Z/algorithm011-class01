package week01;

/**
 * PlusOne
 * @author Wendell
 * @Date 2020年6月23日14:58:29
 */
public class P66_Plus_One {
    /**
     * my way
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        if (digits[digits.length - 1] < 9) {
            digits[digits.length - 1]++;
            return digits;
        }
        int i;
        for (i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                if (i > 0) {
                    digits[i] = 0;
                    continue;
                } else {
                    int[] a = new int[digits.length + 1];
                    a[0] = 1;
                    return a;
                }
            }
            digits[i]++;
            break;
        }
        return digits;
    }

    /**
     * most votes
     * @param digits
     * @return
     */
    public int[] _plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] result = new int[n + 1];
        result[0] = 1;
        return result;
    }
}
