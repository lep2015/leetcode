package org.lep.leetcode.searchforarange;

import java.util.Arrays;

/**
 * Source : https://oj.leetcode.com/problems/search-for-a-range/
 *
 * Created by lverpeng on 2017/7/14.
 *
 * Given a sorted array of integers, find the starting and ending position of a given target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 *
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 *
 */
public class SearchRange {

    /**
     * 查找target在有有序数组中的起始位置
     *
     * 先找左边界，普通二分查找是和target比较，如果相同就返回，这里小于等于num[mid]，如果是等于num[mid]也是收缩右边，最后得到的就是左边界
     * 右边界同上
     *
     * @param num
     * @param target
     * @return
     */
    public int[] search (int[] num, int target) {
        int left = 0;
        int right = num.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (num[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        int targetLeft = left;
        left = 0;
        right = num.length - 1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (num[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        int targetRight = right;
        if (target != num[targetLeft] || target != num[targetRight]) {
            targetLeft = targetRight = -1;
        }
        int[] result = new int[2];
        result[0] = targetLeft;
        result[1] = targetRight;
        return result;
    }

    public static void main(String[] args) {
        SearchRange searchRange = new SearchRange();
        int[] arr = new int[]{5, 7, 7, 8, 8, 10};
        System.out.println(Arrays.toString(searchRange.search(arr, 8)));
    }

}
