package org.lep.leetcode.climbstairs;

/**
 *
 * Source : https://oj.leetcode.com/problems/climbing-stairs/
 *
 * Created by lverpeng on 2017/7/24.
 *
 * You are climbing a stair case. It takes n steps to reach to the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 */
public class ClimbStairs {

    /**
     * 每次爬一个或者两个台阶，爬到梯子顶端有多少种方法
     * 爬到最高一级，要么是从n-1爬上去，要么是从n-2爬上去
     * 使用动态规划，dp[n] = dp[n-1] + dp[n-2]
     *
     * @param n
     * @return
     */
    public int climb (int n) {
        if (n <= 3) {
            return n;
        }
        int[] result = new int[]{2,3};
        for (int i = 4; i < n; i++) {
            int temp = result[0] + result[1];
            result[0] = result[1];
            result[1] = temp;
        }
        return result[1];
    }
}
