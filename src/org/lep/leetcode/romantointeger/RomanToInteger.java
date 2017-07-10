package org.lep.leetcode.romantointeger;

import java.util.HashMap;
import java.util.Map;

/**
 * Source : https://oj.leetcode.com/problems/roman-to-integer/
 *
 * Created by lverpeng on 2017/7/10.
 *
 * Given a roman numeral, convert it to an integer.
 * Input is guaranteed to be within the range from 1 to 3999.
 *
 */
public class RomanToInteger {
    private static final Map<Character, Integer> map = new HashMap<Character, Integer>(){
        {
            put('V', 5);
            put('L', 50);
            put('I', 1);
            put('D', 500);
            put('X', 10);
            put('M', 1000);
            put('C', 100);
        }
    };

    /**
     * 较小的数在较大的数的左边则结果为：较大的数减去较小的数
     * 较大的数在较小的数的右边则结果为：较大的数和较小的数的和
     * 从第二个字符开始依次遍历每一个字符:
     *  先加上第一个字符的值（后面会判断如果右边的数大于该值，则会减去）
     *  将当前数与前一个数进行比较
     *      如果当前数更大，则加上该数
     *      否则，减去前面一个数，再减去两个数共同表示的数：较大的减去较小的
     *
     * @param numStr
     * @return
     */
    public int romanToInteger (String numStr) {
        int result =  map.get(numStr.charAt(0));
        for (int i = 1; i < numStr.length(); i++) {
            int pre = map.get(numStr.charAt(i - 1));
            int current = map.get(numStr.charAt(i));
            if (pre >= current) {
                result += current;
            } else {
                result = result - pre + (current - pre);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        RomanToInteger romanToInteger = new RomanToInteger();
        System.out.println(romanToInteger.romanToInteger("M") + "------1000" );
        System.out.println(romanToInteger.romanToInteger("MXXXVIII") + "------1038");
        System.out.println(romanToInteger.romanToInteger("II") + "------2");
        System.out.println(romanToInteger.romanToInteger("III") + "------3");
        System.out.println(romanToInteger.romanToInteger("IV") + "------4");
        System.out.println(romanToInteger.romanToInteger("MMMXCIV") + "------3094");
    }
}
