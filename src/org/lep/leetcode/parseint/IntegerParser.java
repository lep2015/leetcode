package org.lep.leetcode.parseint;

/**
 * Source : https://oj.leetcode.com/problems/string-to-integer-atoi/
 *
 * Created by lverpeng on 2017/7/4.
 *
 * Implement atoi to convert a string to an integer.
 *
 * Hint: Carefully consider all possible input cases. If you want a challenge,
 *       please do not see below and ask yourself what are the possible input cases.
 *
 * Notes:
 *   It is intended for this problem to be specified vaguely (ie, no given input specs).
 *   You are responsible to gather all the input requirements up front.
 *
 *
 * Requirements for atoi:
 *
 * The function first discards as many whitespace characters as necessary until the first
 * non-whitespace character is found. Then, starting from this character, takes an optional
 * initial plus or minus sign followed by as many numerical digits as possible, and interprets
 * them as a numerical value.
 *
 * The string can contain additional characters after those that form the integral number,
 * which are ignored and have no effect on the behavior of this function.
 *
 * If the first sequence of non-whitespace characters in str is not a valid integral number,
 * or if no such sequence exists because either str is empty or it contains only whitespace
 * characters, no conversion is performed.
 *
 * If no valid conversion could be performed, a zero value is returned. If the correct value
 * is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648)
 * is returned.
 *
 */
public class IntegerParser {

    /**
     * 将字符串转化为int
     * 考虑各种输入：
     * 输入包含非数字
     * 超出int范围
     * 可能是负数
     *
     * @param str
     * @return
     */
    public int parse (String str) throws Exception {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int result = 0;
        boolean flag = true;
        if (str.charAt(0) == '-') {
            flag = false;
            str = str.substring(1);
        } else if (str.charAt(0) == '+') {
            flag = true;
            str = str.substring(1);
        }
        for (int i = 0; i < str.length(); i++) {
            if (!isdDigit(str.charAt(i))) {
                throw new IllegalArgumentException("is not a number");
            }
            if (result > Integer.MAX_VALUE / 10 || result < Integer.MIN_VALUE / 10) {
                throw new Exception("overflow");
            }
            result = result * 10 + str.charAt(i) - 48;
        }
        if (!flag) {
            result = 0 - result;
        }
        return result;
    }

    private boolean isdDigit (char c) {
        if (c >= 48 && c <= 57) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        IntegerParser parser = new IntegerParser();
        System.out.println(parser.parse("123"));
        System.out.println(parser.parse("+123"));
        System.out.println(parser.parse("-123"));
        System.out.println(parser.parse("123ABC"));
    }
}
