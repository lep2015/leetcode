package org.lep.leetcode.spiralMatrix2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Source : https://oj.leetcode.com/problems/spiral-matrix-ii/
 *
 * Created by lverpeng on 2017/7/19.
 *
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 *
 * For example,
 * Given n = 3,
 *
 * You should return the following matrix:
 *
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 *
 */
public class SpiralMatrix2 {

    public List<Integer[]> build (int n) {
        List<Integer[]> result = new ArrayList<Integer[]>();
        for (int i = 0; i < n; i++) {
            result.add(new Integer[n]);
        }
        int row = 0;
        int col = 0;
        int count = 1;
        for (; col < (n+1) / 2 && row < (n+1) / 2; row ++, col++) {
            for (int i = col; i < n - col; i++) {
                result.get(row)[i] = count++;
            }
            for (int i = row + 1; i < n - row; i ++) {
                result.get(i)[n - col - 1] = count++;
            }
            for (int i = n - col - 2; i >= col; i--) {
                result.get(n - row - 1)[i] = count++;
            }
            for (int i = n - row - 2; i > row; i--) {
                result.get(i)[col] = count++;
            }
        }
        return result;
    }

    public static void print (List<Integer[]> list) {
        for (Integer[] arr : list) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println();
    }

    public static void main(String[] args) {

        SpiralMatrix2 spiralMatrix2 = new SpiralMatrix2();
        print(spiralMatrix2.build(0));
        print(spiralMatrix2.build(1));
        print(spiralMatrix2.build(2));
        print(spiralMatrix2.build(3));
        print(spiralMatrix2.build(4));
        print(spiralMatrix2.build(5));
    }

}
