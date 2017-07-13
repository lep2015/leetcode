package org.lep.leetcode.nextpermutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Source : https://oj.leetcode.com/problems/next-permutation/
 *
 * Created by lverpeng on 2017/7/13.
 *
 * * Implement next permutation, which rearranges numbers into the lexicographically next
 * greater permutation of numbers.
 *
 * If such arrangement is not possible, it must rearrange it as the lowest possible order
 * (ie, sorted in ascending order).
 *
 * The replacement must be in-place, do not allocate extra memory.
 *
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs
 * are in the right-hand column.
 *
 *   1,2,3 → 1,3,2
 *   3,2,1 → 1,2,3
 *   1,1,5 → 1,5,1
 *
 */
public class NextPermutation {

    /**
     * 寻找多个数字组成的数字序列中的下一个，比如：1，2，3组成的序列
     * 123,132,213,231,312,312
     *
     * 规律如下：
     * 从右向左找到第一个num[i] > num[i-1]
     * 然后从右向左找到第一个num[k] > num[i-1]
     * 交换两个位置
     * 对num[i-1]后面元素排序
     *
     * 边界条件：i = 1的时候还没找到num[i-1]就把整个数字的各个位翻转顺序
     *
     *
     * @param num
     * @return
     */
    public void nextPermutation (int[] num)  {
        for (int i = num.length - 1; i > 0; i--) {
            if (num[i] > num[i-1]) {
                int k = num.length - 1;
                while (num[i-1] > num[k]) {
                    k --;
                }
                // swap
                int temp = num[i-1];
                num[i-1] = num[k];
                num[k] = temp;
                reverse(num, i, num.length - 1);
                return ;
            }

            // 边界情况,已经是最大了，转化为最小
            if (i == 1) {
                reverse(num, 0, num.length - 1);
                return ;
            }

        }
    }

    private void reverse (int[] arr, int start, int end) {
        if (start < 0 || end > arr.length - 1 || start > end) {
            return;
        }

        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            end --;
            start ++;
        }
    }

    public static void main(String[] args) {
        NextPermutation nextPermutation = new NextPermutation();
        int[] arr1 = new int[]{1, 2, 3, 4};
        nextPermutation.nextPermutation(arr1);

        System.out.println(Arrays.toString(arr1));

        int[] arr2 = new int[]{1, 3, 2, 4};
        nextPermutation.nextPermutation(arr2);
        System.out.println(Arrays.toString(arr2));


        int[] arr3 = new int[]{4,3,2,1};
        nextPermutation.nextPermutation(arr3);
        System.out.println(Arrays.toString(arr3));

    }
}
