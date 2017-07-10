package org.lep.leetcode.longestcommonprefix;

/**
 * Source : https://oj.leetcode.com/problems/longest-common-prefix/
 *
 * Created by lverpeng on 2017/7/10.
 *
 * Write a function to find the longest common prefix string amongst an array of strings.
 */
public class LongestCommonPrefix {

    /**
     * 依次比较每个字符串的每个字符是否相同
     *
     *
     * @param strArr
     * @return
     */
    public String findLongestPrefix (String[] strArr) {
        String prefix = "";
        for (int i = 0; i < strArr[0].length(); i++) {
            boolean equal = true;
            for (int j = 0; j < strArr.length; j++) {
                if (i >= strArr[j].length()) {
                    equal = false;
                    break;
                }
                if (j == 0) {
                    continue;
                }
                if (strArr[j].charAt(i) != strArr[j - 1].charAt(i)) {
                    equal = false;
                }
            }
            if (!equal) {
                break;
            } else {
                prefix += strArr[0].charAt(i);
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
        String[] strArr = new String[]{"abc", "a", "abcd"};
        System.out.println("a-------" + longestCommonPrefix.findLongestPrefix(strArr));

        strArr = new String[]{"abcsdfg", "abc", "abcdasdf"};
        System.out.println("abc-------" + longestCommonPrefix.findLongestPrefix(strArr));
    }
}
