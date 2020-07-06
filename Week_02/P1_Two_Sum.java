package week02;

import java.util.HashMap;

/**
 * @author Wendell
 * @Date 2020-6-30  11:01:10
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
