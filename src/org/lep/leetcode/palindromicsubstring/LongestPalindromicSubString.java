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
     * 动态规划：把原问题分解为相对简单的子问题 求解复杂问题的方法
     * 记忆化的求解递推式
     * 1. 求出所有子字符串，遍历字符串，每次遍历，找到0-i之间所有的子字符串，针对每个子字符串判断是不是回文字符串
     * 2. 针对0-i之间的字符串，从i一个字符开始计算，然后i-1,i
     * i
     * i-1, i
     * i-2, i-1, i
     * i- 3, i-2, i-1, i
     * 总结得出：如果p(j, i)是一个回文字符串的话，那么p(j+1, i-1)一定是
     * 那么递推式就是：p(j, i) <= str[j] == str[i] && p(j+1, i-1)，p(x,y)str[x]-str[y]之间的字符串是回文字符串
     * 在上面的计算中，计算p(j, i)的时候，已经计算过p(j+1, i-1)，那么就可以把p(j+1, i-1)缓存起来，不需要重复计算
     *
     * 动态规划解题方法：
     * 分解子问题，找到状态转移方程
     *
     * 概念：
     * 状态：表示问题的中间结果
     * 阶段：同时可能有多个中间结果
     * 决策：一个状态转移到另一个状态，往往通过决策来进行，有决策就会有状态转移
     * 无后效性：一旦某一个状态确定之后，它之前的状态无法对他之后的状态产生影响
     * 最优子结构：最优解包含的子问题的解也是最优的，就称该问题具有最优子结构，也就是满足最优化原理
     *
     * 动态规划适用范围：
     * 最优子结构
     * 无后效性
     * 子问题的重叠性质
     *
     * 与贪心算法的区别：
     * 贪心算法：采取当前状态下最好的选择，以期导致结果是最好的
     * 贪心算法对每个子结果做出选择，不能回退，也就是只依赖前一个结果
     * 动态规划会依赖原来所有的结果，需要保存原来的结果，根绝原来的结果对当前结果进行选择，有回退功能
     *
     *
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
                if (i == j || (str.charAt(i) == str.charAt(j) && (i - j < 2 || table[j+1][i-1]))) {
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
