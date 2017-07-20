package org.lep.leetcode.minimumpathsum;

/**
 * Source : https://oj.leetcode.com/problems/minimum-path-sum/
 *
 * Created by lverpeng on 2017/7/20.
 *
 * Given a m x n grid filled with non-negative numbers, find a path from top left to
 * bottom right which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 */
public class MinimumPathSum {

    /**
     * 求到右下角所有路径中最小的和
     *
     * 依然使用动态规划
     * grid[i][j] += min (grif[i][j-1] + grid[]i-1[j])
     * 如果是第一列则
     * grid[i][j] += grid[i-1][j]
     * 如果是第一行则
     * grid[i][j] += grid[i][j+1]
     *
     * @param grid
     * @return
     */
    public int findminimumPathSum (int[][] grid) {
        if (grid.length <= 0 || grid[0].length <= 0) {
            return 0;
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (i == 0) {
                    grid[i][j] += grid[i][j-1];
                } else if (j == 0) {
                    grid[i][j] += grid[i-1][j];
                } else {
                    grid[i][j] += Math.min(grid[i][j-1], grid[i-1][j]);
                }
            }
        }
        return grid[grid.length-1][grid[0].length-1];
    }


    public static void main(String[] args) {
        MinimumPathSum minimumPathSum = new MinimumPathSum();
        int[][] arr = new int[][]{
                {1,2,3,4},
                {3,5,3,4},
                {3,1,3,8}
        };

        System.out.println(minimumPathSum.findminimumPathSum(arr));
    }
}
