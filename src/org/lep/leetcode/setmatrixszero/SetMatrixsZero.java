package org.lep.leetcode.setmatrixszero;

import java.util.Arrays;

/**
 * Source : https://oj.leetcode.com/problems/set-matrix-zeroes/
 *
 * Created by lverpeng on 2017/7/25.
 *
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 *
 *
 * Follow up:
 *
 * Did you use extra space?
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 *
 */
public class SetMatrixsZero {

    /**
     * 将原矩阵中为0元素所在的行列set为0
     * 使用不同空间的算法
     * O(mn)：复制一个和原来矩阵一样的矩阵，遍历原矩阵，遇到为0的元素，设置复制矩阵的行列为0
     * O(m+n)：使用一个row[m]记录0至(m-1)行需要置为0的行，一个col[n]数组记录0至(n-1)列需要被置为0的列，最后遍历两个数组对相应行列置位
     * O(1)：因为如果某一个位置出现了0，那么该行和该列都要被置为0，所以该行的第一列最后会变为0，也就是会抹除原来的数字，
     *      该列的第一行也类似，所以第一行和第一列可以代替上面这种方法中的两个数组，用来记录该行该列需要被置为0的情况，
     *      但是第0行和第0列需要额外的两个变量来记录，所以占用空间是常数（利用了原矩阵中的第0行和第0列）
     *
     * 下面实现占用常数空间的方法
     *
     * @param matrix
     * @return
     */
    public int[][] findZero (int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return matrix;
        }
        int firstRow = 1;
        int firstCol = 1;
        // 判断第0行和第0列
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                firstCol = 0;
                break;
            }
        }
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                firstRow = 0;
                break;
            }
        }

        // 遍历第1-(m-1)行和1-(n-1)列
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        setZero(matrix, firstRow, firstCol);
        return matrix;
    }

    /**
     *
     * @param matrix
     * @param firstRow
     * @param firstCol
     */
    public void setZero (int[][] matrix, int firstRow, int firstCol) {
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (firstCol == 0) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
        if (firstRow == 0) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }
    }

    public static void printMatrix (int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SetMatrixsZero setMatrixsZero = new SetMatrixsZero();
        int[][] arr = new int[][]{};
        int[][] arr1 = new int[][]{
                {1}
        };
        int[][] arr2 = new int[][]{
                {0}
        };
        int[][] arr3 = new int[][]{
                {1,0,1}
        };
        int[][] arr4 = new int[][]{
                {1},
                {0},
                {1}
        };
        int[][] arr5 = new int[][]{
                {1,2,3},
                {0,3,4},
                {1,0,4}
        };

        printMatrix(setMatrixsZero.findZero(arr));
        printMatrix(setMatrixsZero.findZero(arr1));
        printMatrix(setMatrixsZero.findZero(arr2));
        printMatrix(setMatrixsZero.findZero(arr3));
        printMatrix(setMatrixsZero.findZero(arr4));
        printMatrix(setMatrixsZero.findZero(arr5));
    }

}
