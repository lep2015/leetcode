package org.lep.leetcode.twosum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * source:https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/#/description
 * Created by lverpeng on 2017/6/21.
 *
 * question:
 *
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 *
 * The function twoSum should return indices of the two numbers such that they add up to the target,
 * where index1 must be less than index2. Please note that your returned answers (both index1 and index2)
 * are not zero-based.
 *
 * You may assume that each input would have exactly one solution.
 *
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 *
 */
public class TwoSum {
    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        int[] numbers = {2, 7, 11, 15};
        System.out.println(Arrays.toString(twoSum.twoSum(numbers, 9)));
        System.out.println(Arrays.toString(twoSum.twoSumUseHash(numbers, 9)));
    }

    /**
     * 最简单的方法对于数组中每一个数，依次遍历其后每一个数字，直到找到两个和为target的数字
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum (int[] numbers, int target) {
        int[] result = new int[2];
        for (int i = 0; i < numbers.length; i ++) {
            for (int j = i; j < numbers.length; j++) {
                if ((numbers[i] + numbers[j]) == target) {
                    result[0] = i + 1;
                    result[1] = j + 1;
                    return result;
                }
            }
        }
        return result;
    }

    /**
     * 由于上面第二次循环只是在查找一个数，那么就可以使用hash来查找，将第一个对应的另一个加数依次添加到hash表里，降低时间复杂度
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSumUseHash (int[] numbers, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> needOtherNum = new HashMap<Integer, Integer>(); // <num, index>
        for (int i = 0; i < numbers.length; i++) {
            // 如果当前num是前面数字所需要的另外一个加数，说明找到了
            if (needOtherNum.containsKey(numbers[i])) {
                result[0] = needOtherNum.get(numbers[i]) + 1;
                result[1] = i + 1;
                break;
            } else {
                // 如果没有找到，则把当前数字所需要的另外一个加数添加到map中
                needOtherNum.put(target - numbers[i], i);
            }
        }
        return result;
    }
}
