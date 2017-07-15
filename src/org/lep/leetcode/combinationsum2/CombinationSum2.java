package org.lep.leetcode.combinationsum2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *Source : https://oj.leetcode.com/problems/combination-sum-ii/
 *
 * Created by lverpeng on 2017/7/14.
 *
 * Given a collection of candidate numbers (C) and a target number (T), find all
 * unique combinations in C where the candidate numbers sums to T.
 *
 * Each number in C may only be used once in the combination.
 *
 * Note:
 *
 * > All numbers (including target) will be positive integers.
 * > Elements in a combination (a1, a2, … , ak) must be in non-descending order.
 *   (ie, a1 ≤ a2 ≤ … ≤ ak).
 * > The solution set must not contain duplicate combinations.
 *
 * For example, given candidate set 10,1,2,7,6,1,5 and target 8,
 * A solution set is:
 * [1, 7]
 * [1, 2, 5]
 * [2, 6]
 * [1, 1, 6]
 *
 */
public class CombinationSum2 {


    private List<List<Integer>> result = new ArrayList<List<Integer>>();

    /**
     * 先考虑一个数字，比如第一位，其他位以此类推，第一位为例
     *
     * @param num
     * @param start
     * @param end
     * @param target
     * @param list
     */
    public void combinationSum(int[] num, int start, int end, int target, List<Integer> list) {
        if (target == 0 && list.size() > 0) {
            Integer[] newList = new Integer[list.size()];
            System.arraycopy(list.toArray(new Integer[list.size()]), 0, newList, 0, list.size());
            result.add(Arrays.asList(newList));
            return;
        }
        int index = start;
        int multiple = 0;
        while (index <= end && num[index] <= target) {
            if (index > start && num[index] == num[index-1]) {
                index ++;
                continue;
            }
            int newTarget = target - num[index];
            list.add(num[index]);
            combinationSum(num, index + 1, end, newTarget, list);
            list.remove(list.size() - 1);
            index++;

        }
    }

    public List<List<Integer>> combination(int[] num, int target) {
        Arrays.sort(num);
        List<Integer> combinationList = new ArrayList<Integer>();
        combinationSum(num, 0, num.length - 1, target, combinationList);
        return result;
    }

    private static void printMatrix(List<List<Integer>> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (List<Integer> intList : list) {
            for (Integer num : intList) {
                stringBuilder.append(num);
                stringBuilder.append(", ");
            }
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length() - 1);
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }

    public static void main(String[] args) {
        CombinationSum2 combinationSum = new CombinationSum2();
        int[] num = new int[]{10,1,2,7,6,1,5};
        printMatrix(combinationSum.combination(num, 8));

    }
}
