package org.lep.leetcode.validnumber;

import org.lep.leetcode.valiadparenthreses.ValiadParentheses;

/**
 *
 * Source : https://oj.leetcode.com/problems/valid-number/
 *
 * Created by lverpeng on 2017/7/21.
 *
 * Validate if a given string is numeric.
 *
 * Some examples:
 * "0" => true
 * "   0.1  " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 *
 * Note: It is intended for the problem statement to be ambiguous.
 *       You should gather all requirements up front before implementing one.
 *
 */
public class ValidNumber {

    /**
     * 判断一个字符串是不是一个合法的数字
     *
     * 重要的是要考虑多种情况
     * 空白：可以出现多次，可能出现在首尾、中间，先去掉首尾，中间如果出现则不是数字
     * 小数点：可以出现在开头（后面必须有数字）、中间（只能出现在e前面）、最后，但是不能不出现在E后面
     * E: 只能出现一次，前后必须有数字，
     * 正负号：可以出现两次，可以在开头出现，在E后出现
     *
     * @param str
     * @return
     */
    public boolean valid (String str) {
        int startIndex = 0;
        int endIndex = str.length() - 1;
        // 去除开头空白
        while (isWhiteSpace(str.charAt(startIndex))) {
            startIndex ++;
            if (startIndex > endIndex) {
                return false;
            }
        }
        // 去除末尾空白
        while (isWhiteSpace(str.charAt(endIndex))) {
            endIndex --;
            if (startIndex > endIndex) {
                return false;
            }
        }
        // 去除开头符号
        if (str.charAt(startIndex) == '+' || str.charAt(startIndex) == '-') {
            startIndex ++;
        }
        if (startIndex > endIndex) {
            return false;
        }
        int head = startIndex;
        boolean hasPoint = false;
        boolean hasE = false;
        while (startIndex <= endIndex) {
            char ch = str.charAt(startIndex);
            // 中间出现空白
            if (isWhiteSpace(ch)) {
                return false;
            }
            if (ch == '.') {
                // 小数点只出现一次，e后除了末尾不能出现小数(如果后不能紧接着出现小数点)，不能只有小数点
                if ((hasE && (startIndex != endIndex)) || (hasE && (startIndex != endIndex
                        || (startIndex > head && str.charAt(startIndex-1) == 'e')))
                        || (head == startIndex && startIndex == endIndex)) {
                    return false;
                }
                hasPoint = true;
            } else if (ch == 'e') {
                // 前后必须有数字
                if (hasE || startIndex == head || startIndex == endIndex) {
                    return false;
                }
                hasE = true;
            } else if (ch == '+' || ch == '-') {
                // 中间出现符号，必须紧跟在E后面，不能出现在最后，如果是倒数第二位，最后一位不能出现小数点
                if (!hasE || (str.charAt(startIndex-1) != 'e') || startIndex == endIndex
                        || (startIndex == endIndex - 1 && str.charAt(startIndex + 1) == '.')) {
                    return false;
                }
            } else if (!isNumber(ch)) {
                return false;
            }
            startIndex ++;

        }
        return true;
    }

    private boolean isNumber (char ch) {
        if (ch >= 48 && ch <= 57) {
            return true;
        }
        return false;
    }

    private boolean isWhiteSpace (char ch) {
        if (ch == ' ' || ch == '\t' || ch == '\r' || ch == '\n' || ch == '\f') {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        ValidNumber validNumber = new ValidNumber();
        System.out.println("true------>" + validNumber.valid("-123.0e-5."));
        System.out.println("false------>" + validNumber.valid("1e-. "));
        System.out.println("true------>" + validNumber.valid("-123.0e-5"));


        System.out.println("true------>" + validNumber.valid("1.044"));
        System.out.println("true------>" + validNumber.valid(" 1.044"));
        System.out.println("true------>" + validNumber.valid(" 1.044 "));
        System.out.println("false------>" + validNumber.valid(" 1. 044 "));
        System.out.println("false------>" + validNumber.valid(" 1.a "));
        System.out.println("true------>" + validNumber.valid(" 1. "));
        System.out.println("false------>" + validNumber.valid(" abc "));
        System.out.println("false------>" + validNumber.valid("e "));
        System.out.println("false------>" + validNumber.valid("1e. "));
        System.out.println("true------>" + validNumber.valid("+123.0"));
        System.out.println("true------>" + validNumber.valid("-123.0"));
        System.out.println("true------>" + validNumber.valid("-123.0e5"));
        System.out.println("true------>" + validNumber.valid("0"));
        System.out.println("true------>" + validNumber.valid("0.1"));
        System.out.println("true------>" + validNumber.valid(".1"));
        System.out.println("true------>" + validNumber.valid(".1e1"));
        System.out.println("true------>" + validNumber.valid("2e10"));

    }
}
