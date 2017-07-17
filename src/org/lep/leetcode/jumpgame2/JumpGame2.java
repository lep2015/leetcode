package org.lep.leetcode.jumpgame2;

import org.lep.leetcode.lettercombinationofaphonenumber.JumpGame;

/**
 * // Source : https://oj.leetcode.com/problems/jump-game-ii/
 *
 * Created by lverpeng on 2017/7/17.
 *
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * For example:
 * Given array A = [2,3,1,1,4]
 *
 * The minimum number of jumps to reach the last index is 2.
 * (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 *
 */
public class JumpGame2 {

    /**
     * 找到需要跳的次数最少的方法
     *
     * 贪心算法：保证当前是是最优的，以期结果是最优的
     *
     * 先保证能调到最后的最好的解，然后再计算能跳到当前最好解位置的最好解
     *
     * @param arr
     * @return
     */
    public int jump (int[] arr) {
        int end = arr.length - 1;
        int count = 0;
        while (end > 0) {
            for (int i = 0; i < end; i++) {
                if (i + arr[i] >= end) {
                    count ++;
                    end = i;
                    break;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        JumpGame2 jumpGame2 = new JumpGame2();
        int[] arr = new int[]{2,3,1,1,4};
        System.out.println("2----->" + jumpGame2.jump(arr));
    }
}
