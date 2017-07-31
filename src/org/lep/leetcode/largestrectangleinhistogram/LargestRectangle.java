package org.lep.leetcode.largestrectangleinhistogram;

import java.util.Stack;

/**
 *
 * Source : https://oj.leetcode.com/problems/largest-rectangle-in-histogram/
 *
 * Created by lverpeng on 2017/7/31.
 *
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 * find the area of largest rectangle in the histogram.
 *
 *                    6
 *                  +---+
 *               5  |   |
 *              +---+   |
 *              |   |   |
 *              |   |   |
 *              |   |   |     3
 *              |   |   |   +---+
 *        2     |   |   | 2 |   |
 *      +---+   |   |   +---+   |
 *      |   | 1 |   |   |   |   |
 *      |   +---+   |   |   |   |
 *      |   |   |   |   |   |   |
 *      +---+---+---+---+---+---+
 *
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 *
 *
 *                    6
 *                  +---+
 *               5  |   |
 *              +-------|
 *              |-------|
 *              |-------|
 *              |-------|     3
 *              |-------|   +---+
 *        2     |-------| 2 |   |
 *      +---+   |-------|---+   |
 *      |   | 1 |-------|   |   |
 *      |   +---|-------|   |   |
 *      |   |   |-------|   |   |
 *      +---+---+---+---+---+---+
 *
 *
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 *
 * For example,
 * Given height = [2,1,5,6,2,3],
 * return 10.
 */
public class LargestRectangle {


    /**
     * 找到直方图中面积最大的矩形的面积
     *
     * 从左向右遍历数组
     *      如果当前元素大于栈顶元素，说明当前是一个递增序列，将当前元素压入栈
     *      如果当前元素小于栈顶元素，则pop出栈顶元素，计算当前栈顶元素和到当前位置的面积，和最大面积比较，更新最大面积，直到栈为空
     *
     *
     * 边界条件
     * 为了计算第一个元素，在栈底压入0
     *
     * @param arr
     * @return
     */
    public int findLargest (int[] arr) {
        int result = 0;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        for (int i = 0; i < arr.length; i++) {
            if (stack.empty() || arr[i] >= arr[stack.peek()]) {
                stack.push(i);
            } else {
                int cur = stack.pop();
                result = Math.max(result, arr[cur] * (i - cur));
                i --;
            }
        }
        return result;
    }

    /**
     * 相比于上面的方法，这里会将每一个递增序列前面所有的元素计算一遍，而不是仅仅计算当前递增序列
     *
     * @param arr
     * @return
     */
    public int findLargest1 (int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; ++i) {
            if (i + 1 < arr.length && arr[i] <= arr[i + 1]) {
                continue;
            }
            int minH = arr[i];
            for (int j = i; j >= 0; --j) {
                minH = Math.min(minH, arr[j]);
                int area = minH * (i - j + 1);
                res = Math.max(res, area);
            }
        }
        return res;
    }


    public static void main(String[] args) {
        LargestRectangle largestRectangle = new LargestRectangle();
        int[] arr = new int[]{2,1,5,6,2,3};
        int[] arr1 = new int[]{2,1,5,6,5,2,3};
//        System.out.println(largestRectangle.findLargest(arr));
//        System.out.println(largestRectangle.findLargest(arr1));
        System.out.println(largestRectangle.findLargest1(arr1));
    }
}
