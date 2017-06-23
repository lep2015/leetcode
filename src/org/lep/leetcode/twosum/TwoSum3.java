package org.lep.leetcode.twosum;

import java.util.HashMap;
import java.util.Map;

/**
 * Source : https://oj.leetcode.com/problems/two-sum-iii-data-structure-design/
 *
 * Created by lverpeng on 2017/6/23.
 *
 *
 * Design and implement a TwoSum class. It should support the following operations: add and find.
 *
 * add - Add the number to an internal data structure.
 * find - Find if there exists any pair of numbers which sum is equal to the value.
 *
 * For example,
 *
 *   add(1); add(3); add(5);
 *   find(4) -> true
 *   find(7) -> false
 *
 */
public class TwoSum3 {
    private Map<Integer, Integer> nums = new HashMap<Integer, Integer>();

    /**
     * 这个题目里面的数组不再是给定的，而是输入的，而且数组元素可能是重复的，因为还是查找，所以可以用hash，
     * add的数字为key，value为该可以重复出现的次数
     *
     * @param num
     */
    public void add (int num) {
        int count = 0;
        if (nums.containsKey(num)) {
            count = nums.get(num);
        }
        nums.put(num, count + 1);
    }

    /**
     * 因为元素可能存在重复，所以要分两种情况考虑：
     * 1. 正好两个相等的数的和为指定的数
     * 2. 两个加数不相等，但是hash表中包含第二个加数
     * 上面两种情况是能找到的，其他情况找不到指定的value
     *
     * @param value
     * @return
     */
    public boolean find (int value) {
       int one = 0;
       int two = 0;
       for (Integer num : nums.keySet()) {
           one = num;
           two = value - one;
           if ((one == two && nums.get(num) > 1) || (one != two && nums.containsKey(two))) {
                return true;
           }
       }
       return false;
    }

    public static void main(String[] args) {
        TwoSum3 twoSum3 = new TwoSum3();
        twoSum3.add(1);
        twoSum3.add(3);
        twoSum3.add(5);
        System.out.println(twoSum3.find(4));
        System.out.println(twoSum3.find(7));
    }
}
