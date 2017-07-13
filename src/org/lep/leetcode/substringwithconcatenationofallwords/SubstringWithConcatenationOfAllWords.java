package org.lep.leetcode.substringwithconcatenationofallwords;

import org.lep.leetcode.parseint.IntegerParser;

import java.util.*;

/**
 * Source : https://oj.leetcode.com/problems/substring-with-concatenation-of-all-words/
 *
 * Created by lverpeng on 2017/7/13.
 *
 * You are given a string, S, and a list of words, L, that are all of the same length.
 * Find all starting indices of substring(s) in S that is a concatenation of each word
 * in L exactly once and without any intervening characters.
 *
 * For example, given:
 * S: "barfoothefoobarman"
 * L: ["foo", "bar"]
 *
 * You should return the indices: [0,9].
 * (order does not matter).
 *
 *
 * 找出L中单词连接成的子串在字符串S中出现所有位置
 *
 */
public class SubstringWithConcatenationOfAllWords {


    /**
     *
     * 将strArr中的所有单词放在hash表中，单词为key，相同单词出现的次数为value
     *
     * strArr中所有单词长度一致，S重要出现所有单词的连接成为的字符串，那么S的长度一定要大于strArr中所有单词的总长度，
     * 也就是起始字符在0-(S.length - strArr.length * strArr[0].length)之间，
     * 以上面的位置为起始位置，依次判断接下来的strArr.length个单词是否正好是上面hash表中的单词，并判断相同单词出现次数，
     *      如果没有出现则退出循环，如果出现的次数大于hash表中单词的次数也break
     * 一轮循环完成之后判断循环的次数是否正好是strArr.length，如果是，说明S包含strArr连接的字符串，记录此时的起始位置到结果中
     *
     * @param S
     * @param strArr
     * @return
     */
    public int[] findSubstring (String S, String[] strArr) {
        if (S.length() < 1 | strArr.length < 1) {
            return new int[]{};
        }
        Map<String, Integer> wordMap = new HashMap<String, Integer>();      // 存放strArr单词的哈希表

        for (String str : strArr) {
            if (wordMap.keySet().contains(str)) {
                wordMap.put(str, wordMap.get(str) + 1);
            } else {
                wordMap.put(str, 1);
            }
        }

        int arrLen = strArr.length;
        int wordLen = strArr[0].length();
        int arrStrLen = arrLen * wordLen;
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < S.length() - arrStrLen; i++) {
            int j = 0;
            Map<String, Integer> subStrMap = new HashMap<String, Integer>();
            for (j = 0; j < arrLen; j++) {
                String subStr = S.substring(i + j * wordLen, i + j * wordLen + wordLen);
                if (!wordMap.keySet().contains(subStr)) {
                    break;
                } else {
                    if (subStrMap.keySet().contains(subStr)) {
                        subStrMap.put(subStr, subStrMap.get(subStr) + 1);
                    } else {
                        subStrMap.put(subStr, 1);
                    }
                }
                if (subStrMap.get(subStr) > wordMap.get(subStr)) {
                    break;
                }
            }

            if (j == arrLen) {
                result.add(i);
            }

        }
        int[] res = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }
        return res;
    }

    /**
     * 上面是以步长为1进行循环，下面以步长为word长度进行循环
     *
     * @param S
     * @param strArr
     * @return
     */
    public int[] findSubstring1 (String S, String[] strArr) {
        if (S.length() < 1 || strArr.length < 1) {
            return new int[]{};
        }
        Map<String, Integer> wordMap = new HashMap<String, Integer>();
        for (int i = 0; i < strArr.length; i++) {
            if (wordMap.keySet().contains(strArr[i])) {
                wordMap.put(strArr[i], wordMap.get(strArr[i]));
            } else {
                wordMap.put(strArr[i], 1);
            }
        }

        List<Integer> result = new ArrayList<Integer>();
        int wordLen = strArr[0].length();
        Map<String, Integer> subStrMap = new HashMap<String, Integer>();
        for (int i = 0; i < wordLen; i++) {
            int count = 0;
            int left = i;       // 记录待匹配子串起始位置
            for (int j = i; j < S.length() - wordLen; j += wordLen) {
                String subStr = S.substring(j, j + wordLen);
                if (wordMap.keySet().contains(subStr)) {
                    if (subStrMap.keySet().contains(subStr)) {
                        subStrMap.put(subStr, subStrMap.get(subStr) + 1);
                    } else {
                        subStrMap.put(subStr, 1);
                    }
                    count ++;
                    if (subStrMap.get(subStr) <= wordMap.get(subStr)) {
                        count ++;
                    } else {
                        // 说明当前开始位置不匹配
                        while (subStrMap.get(subStr) > wordMap.get(subStr)) {
                            //
                            String startWord = S.substring(left, left + wordLen);
                            subStrMap.put(startWord, subStrMap.get(startWord) - 1);
                            left += wordLen;
                            count --;
                        }
                    }
                    if (count == strArr.length) {
                        // 找到了
                        result.add(left);

                        // 向后移动一个单词
                        count --;
                        String startWord = S.substring(left, left + wordLen);
                        subStrMap.put(startWord, subStrMap.get(startWord) - 1);
                        left += wordLen;

                    }
                } else {
                    // 清空变量，重新开始查找
                    left = j + wordLen;
                    subStrMap.clear();
                    count = 0;
                }
            }
        }

        int[] res = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }
        return res;

    }


    public static void main(String[] args) {
        SubstringWithConcatenationOfAllWords substringWithConcatenationOfAllWords = new SubstringWithConcatenationOfAllWords();
        String S = "barfoothefoobarman";
        String[] strArr = new String[]{"foo", "bar"};

        System.out.println(Arrays.toString(substringWithConcatenationOfAllWords.findSubstring(S, strArr)));
        System.out.println(Arrays.toString(substringWithConcatenationOfAllWords.findSubstring1(S, strArr)));
    }

}
