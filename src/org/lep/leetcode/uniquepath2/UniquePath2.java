package org.lep.leetcode.uniquepath2;

/**
 * Source : https://oj.leetcode.com/problems/unique-paths-ii/
 *
 * Created by lverpeng on 2017/7/20.
 *
 * Follow up for "Unique Paths":
 *
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 *
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 *
 * For example,
 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
 *
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 *
 * The total number of unique paths is 2.
 *
 * Note: m and n will be at most 100.
 *
 */
public class UniquePath2 {

    /**
     * 依然使用动态规划
     * 注意障碍，障碍在边上和中间
     *
     * @param maze
     * @return
     */
    public int finAllUniquePaths (int[][] maze) {
        if (maze.length <= 0 || maze[0].length <= 0) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 1) {
                    // 障碍处为0
                    max = maze[i][j] = 0;
                } else {
                    if (i > 0 && j > 0) {
                        max = maze[i][j] = maze[i-1][j] + maze[i][j-1];
                    } else if (i > 0) {
                        // 第一列不一定是1
                        max = maze[i][j] = maze[i-1][j];
                    } else if (j > 0) {
                        // 第一行不一定是1
                        max = maze[i][j] = maze[i][j-1];
                    } else {
                        // 第一个是1
                        max = maze[i][j] = 1;
                    }
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        UniquePath2 uniquePaths = new UniquePath2();
        int[][] arr = new int[][]{
                {0,1},
                {0,0}
        };
        int[][] arr1 = new int[][]{
                {0,1,0},
                {0,0,0}
        };
        int[][] arr2 = new int[][]{
                {0,1,0},
                {0,1,0},
                {0,0,0}
        };
        int[][] arr3 = new int[][]{
                {0,0,0},
                {0,1,0},
                {0,0,0}
        };
        int[][] arr4 = new int[][]{
                {0,0,0,0,0,0,0},
                {0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0}
        };
        System.out.println(uniquePaths.finAllUniquePaths(arr));
        System.out.println(uniquePaths.finAllUniquePaths(arr1));
        System.out.println(uniquePaths.finAllUniquePaths(arr2));
        System.out.println(uniquePaths.finAllUniquePaths(arr3));
        System.out.println(uniquePaths.finAllUniquePaths(arr4));
    }

}
