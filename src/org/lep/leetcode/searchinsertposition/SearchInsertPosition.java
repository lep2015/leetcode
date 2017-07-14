package org.lep.leetcode.searchinsertposition;

/**
 * Source : https://oj.leetcode.com/problems/search-insert-position/
 *
 * Created by lverpeng on 2017/7/14.
 *
 * Given a sorted array and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 *
 * You may assume no duplicates in the array.
 *
 * Here are few examples.
 * [1,3,5,6], 5 → 2
 * [1,3,5,6], 2 → 1
 * [1,3,5,6], 7 → 4
 * [1,3,5,6], 0 → 0
 *
 */
public class SearchInsertPosition {


    /**
     * 二分查找
     *
     * @param num
     * @param target
     * @return
     */
    public int binarySearch (int[] num, int target) {
        int left = 0;
        int right = num.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (num[mid] == target) {
                return mid;
            }
            if (target > num[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;

    }


    public static void main(String[] args) {
        SearchInsertPosition searchInsertPosition = new SearchInsertPosition();
        int[] arr = new int[]{1,3,5,6};
        System.out.println(searchInsertPosition.binarySearch(arr, 5));

        System.out.println(searchInsertPosition.binarySearch(arr, 2));
        System.out.println(searchInsertPosition.binarySearch(arr, 7));
        System.out.println(searchInsertPosition.binarySearch(arr, 0));
    }

}
