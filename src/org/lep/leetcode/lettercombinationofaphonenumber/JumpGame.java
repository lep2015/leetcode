package org.lep.leetcode.lettercombinationofaphonenumber;

/**
 * Source : https://oj.leetcode.com/problems/jump-game/
 *
 * Created by lverpeng on 2017/7/17.
 *
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 *
 * For example:
 * A = [2,3,1,1,4], return true.
 *
 * A = [3,2,1,0,4], return false.
 */
public class JumpGame {

    /**
     * max记录目前能到的最远位置，循环数组，每次max和当前i能到的最远位置作比较，如果比max大，说明能到比原来到更远的位置，更新max
     * 如果max已经大于等于数组长度，则说明能到数组末尾，返回true
     * 如果当前已经大于max说明当前位置已经到不了，返回false
     *
     * 如果上面没有返回，说明不能到数组末尾
     *
     * @param arr
     * @return
     */
    public boolean canJump (int[] arr) {
        if (arr.length <= 0) {
            return false;
        }
        int length = arr.length;
        // 记录最远能到的位置
        int max = 0;
        for (int i = 0; i < max && i < length - 1; i++) {
            if (i + arr[i] > max) {
                max = i +arr[i];
            }
            if (max >= length - 1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        JumpGame jumpGame = new JumpGame();
        int[] arr = new int[]{2,3,1,1,4};
        int[] arr1 = new int[]{3,2,1,0,4};
        int[] arr2 = new int[]{3,2,1,0,4,1};
        System.out.println("true---->" + jumpGame.canJump(arr));
        System.out.println("false--->" + jumpGame.canJump(arr1));
        System.out.println("false--->" + jumpGame.canJump(arr2));
    }
}
