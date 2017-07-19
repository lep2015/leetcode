package org.lep.leetcode.spiralmatrix;

import java.util.Arrays;

/**
 * Source : https://oj.leetcode.com/problems/spiral-matrix/
 *
 * Created by lverpeng on 2017/7/19.
 *
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 *
 * For example,
 * Given the following matrix:
 *
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 *
 * You should return [1,2,3,6,9,8,7,4,5].
 *
 */
public class SpiralMatrix {

    /**
     * 主要是边界问题
     *
     * @param matrix
     * @return
     */
    public int[] join (int[][] matrix) {
        if (matrix.length <= 0) {
            return new int[]{};
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] result = new int[m * n];
        int row = 0;
        int col = 0;
        int index = 0;
        for (; row < (m + 1) / 2 && col < (n + 1) / 2; row ++, col ++) {
            for (int i = col; i < n - col; i++) {
                result[index++] = matrix[row][i];
            }
            for (int i = row + 1; i < m - row; i++) {
                result[index++] = matrix[i][n - col - 1];
            }
            for (int i = n - col - 2; m - row - 1 > row && i >= col; i--) {
                result[index++] = matrix[m - row - 1][i];
            }
            for (int i = m - row - 2; n - col - 1 > col && i > row; i--) {
                result[index++] = matrix[i][col];
            }
        }

        return result;
    }


    public static void main(String[] args) {
        SpiralMatrix spiralMatrix = new SpiralMatrix();
        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] matrix1 = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11, 12}
        };
        int[][] matrix2 = new int[][]{
                {1, 2, 3},
                {4, 5, 6}
        };
        int[][] matrix3 = new int[][]{
                {1, 2, 3}
        };
        int[][] matrix4 = new int[][]{
                {1},
                {4},
                {5}
        };


        System.out.println(Arrays.toString(spiralMatrix.join(matrix)));
        System.out.println(Arrays.toString(spiralMatrix.join(matrix1)));
        System.out.println(Arrays.toString(spiralMatrix.join(matrix2)));
        System.out.println(Arrays.toString(spiralMatrix.join(matrix3)));
        System.out.println(Arrays.toString(spiralMatrix.join(matrix4)));
    }
}
