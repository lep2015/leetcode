package org.lep.leetcode.twosum;

import java.util.Arrays;

/**
 * Source : https://oj.leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 *
 * Created by lverpeng on 2017/6/22.
 *
 * Given an array of integers that is already sorted in ascending order,
 * find two numbers such that they add up to a specific target number.
 *
 * The function twoSum should return indices of the two numbers such that they add up to the target,
 * where index1 must be less than index2. Please note that your returned answers (both index1 and index2)
 * are not zero-based.
 *
 * You may assume that each input would have exactly one solution.
 *
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 *
 */
public class TwoSum2 {
    public static void main(String[] args) {
        TwoSum2 twoSum2 = new TwoSum2();
        int[] numbers = {15, 7, 2, 11};
        twoSum2.quicksort(numbers, 0, 3);
        System.out.println(Arrays.toString(numbers));
        System.out.println(Arrays.toString(twoSum2.twoSum(numbers, 9)));
    }

    /**
     * 相比于一，这个问题里面的输入数组是有序的，可以使用两个指针来分别指向数组的首尾，
     * 根据当前指针指向的两个数的和与target的比较来决定那一个指针移动
     * 时间复杂度：O(n)，因为这里已经排好序了，如果还要排序的话，排序最佳时间复杂度是O(nlogn)，quicksort
     * 空间复杂度：O(1)
     *
     * @param numbers
     * @param target
     */
    public int[] twoSum (int[] numbers, int target) {
        int[] result = new int[2];
        int low = 0;
        int high = numbers.length - 1;

        while (low < high) {
            if ((numbers[low] + numbers[high]) == target) {
                result[0] = low + 1;
                result[1] = high + 1;
                break;
            } else if ((numbers[low] + numbers[high]) > target) {
                high--;
            } else {
                low++;
            }
        }
        return result;
    }

    /**
     * 快速排序
     * 时间复杂度：平均O(nlogn)，最坏O(n^2)
     * 基本思路：
     * 1. 取出一个基准，通常取最后一个元素
     * 2. 定义首尾两个指针，根据基准将数组分成，左边是小于基准，右边大于基准
     * 3. 递归的对左边和右边，执行步骤1、2
     *
     * @param nums
     * @param start
     * @param end
     */
    public void quicksort (int[] nums, int start, int end) {
        if (start >= end) {
            return ;
        }
        int left = start;
        int right = end;
        int mid = nums[end];
        while (left < right) {
            while (nums[left] <= mid && left < right) {
                left++;
            }
            while (nums[right] >= mid && left < right) {
                right--;
            }
            swap(nums, left, right);
        }
        if (nums[left] >= mid) {
            swap(nums, left, end);
        } else {
            left++;
        }
        quicksort(nums, start, left - 1);
        quicksort(nums, left, end);
    }

    public void swap (int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x]  =arr[y];
        arr[y] = temp;
    }
}
