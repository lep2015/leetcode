package org.lep.leetcode.removeduplicatesfromsortedarray;

import com.sun.org.apache.regexp.internal.RE;

import java.util.Arrays;

/**
 * Source : https://oj.leetcode.com/problems/remove-duplicates-from-sorted-array/
 *
 * Created by lverpeng on 2017/7/12.
 *
 * Given a sorted array, remove the duplicates in place such that each element appear
 * only once and return the new length.
 *
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 *
 * For example,
 * Given input array A = [1,1,2],
 *
 * Your function should return length = 2, and A is now [1,2].
 */
public class RemoveDuplicates {

    /**
     *
     * 因为是有序的，所以如果有重复的元素一定是相邻的，只要判断相邻的不相等的个数就知道不重复的元素个数
     *
     * @param num
     * @return
     */
    public int remove (int[] num) {
        int pos = 0;
        for (int i = 0; i < num.length - 1; i++) {
            if (num[i] != num[i + 1]) {
                num[pos] = num[i];
                pos++;
            }
        }
        System.out.println(Arrays.toString(num));
        // 另外加上最后一个元素
        return pos + 1;
    }

    public static void main(String[] args) {
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        int[] arr = new int[]{1,1,2};
        int[] arr1 = new int[]{1,1,1,2};
        int[] arr2 = new int[]{1,1,22,22,22,33};
        System.out.println(removeDuplicates.remove(arr));
        System.out.println(removeDuplicates.remove(arr1));
        System.out.println(removeDuplicates.remove(arr2));
    }
}
