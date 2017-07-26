package org.lep.leetcode.sortcolors;

import java.util.Arrays;

/**
 * Source : https://oj.leetcode.com/problems/sort-colors/
 *
 * Created by lverpeng on 2017/7/26.
 *
 * Given an array with n objects colored red, white or blue, sort them so that objects of
 * the same color are adjacent, with the colors in the order red, white and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 *
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 *
 * Follow up:
 *  > A rather straight forward solution is a two-pass algorithm using counting sort.
 *  > First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array
 *    with total number of 0's, then 1's and followed by 2's.
 *  > Could you come up with an one-pass algorithm using only constant space?
 */
public class SortColors {

    /**
     * 数组由三种颜色组成，将三种颜色归类排序，使相同的颜色紧邻，本题目有以下特点
     * 数组由三种颜色构成，分别用0，1，2代替
     *
     * 题目中提示已经说明，一种直接的办法就是遍历数组两次，分别对两种颜色排序
     *
     * 但是能不能用一次遍历，占用常数空间来完成呢？
     * 利用数组只由0，1，2构成的特性，只要对个数字排序，另一个自然也就是有序的了，可以遍历一次数组，维护两个下标，left和right，
     *          从数组两头开始，left记录0的位置，right记录2的位置
     *
     * @param arr
     */
    public void sort (int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        int i = 0;
        while (i < right) {
            if (arr[i] == 0) {
                swap(arr, i++, left++);
            } else if (arr[i] == 2) {
                swap(arr, i, right--);
            } else {
                i++;
            }
        }
    }

    private void swap (int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }


    public static void main(String[] args) {
        SortColors sortColors = new SortColors();
        int[] arr = new int[]{};
        int[] arr1 = new int[]{0};
        int[] arr2 = new int[]{0,1,2};
        int[] arr3 = new int[]{0,0,1,1,1,2,2};
        int[] arr4 = new int[]{1,2,0,0,1,1,1,2,2,0,1};

        sortColors.sort(arr);
        sortColors.sort(arr1);
        sortColors.sort(arr2);
        sortColors.sort(arr3);
        sortColors.sort(arr4);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr3));
        System.out.println(Arrays.toString(arr4));

    }
}
