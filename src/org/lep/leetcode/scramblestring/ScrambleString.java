package org.lep.leetcode.scramblestring;

import java.util.Arrays;

/**
 * Source : https://oj.leetcode.com/problems/scramble-string/
 *
 * Created by lverpeng on 2017/8/2.
 *
 * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
 *
 * Below is one possible representation of s1 = "great":
 *
 *     great
 *    /    \
 *   gr    eat
 *  / \    /  \
 * g   r  e   at
 *            / \
 *           a   t
 *
 * To scramble the string, we may choose any non-leaf node and swap its two children.
 *
 * For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
 *
 *     rgeat
 *    /    \
 *   rg    eat
 *  / \    /  \
 * r   g  e   at
 *            / \
 *           a   t
 *
 * We say that "rgeat" is a scrambled string of "great".
 *
 * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
 *
 *     rgtae
 *    /    \
 *   rg    tae
 *  / \    /  \
 * r   g  ta  e
 *        / \
 *       t   a
 *
 * We say that "rgtae" is a scrambled string of "great".
 *
 * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 *
 */
public class ScrambleString {

    /**
     * s1是不是s2的一个scramblestring
     * s1按照任意位置进行二分划分，一直递归下去，期间，可以交换非叶子节点的两个子节点左右顺序，一直到叶子节点
     *
     * 一开始想着是找到s1的所有scramblestring，然后判断s2是否在里面
     * 但是其实在寻找s2的scramblestring的时候就可以和s2进行对比判断，而不需要存储所有的scramblestring
     *
     * 选择
     * s1分割的位置，递归的进行如下判断
     * s1在i左边的子串和s2在i左边的子串是scramble的，s1在i的右边的子串和s2在i右边的子串是scramble的，或者
     * s1在i左边的子串和s2在i右边的子串是scramble的，s1在i的右边的子串和s2在i左边的子串是scramble的
     *
     * @param s1
     * @param s2
     */
    public boolean scramble (String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.length() <= 1) {
            return s1.equals(s2);
        }
//        return recursion(s1, s2);
        return recursion1(s1, s2);
    }

    public boolean recursion (String s1, String s2) {
        int len = s1.length();
        if (len == 1) {
            return s1.equals(s2);
        }
        for (int i = 1; i < len; i++) {
            if ((recursion(s1.substring(0, i), s2.substring(0, i)) && recursion(s1.substring(i), s2.substring(i)))
                    || (recursion(s1.substring(0,i), s2.substring(len-i)) && recursion(s1.substring(i), s2.substring(0,len-i)))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 递归的时候有些分支是不必要的，可以剪裁分支
     * 在递归的时候，对s1和s2进行排序，如果排序之后两个字符串不相等则不必要继续递归
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean recursion1 (String s1, String s2) {
        int len = s1.length();
        if (len == 1) {
            return s1.equals(s2);
        }
        char[] s1CharArr = s1.toCharArray();
        Arrays.sort(s1CharArr);
        String sortedS1 = new String(s1CharArr);
        char[] s2CharArr = s2.toCharArray();
        Arrays.sort(s2CharArr);
        String sortedS2 = new String(s1CharArr);
        if (!sortedS1.equals(sortedS2)) {
            return false;
        }

        for (int i = 1; i < len; i++) {
            if ((recursion(s1.substring(0, i), s2.substring(0, i)) && recursion(s1.substring(i), s2.substring(i)))
                    || (recursion(s1.substring(0,i), s2.substring(len-i)) && recursion(s1.substring(i), s2.substring(0,len-i)))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 递归的时候会有一些重复计算，使用数组记录计算过的结果，每次递归的时候判断，如果已经计算过则直接使用计算过的结果
     * 中间结果需要一个三维的boolean数组，因为，每次计算结果的变量是s1.index1,s2.index2,还有当前字符串的长度len
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean scramble2 (String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.length() <= 1) {
            return s1.equals(s2);
        }
        int[][][] calculated = new int[s1.length()][s2.length()][s1.length()];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                Arrays.fill(calculated[i][j], -1);
            }
        }
        return recursion(s1, s2);
    }

    public boolean recursion2 (String s1, int index1, String s2, int index2, int len, int[][][] calculated) {
        if (len == 1) {
            return s1.charAt(index1) == s2.charAt(index2);
        }
        int preresult = calculated[index1][index1][len-1];
        if (preresult != -1) {
            return preresult == 1;
        }
        preresult = 0;
        for (int i = 1; i < len; i++) {
            if (recursion2(s1, index1, s2, index2, i, calculated)
                    && recursion2(s1, index1 + 1, s2, index2 + 1, len - i, calculated)) {
                preresult = 1;
                break;
            }
            if (recursion2(s1, index1, s2, index2 + len - i, i,  calculated)
                    && recursion2(s1, index1 + 1, s2, index2, len - i, calculated)) {
                preresult = 1;
                break;
            }
        }
        calculated[index1][index2][len-1] = preresult;
        return preresult == 1;
    }




    public static void main(String[] args) {
        ScrambleString scrambleString = new ScrambleString();
        System.out.println(scrambleString.scramble("great", "rgtae"));
        System.out.println(scrambleString.scramble2("great", "rgtae"));
    }
}
