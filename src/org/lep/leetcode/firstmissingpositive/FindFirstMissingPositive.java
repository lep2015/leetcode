package org.lep.leetcode.firstmissingpositive;

/**
 *
 * Source : https://oj.leetcode.com/problems/first-missing-positive/
 *
 * Created by lverpeng on 2017/7/15.
 *
 * Given an unsorted integer array, find the first missing positive integer.
 *
 * For example,
 * Given [1,2,0] return 3,
 * and [3,4,-1,1] return 2.
 *
 * Your algorithm should run in O(n) time and uses constant space.
 *
 */
public class FindFirstMissingPositive {

    /**
     * 找到第一个确实的正整数
     * 题目特点：
     * 数组是一个整数数组
     * 数组中的正整数中除了缺失的一个正整数，其他都是连续的，也就是说一个长度为n的数组，数组中的数是1-n（最多，因为可能有0或者负数）
     *
     * 可以把每一个数房放在其下标减1的位置
     * 然后遍历数组，发现num[i] != num[i-1]或说明就找到了缺失的数
     *
     * @param num
     * @return
     */
    public int fistMissingPositive (int[] num) {
        for (int i = 0; i < num.length;) {
            if (num[i] > 0 && num[i] != num[num[i] - 1]) {
                int temp = num[num[i]-1];
                num[num[i]-1] = num[i];
                num[i] = temp;
            } else {
                i ++;
            }
        }

        for (int i = 1; i < num.length; i++) {
            if (num[i] != (num[i-1] + 1)) {
                return num[i - 1] + 1;
            }

        }
        return -1;
    }


    public static void main(String[] args) {
        FindFirstMissingPositive findFirstMissingPositive = new FindFirstMissingPositive();
        int[] arr = new int[]{1,2,0};
        int[] arr1 = new int[]{3,4,-1,1};
        int[] arr2 = new int[]{3,4,1,1};
        int[] arr3 = new int[]{3,4,1,1};
        System.out.println(findFirstMissingPositive.fistMissingPositive(arr));
        System.out.println(findFirstMissingPositive.fistMissingPositive(arr1));
        System.out.println(findFirstMissingPositive.fistMissingPositive(arr2));
        System.out.println(findFirstMissingPositive.fistMissingPositive(arr3));
    }



}
