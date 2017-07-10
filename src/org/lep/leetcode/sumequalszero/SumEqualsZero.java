package org.lep.leetcode.sumequalszero;

import java.util.*;

/**
 * Source : https://oj.leetcode.com/problems/3sum/
 *
 * Created by lverpeng on 2017/7/10.
 *
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 *
 * Note:
 *
 * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
 * The solution set must not contain duplicate triplets.
 *
 *     For example, given array S = {-1 0 1 2 -1 -4},
 *
 *     A solution set is:
 *     (-1, 0, 1)
 *     (-1, -1, 2)
 */
public class SumEqualsZero {

    /**
     * 最简单的方法，计算出所有三个数和为0的情况
     *
     * @param s
     * @return
     */
    public Set<Integer[]> findThreeNum (int[] s) {
        Arrays.sort(s);
        System.out.println(Arrays.toString(s));
        Set<Integer[]> result = new HashSet<Integer[]>();
        if (s.length < 4) {
            return null;
        }
        for (int i = 0; i < s.length - 2; i++) {
            for (int j = i + 1; j < s.length - 1; j++) {
                for (int k = j + 1; k < s.length; k++) {
                    if(s[i] + s[j] + s[k] == 0) {
                        Integer[] arr = {s[i], s[j], s[k]};
                        result.add(arr);
                    }
                }
            }
        }

        return result;
    }

    /**
     * 可以转化为和twosum一样的问题，相当于是多个twosum问题
     * a + b = -c
     * 就是两个数的和是一个定值，针对每一种c的情况求出a、b
     *
     * @param s
     * @return
     */
    public Set<Integer[]> findThreeNum1 (int[] s) {
        Arrays.sort(s);
        Set<Integer[]> set = new HashSet<Integer[]>();
        for (int i = 0; i < s.length - 2; i++) {
            int total = -s[i];
            int left = i + 1;
            int right = s.length -1;
            while (left < right) {
                if (s[left] + s[right] == total) {
                    Integer[] arr = {s[i], s[left], s[right]};
                    set.add(arr);
                    left ++;
                    right --;
                } else if (s[left] + s[right] > total) {
                    while (left < right && s[left] + s[right] > total) {
                        right --;
                    }
                } else {
                    while (left < right && s[left] + s[right] < total) {
                        left ++;
                    }
                }
            }
        }
        return set;
    }

    public static void main(String[] args) {
        SumEqualsZero sumEqualsZero = new SumEqualsZero();
        int[] arr = {-1, 0 ,1, 2, -1, -4};
        printList(sumEqualsZero.findThreeNum(arr));
        printList(sumEqualsZero.findThreeNum1(arr));
    }

    public static void printList (Set<Integer[]> list) {
        for (Integer[] i : list) {
            System.out.println(Arrays.toString(i));
        }
    }

}
