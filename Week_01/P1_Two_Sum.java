package week01;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 两数之和
 *
 * @author Wendell
 * @Date 2020-6-28  20:17:25
 */
public class P1_Two_Sum {

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> container = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (container.containsKey(target - nums[i])) {
                return new int[]{i, container.get(target - nums[i])};
            }
            container.put(nums[i], i);
        }
        return null;
    }
}
