package org.lep.leetcode.rotateimage;

import java.util.Arrays;

/**
 * Source : https://oj.leetcode.com/problems/rotate-image/
 *
 * Created by lverpeng on 2017/7/18.
 *
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * Follow up:
 * Could you do this in-place?
 *
 */
public class RotateImage {

    /**
     * 将二维数组顺时针旋转90度
     *
     * 找到旋转前后的规律
     *
     * @param matrix
     * @return
     */
    public int[][] rotate (int[][] matrix) {
        if (matrix.length == 0) {
            return matrix;
        }
        int[][] rotatedMatrix = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                rotatedMatrix[j][i] = matrix[matrix.length - 1 - i][j];
            }

        }
        return rotatedMatrix;
    }

    /**
     * 如果矩阵是行列数相等
     * 旋转矩阵，，矩阵有四条边，相当于四条边上的元素互换位置，1号换到2号，2号换到3号，3号换到4号，4号换到1号
     *
     *
     * @param matrix
     */
    public void rotate1 (int[][] matrix) {
        if (matrix.length == 0) {
            return;
        }
        int length = matrix.length;
        for (int i = 0; i < length / 2; i++) {
            for (int j = i; j < length - i -1 ; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[length -1 -j][i];
                matrix[length -1 -j][i] = matrix[length - 1 - i][length - 1 - j];
                matrix[length - 1 - i][length - 1 - j] = matrix[j][length - 1 - i];
                matrix[j][length - 1 - i] = temp;
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
        RotateImage rotateImage = new RotateImage();
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

        int[][] matrix5 = new int[][]{};

        int[][] matrix6 = new int[][]{{},{}};

        printMatrix(rotateImage.rotate(matrix));
        printMatrix(rotateImage.rotate(matrix1));
        printMatrix(rotateImage.rotate(matrix2));
        printMatrix(rotateImage.rotate(matrix3));
        printMatrix(rotateImage.rotate(matrix4));
        printMatrix(rotateImage.rotate(matrix5));
        printMatrix(rotateImage.rotate(matrix6));


        System.out.println("========rotate1========");
        int[][] matrix7 = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        rotateImage.rotate1(matrix7);
        printMatrix(matrix7);

        int[][] matrix8 = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        rotateImage.rotate1(matrix8);
        printMatrix(matrix8);

        int[][] matrix9 = new int[][]{
                {1}
        };
        rotateImage.rotate1(matrix9);
        printMatrix(matrix9);
    }
}
