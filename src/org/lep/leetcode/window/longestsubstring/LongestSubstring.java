package org.lep.leetcode.window.longestsubstring;

import java.util.HashSet;
import java.util.Set;

/**
 * Source : https://oj.leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * Created by lverpeng on 2017/6/26.
 *
 * Given a string, find the length of the longest substring without repeating characters.
 * For example, the longest substring without repeating letters for "abcabcbb" is "abc",
 * which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
 *
 */
public class LongestSubstring {

    /**
     * 两个指针：一个指向当前字符，一个指向当前子串开始的位置
     * 将指向当前子串的指针依次向后移动，判断当前哈希表（用字符作为key，value不重要，可以直接用set）中是否存在当前字符
     *  如果已经存在，说明找到一个子串
     *      将指向当前子串的指针向后移动一个位置，作为新的子串起始位置
     *      计算刚刚结束子串的长度，和之前长度作比较，选出较大的一个
     *  如果不存在，更新当前子串的长度，加1
     * 将当前字符加入hash表，将当前指针向后移动，重复上面的操作
     *
     *
     *
     * @param str
     * @return
     */
    public int searchLongestSubstring (String str) {
        int lastIndext = 0;
        int maxLen = 0;
        Set<Character> charSet = new HashSet<Character>();
        for (int i = 0; i < str.length(); i++) {
            if (charSet.contains(str.charAt(i))) {
                int newLen = i - lastIndext;
                maxLen = Math.max(newLen, maxLen);
                lastIndext ++;
            } else {
                maxLen ++;
            }
            charSet.add(str.charAt(i));
        }
        return maxLen;
    }


    public static void main(String[] args) {
        LongestSubstring longestSubstring = new LongestSubstring();
        System.out.println(longestSubstring.searchLongestSubstring("abcabcbb"));
    }
}
