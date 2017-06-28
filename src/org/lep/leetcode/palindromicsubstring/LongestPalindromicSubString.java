package org.lep.leetcode.palindromicsubstring;

import java.util.Arrays;

/**
 * Source : https://oj.leetcode.com/problems/longest-palindromic-substring/
 *
 * Created by lverpeng on 2017/6/28.
 *
 *
 * Given a string S, find the longest palindromic substring in S.
 * You may assume that the maximum length of S is 1000,
 * and there exists one unique longest palindromic substring.
 *
 */
public class LongestPalindromicSubString {
    /**
     * 给定一个字符串，从给定位置开始向两边找出其中的回文字符串
     *
     * @param str
     * @param left
     * @param right
     * @return
     */
    public String findPalindrome (String str, int left, int right) {
       int n = str.length();
       while (left >= 0 && right <= n - 1 && str.charAt(left) == str.charAt(right)) {
           left --;
           right ++;
       }
       return str.substring(left + 1, right);
    }

    /**
     * 依次循环字符串的每一个元素，以钙元素为中心寻找最长的回文字符串
     * 分奇数、偶数两种情况考虑
     *
     * @param str
     * @return
     */
    public String longestPalindromicStrByForce (String str) {
        String lonestStr = "";
        for (int i = 0; i < str.length(); i++) {
            // 奇数的情况
            String subStr = findPalindrome(str, i, i);
            if (lonestStr.length() < subStr.length()) {
                lonestStr = subStr;
            }
            subStr = findPalindrome(str, i, i+1);
            if (lonestStr.length() < subStr.length()) {
                lonestStr = subStr;
            }
        }
        return lonestStr;
    }

    public boolean isPalindromicStr (String str) {
        int length = str.length();
        if (length % 2 == 0) {
            return findPalindrome(str, length / 2 - 1, length / 2).length() == length;
        }
        return findPalindrome(str, length / 2, length / 2).length() == length;
    }

    /**
     * 起始位置为i，依次判断i-j（j去从length到i之间的数，这样背刺只要找到一个就是，从i开始的最长的会问字符串了，直接跳出本次循环）之间的字符串是不是回文字符串，如果是的话和目前最长的会问字符串比较，找到新的最长
     *
     * @param str
     * @return
     */
    public String longestPalindromicStrByForce1 (String str) {
        String longestStr = "";
        for (int i = 0; i < str.length(); i++) {
            for (int j = str.length() - 1; j >= 0; j--) {
                String subStr = str.substring(i, j);
                if (isPalindromicStr(subStr)) {
                    if (longestStr.length() < subStr.length()) {
                        longestStr = subStr;
                    }
                    break;
                }
            }
        }
        return longestStr;
    }

    /**
     * 使用动态规划来计算
     * 上面使用暴力法1的时候，在计算i-j的时候已经判断过i--, j++，所以可以把i--, j++保存下来，下次就不用重新计算判断了
     * 
     * @param str
     * @return
     */
    public String longestPalindromicStrByDp (String str) {
        String longestStr = "";
        boolean[][] table = new boolean[str.length()][str.length()];
        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j >= 0; j--) {
                // i == j 说明j-i之间只有j、i两个元素，一定是回文的，true
                // i、j两个未位置的字符相等，j++、i--两个字符也相同，并且i- j < 2（这个时候只有j、i两个位置的字符），
                // 或者是j++, i__两个位置的字符也相同（j++，i--前面已经计算过，每次保证两个位置和向内两个未知的字符分别相同，
                // 那么最后一定是回文字符串）
                if (i == j || (str.charAt(i) == str.charAt(j) && (i - j < 2 || table[i-1][j+1]))) {
                    table[i][j] = true;
                    if (longestStr.length() < i - j + 1) {
                        longestStr = str.substring(j, i + 1);
                    }
                }
            }
        }
        for (int i = 0; i < table[0].length; i++) {
            System.out.println(Arrays.toString(table[i]));
        }
        return longestStr;
    }

    public static void main(String[] args) {
        LongestPalindromicSubString longestPalindromicSubString = new LongestPalindromicSubString();
        String s = "abacdfgdcaba";
        System.out.println(longestPalindromicSubString.longestPalindromicStrByForce(s));
        System.out.println(longestPalindromicSubString.longestPalindromicStrByForce(s));
        System.out.println(longestPalindromicSubString.longestPalindromicStrByDp(s));

    }
}
