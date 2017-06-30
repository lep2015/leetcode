package org.lep.leetcode.regularexpressionmatching;

/**
 * Source : https://oj.leetcode.com/problems/regular-expression-matching/
 *
 * Created by lverpeng on 2017/6/30.
 *
 * * Implement regular expression matching with support for '.' and '*'.
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 *
 * The matching should cover the entire input string (not partial).
 *
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 *
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "a*") → true
 * isMatch("aa", ".*") → true
 * isMatch("ab", ".*") → true
 * isMatch("aab", "c*a*b") → true
 *
 */
public class RegularExpressMatching {


    /**
     * . 匹配任意字符
     * * 匹配前一个字符0次或者多次
     *
     * 如果pattern为空，str也为空，返回true，否则返回false
     * 如果pattern的长度为1，str的长度也为1，两个字符相同或者pattern为 '.' 则返回true，否则返回false
     * 如果pattern第二个字符为'*' ，s长度为空返回false，否则，如果第一个字符相同或者p的第一个为 '.' 则递归比较s.subString(1) p.subString(1)，否则返回false
     * 如果pattern第二个字符为 '*' ，如果s不为空并且s和p第一个字符相同的时候：
     *      匹配零次：递归比较s和p.subString(2)，如果匹配成功返回true
     *      匹配多次：将s向前移动一个字符进行匹配
     * 如果s为空或者s、p第一个字符不匹配，递归匹配s和p.subString(2)
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch (String s, String p) {
        if (p.length() ==0) {
            return s.length() == 0;
        }
        if (p.length() == 1) {
            if (s.length() == 1 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
                return true;
            }
            return false;
        }
        if (p.charAt(1) != '*') {
            if (s.length() == 0) {
                return false;
            }
            return p.charAt(0) == '.' || p.charAt(0) == s.charAt(0) ? isMatch(s.substring(1), p.substring(1)) : false;
        }

        while (s.length() != 0 && (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0))) {
            if (isMatch(s, p.substring(2))) {
                return true;
            }
            s = s.substring(1);
        }
        return isMatch(s, p.substring(2));
    }

    public static void main(String[] args) {
        RegularExpressMatching regularExpressMatching = new RegularExpressMatching();
        System.out.println(regularExpressMatching.isMatch("aa","a"));
        System.out.println(regularExpressMatching.isMatch("aa","aa"));
        System.out.println(regularExpressMatching.isMatch("aaa","aa"));
        System.out.println(regularExpressMatching.isMatch("aa","a*"));
        System.out.println(regularExpressMatching.isMatch("aa",".*"));
        System.out.println(regularExpressMatching.isMatch("ab",".*"));
        System.out.println(regularExpressMatching.isMatch("aab","c*a*b"));
    }

}
