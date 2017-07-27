package org.lep.leetcode.combinations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Source : https://oj.leetcode.com/problems/combinations/
 *
 * Created by lverpeng on 2017/7/27.
 *
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 *
 * For example,
 * If n = 4 and k = 2, a solution is:
 *
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 */
public class Combination {
    private List<Integer[]> result = null;

    /**
     * 求排列组合Cnk
     * 使用递归求解
     * 或者使用针对每一位寻找剩余位数
     * @param n
     * @param k
     * @return
     */
    public List<Integer[]> combine (int n, int k) {
        result = new ArrayList<Integer[]>();
        List<Integer> list = new ArrayList<Integer>();
        combineByRecursion(n, 1, k, list);
        return result;
    }
    public List<Integer[]> combine1 (int n, int k) {
        result = new ArrayList<Integer[]>();
        List<Integer> list = new ArrayList<Integer>();
        combineByRecursion1(n, k, list);
        return result;
    }

    /**
     * 从前向后递归
     * @param n
     * @param start
     * @param k
     * @param list
     */
    public void combineByRecursion (int n, int start, int k, List<Integer> list) {
        if (k == 0) {
            Integer[] newList = new Integer[list.size()];
            System.arraycopy(list.toArray(new Integer[list.size()]), 0, newList, 0, list.size());
            result.add(newList);
            return;
        }
        for (int i = start; i <= n; i++) {
            list.add(i);
            combineByRecursion(n, i + 1, k - 1, list);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 从后向前递归
     * @param n
     * @param k
     * @param list
     */
    public void combineByRecursion1 (int n, int k, List<Integer> list) {
        if (k == 0) {
            Integer[] newList = new Integer[list.size()];
            System.arraycopy(list.toArray(new Integer[list.size()]), 0, newList, 0, list.size());
            result.add(newList);
            return;
        }
        for (int i = n; i > 0; i--) {
            list.add(i);
            combineByRecursion1(i - 1, k - 1, list);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 不使用递归，使用循环
     * @param n
     * @param k
     */
    public void combine2 (int n, int k) {
        for (int i = 1; i <= n-k; i++) {
            for (int j = i + 1; j < n-k; j++) {

            }

        }
    }

    public static void print (List<Integer[]> list) {
        for (Integer[] arr : list) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Combination combination = new Combination();
        print(combination.combine(4, 2));
        print(combination.combine1(4, 2));
    }
}
