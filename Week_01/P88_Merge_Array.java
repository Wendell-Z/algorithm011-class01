package week01;

import java.util.Arrays;

/**
 * 合并有序数组
 *
 * @author Wendell
 * @Date 2020-6-28  21:25:35
 */
public class P88_Merge_Array {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int end1 = m - 1;
        int end2 = n - 1;
        int index = m + n - 1;
        while (index >= 0) {
            if (end1 < 0 && end2 < 0) break;
            if (end1 < 0) {
                nums1[index--] = nums2[end2--];
                continue;
            }
            if (end2 < 0) {
                nums1[index--] = nums1[end1--];
                continue;
            }
            if (nums1[end1] > nums2[end2]) {
                nums1[index--] = nums1[end1--];
            } else {
                nums1[index--] = nums2[end2--];
            }
        }
    }

    public static void main(String[] args) {
        P88_Merge_Array p = new P88_Merge_Array();
        int[] a = {1, 2, 3, 0, 0, 0};
        int[] b = {2, 5, 6};
        p.merge(a, 3, b, 3);
        System.out.println(Arrays.toString(a));
    }
}
