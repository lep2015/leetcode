package org.lep.leetcode.containerwithmostwater;

/**
 * Source : https://oj.leetcode.com/problems/container-with-most-water/
 *
 * Created by lverpeng on 2017/7/7.
 *
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 *
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 *
 * Note: You may not slant the container.
 *
 * 对于双层循环问题是否可以使用从两头向中间靠近的方式来减少循环嵌套层数，比如这道题目里面，记录首尾两个index，每次将值较小处的index向中间移动
 * 找出问题有什么特点、规律、性质，利用这些东西去解决，可能会得到更好的解。比如这道提里面，最终的i、j，arr[i]总是大于其左边的任意一个数，arr[j]总是大于其右边的任意一个数
 *
 */

public class ContainerWithMostWater {

    /**
     * 求出任意两根线之间能盛放的水最多的横坐标i，j，也就是求出面积最大的i，j
     * 普通解法，直接循环所有的线，找出面积最大的i，j
     *
     * @param arr
     * @return
     */
    public int maxArea (int[] arr) {
        int maxArea = 0;
        int tempArea = 0;
        int loopCount = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j ++) {
                tempArea = Math.min(arr[i], arr[j]) * Math.abs(arr[j] - arr[i]);
                if (tempArea > maxArea) {
                    maxArea = tempArea;
                }
                loopCount ++;
            }
        }
        System.out.println("maxArea:loopCount-----" + loopCount);
        return maxArea;
    }

    /**
     * 从两边向中间移动，比较得出面积最大的
     * 每次移动：将较短的边向中间移动
     *
     * 上面是两层循环，这里只需要一层
     *
     * @param arr
     * @return
     */
    public int maxArea1 (int[] arr) {
        int maxArea = 0;
        int tempArea = 0;
        int low = 0;
        int high = arr.length - 1;
        int loopCount = 0;
        while (low < high) {
            tempArea = Math.min(arr[low], arr[high]) * Math.abs(arr[low] - arr[high]);
            if (tempArea > maxArea) {
                maxArea = tempArea;
            }
            if (arr[low] < arr[high]) {
                low ++;
            } else {
                high --;
            }
            loopCount ++;
        }
        System.out.println("maxArea1:loopCount-----" + loopCount);
        return maxArea;
    }

    /**
     * 假设最终找到i，j，那么arr[i] 要大于0-i（i左边）之间的数，arr[j]要大于j-arr.length（j右边）之间的数，
     * 那么如果arr[i]较小，i可以一直右移知道找到比它本身大的数，如果arr[j]较小，j可以一直左移一直找到比他大的数，计算一次面积，并比较
     * 直观理解就是：一个矩形面积要最大，在长度缩小的情况下，要拿更大的宽度来弥补
     *
     * 比上面上一些运算，循环次数是一样的
     * @param arr
     * @return
     */
    public int maxArea2 (int[] arr) {
        int tempArea = 0;
        int maxArea = 0;
        int low = 0;
        int high = arr.length - 1;
        int loopCount = 0;
        while (low < high) {
            tempArea = Math.min(arr[low], arr[high]) * Math.abs(arr[low] - arr[high]);
            if (tempArea > maxArea) {
                maxArea = tempArea;
            }
            if (arr[low] < arr[high]) {
                int nextLow = low + 1;
                while (nextLow < high && arr[low] > arr[nextLow]) {
                    nextLow ++;
                    loopCount ++;
                }
                low = nextLow;
            } else {
                int nextHigh = high - 1;
                while (nextHigh > low && arr[high] > arr[nextHigh]) {
                    nextHigh--;
                    loopCount ++;
                }
                high = nextHigh;
            }
            loopCount ++;
        }
        System.out.println("maxArea2:loopCount-----" + loopCount);
        return maxArea;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4,6,2,6,7,11,2};
        ContainerWithMostWater containerWithMostWater = new ContainerWithMostWater();
        System.out.println(containerWithMostWater.maxArea(arr));
        System.out.println(containerWithMostWater.maxArea1(arr));
        System.out.println(containerWithMostWater.maxArea2(arr));
    }


}
