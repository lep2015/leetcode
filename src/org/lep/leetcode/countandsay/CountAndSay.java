package org.lep.leetcode.countandsay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Source : https://oj.leetcode.com/problems/count-and-say/
 *
 * Created by lverpeng on 2017/7/14.
 *
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 *
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 *
 * Given an integer n, generate the nth sequence.
 *
 * Note: The sequence of integers will be represented as a string.
 *
 */
public class CountAndSay {


    /**
     * 循环n次，根据上一个字符串找到下一个
     *
     * @param n
     * @return
     */
    public String countAndSay (int n) {
        if (n == 0) {
            return "1";
        }
        if (n == 1) {
            return "11";
        }
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("11");
        for (int i = 2; i <= n; i++) {
            getNext(list);
        }

        System.out.println(Arrays.toString(list.toArray()));
        return list.get(list.size() - 1);
    }

    private void getNext (List<String> list) {
        String last = list.get(list.size() - 1);
        String next = "";
        int count = 1;
        for (int i = 1; i < last.length(); i++) {
            if (last.charAt(i) == last.charAt(i - 1)) {
                count ++;
                if (i == last.length() - 1) {
                    next += count + "" +  last.charAt(i - 1);
                }
            } else {
                next += count + "" +  last.charAt(i - 1);
                count = 1;
                if (i == last.length() - 1) {
                    next += count + "" +  last.charAt(i);
                }
            }
        }
        list.add(next);
    }

    public static void main(String[] args) {
        CountAndSay countAndSay = new CountAndSay();
        System.out.println(countAndSay.countAndSay(2));
        System.out.println(countAndSay.countAndSay(3));
        System.out.println(countAndSay.countAndSay(4));
        System.out.println(countAndSay.countAndSay(5));
        System.out.println(countAndSay.countAndSay(6));
    }
}
