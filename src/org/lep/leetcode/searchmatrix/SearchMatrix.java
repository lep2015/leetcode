package org.lep.leetcode.searchmatrix;

/**
 * Source : https://oj.leetcode.com/problems/search-a-2d-matrix/
 *
 * Created by lverpeng on 2017/7/25.
 *
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 *
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 *
 * For example,
 *
 * Consider the following matrix:
 *
 * [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 *
 * Given target = 3, return true.
 *
 */
public class SearchMatrix {

    /**
     * 因为矩阵是连续有序的，所以可以当做一维数组处理，使用二分法搜索
     * 也可以使用二分法先搜索第一列，确定处于哪一行，再对该行使用二分法搜索
     *
     *
     *
     * @return
     */
    public boolean search (int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = m * n;
        int mid = (left + right) / 2;
        int midi = mid / n;
        int midj = mid % n;
        while (left <= right) {
            if (matrix[midi][midj] == target) {
                return true;
            }
            if (matrix[midi][midj] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
            mid = (left + right) / 2;
            midi = mid / n;
            midj = mid % m;
        }
        return false;
    }


    public static void main(String[] args) {
        SearchMatrix searchMatrix = new SearchMatrix();
        int[][] matrix = new int[][]{
                {1,   3,  5,  7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };
        System.out.println(searchMatrix.search(matrix, 3));
        System.out.println(searchMatrix.search(matrix, 11));
        System.out.println(searchMatrix.search(matrix, 34));
        System.out.println(searchMatrix.search(matrix, 0));

    }
}
