package org.lep.leetcode.minimumwindowsubstring;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Source : https://oj.leetcode.com/problems/minimum-window-substring/
 *
 * Created by lverpeng on 2017/7/27.
 *
 * Given a string S and a string T, find the minimum window in S which will
 * contain all the characters in T in complexity O(n).
 *
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 *
 * Minimum window is "BANC".
 *
 * Note:
 *
 * > If there is no such window in S that covers all characters in T,
 *   return the emtpy string "".
 *
 * > If there are multiple such windows, you are guaranteed that there
 *   will always be only one unique minimum window in S.
 *
 */
public class MinimumWindowSubstring {

    /**
     * 寻找S中包含T的最短字符串
     *
     * 窗口的方法，
     * 先将T中所有字符保存在hash表中，值为每个字符出现的次数
     * 从头开始遍历，判断该字符是否在hash表中，如果在，则将该字符对应的值减1，并记录此时已包含T中字符总数
     * 如果字符总数等于T的长度说明找到一个子串，找到第一个包含T的字符串，然后将此时子串的长度和之前最小长度比较，更新最小字串的长度，并记录此时子串的起始位置
     * 然后移动子串的左边界，略过不再T中的字符
     * 当遍历完成的时候，记录的最小字串的起始位置和最小字串的长度可以得到包含T的最小字串
     *
     * @param S
     * @param T
     * @return
     */
    public String findMinimumWindowSubString (String S, String T) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        // 初始化hash表
        for (int i = 0; i < T.length(); i++) {
            if (map.keySet().contains(T.charAt(i))) {
                map.put(T.charAt(i), map.get(T.charAt(i)) + 1);
            } else {
                map.put(T.charAt(i), 1);
            }
        }

        int minLen = S.length();
        int left = 0;
        int minStart = 0;
        int count = 0;
        for (int i = 0; i < S.length(); i++) {
            Character ch = S.charAt(i);
            if (map.keySet().contains(ch)) {
                map.put(ch, map.get(ch) - 1);
                if (map.get(ch) >= 0) {
                    count ++;
                }
                // 如果找到一个子串
                while (count == T.length()) {
                    if (i - left + 1 < minLen) {
                        minLen = i - left + 1;
                        minStart = left;
                    }
                    if (map.keySet().contains(S.charAt(left))) {
                        // 如果右移的时候遇到了T中的字符，将hash表中对应字符加1，因为在后面要查找该字符
                        map.put(S.charAt(left), map.get(S.charAt(left)) + 1);
                        if (map.get(S.charAt(left)) > 0) {
                            // 如果加1之后小于1，说明之前是负数，说明现在窗口内还有多个T中的字符，需要继续向前移动窗口
                            // 如果大于1才停止向前移动窗口
                            count--;
                        }
                    }
                    left ++;
                }
            }
        }
        if (minLen > S.length()) {
            return "";
        }
        return S.substring(minStart, minLen + minStart);

    }


    public static void main(String[] args) {
        MinimumWindowSubstring minimumWindowSubstring = new MinimumWindowSubstring();
        System.out.println(minimumWindowSubstring.findMinimumWindowSubString("ADOBECODEBANC", "ABC"));
    }
}
