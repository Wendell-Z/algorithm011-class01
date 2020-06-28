package week01;

/**
 * 删除排序数组中的重复项
 *
 * @author Wnendell
 * @Date 2020-6-26  23:43:11
 */
public class P26_Remove_Duplicates {

    // 我的实现 类似与双指针 执行效率相同
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int now = nums[0];
        int index = 1;
        for (int i = 1; i < nums.length; i++) {
            if (now < nums[i]) {
                now = nums[i];
                nums[index] = now;
                index++;
            }
        }
        return index;
    }

    // 官网题解 双指针
    public int useDoublePointer(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    // 测试用例
    public static void main(String[] args) {
        int[] a = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        P26_Remove_Duplicates p = new P26_Remove_Duplicates();
        System.out.println(p.removeDuplicates(a));
        for (Integer i : a) {
            System.out.print(i + " ");
        }
    }


}
