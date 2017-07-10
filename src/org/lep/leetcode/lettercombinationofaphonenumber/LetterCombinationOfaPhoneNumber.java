package org.lep.leetcode.lettercombinationofaphonenumber;

import java.util.*;

/**
 * Source : https://oj.leetcode.com/problems/letter-combinations-of-a-phone-number/
 *
 * Created by lverpeng on 2017/7/10.
 *
 * Given a digit string, return all possible letter combinations that the number could represent.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 *
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * Note:
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 *

 */
public class LetterCombinationOfaPhoneNumber {

    private Map<Character, String[]> map = new HashMap<Character, String[]>(){{
        put('0', new String[]{"", "", "", ""});
        put('1', new String[]{"", "", "", ""});
        put('2', new String[]{"a", "b", "c", ""});
        put('3', new String[]{"d", "e", "f", ""});
        put('4', new String[]{"g", "h", "i", ""});
        put('5', new String[]{"j", "k", "l", ""});
        put('6', new String[]{"m", "n", "o", ""});
        put('7', new String[]{"p", "q", "r", "s"});
        put('8', new String[]{"t", "u", "v", ""});
        put('9', new String[]{"w", "x", "y", "z"});
    }};


    /**
     * 广度优先
     * 先计算出
     *
     * @param numStr
     * @return
     */
    public String[] letterCombination (String numStr) {
        List<String> result = new ArrayList<String>();

        for (int i = 0; i < numStr.length(); i++) {
            List<String> currentStrArr = new ArrayList<String>();
            if (result.size() == 0) {
                for (int j = 0; j < 4 && !"".equals(map.get(numStr.charAt(i))[j]); j++) {
                    currentStrArr.add(map.get(numStr.charAt(i))[j] + "");
                }
            } else {
                for (String str : result) {
                    for (int j = 0; j < 4 && !"".equals(map.get(numStr.charAt(i))[j]); j++) {
                        currentStrArr.add(str + map.get(numStr.charAt(i))[j]);
                    }
                }
            }
            result = currentStrArr;
        }

        return result.toArray(new String[result.size()]);
    }

    /**
     * 深度优先
     *
     * @param numStr
     * @param index
     * @param result
     * @param str
     * @return
     */
    public String letterConbinationByDFS (String numStr, int index, List<String> result, String str) {
        if (index >= numStr.length()) {
            return str;
        }
        char ch = numStr.charAt(index);

        for (int j = 0; j < 4; j++) {
            if (map.get(ch)[j].equals("")) {
                return "";
            }
            str += map.get(ch)[j] + "";

            str = letterConbinationByDFS(numStr, index + 1, result, str);
            if (!str.equals("")) {
                result.add(str);
                str = str.substring(0, str.length() - 1);
            }
        }
        return str;
    }

    public static void main(String[] args) {
        LetterCombinationOfaPhoneNumber letterCombinationOfaPhoneNumber = new LetterCombinationOfaPhoneNumber();
        System.out.println(Arrays.toString(letterCombinationOfaPhoneNumber.letterCombination("23")));
        List<String> list = new ArrayList<String>();
        letterCombinationOfaPhoneNumber.letterConbinationByDFS("23", 0 , list, "");
        System.out.println(Arrays.toString(list.toArray(new String[list.size()])));
    }
}
