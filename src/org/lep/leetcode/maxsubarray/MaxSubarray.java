package org.lep.leetcode.maxsubarray;

import java.util.Map;

/**
 *
 * Source : https://oj.leetcode.com/problems/maximum-subarray/
 *
 * Created by lverpeng on 2017/7/18.
 *
 * Find the contiguous subarray within an array (containing at least one number)
 * which has the largest sum.
 *
 * For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
 * the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 *
 * More practice:
 *
 * If you have figured out the O(n) solution, try coding another solution using
 * the divide and conquer approach, which is more subtle.
 *
 */
public class MaxSubarray {

    /**
     * 找到所有子数组中最大的和
     *
     * 数组从前向后遍历，针对每个数组元素有两个选择，要么加入已经存在子数组，如果该元素的值大于该元素和前面数组总和的和还要大，
     *          那就重新开始一个新的子数组，遍历完数组找到最大的和
     *
     *
     * @param arr
     * @return
     */
    public int maxSubarray (int[] arr) {
        int loopCount = 0;
        int[] sum = new int[arr.length];
        int max = 0;
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = Math.max(arr[i], sum[i - 1] + arr[i]);
            max = Math.max(max, sum[i]);
            loopCount ++;
        }
        System.out.println("maxSubarray-->" + loopCount);
        return max;
    }


    public int maxSubarray1 (int[] arr) {
        int loopCount = 0;
        // 只记录上一个和
        int sum = 0;
        int max = 0;
        for (int i = 1; i < arr.length; i++) {
            if (sum < 0) {
                sum = 0;
            }
            sum += arr[i];
            max = Math.max(max, sum);
            loopCount ++;
        }
        System.out.println("maxSubarray1-->" + loopCount);
        return max;
    }

    /**
     * 使用分治法
     *
     * @param arr
     * @return
     */
    int loopCount1 = 0;
    public int maxSubarray2 (int[] arr) {
        loopCount1 = 0;
        return divide(arr, 0, arr.length - 1);
    }

    private int divide (int[] arr, int low, int high) {
        if (low == high) {
            return arr[low];
        }
        if (low == high - 1) {
            return Math.max(arr[low] + arr[high], Math.max(arr[low], arr[high]));
        }
        int mid = (low + high) / 2;
        int lmax = divide(arr, low, mid - 1);
        int rmax = divide(arr, mid + 1, high);
        int mmax = arr[mid];
        int temp = mmax;
        for (int i = mid - 1; i > 0; i--) {
            temp += arr[i];
            if (mmax < temp) {
                mmax = temp;
            }
            loopCount1 ++;
        }
        temp = mmax;
        for (int i = mid + 1; i < high; i++) {
            temp += arr[i];
            if (temp > mmax) {
                mmax = temp;
            }
            loopCount1 ++;
        }
        System.out.println("maxSubarray2-->" + loopCount1);
        return Math.max(mmax, Math.max(lmax, rmax));
    }


    public static void main(String[] args) {
        MaxSubarray maxSubarray = new MaxSubarray();
        int[] arr = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubarray.maxSubarray(arr));
        System.out.println(maxSubarray.maxSubarray1(arr));
        System.out.println(maxSubarray.maxSubarray2(arr));



        int[] arr1 = new int[]{-2,1,-3,4,-1,2,1,-5,4,-2,1,-3,4,-1,2,1,-5,4,-2,1,-3,4,-1,2,1,-5,4,-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubarray.maxSubarray(arr1));
        System.out.println(maxSubarray.maxSubarray1(arr1));
        System.out.println(maxSubarray.maxSubarray2(arr1));
    }
}
