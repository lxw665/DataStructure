package 数据结构.堆;

/**
 * @author lxw
 * @date 2022/7/15 10:34
 */
public class MyUtils {
    public static int[] getNums(int size) {
        int[] nums = new int[size];
        for (int i  = 0; i < nums.length; i++) {
            nums[i] = (int) (Math.random() * 100);
        }
        return nums;
    }
}
