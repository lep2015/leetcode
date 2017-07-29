package org.lep.leetcode.searchinrotatedsortedarray;

/**
 * Source : https://oj.leetcode.com/problems/search-in-rotated-sorted-array-ii/
 *
 * Created by lverpeng on 2017/7/29.
 *
 * Follow up for "Search in Rotated Sorted Array":
 * What if duplicates are allowed?
 *
 * Would this affect the run-time complexity? How and why?
 *
 * Write a function to determine if a given target is in the array.
 *
 */
public class SearchInRotatedSortedDuplicateArray {

    /**
     * 相比较原来那个rotatedarray问题，这里的数组元素有可能是重复的，所以当arr[mid] = arr[left]的时候left-mid不一定是有序的，比如：
     * 原始：123333
     * 旋转：312333
     * 这个时候arr[mid]=3,arr[left]=3,但是左边不再是有有序的，只能排除left这个元素
     *
     * @param arr
     * @param target
     * @return
     */
    public int search (int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] > arr[left]) {
                // 左边是有序的
                if (arr[left] <= target && arr[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (arr[left] > arr[mid]) {
                // 右边是有序的
                if (arr[right] >= target && target > arr[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                left ++;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SearchInRotatedSortedDuplicateArray searchInRotatedSortedDuplicateArray = new SearchInRotatedSortedDuplicateArray();
        int[] arr = new int[]{3,1,2,3,3,3};
        int[] arr1 = new int[]{3,3,3,3,3};
        int[] arr2 = new int[]{3,4,5,1,2};
        System.out.println(searchInRotatedSortedDuplicateArray.search(arr, 2));
        System.out.println(searchInRotatedSortedDuplicateArray.search(arr1, 2));
        System.out.println(searchInRotatedSortedDuplicateArray.search(arr2, 1));
    }
}
