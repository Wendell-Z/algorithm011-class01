package week01;

import java.util.Arrays;

/**
 * 旋转数组
 *
 * @author Wndell
 * @Date 2020-6-27  1:04:22
 */
public class P189_Rotate_Array {

    // 我的实现 暴力
    public void rotate(int[] nums, int k) {
        int move = k % nums.length;
        boolean moveFlag = false;
        if (move > k >> 1) {
            //左移
            move = nums.length - move;
            moveFlag = true;
        }
        if (moveFlag) {
            //左移
            for (int i = 0; i < move; i++) {
                int temp = nums[0];
                for (int j = 1; j < nums.length; j++) {
                    nums[j - 1] = nums[j];
                }
                nums[nums.length - 1] = temp;
            }
        } else {
            //右移
            for (int i = nums.length - 1; i >= nums.length - move; i--) {
                int temp = nums[nums.length - 1];
                for (int j = nums.length - 1 - 1; j >= 0; j--) {
                    nums[j + 1] = nums[j];
                }
                nums[0] = temp;
            }
        }

    }

    // 官网思路实现
    public void tempChange(int[] nums, int k) {
        k = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }


    }

    // 官网思路实现 n个元素总共被反转了3次
    public void reverseArr(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
        reverse(nums, 0, k - 1);
        System.out.println(Arrays.toString(nums));
        reverse(nums, k, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
