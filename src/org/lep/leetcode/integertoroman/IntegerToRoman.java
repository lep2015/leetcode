package org.lep.leetcode.integertoroman;

import org.lep.leetcode.parseint.IntegerParser;

/**
 * Source : https://oj.leetcode.com/problems/integer-to-roman/
 *
 * Created by lverpeng on 2017/7/10.
 *
 * Given an integer, convert it to a roman numeral.
 *
 * Input is guaranteed to be within the range from 1 to 3999.
 *
 */
public class IntegerToRoman {
    private static final int[] value = new int[] {1000,900,500,400, 100, 90,  50, 40,  10, 9,   5,  4,   1};
    private static final String[] symbol = new String[] {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

    /**
     * 罗马数字只有1、5、10、50、100、500、1000，将integer从左到右循环，num大于当前的罗马符号的时候减1，直到小于当前罗马符号的时候进行下一次循环
     * 特殊情况，4、9、40、90、400、900也需要加入符号表考虑
     *
     * @param num
     * @return
     */
    public String integerToRoamn (int num) {
        if (num < 1 || num > 3999) {
            return null;
        }
        String roman = "";
        for (int i = 0; num != 0; i++ ) {
            while (num >= value[i]) {
                num = num - value[i];
                roman += symbol[i];
            }
        }
        return roman;
    }

    public static void main(String[] args) {
        IntegerToRoman integerToRoman = new IntegerToRoman();
        System.out.println(integerToRoman.integerToRoamn(1000) + "------M");
        System.out.println(integerToRoman.integerToRoamn(1038) + "------MXXXVIII");
        System.out.println(integerToRoman.integerToRoamn(1) + "------I");
        System.out.println(integerToRoman.integerToRoamn(2) + "------II");
        System.out.println(integerToRoman.integerToRoamn(3) + "------III");
        System.out.println(integerToRoman.integerToRoamn(4) + "------IV");
        System.out.println(integerToRoman.integerToRoamn(3094) + "------MMMXCIV");
    }

}
