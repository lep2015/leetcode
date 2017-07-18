package org.lep.leetcode.groupanagrams;

import java.util.*;

/**
 *
 * Source : https://oj.leetcode.com/problems/anagrams/
 *
 * Created by lverpeng on 2017/7/18.
 *
 * Given an array of strings, group anagrams together.
 *
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Return:
 *
 * [
 *   ["ate", "eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 *
 * Note:
 *
 * For the return value, each inner list's elements must follow the lexicographic order.
 * All inputs will be in lower-case.
 *
 * Update (2015-08-09):
 * The signature of the function had been updated to return list<list<string>> instead
 * of list<string>, as suggested here. If you still see your function signature return
 * a list<string>, please click the reload button  to reset your code definition.
 *
 */
public class GroupAnagram {


    public List<String[]> anagram (String[] strArr) {
        List<String[]> result = new ArrayList<String[]>();
        Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
        for (int i = 0; i < strArr.length; i++) {
            char[] charArr = strArr[i].toCharArray();
            Arrays.sort(charArr);
            String str = new String(charArr);
            if (map.keySet().contains(str)) {
                map.get(str).add(i);
            } else {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                map.put(str, list);
            }
        }
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            String[] strs = new String[entry.getValue().size()];
            int index = 0;
            for (Integer i : entry.getValue()) {
                strs[index] = strArr[entry.getValue().get(index)];
                index ++;
            }
            Arrays.sort(strs);
            result.add(strs);
        }
        return result;
    }

    public static void printList (List<String[]> list) {
        for (String[] strs : list) {
            System.out.println(Arrays.toString(strs));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        GroupAnagram groupAnagram = new GroupAnagram();
        printList(groupAnagram.anagram(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));

    }
}
