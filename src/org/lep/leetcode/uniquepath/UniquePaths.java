package org.lep.leetcode.uniquepath;

import java.util.Arrays;

/**
 * Source : https://oj.leetcode.com/problems/unique-paths/
 *
 * Created by lverpeng on 2017/7/20.
 *
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach
 * the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 *
 *    start                                                  
 *    +---------+----+----+----+----+----+
 *    |----|    |    |    |    |    |    |
 *    |----|    |    |    |    |    |    |
 *    +----------------------------------+
 *    |    |    |    |    |    |    |    |
 *    |    |    |    |    |    |    |    |
 *    +----------------------------------+
 *    |    |    |    |    |    |    |----|
 *    |    |    |    |    |    |    |----|
 *    +----+----+----+----+----+---------+
 *                                   finish
 *
 *
 * How many possible unique paths are there?
 *
 * Above is a 3 x 7 grid. How many possible unique paths are there?
 *
 * Note: m and n will be at most 100.
 *
 */
public class UniquePaths {


    /**
     * 相当于走迷宫，不过只可以向前或者向下，两个方向
     *
     * 使用深度优先，递归查找
     *
     * @param x
     * @param y
     * @param m
     * @param n
     * @param result
     * @return
     */
    public int walk (int x, int y, int m, int n, int result) {
        if (x == n  - 1 && y == m - 1) {
            return result + 1;
        }
        if (x < n - 1) {
            result = walk(x+1, y, m, n, result);
        }
        if (y < m - 1) {
            result = walk(x, y+1, m, n, result);
        }
        return result;
    }


    public int finAllUniquePaths (int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        return walk(0, 0, m, n, 0);
    }


    /**
     * 使用动态规划
     * dp[i][j] = dp[i][j-1] + do[i-1][j]
     *
     * 边界条件
     * 如果i = 0或者 j = 0,dp[0][j] = 1;
     *
     * @param m
     * @param n
     */
    public int finAllUniquePathsByDP (int m, int n) {
        int[][] maze = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    maze[i][j] = 1;
                } else {
                    maze[i][j] = maze[i-1][j] + maze[i][j-1];
                }
            }
        }
        return maze[m-1][n-1];
    }

    /**
     * 动态规划，使用滚动数组
     *
     * @param m
     * @param n
     * @return
     */
    public int finAllUniquePathsByDPAndScrollArray (int m, int n) {
        int[] scrollArray = new int[n];
        Arrays.fill(scrollArray, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                scrollArray[j] += scrollArray[j-1];
            }
        }
        return scrollArray[n -1];
    }

    public static void main(String[] args) {
        UniquePaths uniquePaths = new UniquePaths();
        System.out.println(uniquePaths.finAllUniquePaths(1,1));
        System.out.println(uniquePaths.finAllUniquePaths(1,2));
        System.out.println(uniquePaths.finAllUniquePaths(1,3));


        System.out.println(uniquePaths.finAllUniquePaths(2,2));
        System.out.println(uniquePaths.finAllUniquePaths(2,3));
        System.out.println(uniquePaths.finAllUniquePaths(3,3));

        System.out.println("======dp=======");

        System.out.println(uniquePaths.finAllUniquePathsByDP(1,1));
        System.out.println(uniquePaths.finAllUniquePathsByDP(1,2));
        System.out.println(uniquePaths.finAllUniquePathsByDP(1,3));


        System.out.println(uniquePaths.finAllUniquePathsByDP(2,2));
        System.out.println(uniquePaths.finAllUniquePathsByDP(2,3));
        System.out.println(uniquePaths.finAllUniquePathsByDP(3,3));

        System.out.println("======dp use scroll array=======");
        System.out.println(uniquePaths.finAllUniquePathsByDPAndScrollArray(2,2));
        System.out.println(uniquePaths.finAllUniquePathsByDPAndScrollArray(2,3));
        System.out.println(uniquePaths.finAllUniquePathsByDPAndScrollArray(3,3));

    }
}
