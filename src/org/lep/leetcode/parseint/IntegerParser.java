package org.lep.leetcode.parseint;

/**
 * Created by lverpeng on 2017/7/4.
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
