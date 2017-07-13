package org.lep.leetcode.searchinrotatedsortedarray;

import javax.naming.directory.SearchControls;

/**
 * Source : https://oj.leetcode.com/problems/search-in-rotated-sorted-array/
 *
 * Created by lverpeng on 2017/7/13.
 *
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 *
 */
public class SearchInRotatedSortedArray {


    /**
     * 有序数组以某一个支点被翻转过，在数组中查找某一个元素
     *
     * 数组是局部有序的，使用二分查找的过程中使用这个特点
     *
     * @param num
     * @return
     */
    public int search (int[] num, int target) {
        int left = 0;
        int right = num.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (num[mid] == target) {
                return mid;
            }
            // left- mid之间是局部有序的
            if (num[left] <= num[mid]) {
                if (num[left] <= target && target < num[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // mid - right是有序的
                if (mid < target && target <= num[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        SearchInRotatedSortedArray searchInRotatedSortedArray = new SearchInRotatedSortedArray();
        int[] arr = new int[]{4, 5, 6, 7, 0, 1, 2};
        System.out.println(searchInRotatedSortedArray.search(arr, 0));
        System.out.println(searchInRotatedSortedArray.search(arr, 5));
        System.out.println(searchInRotatedSortedArray.search(arr, 7));
    }
}
