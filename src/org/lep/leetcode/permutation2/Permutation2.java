package org.lep.leetcode.permutation2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Source : https://oj.leetcode.com/problems/permutations-ii/
 *
 * Created by lverpeng on 2017/7/17.
 *
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 *
 * For example,
 * [1,1,2] have the following unique permutations:
 * [1,1,2], [1,2,1], and [2,1,1].
 *
 */
public class Permutation2 {

    /**
     * 找出数组元素可以组成的所有排列
     *
     * 递归求出每一个元素开头的组合
     *
     *
     * @param arr
     * @return
     */
    public void permute (int[] arr, int start, int end, List<int[]> result) {
        if (start >= end-1) {int[] newArr = new int[arr.length];
            System.arraycopy(arr,0, newArr, 0, arr.length);
            result.add(newArr);

            return ;
        }

        for (int i = start; i < end; i++) {
            if (i < end - 1 && arr[i] == arr[i + 1]) {
                // 如果是相同的元素，则跳过当前元素
                continue;
            }
            swap(arr, start, i);
            permute(arr, start + 1, end, result);
            swap(arr, i, start);
        }
    }

    private void swap (int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printList (List<int[]> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(Arrays.toString(list.get(i)));
        }
    }

    public static void main(String[] args) {
        Permutation2 permutation2 = new Permutation2();
        int[] arr = new int[]{1,2,1};
        Arrays.sort(arr);
        int[] arr1 = new int[]{1,3,2};
        Arrays.sort(arr1);
        List<int[]> result = new ArrayList<int[]>();
        permutation2.permute(arr, 0, arr.length, result);
        printList(result);
        System.out.println();
        result = new ArrayList<int[]>();
        permutation2.permute(arr1, 0, arr1.length, result);
        printList(result);
    }
}
