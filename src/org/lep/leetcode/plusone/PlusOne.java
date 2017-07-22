package org.lep.leetcode.plusone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Source : https://oj.leetcode.com/problems/plus-one/
 *
 * Created by lverpeng on 2017/7/22.
 *
 * Given a non-negative number represented as an array of digits, plus one to the number.
 *
 * The digits are stored such that the most significant digit is at the head of the list.
 *
 */
public class PlusOne {

    /**
     * 加法运算，注意进位
     *
     * @param digit
     * @return
     */
    public Integer[] plusOne (int[] digit) {
        int carry = 1;
        List<Integer> result = new ArrayList<Integer>();
        for (int i = digit.length - 1; i > -1; i--) {
            carry += digit[i];
            result.add(0, carry % 10);
            carry = carry / 10;
        }
        if (carry > 0) {
            result.add(0, carry);
        }
        return result.toArray(new Integer[result.size()]);
    }


    public static void main(String[] args) {
        PlusOne plusOne = new PlusOne();
        int[] arr = new int[]{1,2,3};
        int[] arr1 = new int[]{1,9,9};
        int[] arr2 = new int[]{9,9,9};
        int[] arr3 = new int[]{1};
        int[] arr4 = new int[]{9};
        int[] arr5 = new int[]{};

        System.out.println(Arrays.toString(plusOne.plusOne(arr)));
        System.out.println(Arrays.toString(plusOne.plusOne(arr1)));
        System.out.println(Arrays.toString(plusOne.plusOne(arr2)));
        System.out.println(Arrays.toString(plusOne.plusOne(arr3)));
        System.out.println(Arrays.toString(plusOne.plusOne(arr4)));
        System.out.println(Arrays.toString(plusOne.plusOne(arr5)));
    }
}
