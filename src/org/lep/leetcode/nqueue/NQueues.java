package org.lep.leetcode.nqueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Source : https://oj.leetcode.com/problems/n-queens/
 *
 * Created by lverpeng on 2017/7/18.
 *
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard
 * such that no two queens attack each other.
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement,
 * where 'Q' and '.' both indicate a queen and an empty space respectively.
 *
 * For example,
 * There exist two distinct solutions to the 4-queens puzzle:
 *
 * [
 *  [".Q..",  // Solution 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // Solution 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 *
 */
public class NQueues {


    public List<String> solve (int n) {
        int[][] board = new int[n][n];
        List<String> result = new ArrayList<String>();
        revursion(board, 0, result);
        return result;
    }

    /**
     * n皇后问题，皇后所在位置的行、列、对角线都不能有其他皇后存在
     * 使用递归解决
     *
     * @param board
     * @param row
     * @param result
     */
    public void revursion (int[][] board, int row, List<String> result) {
        if (row == board.length) {
            // 找到解
            StringBuilder stringBuilder = new StringBuilder();
            if (result.size() > 0) {
                stringBuilder.append("\n");
            }
            stringBuilder.append("[");
            for (int i = 0; i < board.length; i++) {
                stringBuilder.append("\"");
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] == 1) {
                        stringBuilder.append("Q");
                    } else {
                        stringBuilder.append(".");
                    }
                }
                stringBuilder.append("\",\n");
            }
            stringBuilder = stringBuilder.delete(stringBuilder.length() - 3, stringBuilder.length());
            stringBuilder.append("]");
            result.add(stringBuilder.toString());
        }

        for (int i = 0; i < board.length ; i++) {
            if (isValiad (board, row, i)) {
                board[row][i] = 1;
                revursion(board, row + 1, result);
                board[row][i] = 0;
            }
        }

    }


    private boolean isValiad (int[][] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1 || (col - row + i >= 0 && board[i][col - row + i] == 1) || (col + row - i < board.length && board[i][col + row - i] == 1)) {
                return false;
            }
       }
       return true;
    }


    public static void main(String[] args) {
        NQueues nQueues = new NQueues();
        List<String> list = nQueues.solve(4);
        System.out.println("=======" + list.size() + "=======");;
        System.out.println(Arrays.toString(list.toArray(new String[list.size()])));

        list = nQueues.solve(8);
        System.out.println("=======" + list.size() + "=======");
        System.out.println(Arrays.toString(list.toArray(new String[list.size()])));
    }

}
