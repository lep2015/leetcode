package org.lep.leetcode.trappingrainwater;

/**
 * Source : https://oj.leetcode.com/problems/trapping-rain-water/
 *
 * Created by lverpeng on 2017/7/15.
 *
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 *
 * For example,
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 *
 *     ^
 *     |
 *   3 |                       +--+
 *     |                       |  |
 *   2 |          +--+xxxxxxxxx|  +--+xx+--+
 *     |          |  |xxxxxxxxx|  |  |xx|  |
 *   1 |   +--+xxx|  +--+xxx+--+  |  +--+  +--+
 *     |   |  |xxx|  |  |xxx|  |  |  |  |  |  |
 *   0 +---+--+---+--+--+---+--+--+--+--+--+--+----->
 *       0  1   0  2  1   0  1  3  2  1  2  1
 *
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case,
 * 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 *
 *
 *
 */
public class TrappingRainWater {


    /**
     * 找到数组组成的柱条之间存水的单位数
     *
     * 先找到最大的，分为左右两边
     * 依次遍历左边，将当前值与左边最大的值比较，如果大于左边最大值则更新左边最大值，计算该位置可以存的水量
     * 右边类似
     *
     *
     * @param num
     */
    public int trap (int[] num) {
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < num.length; i++) {
            if (max < num[i]) {
                max = num[i];
                maxIndex = i;
            }
        }

        // 当前位置以前的较大值
        int preMax = 0;
        int result = 0;
        // 计算左边
        for (int i = 0; i < maxIndex; i++) {
            if (preMax < num[i]) {
                preMax = num[i];
            }
            result += preMax - num[i];
        }

        // 计算右边
        int afterMax = 0;
        for (int i = num.length - 1; i > maxIndex ; i--) {
            if (afterMax < num[i]) {
                afterMax = num[i];
            }
            result += afterMax - num[i];
        }

        return result;
    }

    public static void main(String[] args) {
        TrappingRainWater trappingRainWater = new TrappingRainWater();
        int[] arr = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trappingRainWater.trap(arr));
    }
}
