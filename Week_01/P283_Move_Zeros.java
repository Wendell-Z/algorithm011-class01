package week01;

import java.util.Arrays;

/**
 * move zeros
 *
 * @author Wendell
 * @Date 2020-6-26  20:20:44
 */
public class P283_Move_Zeros {

    // 我的实现
    public void moveZeroes(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[count] = nums[i];
                count++;
            }
        }
        for (int i = count; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    //一次循环实现
    public void singleLoop(int[] nums) {
        int mark = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (mark < i) {
                    nums[mark] = nums[i];
                    nums[i] = 0;
                }
                mark++;
            }
        }
    }


    public static void main(String[] args) {
        int[] a = {0, 1, 0, 3, 12};
        P283_Move_Zeros p = new P283_Move_Zeros();
        //p.moveZeroes(a);
        p.singleLoop(a);
        System.out.println(Arrays.toString(a));
    }
}
