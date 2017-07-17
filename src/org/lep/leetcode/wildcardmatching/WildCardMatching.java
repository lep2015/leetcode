package org.lep.leetcode.wildcardmatching;

/**
 * Source : https://oj.leetcode.com/problems/wildcard-matching/
 *
 * Created by lverpeng on 2017/7/17.
 *
 * Implement wildcard pattern matching with support for '?' and '*'.
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
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
 * isMatch("aa", "*") → true
 * isMatch("aa", "a*") → true
 * isMatch("ab", "?*") → true
 * isMatch("aab", "c*a*b") → false
 *
 */
public class WildCardMatching {

    /**
     * '?'：匹配任意一个字符
     * '*'：匹配任意多个字符
     *
     * 两个字符串从头开始比较
     * 如果pattern是'*'则记录此时的str为lastStr（相当于做了标记），pattern跳过'*'
     * 如果该位置的字符相同或者pattern的字符是'?'则比较下一个
     * 如果不是上面两种情况，则判断lasStr是否是空（其实也是判断pattern上一个字符是否为'*'），如果不为空则说明pattern上一个字符是'*'，
     *          可以匹配任意字符，将str向后移动一位
     * 上面条件都不满足，说明不匹配
     *
     * 如果str长度小于pattern，判断pattern剩下的是不是'*'，如果不是则匹配失败，否则成功
     *
     * @param str
     * @param pattern
     * @return
     */
    public boolean isMatch (String str, String pattern) {
        String lastStr = "";
        String lastPattern = "";
        while (!str.isEmpty()) {
            if (pattern.isEmpty()) {
                return false;
            }
            if (pattern.charAt(0) == '*') {
                pattern = pattern.substring(1);
                if (pattern.isEmpty()) {
                    return true;
                }
                lastPattern = pattern;
                lastStr = str;
            } else if (pattern.charAt(0) == '?' || pattern.charAt(0) == str.charAt(0)) {
                pattern = pattern.substring(1);
                str = str.substring(1);
            } else if (!lastPattern.isEmpty()) {
                pattern = lastPattern;
                str = lastStr.substring(1);
            } else {
                return false;
            }
        }

        while (!pattern.isEmpty() && pattern.charAt(0) == '*') {
            pattern = pattern.substring(1);
        }
        return pattern.isEmpty();

    }

    public static void main(String[] args) {
        WildCardMatching wildCardMatching = new WildCardMatching();
        System.out.println("true------->" + wildCardMatching.isMatch("aabb","aa*ba"));
        System.out.println("false------->" + wildCardMatching.isMatch("aa","a"));
        System.out.println("true-------->" + wildCardMatching.isMatch("aa","aa"));
        System.out.println("false------->" + wildCardMatching.isMatch("aaa","aa"));
        System.out.println("true-------->" + wildCardMatching.isMatch("aa","*"));
        System.out.println("true-------->" + wildCardMatching.isMatch("aa","a*"));
        System.out.println("true-------->" + wildCardMatching.isMatch("ab","?*"));
        System.out.println("false------->" + wildCardMatching.isMatch("aab","c*a*b"));
        System.out.println("true------->" + wildCardMatching.isMatch("aabbb","aa*"));
    }
}
